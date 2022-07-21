package com.price.explorer.priceexplorer.tasks;

import com.price.explorer.priceexplorer.model.fuel.DataStationList;
import com.price.explorer.priceexplorer.util.XmlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Slf4j
@Component
public class Runner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        log.info("Started data extraction at {}", LocalDateTime.now());
        DataStationList stationList = XmlUtils.unmarshal("./data.xml");
        var stationMap = stationList.getStationMap();
        var headers = "Departement,Adresse,Ville,Code postale,e10,e85,sp98,gazole";
        BufferedWriter writer = new BufferedWriter(new FileWriter("./data.csv", true));
        writer.append(headers);
        stationMap.keySet()
                  .stream()
                  .forEach(k -> {
                      stationMap.get(k).stream()
                              .forEach(l -> {
                                  try {
                                      writer.append(l.toCsv(k));
                                  } catch (IOException e) {
                                      log.warn(e.getLocalizedMessage());
                                  }
                              });
                  });
        writer.close();
        log.info("End data extraction at {}", LocalDateTime.now());
    }
}

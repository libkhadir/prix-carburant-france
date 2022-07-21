package com.price.explorer.priceexplorer.tasks;

import com.price.explorer.priceexplorer.model.fuel.DataStationList;
import com.price.explorer.priceexplorer.util.XmlUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
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
        var headers = "Departement,Adresse,Ville,Code postale,E10,E85,SP98,GAZOLE\n";
        stationMap.keySet()
                  .stream()
                  .sorted()
                  .forEach(k -> {
                      try {
                          BufferedWriter writer = new BufferedWriter(new FileWriter("./data" + k + ".csv", true));
                          writer.append(headers);
                          stationMap.get(k).stream()
                                  .forEach(l -> {
                                      try {
                                          writer.append(l.toCsv(k) + "\n");
                                      } catch (IOException e) {
                                          log.warn(e.getLocalizedMessage());
                                      }
                                  });
                          writer.close();
                      } catch (IOException e) {
                          log.warn(e.getLocalizedMessage());
                      }
                  });
        log.info("End data extraction at {}", LocalDateTime.now());
    }
}

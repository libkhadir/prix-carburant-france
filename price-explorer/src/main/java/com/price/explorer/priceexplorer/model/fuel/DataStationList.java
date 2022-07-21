package com.price.explorer.priceexplorer.model.fuel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@XmlAccessorType (XmlAccessType.FIELD)
@XmlRootElement(name = "pdv_liste")
@XmlSeeAlso(DataStationList.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DataStationList {
    @XmlElement(name = "pdv")
    private List<Station> stations;

    public Map<String, List<Station>> getStationMap() {
        var stationMap = new HashMap<String, List<Station>>();
        stations.stream()
                .forEach(s -> {
                    if (stationMap.containsKey(s.getDepartment())) {
                        stationMap.get(s.getDepartment()).add(s);
                    } else {
                        stationMap.put(s.getDepartment(), new ArrayList<Station>());
                        stationMap.get(s.getDepartment()).add(s);
                    }
                });
        return stationMap;
    }
}

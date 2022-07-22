package com.price.explorer.priceexplorer.model.fuel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType (XmlAccessType.FIELD)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Station {
    @XmlElement(name = "adresse")
    private String adresse;
    @XmlElement(name = "ville")
    private String ville;
    @XmlAttribute(name = "cp")
    private String cp;
    @XmlAttribute(name = "latitude")
    private String latitude;
    @XmlAttribute(name = "longitude")
    private String longitude;
    @XmlElement(name = "prix")
    private List<FuelPrice> prices;

    public String getDepartment() {
        return cp.substring(0, 2);
    }

    public String getPrice(final String type) {
        return prices == null ? "" : prices.stream()
                .filter(p -> p.getNom().equalsIgnoreCase(type))
                .findFirst()
                .map(FuelPrice::formatValeur)
                .orElse("");
    }

    public String toCsv(String department) {
        return department + "," +
               adresse.replace(",", " ").replace("\"", "") + "," +
               ville + "," +
               cp + "," +
               getPrice("e10") + "," +
               getPrice("e85") + "," +
               getPrice("sp98") + "," +
               getPrice("gazole") + "," +
               latitude + "," +
               longitude;
    }
}

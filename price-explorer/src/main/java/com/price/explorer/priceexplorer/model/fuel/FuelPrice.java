package com.price.explorer.priceexplorer.model.fuel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import java.text.NumberFormat;
import java.util.Locale;


@XmlAccessorType (XmlAccessType.FIELD)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FuelPrice {
    @XmlAttribute(name = "nom")
    private String nom;
    @XmlAttribute(name = "valeur")
    private Double valeur;

    public String formatValeur() {
        var formatter = NumberFormat.getCurrencyInstance(Locale.FRANCE);
        return formatter.format(this.valeur)
                .replace(",", ".");
    }
}

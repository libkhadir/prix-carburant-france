package com.price.explorer.priceexplorer.model.fuel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FuelDataDto {
    private String department;
    private String address;
    private String sp98Price;
    private String gazolePrice;
    private String e85Price;
    private String e10Price;
}

package com.tharv.milk.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CollectionDTO {

    private int id;
    private int farmerId;
    private String milkType;
    private BigDecimal quantity;
    private BigDecimal fat;
    private BigDecimal snf;
    private String shift;
    private LocalDate collectionDate;

    public enum MilkType {
        COW("Cow"),
        BUFFALO("Buffalo");

        private final String value;

        MilkType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}

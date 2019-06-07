package com.company.entity;

public class Drug {
    private Integer drugId;
    private String name;

    public Integer getDrugId() {
        return drugId;
    }

    public void setDrugId(Integer drugId) {
        this.drugId = drugId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Drug{" +
                "drugId=" + drugId +
                ", name='" + name + '\'' +
                '}';
    }
}

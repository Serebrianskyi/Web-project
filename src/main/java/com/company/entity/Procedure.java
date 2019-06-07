package com.company.entity;

public class Procedure {
    private Integer procedureId;
    private String name;


    public Integer getProcedureId() {
        return procedureId;
    }

    public void setProcedureId(Integer procedureId) {
        this.procedureId = procedureId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Procedure: " +
                "procedureId: " + procedureId +
                ", name: '" + name + '\'';
    }
}

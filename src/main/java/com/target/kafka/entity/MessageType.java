package com.target.kafka.entity;

public enum MessageType {
    FINANCIAL("FINANCIAL"),
    BANKING("BANKING"),
    HEALTHCARE("HEALTHCARE"),
    ENTERTAINMENT("ENTERTAINMENT");

    private String type;

    MessageType(String type) {
        this.type = type;
    }


    public String getType(){
        return type;
    }
}

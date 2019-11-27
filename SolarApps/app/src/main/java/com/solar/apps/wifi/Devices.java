package com.solar.apps.wifi;



public class Devices{

    String name;
    String capabilities;

    public Devices(String name ,String capabilities){
    this.name=name;
    this.capabilities=capabilities;
    }

    public String getCapabilities() {
        return capabilities;
    }

    public void setCapabilities(String capabilities) {
        this.capabilities = capabilities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
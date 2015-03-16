package com.example.bhavana.energysaverapp.Model;

public class Profile {
    private String name;
    private int numberOfDevices;
    private int phoneNo;
    private String postalCode;
    private float providerRate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfDevices() {
        return numberOfDevices;
    }

    public void setNumberOfDevices(int numberOfDevices) {
        this.numberOfDevices = numberOfDevices;
    }

    public int getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(int phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public float getProviderRate() {
        return providerRate;
    }

    public void setProviderRate(float providerRate) {
        this.providerRate = providerRate;
    }
}

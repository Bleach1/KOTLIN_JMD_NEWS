package com.national.security.community.DesignPattern.Factory.SimpleFactory;

public class Client {
    public static void main(String args[]) {
        try {
            DriverFactory.driverCar("Benz").drive();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

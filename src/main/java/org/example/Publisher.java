package org.example;

import org.example.service.AvailabilityService;
import org.example.service.ReaderService;
import org.example.service.RentalService;

import javax.xml.ws.Endpoint;

public class Publisher {
    public static void main(String[] args) {
        Endpoint.publish("http://127.0.0.1:8081/reader", new ReaderService());
        Endpoint.publish("http://127.0.0.1:8081/availability", new AvailabilityService());
        Endpoint.publish("http://127.0.0.1:8081/rental", new RentalService());
        System.out.println("Сервисы запущены");
    }
}
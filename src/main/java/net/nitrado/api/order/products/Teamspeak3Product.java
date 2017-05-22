package net.nitrado.api.order.products;

import net.nitrado.api.Nitrapi;
import net.nitrado.api.order.PartPricing;

public class Teamspeak3Product extends PartPricing {
    public Teamspeak3Product(Nitrapi nitrapi, int locationId) {
        super(nitrapi, locationId);
        product = "teamspeak3";
    }
}

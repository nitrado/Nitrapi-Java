package net.nitrado.api.order.products;

import net.nitrado.api.Nitrapi;
import net.nitrado.api.order.PartPricing;

public class Teamspeak3 extends PartPricing {
    public Teamspeak3(Nitrapi nitrapi, int locationId) {
        super(nitrapi, locationId);
        product = "teamspeak3";
    }
}

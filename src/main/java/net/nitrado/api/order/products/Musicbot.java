package net.nitrado.api.order.products;

import net.nitrado.api.Nitrapi;
import net.nitrado.api.order.PartPricing;

public class Musicbot extends PartPricing {
    public Musicbot(Nitrapi nitrapi, int locationId) {
        super(nitrapi, locationId);
        product = "musicbot";
    }
}

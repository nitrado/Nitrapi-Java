package net.nitrado.api.order.products;

import net.nitrado.api.Nitrapi;
import net.nitrado.api.order.PartPricing;

public class Mumble extends PartPricing {
    public Mumble(Nitrapi nitrapi, int locationId) {
        super(nitrapi, locationId);
        product = "mumble";
    }
}

package net.nitrado.api.order.products;

import net.nitrado.api.Nitrapi;
import net.nitrado.api.order.PartPricing;

public class Webspace extends PartPricing {
    public Webspace(Nitrapi nitrapi, int locationId) {
        super(nitrapi, locationId);
        product = "webspace";
    }
}

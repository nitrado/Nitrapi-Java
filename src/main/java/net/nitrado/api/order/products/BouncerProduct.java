package net.nitrado.api.order.products;

import net.nitrado.api.Nitrapi;
import net.nitrado.api.order.PartPricing;

public class BouncerProduct extends PartPricing {
    public BouncerProduct(Nitrapi nitrapi, int locationId) {
        super(nitrapi, locationId);
        product = "bouncer";
    }
}

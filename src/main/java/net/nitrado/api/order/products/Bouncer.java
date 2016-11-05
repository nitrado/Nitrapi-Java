package net.nitrado.api.order.products;

import net.nitrado.api.Nitrapi;
import net.nitrado.api.order.PartPricing;

public class Bouncer extends PartPricing {
    public Bouncer(Nitrapi nitrapi, int locationId) {
        super(nitrapi, locationId);
        product = "bouncer";
    }
}

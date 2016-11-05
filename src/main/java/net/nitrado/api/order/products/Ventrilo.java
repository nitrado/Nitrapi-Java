package net.nitrado.api.order.products;

import net.nitrado.api.Nitrapi;
import net.nitrado.api.order.PartPricing;

public class Ventrilo extends PartPricing {
    public Ventrilo(Nitrapi nitrapi, int locationId) {
        super(nitrapi, locationId);
        product = "ventrilo";
    }
}

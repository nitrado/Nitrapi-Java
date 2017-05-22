package net.nitrado.api.order.products;

import net.nitrado.api.Nitrapi;
import net.nitrado.api.order.PartPricing;

public class VentriloProduct extends PartPricing {
    public VentriloProduct(Nitrapi nitrapi, int locationId) {
        super(nitrapi, locationId);
        product = "ventrilo";
    }
}

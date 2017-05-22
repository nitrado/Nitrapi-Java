package net.nitrado.api.order.products;

import net.nitrado.api.Nitrapi;
import net.nitrado.api.order.PartPricing;

public class WebspaceProduct extends PartPricing {
    public WebspaceProduct(Nitrapi nitrapi, int locationId) {
        super(nitrapi, locationId);
        product = "webspace";
    }
}

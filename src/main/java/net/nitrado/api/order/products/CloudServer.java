package net.nitrado.api.order.products;

import net.nitrado.api.Nitrapi;
import net.nitrado.api.order.PartPricing;

public class CloudServer extends PartPricing {
    public CloudServer(Nitrapi nitrapi, int locationId) {
        super(nitrapi, locationId);
        product = "cloud_server";
    }
}

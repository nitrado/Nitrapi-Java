package net.nitrado.api.order.products;

import net.nitrado.api.Nitrapi;
import net.nitrado.api.order.PartPricing;

public class CloudServerProduct extends PartPricing {
    public CloudServerProduct(Nitrapi nitrapi, int locationId) {
        super(nitrapi, locationId);
        product = "cloud_server";
        additionals.put("image_id", "0");
    }

    public void setImage(int image) {
        additionals.put("image_id", "" + image);
    }
}

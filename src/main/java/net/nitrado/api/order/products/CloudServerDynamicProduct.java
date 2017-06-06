package net.nitrado.api.order.products;

import net.nitrado.api.Nitrapi;
import net.nitrado.api.order.PartPricing;

public class CloudServerDynamicProduct extends PartPricing {
    public CloudServerDynamicProduct(Nitrapi nitrapi, int locationId) {
        super(nitrapi, locationId);
        product = "cloud_server_dynamic";
        additionals.put("image", "0");
    }

    public void setImage(int image) {
        additionals.put("image_id", "" + image);
    }
}

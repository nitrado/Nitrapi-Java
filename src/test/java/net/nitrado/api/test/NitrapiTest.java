package net.nitrado.api.test;

import net.nitrado.api.Nitrapi;
import net.nitrado.api.common.exceptions.NitrapiErrorException;
import net.nitrado.api.common.exceptions.NitrapiException;
import net.nitrado.api.customer.Customer;
import net.nitrado.api.services.Service;
import net.nitrado.api.services.gameservers.GameList;
import net.nitrado.api.services.gameservers.Gameserver;
import net.nitrado.api.services.gameservers.minecraft.Minecraft;
import net.nitrado.api.services.gameservers.minecraft.Plugin;

import org.junit.Test;

import static org.junit.Assert.*;

public class NitrapiTest extends ApiTestCase {

    /**
     * a simple test that should always be successful
     */
    @Test
    public void testSuccessful() {
    }


    @Test
    public void testCustomer() throws NitrapiException {
        client.addNextResponseFromFile("customer/customer.json");
        Customer customer = api.getCustomer();
        assertEquals(1337, customer.getUserId());
        assertEquals("Marty", customer.getUsername());
        assertEquals("America/Los_Angeles", customer.getTimezone());
        assertEquals(1955, customer.getCredit());
        assertEquals("marty.mcfly@biffco.com", customer.getEmail());
        assertEquals("https://server.nitrado.net/users.nitrado/1337.jpg", customer.getAvatar());
        assertDate(1985, 10, 25, 01, 15, 00, customer.getRegisteredDate());
    }


    @Test(expected = NitrapiErrorException.class)
    public void testError() {
        client.addNextResponseFromFile("error.json");
        api.getServices();
    }


    @Test
    public void testUnsupportedService() {
        client.addNextResponseFromFile("services/unsupported_service_in_servicelist.json");
        System.out.println("Warning about CoolServer should appear: ");
        Service[] services = api.getServices();
        assertEquals(0, services.length);
    }
}

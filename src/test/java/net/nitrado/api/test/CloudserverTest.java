package net.nitrado.api.test;

import net.nitrado.api.common.exceptions.NitrapiException;
import net.nitrado.api.services.Service;
import net.nitrado.api.services.cloudservers.CloudServer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CloudserverTest extends ApiTestCase {

    CloudServer server;

    @Before
    public void init() throws NitrapiException {

        client.addNextResponseFromFile("services/cloud_server.json");
        client.addNextResponseFromFile("services/cloudservers/cloudserver.json");

        Service service = api.getService(2);
        assertEquals(service.getClass(), CloudServer.class);

        server = (CloudServer) service;
    }

    @Test
    public void testBasicAttributes() {
        assertEquals(2, server.getId());
        assertEquals(Service.Status.ACTIVE, server.getStatus());
    }

}

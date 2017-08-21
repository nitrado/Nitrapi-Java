package net.nitrado.api.test;

import net.nitrado.api.common.exceptions.NitrapiException;
import net.nitrado.api.services.Service;
import net.nitrado.api.services.gameservers.Gameserver;
import net.nitrado.api.services.gameservers.minecraft.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MinecraftTest extends ApiTestCase {

    Gameserver server;
    Minecraft minecraft;

    @Before
    public void init() throws NitrapiException {

        client.addNextResponseFromFile("services/service_3.json");
        client.addNextResponseFromFile("services/gameservers/gameserver.json");
        client.addNextResponseFromFile("services/gameservers/minecraft/minecraft.json");

        Service service = api.getService(3);
        assertEquals(service.getClass(), Gameserver.class);

        server = (Gameserver) service;
        minecraft = server.getMinecraft();
    }

    @Test
    public void testBasicAttributes() {
        assertFalse(minecraft.isTaskRunning());
        assertEquals("world", minecraft.getCurrentWorld());

        World[] allWorlds = minecraft.getAllWorlds();
        assertEquals(3, allWorlds.length);
        World world = allWorlds[0];
        assertEquals("world_the_end", world.getWorld());
        assertEquals("minecraftbukkit", world.getGame());
        assertEquals(0, world.getSize());
        assertEquals(0, world.getTimestamp());
        World[] backupWorlds = minecraft.getWorldBackups();
        assertEquals(3, backupWorlds.length);
        world = backupWorlds[0];
        assertEquals("world", world.getWorld());
        assertEquals("minecraftbukkit", world.getGame());
        assertEquals(1440068630, world.getTimestamp());
        assertEquals(3419356, world.getSize());

        assertEquals("cdd49c262cb98db636b8fae2837869ef", minecraft.getBinaryMD5());
        assertEquals("minecraft_server.jar", minecraft.getBinary());

        Overviewmap map = minecraft.getOverviewmap();
        assertTrue(map.isEnabled());
        assertEquals("https://minecraft.nitrado.net/map/127.0.0.1", map.getUrl());
        assertTrue(map.isSigns());
        assertFalse(map.isReset());
        assertDate(2015, 8, 21, 15, 6, 40, map.getLastReset());
        assertDate(2015, 8, 20, 17, 37, 14, map.getLastEnable());
        assertNull(map.getModified());

        McMyAdmin admin = minecraft.getMcMyAdmin();
        assertFalse(admin.isEnabled());
        assertEquals("http://127.0.0.1:8080/", admin.getUrl());
        assertEquals("ni123456_1", admin.getUsername());
        assertEquals("e8cedb815d058f86d248427e0f75d0e8", admin.getPassword());
        assertEquals("DE", admin.getLanguage());

        BungeeCord bungee = minecraft.getBungeeCord();
        assertTrue(bungee.isEnabled());
        assertTrue(bungee.isOnly());
        assertEquals(BungeeCord.FirewallStatus.ON_SELF, bungee.getFirewall());
        assertEquals("8.8.8.8", bungee.getFirewallIp());

        RemoteToolkit rtk = minecraft.getRemoteToolkit();
        assertTrue(rtk.isEnabled());
        assertEquals("ni123456_1", rtk.getUsername());
        assertEquals("e8cedb815d058f86d248427e0f75d0e8", rtk.getPassword());

        Version[] versions = minecraft.getVersions();
        assertEquals(4, versions.length);
        Version version = versions[0];
        assertEquals("1.8", version.getVersion());
        assertEquals("Spigot 1.7/1.8 (beta)", version.getName());
        assertEquals("870c9021be261bd285c966c642b23c32", version.getMd5());
        assertFalse(version.isInstalled());
    }

    @Test
    public void testUUID() throws NitrapiException {
        client.addNextResponseFromFile("services/gameservers/minecraft/uuid.json");
        assertEquals("abcde123-1234-1234-1234-abcdef123456", minecraft.getUserUUID("tyrola"));
    }

    @Test
    public void testAvatar() throws NitrapiException {
        client.addNextResponseFromFile("services/gameservers/minecraft/avatar.json");
        assertEquals("blob", minecraft.getUserAvatar("tyrola"));
    }

    @Test
    public void testPlugins() throws NitrapiException {
        client.addNextResponseFromFile("services/gameservers/minecraft/plugins.json");
        Plugin[] plugins = minecraft.getPlugins();
        assertEquals(3, plugins.length);
        Plugin plugin = plugins[0];
        assertEquals("ChestShop.jar", plugin.getFile());
        assertEquals("ChestShop", plugin.getName());
        assertEquals("com.Acrobot.ChestShop.ChestShop", plugin.getMain());
        assertEquals("3.8.12", plugin.getVersion());
    }
}


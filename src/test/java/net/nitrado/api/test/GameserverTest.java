package net.nitrado.api.test;

import net.nitrado.api.Nitrapi;
import net.nitrado.api.common.exceptions.NitrapiException;
import net.nitrado.api.services.Service;
import net.nitrado.api.services.ServiceDetails;
import net.nitrado.api.services.fileserver.FileEntry;
import net.nitrado.api.services.fileserver.FileServer;
import net.nitrado.api.services.gameservers.*;
import net.nitrado.api.services.gameservers.customersettings.CustomerSettings;
import net.nitrado.api.services.gameservers.customersettings.CustomerSettingsNotFoundException;
import net.nitrado.api.services.gameservers.ddoshistory.DDoSAttack;
import net.nitrado.api.services.gameservers.ddoshistory.DDoSStat;
import net.nitrado.api.services.gameservers.taskmanager.Task;
import net.nitrado.api.services.gameservers.taskmanager.TaskManager;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameserverTest extends ApiTestCase {

    Gameserver server;

    @Before
    public void init() throws NitrapiException {

        client.addNextResponseFromFile("services/service_3.json");
        client.addNextResponseFromFile("services/gameservers/gameserver.json");

        Service service = api.getService(3);
        assertEquals(service.getClass(), Gameserver.class);

        server = (Gameserver) service;
    }

    @Test
    public void testBasicAttributes() {
        assertEquals(3, server.getId());
        assertEquals(Service.Status.ACTIVE, server.getStatus());
        assertEquals(Gameserver.Status.STARTED, server.getGameserverStatus());
        assertEquals("b05ac3d5d71a8858fca3011a8c179f1d9ec5ab41", server.getWebsocketToken());
        assertEquals("ni1_1", server.getUsername());
        assertEquals(1, server.getUserId());
        assertEquals(false, server.isMinecraftMode());
        assertEquals("127.0.0.1", server.getIp());
        assertEquals(25565, server.getPort());
        assertEquals(Gameserver.Type.GAMESERVER, server.getType());
        assertEquals(Gameserver.MemoryType.STANDARD, server.getMemoryType());
        assertEquals(1024, server.getMemoryTotal());
        assertEquals("mc", server.getGame());
        assertEquals(1, server.getModpacks().size());
        Modpack modpack = server.getModpacks().get("mcrdns");
        assertEquals("Pixelmon Craft", modpack.getName());
        assertEquals("4.0.6", modpack.getModpackVersion());
        assertEquals("1.8.4", modpack.getGameVersion());
        assertEquals(4, server.getSlots());
        assertEquals("DE", server.getLocation());

        ServiceDetails details = server.getDetails();
        assertEquals("10.10.0.6:27015", details.getAddress());
        assertEquals("Nitrado.net Battlefield 4 Server", details.getName());
        assertEquals("Battlefield 4", details.getGame());
        assertEquals("bf4", details.getPortlistShort());
        assertEquals("bf4", details.getFolderShort());

    }

    @Test
    public void testCredentials() {
        Credentials ftp = server.getCredentials("ftp");
        assertEquals("dev001.nitrado.net", ftp.getHostname());
        assertEquals(21, ftp.getPort());
        assertEquals("ni1_1", ftp.getUsername());
        assertEquals("4x8x15x16x23x42", ftp.getPassword());
        assertNull(ftp.getDatabase());

        Credentials mysql = server.getCredentials("mysql");
        assertEquals("dev001.nitrado.net", mysql.getHostname());
        assertEquals(3306, mysql.getPort());
        assertEquals("ni1_1", mysql.getUsername());
        assertEquals("4x8x15x16x23x42", mysql.getPassword());
        assertEquals("ni1_1_DB", mysql.getDatabase());
    }

    @Test
    public void testCustomerSettings() throws CustomerSettingsNotFoundException {
        CustomerSettings settings = server.getCustomerSettings();
        assertTrue(settings.hasCategory("base"));
        assertTrue(settings.hasCategory("config"));
        assertFalse(settings.hasCategory("mysql"));
        assertTrue(settings.hasSetting("base", "dailyrestart"));
        assertEquals("false", settings.readSetting("base", "dailyrestart"));
    }

    @Test
    public void testScheduledTasks() throws NitrapiException {
        client.addNextResponseFromFile("services/gameservers/scheduledtasks.json");
        TaskManager manager = server.getTaskManager();
        Task[] tasks = manager.getScheduledTasks();
        assertEquals(1, tasks.length);
        Task task = tasks[0];
        assertEquals(1, task.getId());
        assertEquals(1, task.getServiceId());
        assertEquals("0", task.getMinute());
        assertEquals("*", task.getHour());
        assertEquals("*", task.getDay());
        assertEquals("*", task.getMonth());
        assertEquals("*", task.getWeekday());
        assertDate(2015, 07, 16, 10, 00, 00, task.getNextRun());
        assertNull(task.getLastRun());
        assertEquals(Task.ActionType.RESTART, task.getActionMethod());
        assertNull(task.getActionData());
    }

    @Test
    public void testFileServer() throws NitrapiException {
        client.addNextResponseFromFile("services/gameservers/filelist.json");
        FileServer fileServer = server.getFileServer();

        FileEntry[] files = fileServer.getFileList();
        assertEquals(2, files.length);
        FileEntry file = files[0];
        assertEquals(FileEntry.Type.FILE, file.getType());
        assertEquals("/games/ni1_1/ftproot/restart.log", file.getPath());
        assertEquals("restart.log", file.getName());
        assertEquals(7242, file.getSize());
        assertEquals("ni1_1", file.getOwner());
        assertEquals("ni1_1", file.getGroup());
        assertEquals("100660", file.getChmod());
        assertEquals(1430319118, file.getCreatedAt());
        assertEquals(1430319118, file.getModifiedAt());
        assertEquals(1430298175, file.getAccessedAt());
    }

    @Test
    public void testDDoSHistory() throws NitrapiException {
        client.addNextResponseFromFile("services/gameservers/ddoshistory.json");
        DDoSAttack[] history = server.getDDoSHistory();
        assertEquals(2, history.length);
        DDoSAttack attack = history[1];
        assertEquals(2, attack.getId());
        assertDate(2015, 06, 30, 13, 05, 30, attack.getStartedAt());
        assertDate(2015, 06, 30, 13, 18, 14, attack.getEndedAt());
        assertEquals("Syn Flood", attack.getAttackType());
        assertEquals("31.214.227.14", attack.getIp());
        assertEquals("ms937", attack.getServer());
        assertEquals(220715, attack.getPps());
        assertEquals(112885484, attack.getBandwidth());
        assertEquals(764, attack.getDuration());
        DDoSStat[] stats = attack.getData();
        assertEquals(34, stats.length);
    }

    @Test
    public void testStats() throws NitrapiException {
        client.addNextResponseFromFile("services/gameservers/stats.json");
        Stats stats = server.getStats();
        assertEquals(5, stats.getCurrentPlayers().length);
        assertEquals(3, stats.getCpuUsage().length);
        assertEquals(3, stats.getMemoryUsage().length);
    }

    @Test
    public void testQuota() {
        Quota quota = server.getQuota();
        assertEquals(1000, quota.getBlockUsage());
        assertEquals(2000, quota.getBlockSoftLimit());
        assertEquals(4000, quota.getBlockHardLimit());
        assertEquals(10, quota.getFileUsage());
        assertEquals(10000, quota.getFileSoftLimit());
        assertEquals(100000, quota.getFileHardLimit());
    }

    @Test
    public void testQuery() {
        Query query = server.getQuery();
        assertEquals("Minecraft Server", query.getServerName());
        assertEquals("127.0.0.1:25565", query.getConnectIp());
        assertEquals("random", query.getMap());
        assertEquals("1.8", query.getVersion());
        assertEquals(1, query.getPlayerCurrent());
        assertEquals(4, query.getPlayerMax());
        Query.Player[] players = query.getPlayers();
        assertEquals(1, players.length);
        Query.Player player = players[0];
        assertEquals(1, (int)player.getId());
        assertEquals("Tyrola", player.getName());
        assertFalse(player.isBot());
        assertEquals(0, (int)player.getScore());
        assertEquals(0, (int)player.getFrags());
        assertEquals(0, (int)player.getDeaths());
        assertEquals(31,(int)player.getTime());
        assertEquals(8, (int)player.getPing());
    }


    @Test
    public void testGameserverActions() throws NitrapiException {
        client.addNextResponseFromFile("success.json");
        server.doRestart("asdf");
        assertEquals(Nitrapi.NITRAPI_LIVE_URL + "services/3/gameservers/restart", client.getLastUrl());
    }
}

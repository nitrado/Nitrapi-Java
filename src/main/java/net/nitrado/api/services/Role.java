package net.nitrado.api.services;

import net.nitrado.api.common.Value;

/**
 * Type of access the user has on a server.
 */
public class Role extends Value {
    public Role(String value) {
        super(value);
    }

    /**
     * Owner of this server
     */
    public static final Role ROLE_OWNER = new Role("ROLE_OWNER");
    /**
     * Can change server game
     */
    public static final Role ROLE_GAMESERVER_CHANGE_GAME = new Role("ROLE_GAMESERVER_CHANGE_GAME");
    /**
     * Can use generic control commands
     */
    public static final Role ROLE_WEBINTERFACE_GENERAL_CONTROL = new Role("ROLE_WEBINTERFACE_GENERAL_CONTROL");
    /**
     * Can view current backups
     */
    public static final Role ROLE_WEBINTERFACE_BACKUPS_READ = new Role("ROLE_WEBINTERFACE_BACKUPS_READ");
    /**
     * Can restore available backups
     */
    public static final Role ROLE_WEBINTERFACE_BACKUPS_WRITE = new Role("ROLE_WEBINTERFACE_BACKUPS_WRITE");
    /**
     * Can view the debug console
     */
    public static final Role ROLE_WEBINTERFACE_DEBUG_CONSOLE_READ = new Role("ROLE_WEBINTERFACE_DEBUG_CONSOLE_READ");
    /**
     * Can use the debug console
     */
    public static final Role ROLE_WEBINTERFACE_DEBUG_CONSOLE_WRITE = new Role("ROLE_WEBINTERFACE_DEBUG_CONSOLE_WRITE");
    /**
     * Can view/download files from the file server
     */
    public static final Role ROLE_WEBINTERFACE_FILEBROWSER_READ = new Role("ROLE_WEBINTERFACE_FILEBROWSER_READ");
    /**
     * Can change/upload files via file server
     */
    public static final Role ROLE_WEBINTERFACE_FILEBROWSER_WRITE = new Role("ROLE_WEBINTERFACE_FILEBROWSER_WRITE");
    /**
     * Can see ftp access credentials
     */
    public static final Role ROLE_WEBINTERFACE_FTP_CREDENTIALS_READ = new Role("ROLE_WEBINTERFACE_FTP_CREDENTIALS_READ");
    /**
     * Can change ftp access credentials
     */
    public static final Role ROLE_WEBINTERFACE_FTP_CREDENTIALS_WRITE = new Role("ROLE_WEBINTERFACE_FTP_CREDENTIALS_WRITE");
    /**
     * Allows a user to send RCON-commands that retrieve infos
     */
    public static final Role ROLE_WEBINTERFACE_GAME_RCON_READ = new Role("ROLE_WEBINTERFACE_GAME_RCON_READ");
    /**
     * Allows a user to send RCON-commands that change settings
     */
    public static final Role ROLE_WEBINTERFACE_GAME_RCON_WRITE = new Role("ROLE_WEBINTERFACE_GAME_RCON_WRITE");
    /**
     * Can see webinterface logs
     */
    public static final Role ROLE_WEBINTERFACE_LOGS_READ = new Role("ROLE_WEBINTERFACE_LOGS_READ");
    /**
     * Can view MySQL access credentials
     */
    public static final Role ROLE_WEBINTERFACE_MYSQL_CREDENTIALS_READ = new Role("ROLE_WEBINTERFACE_MYSQL_CREDENTIALS_READ");
    /**
     * Can change MySQL access credentials
     */
    public static final Role ROLE_WEBINTERFACE_MYSQL_CREDENTIALS_WRITE = new Role("ROLE_WEBINTERFACE_MYSQL_CREDENTIALS_WRITE");
    /**
     * Can view scheduled server restarts
     */
    public static final Role ROLE_WEBINTERFACE_SCHEDULED_RESTART_READ = new Role("ROLE_WEBINTERFACE_SCHEDULED_RESTART_READ");
    /**
     * Can edit scheduled server restarts
     */
    public static final Role ROLE_WEBINTERFACE_SCHEDULED_RESTART_WRITE = new Role("ROLE_WEBINTERFACE_SCHEDULED_RESTART_WRITE");
    /**
     * Has access to server settings
     */
    public static final Role ROLE_WEBINTERFACE_SETTINGS_READ = new Role("ROLE_WEBINTERFACE_SETTINGS_READ");
    /**
     * Can change server settings
     */
    public static final Role ROLE_WEBINTERFACE_SETTINGS_WRITE = new Role("ROLE_WEBINTERFACE_SETTINGS_WRITE");
    /**
     * Can edit the server page
     */
    public static final Role ROLE_WEBINTERFACE_SERVERPAGE_EDIT = new Role("ROLE_WEBINTERFACE_SERVERPAGE_EDIT");
    /**
     * Can execute live commands
     */
    public static final Role ROLE_WEBINTERFACE_LIVE_COMMANDS = new Role("ROLE_WEBINTERFACE_LIVE_COMMANDS");
    /**
     * Can authorize the support
     */
    public static final Role ROLE_SUPPORT_AUTHORIZATION = new Role("ROLE_SUPPORT_AUTHORIZATION");

}

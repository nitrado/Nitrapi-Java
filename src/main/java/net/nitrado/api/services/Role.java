package net.nitrado.api.services;

/**
 * Type of access the user has on a server.
 */
public enum Role {
    /**
     * Owner of this server
     */
    ROLE_OWNER,
    /**
     * Can change server game
     */
    ROLE_GAMESERVER_CHANGE_GAME,
    /**
     * Can use generic control commands
     */
    ROLE_WEBINTERFACE_GENERAL_CONTROL,
    /**
     * Can view current backups
     */
    ROLE_WEBINTERFACE_BACKUPS_READ,
    /**
     * Can restore available backups
     */
    ROLE_WEBINTERFACE_BACKUPS_WRITE,
    /**
     * Can view the debug console
     */
    ROLE_WEBINTERFACE_DEBUG_CONSOLE_READ,
    /**
     * Can use the debug console
     */
    ROLE_WEBINTERFACE_DEBUG_CONSOLE_WRITE,
    /**
     * Can view/download files from the file server
     */
    ROLE_WEBINTERFACE_FILEBROWSER_READ,
    /**
     * Can change/upload files via file server
     */
    ROLE_WEBINTERFACE_FILEBROWSER_WRITE,
    /**
     * Can see ftp access credentials 
     */
    ROLE_WEBINTERFACE_FTP_CREDENTIALS_READ,
    /**
     * Can change ftp access credentials
     */
    ROLE_WEBINTERFACE_FTP_CREDENTIALS_WRITE,
    /**
     * Allows a user to send RCON-commands that retrieve infos
     */
    ROLE_WEBINTERFACE_GAME_RCON_READ,
    /**
     * Allows a user to send RCON-commands that change settings
     */
    ROLE_WEBINTERFACE_GAME_RCON_WRITE,
    /**
     * Can see webinterface logs
     */
    ROLE_WEBINTERFACE_LOGS_READ,
    /**
     * Can view MySQL access credentials
     */
    ROLE_WEBINTERFACE_MYSQL_CREDENTIALS_READ,
    /**
     * Can change MySQL access credentials
     */
    ROLE_WEBINTERFACE_MYSQL_CREDENTIALS_WRITE,
    /**
     * Can view scheduled server restarts
     */
    ROLE_WEBINTERFACE_SCHEDULED_RESTART_READ,
    /**
     * Can edit scheduled server restarts
     */
    ROLE_WEBINTERFACE_SCHEDULED_RESTART_WRITE,
    /**
     * Has access to server settings
     */
    ROLE_WEBINTERFACE_SETTINGS_READ,
    /**
     * Can change server settings
     */
    ROLE_WEBINTERFACE_SETTINGS_WRITE,
    /**
     * Can authorize the support
     */
    ROLE_SUPPORT_AUTHORIZATION
}

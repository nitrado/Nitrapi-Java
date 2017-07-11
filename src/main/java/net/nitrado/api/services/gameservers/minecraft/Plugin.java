package net.nitrado.api.services.gameservers.minecraft;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * Information about a Minecraft bukkit/spigot plugin.
 */
public class Plugin {
    private class Details {
        private String name;
        private String main;
        private String version;
        private String author;
        private String[] authors;
        @SerializedName("soft-depend")
        private String[] softDepend;
        private String description;
        private HashMap<String, Command> commands;
    }

    /**
     * A Command that is provided by a plugin.
     */
    class Command {
        private String description;
        private String usage;

        /**
         * Returns the description of this command.
         *
         * @return the description
         */
        public String getDescription() {
            return description;
        }

        /**
         * Returns an usage example for this command.
         *
         * @return an usage example
         */
        public String getUsage() {
            return usage;
        }
    }

    private String file;
    private Details details;

    /**
     * Returns the filename of this plugin.
     *
     * @return the filename
     */
    public String getFile() {
        return file;
    }

    /**
     * Returns the name of this plugin.
     *
     * @return the name
     */
    public String getName() {
        return details.name;
    }

    /**
     * Returns the main class of this plugin.
     *
     * @return the name of the main class
     */
    public String getMain() {
        return details.main;
    }

    /**
     * Returns the version of this plugin.
     *
     * @return the version number
     */
    public String getVersion() {
        return details.version;
    }

    /**
     * Returns the author of this plugin if there is only one.
     *
     * @return the name of the author
     * @see Plugin#getAuthors()
     */
    public String getAuthor() {
        return details.author;
    }

    /**
     * Returns the authors of this plugin if there are more than one.
     *
     * @return a list of names of the authors
     * @see Plugin#getAuthor()
     */
    public String[] getAuthors() {
        return details.authors;
    }

    /**
     * Returns the dependencies of this plugin.
     *
     * @return a list of names of plugins this plugin depends on.
     */
    public String[] getSoftDepend() {
        return details.softDepend;
    }

    /**
     * Returns the description of this plugin.
     *
     * @return the description
     */
    public String getDescription() {
        return details.description;
    }

    /**
     * Returns a list of commands that are provided by this plugin.
     *
     * @return a list of commands
     */
    public Map<String, Command> getCommands() {
        return details.commands;
    }
}

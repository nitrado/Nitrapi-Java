package net.nitrado.api.services.fileserver;

import com.google.gson.JsonObject;
import net.nitrado.api.Nitrapi;
import net.nitrado.api.common.exceptions.NitrapiErrorException;
import net.nitrado.api.common.http.Parameter;
import net.nitrado.api.services.Service;
import net.nitrado.api.services.cloudservers.CloudServer;
import net.nitrado.api.services.gameservers.Gameserver;

import java.io.*;
import java.nio.charset.Charset;

/**
 * Access the files on your gameserver.
 */
public class FileServer {
    /**
     * Token you need to download/upload a file
     */
    public class Token {
        private String url;
        private String token;

        /**
         * Returns the url.
         *
         * @return the url
         */
        public String getUrl() {
            return url;
        }

        /**
         * Returns the token.
         *
         * @return the token.
         */
        public String getToken() {
            return token;
        }
    }

    private transient Service service;
    private transient Nitrapi api;

    private String url;
    private boolean hasPermissions;
    private boolean hasBookmarks;


    /**
     * Used internally.
     * <p>
     * Call Gameserver.getFileServer() instead.
     *
     * @param service Gameserver object
     * @param api     api reference
     * @see Gameserver#getFileServer()
     */
    public FileServer(Gameserver service, Nitrapi api) {
        this.service = service;
        this.api = api;
        this.url = "gameservers";
    }

    /**
     * Used internally.
     * <p>
     * Call CloudServer.getFileServer() instead.
     *
     * @param service CloudServer object
     * @param api     api reference
     * @see CloudServer#getFileServer()
     */
    public FileServer(CloudServer service, Nitrapi api) {
        this.service = service;
        this.api = api;
        this.url = "cloud_servers";
        this.hasPermissions = true;
        this.hasBookmarks = true;
    }

    /**
     * Returns the upload token and url.
     *
     * @param path     path of the new file
     * @param name     name of the new file
     * @param username name of the user which should execute this request (for Cloud Servers)
     * @return upload token
     */
    public Token getUploadToken(String path, String name, String username) {
        JsonObject data = api.dataPost("services/" + service.getId() + "/" + url + "/file_server/upload", new Parameter[]{
                new Parameter("path", path),
                new Parameter("file", name),
                new Parameter("username", username)
        });
        return api.fromJson(data.get("token"), Token.class);
    }

    public Token getUploadToken(String path, String name) {
        return getUploadToken(path, name, null);
    }

    /**
     * Uploads a specific file. File will be overwritten if it's already existing.
     *
     * @param file path to the local file
     * @param path path to the remote directory
     * @param name name of the file on the server
     * @throws IOException If an I/O error occurs
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_WRITE
     */
    public void uploadFile(String file, String path, String name) throws IOException {
        File localFile = new File(file);
        if (!localFile.exists()) {
            throw new NitrapiErrorException("Can't find local file \"" + file + "\".");
        }

        Token token = getUploadToken(path, name);
        InputStream stream = new FileInputStream(localFile);
        byte[] content = new byte[stream.available()];
        stream.read(content);

        api.rawPost(token.getUrl(), token.getToken(), content);
    }

    /**
     * Writes a specific file. File will be overwritten if it's already existing.
     *
     * @param path     path to the remote directory
     * @param name     name of the remote file
     * @param content  contents of the file
     * @param username name of the user which should execute this request (for Cloud Servers)
     * @throws IOException If an I/O error occurs
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_WRITE
     */
    public void writeFile(String path, String name, String content, String username) throws IOException {
        Token token = getUploadToken(path, name, username);

        api.rawPost(token.getUrl(), token.getToken(), content.getBytes(Charset.forName("UTF-8")));
    }

    public void writeFile(String path, String name, String content) throws IOException {
        writeFile(path, name, content, null);
    }


    /**
     * Lists all files and folders inside of the user root directory.
     *
     * @return a list of the contents of the root directory
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     */
    public FileEntry[] getFileList() {
        JsonObject data = api.dataGet("services/" + service.getId() + "/" + url + "/file_server/list", null);
        return api.fromJson(data.get("entries"), FileEntry[].class);
    }

    /**
     * Lists all files and folders inside of a given directory.
     *
     * @param path path of the directory
     * @return a list of the contents of the given directory
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     */
    public FileEntry[] getFileList(String path) {
        JsonObject data = api.dataGet("services/" + service.getId() + "/" + url + "/file_server/list", new Parameter[]{new Parameter("dir", path)});
        return api.fromJson(data.get("entries"), FileEntry[].class);
    }

    /**
     * Searches inside a specific directory recursively for specific file pattern.
     *
     * @param path   path of the directory
     * @param search search pattern
     * @return a list of files matching the pattern
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     */
    public FileEntry[] doFileSearch(String path, String search) {
        JsonObject data = api.dataGet("services/" + service.getId() + "/" + url + "/file_server/list", new Parameter[]{new Parameter("dir", path), new Parameter("search", search)});
        return api.fromJson(data.get("entries"), FileEntry[].class);
    }

    /**
     * Returns download token and url for a file
     *
     * @param file path of the file
     * @return the download token
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     */
    private Token getDownloadToken(String file) {
        JsonObject data = api.dataGet("services/" + service.getId() + "/" + url + "/file_server/download", new Parameter[]{new Parameter("file", file)});
        return api.fromJson(data.get("token"), Token.class);
    }

    /**
     * Downloads a file and safes it directly to a specified path.
     *
     * @param file path of the remote file
     * @param path path of local directory
     * @param name name of the local file
     * @throws IOException If an I/O error occurs
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     */
    public void downloadFile(String file, String path, String name) throws IOException {
        File outputFolder = new File(path);
        if (!outputFolder.canWrite()) {
            throw new NitrapiErrorException("The folder \"" + outputFolder.getPath() + "\" is not writable.");
        }
        File outputFile = new File(outputFolder, name);
        if (outputFile.exists()) {
            throw new NitrapiErrorException("The file \"" + outputFile.getPath() + "\" already exists.");
        }

        Token token = getDownloadToken(file);

        InputStream stream = api.rawGet(token.getUrl());
        byte[] buffer = new byte[stream.available()];
        stream.read(buffer);

        OutputStream outStream = new FileOutputStream(outputFile);
        outStream.write(buffer);
    }

    /**
     * Reads a specific file.
     *
     * @param file path of the file
     * @return contents of the file
     * @throws IOException If an I/O error occurs
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     */
    public String readFile(String file) throws IOException {
        Token token = getDownloadToken(file);

        BufferedReader reader = new BufferedReader(new InputStreamReader(api.rawGet(token.getUrl())));
        StringBuffer content = new StringBuffer();
        String line;
        while ((line = reader.readLine()) != null) {
            content.append(line + "\n");
        }
        reader.close();
        return content.toString();
    }


    /**
     * Returns the url where you can download this file.
     *
     * @param file path of the file
     * @return url where you can download the file
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     */
    public String getDownloadUrl(String file) {
        Token token = getDownloadToken(file);
        return token.getUrl();
    }

    /**
     * Deletes a file.
     *
     * @param file path of the file
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_WRITE
     */
    public void deleteFile(String file) {
        api.dataDelete("services/" + service.getId() + "/" + url + "/file_server/delete", new Parameter[]{new Parameter("path", file)});
    }

    /**
     * Deletes a directory with content.
     * <br>
     * Same as deleteFile().
     *
     * @param file path of the directory
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_WRITE
     */
    public void deleteDirectory(String file) {
        deleteFile(file);
    }

    /**
     * Moves a file to another directory.
     *
     * @param sourceFile path of the source file
     * @param targetDir  path of the target directory
     * @param fileName   new name of the file
     * @param username   name of the user which should execute this request (for Cloud Servers)
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_WRITE
     */
    public void moveFile(String sourceFile, String targetDir, String fileName, String username) {
        if (sameDirectory(sourceFile, targetDir)) {
            return;
        }
        api.dataPost("services/" + service.getId() + "/" + url + "/file_server/move", new Parameter[]{
                new Parameter("source_path", sourceFile),
                new Parameter("target_path", targetDir),
                new Parameter("target_filename", fileName),
                new Parameter("username", username)
        });
    }

    public void moveFile(String sourceFile, String targetDir, String fileName) {
        moveFile(sourceFile, targetDir, fileName, null);
    }

    /**
     * Recursively moves a directory to another directory.
     *
     * @param source   path of the source directory
     * @param target   path of the target directory
     * @param username name of the user which should execute this request (for Cloud Servers)
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_WRITE
     */
    public void moveDirectory(String source, String target, String username) {
        api.dataPost("services/" + service.getId() + "/" + url + "/file_server/move", new Parameter[]{
                new Parameter("source_path", source),
                new Parameter("target_path", target),
                new Parameter("username", username)
        });
    }

    public void moveDirectory(String source, String target) {
        moveDirectory(source, target, null);
    }

    /**
     * Copies a file to another directory.
     *
     * @param sourceFile path of the source file
     * @param targetDir  path of the target directory
     * @param fileName   name of the new file
     * @param username   name of the user which should execute this request (for Cloud Servers)
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_WRITE
     */
    public void copyFile(String sourceFile, String targetDir, String fileName, String username) {
        if (sameDirectory(sourceFile, targetDir)) {
            return;
        }
        api.dataPost("services/" + service.getId() + "/" + url + "/file_server/copy", new Parameter[]{
                new Parameter("source_path", sourceFile),
                new Parameter("target_path", targetDir),
                new Parameter("target_filename", fileName)
        });
    }

    public void copyFile(String sourceFile, String targetDir, String fileName) {
        copyFile(sourceFile, targetDir, fileName, null);
    }

    private boolean sameDirectory(String path, String dir) {
        return path.substring(0, path.lastIndexOf("/")).equals(dir);
    }

    /**
     * Recursively copies a directory to another directory.
     *
     * @param source    path of the source directory
     * @param targetDir path of the new parent directory
     * @param dirName   name of the new directory
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_WRITE
     */
    public void copyDirectory(String source, String targetDir, String dirName, String username) {
        copyFile(source, targetDir, dirName, username);
    }

    public void copyDirectory(String source, String targetDir, String dirName) {
        copyFile(source, targetDir, dirName, null);
    }

    /**
     * Creates a new directory.
     *
     * @param path     path of the parent directory
     * @param name     name of the new directory
     * @param username name of the user which should execute this request (for Cloud Servers)
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_WRITE
     */
    public void createDirectory(String path, String name, String username) {
        api.dataPost("services/" + service.getId() + "/" + url + "/file_server/mkdir", new Parameter[]{
                new Parameter("path", path),
                new Parameter("name", name),
                new Parameter("username", username)
        });
    }

    public void createDirectory(String path, String name) {
        createDirectory(path, name, null);
    }

    /**
     * Returns the size of a given file or directory.
     *
     * @param path path to the file
     * @return the size
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     */
    public long getFileSize(String path) {
        JsonObject data = api.dataGet("services/" + service.getId() + "/" + url + "/file_server/size", new Parameter[]{new Parameter("path", path)});
        return data.get("size").getAsLong();
    }

    public boolean supportsPermissions() {
        return hasPermissions;
    }

    /**
     * Changes the owner of a specified path.
     * Only works with Cloud Servers.
     *
     * @param path
     * @param username
     * @param group
     * @param recursive
     */
    public void chown(String path, String username, String group, boolean recursive) {
        if (!hasPermissions) {
            throw new NitrapiErrorException("This service does not support chown.");
        }
        api.dataPost("services/" + service.getId() + "/" + url + "/file_server/chown", new Parameter[]{
                new Parameter("path", path),
                new Parameter("username", username),
                new Parameter("group", group),
                new Parameter("recursive", recursive)
        });
    }

    public void chown(String path, String username, String group) {
        chown(path, username, group, false);
    }

    /**
     * Changes the access permissions of a specified path.
     * Only works with Cloud Servers.
     *
     * @param path
     * @param chmod
     * @param recursive
     */
    public void chmod(String path, String chmod, boolean recursive) {
        if (!hasPermissions) {
            throw new NitrapiErrorException("This service does not support chmod.");
        }
        api.dataPost("services/" + service.getId() + "/" + url + "/file_server/chmod", new Parameter[]{
                new Parameter("path", path),
                new Parameter("chmod", chmod),
                new Parameter("recursive", recursive)
        });
    }

    public void chmod(String path, String chmod) {
        chmod(path, chmod, false);
    }

    /**
     * Returns a list with Bookmarks for easier Navigation.
     * Only works with Cloud Servers;
     *
     * @return
     */
    public String[] getBookmarks() {
        if (!hasBookmarks) {
            throw new NitrapiErrorException("This service does not support bookmarks.");
        }
        JsonObject data = api.dataGet("services/" + service.getId() + "/" + url + "/file_server/bookmarks", null);

        String[] bookmarks = api.fromJson(data.get("bookmarks"), String[].class);
        return bookmarks;
    }
}

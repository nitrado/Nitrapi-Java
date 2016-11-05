package net.nitrado.api.services.gameservers.fileserver;

import com.google.gson.JsonObject;
import net.nitrado.api.Nitrapi;
import net.nitrado.api.common.exceptions.NitrapiErrorException;
import net.nitrado.api.common.http.Parameter;
import net.nitrado.api.services.gameservers.Gameserver;

import java.io.*;
import java.nio.charset.StandardCharsets;

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

    private transient Gameserver service;
    private transient Nitrapi api;

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
    }

    /**
     * Returns the upload token and url.
     *
     * @param path path of the new file
     * @param name name of the new file
     * @return upload token
     */
    public Token getUploadToken(String path, String name) {
        JsonObject data = api.dataPost("services/" + service.getId() + "/gameservers/file_server/upload", new Parameter[]{
                new Parameter("path", path),
                new Parameter("file", name)
        });
        return api.fromJson(data.get("token"), Token.class);
    }

    /**
     * Uploads a specific file. File will be overwritten if it's already existing.
     *
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_WRITE
     * @param file path to the local file
     * @param path path to the remote directory
     * @param name name of the file on the server
     * @throws IOException If an I/O error occurs
     */
    public void uploadFile(String file, String path, String name) throws IOException {
        File localFile = new File(file);
        if (!localFile.exists()) {
            throw new NitrapiErrorException("Can't find local file \"" + file + "\".", -1);
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
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_WRITE
     * @param path    path to the remote directory
     * @param name    name of the remote file
     * @param content contents of the file
     * @throws IOException If an I/O error occurs
     */
    public void writeFile(String path, String name, String content) throws IOException {
        Token token = getUploadToken(path, name);

        api.rawPost(token.getUrl(), token.getToken(), content.getBytes(StandardCharsets.UTF_8));
    }


    /**
     * Lists all files and folders inside of the user root directory.
     *
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     * @return a list of the contents of the root directory
     */
    public FileEntry[] getFileList() {
        JsonObject data = api.dataGet("services/" + service.getId() + "/gameservers/file_server/list", null);
        return api.fromJson(data.get("entries"), FileEntry[].class);
    }

    /**
     * Lists all files and folders inside of a given directory.
     *
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     * @param path path of the directory
     * @return a list of the contents of the given directory
     */
    public FileEntry[] getFileList(String path) {
        JsonObject data = api.dataGet("services/" + service.getId() + "/gameservers/file_server/list", new Parameter[]{new Parameter("dir", path)});
        return api.fromJson(data.get("entries"), FileEntry[].class);
    }

    /**
     * Searches inside a specific directory recursively for specific file pattern.
     *
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     * @param path   path of the directory
     * @param search search pattern
     * @return a list of files matching the pattern
     */
    public FileEntry[] doFileSearch(String path, String search) {
        JsonObject data = api.dataGet("services/" + service.getId() + "/gameservers/file_server/list", new Parameter[]{new Parameter("dir", path), new Parameter("search", search)});
        return api.fromJson(data.get("entries"), FileEntry[].class);
    }

    /**
     * Returns download token and url for a file
     *
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     * @param file path of the file
     * @return the download token
     */
    private Token getDownloadToken(String file) {
        JsonObject data = api.dataGet("services/" + service.getId() + "/gameservers/file_server/download", new Parameter[]{new Parameter("file", file)});
        return api.fromJson(data.get("token"), Token.class);
    }

    /**
     * Downloads a file and safes it directly to a specified path.
     *
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     * @param file path of the remote file
     * @param path path of local directory
     * @param name name of the local file
     * @throws IOException If an I/O error occurs
     */
    public void downloadFile(String file, String path, String name) throws IOException {
        File outputFolder = new File(path);
        if (!outputFolder.canWrite()) {
            throw new NitrapiErrorException("The folder \"" + outputFolder.getPath() + "\" is not writable.", -1);
        }
        File outputFile = new File(outputFolder, name);
        if (outputFile.exists()) {
            throw new NitrapiErrorException("The file \"" + outputFile.getPath() + "\" already exists.", -1);
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
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     * @param file path of the file
     * @return contents of the file
     * @throws IOException If an I/O error occurs
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
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     * @param file path of the file
     * @return url where you can download the file
     */
    public String getDownloadUrl(String file) {
        Token token = getDownloadToken(file);
        return token.getUrl();
    }
    /**
     * Deletes a file.
     *
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_WRITE
     * @param file path of the file
     */
    public void deleteFile(String file) {
        api.dataDelete("services/" + service.getId() + "/gameservers/file_server/delete", new Parameter[]{new Parameter("path", file)});
    }

    /**
     * Deletes a directory with content.
     * <br>
     * Same as deleteFile().
     *
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_WRITE
     * @param file path of the directory
     */
    public void deleteDirectory(String file) {
        deleteFile(file);
    }

    /**
     * Moves a file to another directory.
     *
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_WRITE
     * @param sourceFile path of the source file
     * @param targetDir  path of the target directory
     * @param fileName   new name of the file
     */
    public void moveFile(String sourceFile, String targetDir, String fileName) {
        if (sameDirectory(sourceFile, targetDir)) {
            return;
        }
        api.dataPost("services/" + service.getId() + "/gameservers/file_server/move", new Parameter[]{
                new Parameter("source_path", sourceFile),
                new Parameter("target_path", targetDir),
                new Parameter("target_filename", fileName)
        });
    }

    /**
     * Recursively moves a directory to another directory.
     *
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_WRITE
     * @param source path of the source directory
     * @param target path of the target directory
     */
    public void moveDirectory(String source, String target) {
        api.dataPost("services/" + service.getId() + "/gameservers/file_server/move", new Parameter[]{
                new Parameter("source_path", source),
                new Parameter("target_path", target),
        });
    }

    /**
     * Copies a file to another directory.
     *
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_WRITE
     * @param sourceFile path of the source file
     * @param targetDir  path of the target directory
     * @param fileName   name of the new file
     */
    public void copyFile(String sourceFile, String targetDir, String fileName) {
        if (sameDirectory(sourceFile, targetDir)) {
            return;
        }
        api.dataPost("services/" + service.getId() + "/gameservers/file_server/copy", new Parameter[]{
                new Parameter("source_path", sourceFile),
                new Parameter("target_path", targetDir),
                new Parameter("target_filename", fileName)
        });
    }

    private boolean sameDirectory(String path, String dir) {
        return path.substring(0, path.lastIndexOf("/")).equals(dir);
    }

    /**
     * Recursively copies a directory to another directory.
     *
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_WRITE
     * @param source    path of the source directory
     * @param targetDir path of the new parent directory
     * @param dirName   name of the new directory
     */
    public void copyDirectory(String source, String targetDir, String dirName) {
        copyFile(source, targetDir, dirName);
    }

    /**
     * Creates a new directory.
     *
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_WRITE
     * @param path path of the parent directory
     * @param name name of the new directory
     */
    public void createDirectory(String path, String name) {
        api.dataPost("services/" + service.getId() + "/gameservers/file_server/mkdir", new Parameter[]{
                new Parameter("path", path),
                new Parameter("name", name)
        });
    }

    /**
     * Returns the size of a given file or directory.
     *
     * @permission ROLE_WEBINTERFACE_FILEBROWSER_READ
     * @param path path to the file
     * @return the size
     */
    public long getFileSize(String path) {
        JsonObject data = api.dataGet("services/" + service.getId() + "/gameservers/file_server/size", new Parameter[]{new Parameter("path", path)});
        return data.get("size").getAsLong();
    }

}

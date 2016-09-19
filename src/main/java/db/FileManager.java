package db;

import java.io.*;
import java.util.Map;

/**
 * Created by svkreml on 19.09.2016.
 */
public class FileManager {
    String fileName;

    ObjectOutputStream saveStream;
    ObjectInputStream loadStream;

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    public void save(Map<String, Hasher> db) {
        try {
            saveStream = new ObjectOutputStream(
                    new FileOutputStream(fileName));
            saveStream.writeObject(db);
            saveStream.flush();
            saveStream.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }

    public Map<String, Hasher> load() {
        Map<String, Hasher> db = null;
        try {
            loadStream = new ObjectInputStream(
                    new FileInputStream(fileName));

            db = (Map<String, Hasher>) loadStream.readObject();
            loadStream.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }
        return db;
    }

    ;
}

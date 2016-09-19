package db;

import java.io.*;

/**
 * Created by svkreml on 19.09.2016.
 */
public class FileManager {
    String fileName;

    ObjectOutputStream saveStream;
    ObjectInputStream loadStream;

    public FileManager(String fileName) {
        this.fileName = fileName;
        /*File newFile = new File(fileName);
        try {
            newFile.createNewFile();
        } catch (IOException e) {
            //e.printStackTrace();
        }
*/
    }
    public void saveName(String fileName) {
        this.fileName = fileName;
    }
    public void save(Object db) {
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

    public Object load() {
        Object object = null;
        try {
            loadStream = new ObjectInputStream(
                    new FileInputStream(fileName));

            object =  loadStream.readObject();
            loadStream.close();
        } catch (IOException ex) {
            System.out.println(ex.toString());
            System.out.println("File Empty");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }
        return object;
    }

    ;
}

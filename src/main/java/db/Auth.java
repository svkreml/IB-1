package db;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static db.Hasher.toSHA1;

/**
 * Created by svkreml on 19.09.2016.
 */
public class Auth{
    Map<String, Hasher> db = new HashMap();
    FileManager filemanager = null;
    public Auth(FileManager filemanager) {
        this.filemanager=filemanager;
        db = (Map<String, Hasher>)filemanager.load();
    }

    public boolean lookLogin(String login) {
        return db.containsKey(login);
    }

    public String registarion(String login, String password) {
        if (db.containsKey(login)) return "Already exist";
        Date date = new Date();

        Hasher hasher = new Hasher(password, date.getTime());
        db.put(login, hasher);
        filemanager.save(db);
        return "Created";
    }

    public String autorisation(String login, String password) {
        if (!db.containsKey(login)) return "login not found";
        Hasher hasher = db.get(login);
        if (hasher.getHash().equals(toSHA1(password + hasher.getDate())))
            return "access";
        else return "wrong password";
    }



}


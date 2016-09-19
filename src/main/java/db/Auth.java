package db;

import org.apache.commons.codec.binary.Hex;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static db.Hasher.toSHA1;

/**
 * Created by svkreml on 19.09.2016.
 */
public class Auth {
    Map<String, Hasher> db;
    FileManager filemanager = null;

    public Auth(FileManager filemanager) {
        this.filemanager = filemanager;
        db = (Map<String, Hasher>) filemanager.load();
        if (db == null) db = new HashMap();
    }

    public boolean findLogin(String login) {
        return db.containsKey(login);
    }

    public Set<String> listLogin() {
        return db.keySet();
    }

    public String list() {
        StringBuilder buff = new StringBuilder();
        buff.append("login               date            hash\n");
        buff.append("---------------------------------------------------\n");
        for (String key : db.keySet()) {
            char[] spaces = new char[(22 - key.length())];
            for (int i = 0; i < (20 - key.length()); i++) {
                spaces[i] = ' ';
            }
            buff.append(key + String.valueOf(spaces) + db.get(key).getDate() + "   " + Hex.encodeHexString(db.get(key).getHash()) + '\n');
        }
        return buff.toString();
    }

    public boolean deleteLogin(String login) {
        if (db.containsKey(login)) {
            db.remove(login);
            filemanager.save(db);
            return true;
        }
        return false;
    }

    public String registarion(String login, String password) {
        if (login.length() > 20) return "login too long";
        if (password.length() > 20) return "password too long";
        if (db.containsKey(login)) return login + " already exist";
        Date date = new Date();

        Hasher hasher = new Hasher(password, date.getTime());
        db.put(login, hasher);
        filemanager.save(db);
        return "Created login " + login;
    }

    public String autorisation(String login, String password) {
        if (!db.containsKey(login)) return "login " + login + " not found";
        Hasher hasher = db.get(login);
        if (hasher.getHash().equals(toSHA1(password + hasher.getDate())))
            return "Access";
        else return "wrong password";
    }


}


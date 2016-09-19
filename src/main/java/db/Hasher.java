package db;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by svkreml on 19.09.2016.
 */
class Hasher implements Serializable {

    public static byte[] toSHA1(String convertme) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md.digest(convertme.getBytes());
    }
    Hasher(String password, long date) {
        this.date = String.valueOf(date);
        this.hash = toSHA1(password + date);
    }

    byte[] hash;

    public String getDate() {
        return date;
    }

    public byte[] getHash() {
        return hash;
    }

    String date;

}
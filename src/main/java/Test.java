import db.Auth;
import db.FileManager;

/**
 * Created by svkreml on 19.09.2016.
 */
public class Test {
    public static void main(String[] args) {
        String filename = "db.db";
                FileManager filemanager = new FileManager(filename);
                Auth auth = new Auth(filemanager);

        System.out.println(auth.registarion("login54", "password"));
        System.out.println(auth.autorisation("login54", "password"));
    }
}

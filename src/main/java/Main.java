import db.Auth;
import db.FileManager;

/**
 * Created by svkreml on 19.09.2016.
 */
public class Main {
    public static void main(String[] args) {
        //args=new String[]{"", "2","log","pass","pass"};
        String filename = "db.db";
        if (args.length > 2) {
            try {// 1 reg // 2 auto
                int type = Integer.valueOf(args[1]);
                FileManager filemanager = new FileManager(filename);
                Auth auth = new Auth(filemanager);
                String login;
                String password;
                switch (type) {
                    case 2:
                        login = args[2];
                        password = args[3];
                        String password2 = args[4];
                        if (password.equals(password2))
                            System.out.println(auth.registarion(login, password));
                        else {
                            System.out.println("passw doesnot match");
                            System.exit(-1);
                        }
                        break;
                    case 1:
                        login = args[2];
                        password = args[3];
                        System.out.println(auth.autorisation(login, password));
                        break;
                    default:
                        System.out.println("dunno");
                        System.exit(-1);
                        break;
                }
            }            catch(Exception e){
                System.out.println("Exception = " + e);
            }
        } else{
            System.out.println("Нужны параметры:\n1)1 или 2 авторизация или регистация\n2)login, password, password2(если регистрация)");
            System.exit(-1);
        }
    }
}

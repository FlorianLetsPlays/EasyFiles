import custom_objects.User;
import custom_objects.UsersSave_list;
import custom_objects.UsersSave_map;
import java_objects.BadWordList;
import java_objects.MySQLConf;

public class Main {

    public static void main(String[] args) {
        //file for maps
        UsersSave_map usersSaveMap = new UsersSave_map();
        usersSaveMap.load();

        //file for lists
        UsersSave_list usersSavelist = new UsersSave_list();
        usersSavelist.load();

        //file for mysql
        MySQLConf mySQLConf = new MySQLConf();
        mySQLConf.load();

        //file for badwords
        BadWordList badWordList = new BadWordList();
        badWordList.load();

        //mapping
        User user = new User("User4", 18, "user4", "User4 Street");

        String json = user.getMapped().toString();

        User user2 = new User();
        user2.setMapped(json);

        System.out.println(user2.getMapped().toString(1));


    }

}

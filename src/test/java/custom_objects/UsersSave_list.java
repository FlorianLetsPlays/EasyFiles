package custom_objects;

import de.flp.easyfiles.FileConfiguration;
import de.flp.easyfiles.FileInformation;
import de.flp.easyfiles.mapper.anotaions.CustumIndex;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@FileInformation(name = "users_list.json")
public class UsersSave_list extends FileConfiguration {

    @CustumIndex
    private User admin;
    @CustumIndex(types = User.class)
    private ArrayList<User> users;

    public UsersSave_list() {
    }

    @Override
    public void setDefault() {
        defaults.put("admin", new User("Admin", 18, "admin", "Admin Street"));
        defaults.put("users", new ArrayList<User>() {{
            add(new User("User1", 18, "user1", "User1 Street"));
            add(new User("User2", 16, "user2", "User2 Street"));
            add(new User("User3", 17, "user3", "User3 Street"));
        }});
    }
}
package custom_objects;

import de.flp.easyfiles.FileConfiguration;
import de.flp.easyfiles.FileInformation;
import de.flp.easyfiles.mapper.anotaions.CustumIndex;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;

@Setter
@Getter
@FileInformation(name = "users_map.json")
public class UsersSave_map extends FileConfiguration {

    @CustumIndex
    private User admin;
    @CustumIndex(types = {
            String.class,
            User.class
    })
    private HashMap<String, User> users;

    public UsersSave_map() {
    }

    @Override
    public void setDefault() {
        defaults.put("admin", new User("Admin", 18, "admin", "Admin Street"));
        defaults.put("users", new HashMap<String, User>() {{
            put("user1", new User("User1", 18, "user1", "User1 Street"));
            put("user2", new User("User2", 18, "user2", "User2 Street"));
            put("user3", new User("User3", 18, "user3", "User3 Street"));
        }});
    }
}

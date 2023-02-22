package custom_objects;

import de.flp.easyfiles.mapper.ObjectMapper;
import de.flp.easyfiles.mapper.anotaions.JavaIndex;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User extends ObjectMapper {

    @JavaIndex
    private String name;
    @JavaIndex
    private int age;
    @JavaIndex
    private String username;
    @JavaIndex
    private String address;

    public User() {
    }

    public User(String name, int age, String username, String address) {
        this.name = name;
        this.age = age;
        this.username = username;
        this.address = address;
    }
}

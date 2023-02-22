package java_objects;

import de.flp.easyfiles.FileConfiguration;
import de.flp.easyfiles.FileInformation;
import de.flp.easyfiles.mapper.anotaions.JavaIndex;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@FileInformation(name = "mysql.json", path = "\\src\\test\\resources\\")
public class MySQLConf extends FileConfiguration {

    @JavaIndex
    private String host;

    @JavaIndex
    private Integer port;

    @JavaIndex
    private String database;

    @JavaIndex
    private String username;

    @JavaIndex
    private String password;

    public MySQLConf() {
    }

    @Override
    public void setDefault() {
        defaults.put("host", "localhost");
        defaults.put("port", 3306);
        defaults.put("database", "flamebot");
        defaults.put("username", "root");
        defaults.put("password", "");
    }
}

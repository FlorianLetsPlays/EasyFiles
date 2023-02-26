package java_objects;

import de.flp.easyfiles.FileConfiguration;
import de.flp.easyfiles.FileInformation;
import de.flp.easyfiles.mapper.anotaions.JavaIndex;

import java.util.LinkedList;

@FileInformation(name = "badwords.json", path = "\\src\\test\\resources\\")
public class BadWordList extends FileConfiguration {

    @JavaIndex
    private LinkedList<String> blacklistedWords;

    public LinkedList<String> getBlacklistedWords() {
        return blacklistedWords;
    }

    @Override
    public void setDefault() {
        defaults.put("blacklistedWords", new LinkedList<String>() {{
            add("Hurensohn");
        }});
    }
}

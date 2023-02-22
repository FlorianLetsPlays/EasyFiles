package de.flp.easyfiles;

import de.flp.easyfiles.mapper.ObjectMapper;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public abstract class FileConfiguration extends ObjectMapper {

    private File file;

    public HashMap<String, Object> defaults;

    private final static String PATH = System.getProperty("user.dir");


    public void load() {

        try {

            if (!exists()) {
                create();
            }

            if(defaults == null) {
                defaults = new HashMap<>();
                setDefault();
            }

            String json = new String(Files.readAllBytes(Paths.get(file.toURI())));

            Boolean setDefault = setMapped(json, defaults);

            if (setDefault) {
                save();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public Boolean exists() {
        file = new File(PATH + getClass().getDeclaredAnnotation(FileInformation.class).path() + getClass().getDeclaredAnnotation(FileInformation.class).name());
        return file.exists();
    }

    private void create() {
        try {

            if(defaults == null) {
                defaults = new HashMap<>();
                setDefault();
            }

            File yourFile = new File(PATH + getClass().getDeclaredAnnotation(FileInformation.class).path() + getClass().getDeclaredAnnotation(FileInformation.class).name());
            yourFile.createNewFile(); // if file already exists will do nothing
            setDefaults();
            save();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save() {
        JSONObject json = getMapped();

        if (!exists()) {
            create();
        }

        try (PrintWriter out = new PrintWriter(new FileWriter(file.getPath()))) {
            out.write(json.toString(3));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void delete() {
        if (exists()) {
            file.delete();
        }
    }

    private void setDefaults() {
        for (Field field : getClass().getDeclaredFields()) {
            try {

                field.setAccessible(true);

                field.set(this, defaults.get(field.getName()));

            } catch (Exception a) {
                a.printStackTrace();
            }
        }
    }


    public void setDefault() {
    }

}

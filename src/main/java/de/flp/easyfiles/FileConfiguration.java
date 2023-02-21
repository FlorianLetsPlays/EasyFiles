package de.flp.easyfiles;

import de.flp.easyfiles.mapper.ObjektMapper;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class FileConfiguration extends ObjektMapper {

    private File file;

    private final static String PATH = System.getProperty("user.dir");


    public void load() {

        try {
            if (!exists()) {
                create();
            }

            String json = new String(Files.readAllBytes(Paths.get(file.toURI())));

            setMapped(json);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private Boolean exists() {
        file = new File(PATH + getClass().getDeclaredAnnotation(FileInformation.class).path() + getClass().getDeclaredAnnotation(FileInformation.class).name());
        return file.exists();
    }

    private void create() {
        try {
            File yourFile = new File(PATH + getClass().getDeclaredAnnotation(FileInformation.class).path() + getClass().getDeclaredAnnotation(FileInformation.class).name());
            yourFile.createNewFile(); // if file already exists will do nothing
            setDefault();
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

   public void setDefault() {
   }

}

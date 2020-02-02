package main;
import com.google.gson.Gson;
import main.StorageType;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.HashMap;

public class TestStorage implements StorageType {
    private final Gson gson;
    HashMap<String, String> hm = new HashMap();

    public TestStorage() {
       this.gson = new Gson();

    }

    @Override
    public void write(@Nullable File storage, String string) throws FileNotFoundException {
        try {
            FileWriter sw = new FileWriter(storage);
            sw.write(string);
            sw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String read(@Nullable File storage) throws FileNotFoundException {
        String allLines="";
        try {
            FileReader fr = new FileReader(storage);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
                allLines+=line;
            }
        }
        catch (IOException ignored) {}
        return allLines;
    }

    @Override
    public boolean needsGivenStorage() {
        return true;
    }

}

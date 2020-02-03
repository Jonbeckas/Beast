package presets;

import com.google.gson.Gson;
import main.StorageType;
import org.jetbrains.annotations.Nullable;

import java.io.*;
import java.util.HashMap;

public class StoreToFile implements StorageType {
    private final Gson gson = new Gson();
    HashMap<String, String> hm = new HashMap();

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
        File saveFi = new File(storage.getAbsolutePath()+".new");
        try {
            FileReader fr = new FileReader(storage);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
                allLines+=line;
            }
            storage.delete();
            saveFi.renameTo(storage);
        }
        catch (IOException ignored) {}
        return allLines;
    }

    @Override
    public boolean needsGivenStorage() {
        return true;
    }
}

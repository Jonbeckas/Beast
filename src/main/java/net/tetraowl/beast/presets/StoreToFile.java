package net.tetraowl.beast.presets;

import net.tetraowl.beast.BeastInstance;
import net.tetraowl.beast.CacheType;

import java.io.*;

public class StoreToFile extends BeastInstance {
    File storage ;

    public StoreToFile(CacheType cacheType, File storeageFile) {
        super(new GSONSerializer(), cacheType);
        this.storage = storeageFile;
        storeageFile.getParentFile().mkdirs();
        super.loadCache();
    }


    @Override
    protected void save(String s) {
        try {
            FileWriter sw = new FileWriter(storage);
            sw.write(s);
            sw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String load() {
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
}

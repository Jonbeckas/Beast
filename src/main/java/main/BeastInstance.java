package main;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BeastInstance {
    StorageType st;
    private Gson gson = new Gson();
    private String beastDirectory;
    private String id;

    public BeastInstance(Class<StorageType> storageType, String beastDirectory,String id) {
        try {
            st = storageType.getDeclaredConstructor().newInstance();
            this.beastDirectory = beastDirectory;
            this.id = id;
        } catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    private File getConfFile() {
        String path;
        if (beastDirectory.equals("")) {
            path = this.id+".beast";
        } else {
            path = this.beastDirectory+"/"+this.id+".beast";
        }
        File file = new File(path);
        file.getParentFile().mkdirs();
        return file;
    }

    private LinkedList<StoreObject> getStoreObjs() {
        String readed;
        try {
            if (this.st.needsGivenStorage()) {
                readed = this.st.read(getConfFile());
            } else {
                readed = this.st.read(null);
            }
        } catch (FileNotFoundException e) {
            readed = "";
        }
        Type typeOfT = TypeToken.getParameterized(List.class,StoreObject.class).getType();
        List<StoreObject> lso = this.gson.fromJson(readed, typeOfT);
        return lso == null ? new LinkedList<>() : new LinkedList<>(lso);
    }


    public void set(Object o, String id) {
       StoreObject so = new StoreObject(id, o);
        List<StoreObject> lso = getStoreObjs();
        boolean isIn = false;
        for (StoreObject storeObject : lso) {
            if (storeObject.getId().equals(so.getId())) {
                lso.remove(storeObject);
                lso.add(so);
                isIn = true;
                break;
            }
        }
        if (!isIn) {
            lso.add(so);
        }
        try {
            if (this.st.needsGivenStorage()) {
                this.st.write(getConfFile(), gson.toJson(lso));
            } else {
                this.st.write(null, gson.toJson(lso));

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public Object get(Class clazz, String id) {
        LinkedList<StoreObject> lso = getStoreObjs();
        for (StoreObject storeObject : lso) {
            if (storeObject.getId().equals(id)) {
                return clazz.cast(storeObject.getObject());
            }
        }
        return null;
    }

    public ArrayList<Object> getList (String id) {
        LinkedList<StoreObject> lso = getStoreObjs();
        for (StoreObject storeObject : lso) {
            if (storeObject.getId().equals(id)) {
                return ArrayList.class.cast(storeObject.getObject());
            }
        }
        return null;

    }
}

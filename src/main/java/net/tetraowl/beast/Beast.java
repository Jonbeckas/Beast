package net.tetraowl.beast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Beast {
    StorageType st;
    private Gson gson = new Gson();
    private File confFile;

    public Beast(Class storageType, File confFile) {
        try {
            Class<StorageType> c = storageType;
            st = c.getDeclaredConstructor().newInstance();
            this.confFile = confFile;
        } catch (InstantiationException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    private File getConfFile() {
        this.confFile.getParentFile().mkdirs();
        return this.confFile;
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

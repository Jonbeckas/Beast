package net.tetraowl.beast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class BeastInstance {
    private final CacheType cacheType;
    private final Serializer serializer;
    private Gson gson = new Gson();
    private HashMap<String, Object> cachemap = new HashMap<>();
    private Beast beast;

    public BeastInstance(Serializer serializer, CacheType cacheType) {
        this.cacheType = cacheType;
        this.serializer = serializer;

    }


    public void initCaching() {
        if (cacheType == CacheType.CACHE_ON_START) {
            for (StoreObject so: getStoreObjs()) {
                cachemap.put(so.getId(), so.getObject());
            }
        }
    }

    protected abstract void save(String s);
    protected abstract String load();


    private LinkedList<StoreObject> getStoreObjs() {
        String readed = load();
        if (readed ==null) readed ="";
        return this.serializer.deserialize(readed);
    }



    public void set(Serializable o, String id) {
       StoreObject so = new StoreObject(id,(Object) o);
       if (this.cacheType == CacheType.CACHE_ON_START || this.cacheType == CacheType.CACHE) {
           this.cachemap.put(id,o);
       }
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
        save(this.serializer.serialize(lso));
    }


    public Object get(String id) {
        LinkedList<StoreObject> lso = getStoreObjs();
        if (this.cachemap.containsKey(id)) {
            return  cachemap.get(id);
        }
        for (StoreObject storeObject : lso) {
            if (storeObject.getId().equals(id)) {
                return storeObject.getObject();
            }
        }
        return null;
    }

    public List<Object> getList (String id) {
        LinkedList<StoreObject> lso = getStoreObjs();
        if (this.cachemap.containsKey(id)) {
            return   List.class.cast(cachemap.get(id));
        }
        for (StoreObject storeObject : lso) {
            if (storeObject.getId().equals(id)) {
                return List.class.cast(storeObject.getObject());
            }
        }
        return null;

    }

    public void move(String id,String toStorageId) throws Beast.IdAlreadyExistException {
        BeastInstance bi = this.beast.get(toStorageId);
        if (bi.get(id) == null) {
            bi.set((Serializable) get(id),id);
        } else {
            throw new Beast.IdAlreadyExistException();
        }
    }

    public void setBeast(Beast beast) {
        this.beast = beast;
    }
}

package net.tetraowl.beast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class StorageType {
    private final CacheType cacheType;
    private final Serializer serializer;
    private Gson gson = new Gson();
    private HashMap<String, Object> cachemap = new HashMap<>();
    private Beast beast;

    public StorageType(Serializer serializer, CacheType cacheType) {
        this.cacheType = cacheType;
        this.serializer = serializer;
    }

    protected LinkedList<StoreObject> getStoreObjs() {
        String readed = load();
        if (readed ==null) readed ="";
        return this.serializer.deserialize(readed);
    }

    protected void set(Object o,String id) {
        StoreObject so = new StoreObject(id,(Object) o);
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

    protected void remove(String id) {
        this.cachemap.remove(id);
        LinkedList<StoreObject> ll = getStoreObjs();
        ll.removeIf(o -> o.getId().equals(id));
        save(this.serializer.serialize(ll));
    }

    protected boolean cacheContaines(String id) {
        return this.cachemap.containsKey(id);
        }

    //store to cache
    protected void cache (String id,Object o) {
        if (this.cacheType == CacheType.CACHE_ON_START || this.cacheType == CacheType.CACHE) {
            this.cachemap.put(id, o);
        }
    }
    //returns null if caching is disabled or izem not in cache
    protected Object getCache(String id) {
        if (this.cachemap.containsKey(id)) {
            return  cachemap.get(id);
        } else {
            return null;
        }
    }

    //check if contains id
    public boolean containesId(String id) {
        LinkedList<StoreObject> ll = getStoreObjs();
        for (StoreObject o : ll) {
            if (o.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public BeastItem getBeastItem(String id) {
        return new BeastItem(this,id);
    }


    public void setBeast(Beast beast) {
        this.beast = beast;
    }

    public Beast getBeast() {
        return this.beast;
    }



    public void loadCache() {
        if (cacheType == CacheType.CACHE_ON_START) {
            for (StoreObject so: getStoreObjs()) {
                cachemap.put(so.getId(), so.getObject());
            }
        }
    }

    protected abstract void save(String s);
    protected abstract String load();

}

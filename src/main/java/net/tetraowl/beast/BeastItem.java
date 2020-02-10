package net.tetraowl.beast;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class BeastItem {

    private final StorageType storageType;
    private final String id;

    public BeastItem(StorageType storageType, String id) {
        this.id = id;
        this.storageType  = storageType;
    }

    public void set(Serializable o) {
        this.storageType.cache(id,o);
        this.storageType.set(o,this.id);
    }

    public Object get() {
        LinkedList<StoreObject> lso = storageType.getStoreObjs();
        if (storageType.cacheContaines(id)) {
            return storageType.getCache(id);
        }
        for (StoreObject storeObject : lso) {
            if (storeObject.getId().equals(id)) {
                return storeObject.getObject();
            }
        }
        return null;
    }

    public List<Object> getList () {
        LinkedList<StoreObject> lso = this.storageType.getStoreObjs();
        if (this.storageType.cacheContaines(id)) {
            return   List.class.cast(this.storageType.getCache(id));
        }
        for (StoreObject storeObject : lso) {
            if (storeObject.getId().equals(id)) {
                return List.class.cast(storeObject.getObject());
            }
        }
        return null;

    }

    public void move(String toStorageId) throws Beast.IdAlreadyExistException {
        StorageType storageType = this.storageType.getBeast().getStorageType(toStorageId);
        if (storageType.getBeastItem(this.id).get() == null) {
            storageType.getBeastItem(this.id).set((Serializable) get());
        } else {
            throw new Beast.IdAlreadyExistException();
        }
    }

    public void remove() {
        this.storageType.remove(id);
    }


}

package net.tetraowl.beast;

import java.util.HashMap;

public class Beast  {
    private StorageType defaultstorageType;
    private HashMap<String, StorageType> allBeastInstance = new HashMap<>();
    public final static String DEFAULT_STORAGE_ID ="defaultasdfghjklpüoiuztrxcvbdgvufzfvsduc6fedzawtf";
    public Beast(StorageType defaultstorageType) {
        this.defaultstorageType = defaultstorageType;
        this.defaultstorageType.setBeast(this);
    }

    public BeastItem get(String id) {
        for ( StorageType st : allBeastInstance.values()) {
            if (st.containesId(id)) {
                System.out.println(id+" is in "+st);
                return st.getBeastItem(id);
            } else {
                System.out.println(id+" is not in "+st);
            }
        }
        return this.defaultstorageType.getBeastItem(id);
    }

    public BeastItem create(String id,String storageTypeId) {
        return this.allBeastInstance.get(storageTypeId).getBeastItem(id);
    }

    public StorageType getStorageType(String storageTypeId) {
        if (storageTypeId.equals(DEFAULT_STORAGE_ID)){
            return this.defaultstorageType;
        } else {
            return this.allBeastInstance.get(storageTypeId);
        }
    }

    public void addStorageType(String id, StorageType bi) throws IdAlreadyExistException {
        if (!allBeastInstance.containsKey(id)){
            bi.setBeast(this);
            this.allBeastInstance.put(id,bi);
        } else {
            throw new IdAlreadyExistException();
        }
    }

    public static class IdAlreadyExistException extends Exception { }
}

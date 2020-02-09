package net.tetraowl.beast;

import java.util.HashMap;

public class Beast  {
    private BeastInstance defaultstorageType;
    private HashMap<String, BeastInstance> allBeastInstance = new HashMap<>();
    public Beast(BeastInstance bi) {
        this.defaultstorageType = bi;
        this.defaultstorageType.setBeast(this);
    }

    public BeastInstance get(String id) {
        if (!this.allBeastInstance.containsKey(id)) {
            this.allBeastInstance.put(id,defaultstorageType);
        }
        return allBeastInstance.get(id);
    }


    public void addBeastInstance(String id, BeastInstance bi) throws IdAlreadyExistException {
        if (!allBeastInstance.containsKey(id)){
            bi.setBeast(this);
            this.allBeastInstance.put(id,bi);
        } else {
            throw new IdAlreadyExistException();
        }
    }

    public static class IdAlreadyExistException extends Exception { }
}

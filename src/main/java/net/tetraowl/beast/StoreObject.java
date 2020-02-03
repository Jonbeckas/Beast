package net.tetraowl.beast;

public class StoreObject {
    private final String id;
    private final Object object;

    public StoreObject(String id, Object object) {
        this.id = id;
        this.object = object;
    }

    public String getId() {
        return id;
    }

    public Object getObject() {
        return object;
    }
}

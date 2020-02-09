package net.tetraowl.beast;

import java.io.Serializable;
import java.util.LinkedList;

public interface Serializer {
    public String serialize(Object s);
    public LinkedList<StoreObject> deserialize(String s);

}

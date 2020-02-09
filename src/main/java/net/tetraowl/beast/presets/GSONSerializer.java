package net.tetraowl.beast.presets;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.tetraowl.beast.Serializer;
import net.tetraowl.beast.StoreObject;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

public class GSONSerializer implements Serializer {
    Gson gson = new Gson();;

    @Override
    public String serialize(Object s) {
        return gson.toJson(s);
    }

    @Override
    public LinkedList<StoreObject> deserialize(String s) {
        Type typeOfT = TypeToken.getParameterized(List.class,StoreObject.class).getType();
        List<StoreObject> lso = this.gson.fromJson(s, typeOfT);
        return lso == null ? new LinkedList<>() : new LinkedList<>(lso);
    }
}

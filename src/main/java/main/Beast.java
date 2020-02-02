package main;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import main.StorageType;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.net.URI;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Beast {
    private HashMap<String, BeastInstance> instances = new HashMap<>();
    private String directory;

    public Beast(String directory) {
        this.directory = directory;
    }

    public void openInstance (Class st,String id) {
        this.instances.put(id,new BeastInstance(st,this.directory,id));
    }

    public BeastInstance openInstanceAndGet (Class st,String id) {
        BeastInstance bi = new BeastInstance(st,this.directory,id);
        this.instances.put(id,bi);
        return bi;
    }

    public BeastInstance getBeastInstance (String id) {
       return this.instances.get(id);
    }
}

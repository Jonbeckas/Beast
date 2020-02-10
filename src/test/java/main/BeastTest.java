package main;

import net.tetraowl.beast.Beast;
import net.tetraowl.beast.CacheType;
import net.tetraowl.beast.StoreObject;
import net.tetraowl.beast.presets.Caching;
import net.tetraowl.beast.presets.GSONSerializer;
import net.tetraowl.beast.presets.StoreToFile;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BeastTest {
    public static void main(String[] args) {
        Beast beast = new Beast(new StoreToFile( CacheType.CACHE_ON_START,new File("testing/lol.beast")));
        try {
            beast.addStorageType("caching",new Caching(CacheType.CACHE));
        } catch (Exception ignore) {};
        LinkedList<StoreObject> ll = new LinkedList<>();
        ll.add(new StoreObject("hmm","oko"));
        ll.add(new StoreObject("ok",1));
        beast.get("KOOOL").set("hallo");
        beast.create("lol","caching").set(ll);
        List<Object> arrayList = beast.get("lol").getList();
        System.out.println((arrayList.get(0)+" und "+arrayList.get(1)));
        System.out.println( beast.get("lol").getList()+"    "+beast.get("KOOOL").get());
        try {
            beast.get("lol").move(Beast.DEFAULT_STORAGE_ID);
        } catch (Beast.IdAlreadyExistException e) {
            e.printStackTrace();
        }
    }

}
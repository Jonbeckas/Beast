package main;

import net.tetraowl.beast.Beast;
import net.tetraowl.beast.BeastInstance;
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
            beast.addBeastInstance("caching",new Caching(CacheType.CACHE));
        } catch (Exception ignore) {};
        LinkedList<StoreObject> ll = new LinkedList<>();
        ll.add(new StoreObject("hmm","oko"));
        ll.add(new StoreObject("ok",1));
        beast.get("KOOOL").set("LOLOLOLOLO","hallo");
        beast.get("caching").set(ll,"hi");
        List<Object> arrayList = beast.get("caching").getList("hi");
        System.out.println((arrayList.get(0)+" und "+arrayList.get(1)));
        System.out.println( beast.get("caching").getList("hi")+"    "+beast.get("KOOOL").get("hallo"));
        try {
            beast.get("caching").move("hi","KOOOL");
        } catch (Beast.IdAlreadyExistException e) {
            e.printStackTrace();
        }
    }

}
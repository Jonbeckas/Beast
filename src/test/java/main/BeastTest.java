package main;

import presets.Caching;
import presets.StoreToFile;

import java.util.ArrayList;
import java.util.LinkedList;

public class BeastTest {
    public static void main(String[] args) {
        Beast b = new Beast("testing");
        BeastInstance bi = b.openInstanceAndGet(StoreToFile.class,"lol");
        b.openInstance(Caching.class,"toll");
        BeastInstance bild = b.getBeastInstance("toll");
        LinkedList<StoreObject> ll = new LinkedList<>();
        ll.add(new StoreObject("hmm","oko"));
        ll.add(new StoreObject("ok",1));
        bild.set("LOLOLOLOLO","hallo");
        bi.set(ll,"hi");
        ArrayList<Object> arrayList = bi.getList("hi");
        System.out.println((arrayList.get(0)+" und "+arrayList.get(1)));
        System.out.println( bi.getList("hi")+"    "+bild.get(String.class,"hallo"));
    }

}
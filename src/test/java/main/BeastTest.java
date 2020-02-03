package main;

import net.tetraowl.beast.Beast;
import net.tetraowl.beast.StoreObject;
import net.tetraowl.beast.presets.Caching;
import net.tetraowl.beast.presets.StoreToFile;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;

public class BeastTest {
    public static void main(String[] args) {
        Beast bi = new Beast(StoreToFile.class,new File("testing/lol.beast"));
        Beast bild =new Beast(Caching.class,new File("testing/toll.beast"));
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
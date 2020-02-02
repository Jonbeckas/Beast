package main;

public class BeastTest {
    public static void main(String[] args) {
        Beast b = new Beast("testing");
        BeastInstance bi = b.openInstanceAndGet(TestStorage.class,"lol");
        b.openInstance(TestLolStorage.class,"toll");
        BeastInstance bild = b.getBeastInstance("toll");
        bild.set("LOLOLOLOLO","hallo");
        bi.set("Hallo","hi");
        System.out.println( bi.get(String.class,"hi")+"    "+bild.get(String.class,"hallo"));
    }

}
import java.util.ArrayList;

public interface Podminky {
    static ArrayList<Integer> naplneni(int b, int pocatecni, int konecna){//metoda která naplňuje array list čísli potřebnými k utvoření podmínky, vrací arraylist typu integer
        ArrayList<Integer> podminky = new ArrayList<>();//deklarace arraylistu
        for(int i = pocatecni; i <= konecna; i += b){
            podminky.add(i);//naplněné arraylistu
        }
        return podminky;
    }

    static ArrayList<Integer> napleni(int a, int pocatecni, int konecna, int b, int pocatecni2, int konecna2){//přetížená metoda pro naplněné, dělá to stejné jako metoda před ní, akorát s rozšířenou podmínkou
        ArrayList<Integer> podminky = new ArrayList<>();
        for(int i = pocatecni; i <= konecna; i += a){
            podminky.add(i);
        }
        for(int i = pocatecni2; i <= konecna2; i += b){
            podminky.add(i);
        }
        return podminky;
    }

    static boolean vyhodnoceni(int poradi, ArrayList<Integer> podminky){//metoda vyhodnocuje zda se nějaké z čísel v arralistu podmínky neshoduje s indexem pořadí, metoda vrací boolean
        boolean pravda = false;//deklarace a nastavení proměnné
        if (podminky.contains(poradi)){//pokud arraylist podminky obsahuje číslo pořadí, tak metoda vrátí false
            return false;
        }
        else pravda = true;//nastavení proměnné
        //System.out.println(podminky);
        return pravda;
    }


}

import java.util.ArrayList;

public interface Podminky{
    static ArrayList<Integer> naplneni(int b, int pocatecni, int konecna){
        ArrayList<Integer> podminky = new ArrayList<>();
        for(int i = pocatecni; i <= konecna; i += b){
            podminky.add(i);
        }
        return podminky;
    }

    static ArrayList<Integer> napleni(int a, int pocatecni, int konecna, int b, int pocatecni2, int konecna2){
        ArrayList<Integer> podminky = new ArrayList<>();
        for(int i = pocatecni; i <= konecna; i += a){
            podminky.add(i);
        }
        for(int i = pocatecni2; i <= konecna2; i += b){
            podminky.add(i);
        }
        return podminky;
    }

    static boolean vyhodnoceni(int poradi, ArrayList<Integer> podminky){
        boolean pravda = false;
        if (podminky.contains(poradi)){
            return false;
        }
        else pravda = true;
        return pravda;
    }


}

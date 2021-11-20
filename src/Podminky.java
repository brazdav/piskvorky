import java.util.ArrayList;

public interface Podminky {
    ArrayList<Integer> podminky = new ArrayList<>();
    static void naplneni(int b, int pocatecni, int konecna){
        for(int i = pocatecni; i <= konecna; i += b){
            podminky.add(i);
        }
    }

    static void napleni(int a, int pocatecni, int konecna, int b, int pocatecni2, int konecna2){
        for(int i = pocatecni; i <= konecna; i += a){
            podminky.add(i);
        }
        for(int i = pocatecni2; i <= konecna2; i += b){
            podminky.add(i);
        }
    }

    static boolean vyhodnoceni(int poradi){
        boolean pravda = false;
        for (int i:podminky) {
            if (i != poradi){
                pravda = true;
            }
            else return false;
        }
        return pravda;
    }


}

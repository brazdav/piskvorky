import java.util.ArrayList;

public interface Podminky {
    ArrayList<Integer> podminky = new ArrayList<>();
    static void naplneni(int b, int pocatecni, int konecna){
        for(int i = pocatecni; i <= konecna; i += b){
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

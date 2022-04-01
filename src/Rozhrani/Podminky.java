package Rozhrani;

import java.util.ArrayList;

/**
 * Rozhraní Podmínky zajišťuje, že se nanapojí tlačítka která jsou indexovaná v poli za sebou,
 * když nejsou ve stejné řadě, okrajové hodnoty
 * Obsahuje 3 metody
 * @author Vojtěch Brázda
 * @version 1.0.0
 *
 */
public interface Podminky{
    /**
     * Metoda naplneni, naplňuje ArrayList typu Integer jménem podminky
     * Pomocí cyklu for přidá všechny hodnoty do ArrayListu
     * Pomáhá ošetřovat metody
     * @param b o kolik se bude posouvat v tom ArrayListu
     * @param pocatecni
     * @param konecna
     * @return ArrayList podmínky
     */
    static ArrayList<Integer> naplneni(int b, int pocatecni, int konecna){
        ArrayList<Integer> podminky = new ArrayList<>();
        for(int i = pocatecni; i <= konecna; i += b){
            podminky.add(i);
        }
        return podminky;
    }

    /**
     *
     * @param a
     * @param pocatecni
     * @param konecna
     * @param b
     * @param pocatecni2
     * @param konecna2
     * @return
     */
    static ArrayList<Integer> naplneni(int a, int pocatecni, int konecna, int b, int pocatecni2, int konecna2){
        ArrayList<Integer> podminky = new ArrayList<>();
        for(int i = pocatecni; i <= konecna; i += a){
            podminky.add(i);
        }
        for(int i = pocatecni2; i <= konecna2; i += b){
            podminky.add(i);
        }
        return podminky;
    }

    /**
     *
     * @param poradi
     * @param podminky
     * @return
     */
    static boolean vyhodnoceni(int poradi, ArrayList<Integer> podminky){
        boolean pravda = false;
        if (podminky.contains(poradi)){
            return false;
        }
        else pravda = true;
        return pravda;
    }


}

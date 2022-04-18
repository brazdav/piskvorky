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
     * Pomocí cyklu for přidá všechny hodnoty z daného okraje do ArrayListu
     * Pomáhá ošetřovat metody
     * @param b o kolik indexů se budou hraniční tlačítka posouvat v tom ArrayListu
     * @param pocatecni jaké hraniční tlačítko je první
     * @param konecna určuje, jaké hraniční tlačitko bude jako poslední
     * @return ArrayList podmínky
     */
    default ArrayList<Integer> naplneni(int b, int pocatecni, int konecna){
        ArrayList<Integer> podminky = new ArrayList<>();
        for(int i = pocatecni; i <= konecna; i += b){
            podminky.add(i);
        }
        return podminky;
    }

    /**
     * Přetížená metoda naplnění, potřebujeme jí k oštření křížových směrů.
     * Metoda nastavuje dva typy hranice.
     * @param a určuje o kolik se bude posouvat první hranice
     * @param pocatecni určuje první index hraničního tlačitka z první hranice
     * @param konecna   určuje poslední index hraničního tlačitka z první hranice
     * @param b určuje o kolik se bude posouvat druhé hranice
     * @param pocatecni2 určuje první index hraničního tlačitka z druhé hranice
     * @param konecna2 určuje poslední index hraničního tlačitka z druhé hranice
     * @return ArrayList podminky
     */
    default ArrayList<Integer> naplneni(int a, int pocatecni, int konecna, int b, int pocatecni2, int konecna2){
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
     * Metoda vyhodnoceni vyhodnocuje zda je nebo není index právě hraniční hodnotou.
     * Pokud ArrayList podminky bude obsahovat vloženou hodnotu z parametru poradí, vrátí false.
     * Naopak pokud ArrayList podminky hodnotu obsahovat nebu a zároveň hodnota bude větší nebo rovna nule, metoda vrátí true.
     * @param poradi je index tlačítka, u kterého cheme zkontrolovat, zda-li je hraniční
     * @param podminky je ArrayList naplněný předchozími metodami
     * @return boolean pravda
     */
    default boolean vyhodnoceni(int poradi, ArrayList<Integer> podminky){
        boolean pravda = false;
        if (podminky.contains(poradi)){
            return false;
        }
        else if (poradi >= 0)pravda = true;
        return pravda;
    }


}

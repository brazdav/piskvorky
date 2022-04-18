package AI;

import Rozhrani.Podminky;

import javax.swing.*;
import java.util.ArrayList;

/**
 * Třída CheckAI implementuje rozhraní Podminky
 * Obsahuje metody check, countIndex, getIndex a getRow
 */
public class CheckAI implements Podminky{
    private int row = 0;
    private int index;
    private String znak1;
    private String znak2;

    /**
     * Metoda check se nejdříve rozhodne jaký znak bude na tlačítkách vyhledávát a to za pomocí stringu znak
     * S pomocí získaného parametru index zavolá metodu coundIndex, která vrátí vypočítaný index tlačítka, na kterém se hledá daný znak
     * Pokud je vypočítaný index v rozmezí ArrayListu, což je 0-224 a zároveň není stejný jako index vstupující jako parametr
     * spustí se podmínka, kde se kontroluje, zda-li je na tlačítků hledaný string znak, pokud ano, hodnota row se navíší o jedna a metoda se volá dokola, dokud na tlačítku nebude špatný string
     * Pokud se na tlačítku nenajde základní hledaný string, kontroluji, zda je na tlačítku string tomu opačný
     * Při spuštění podmínky se provede switch, jenž počítá index na druhou stranu řady či sloupce po sobě jdoucích tlačítek se stejným stringem
     * Row zde snižujeme o jedna, protože je řada z jedné strany již zablokována a tudíž pro nás není tak nebezpečná
     * Pro útok platí, že je pro něj lepší rozvíjet řadu, kde není blokovaný z nějaké strany
     * Pokud je na druhé straně stejný nebo již opačný string, nastavujeme row na nulu, aby se s ním nepočítalo
     * Pokud se nenajde hledaný string ani k tomu opačný zavolám metodu countIndex() a hledám dříve uložený string za vynechnou mezerou, pokud se tak stane, navišuji row o jedna
     * Pokud je index mimo rozsah ArrayListu, nebo je roven púvodnímu indexu, nastavuji row na nulu, aby se s tímto indexem nepočítalo
     * @param index index v ArrayListu okolo kterého budeme tlačítka testovat na obsahované řetězce
     * @param buttons ArrayList tlačítek
     * @param znak string, který budeme na tlačítkách hledat
     * @param site představuje stranu, kterou budeme tlačítka testovat
     * @return metoda vrací objekt třídy, který nám slouží jako přistupovatel k proměnným třídy
     */
    public CheckAI check(int index, ArrayList<JButton> buttons, String znak, String site){
        if (znak.equals("X")){
            znak1 = znak;
            znak2 = "O";
        }
        else {
            znak1 = znak;
            znak2 = "X";
        }
        this.index = index;
        this.index = countIndex(site, this.index);
        if (this.index >= 0 && this.index <= 224 && this.index != index) {
            if (buttons.get(this.index).getText().equals(znak1)) {
                row++;
                check(this.index, buttons, znak1, site);
            }
            else if (buttons.get(this.index).getText().equals(znak2)) {
                int prom = this.index;
                switch (site) {
                    case "left":
                        if (vyhodnoceni(prom, naplneni(15,0,210)))
                            prom = prom + (prom + 2);
                        break;
                    case "right":
                        if (vyhodnoceni(prom, naplneni(15, 14,224)))
                            prom = prom - (this.row + 2);
                        break;
                    case "up":
                        if (vyhodnoceni(prom, naplneni(1,0,14)))
                            prom = prom + (this.row + 2) * 15;
                        break;
                    case "down":
                        if (vyhodnoceni(prom, naplneni(1,210,224)))
                            prom = prom - (this.row + 2) * 15;
                        break;
                    case "leftup":
                        if (vyhodnoceni(prom, naplneni(1,0,14,15,0,210)))
                            prom = prom + (this.row + 2) * 16;
                        break;
                    case "leftdown":
                        if (vyhodnoceni(prom, naplneni(15,0,210,1,210,224)))
                            prom = prom - (this.row + 2) * 14;
                        break;
                    case "rightup":
                        if (vyhodnoceni(prom, naplneni(15,14,224,1,0,14)))
                            prom = prom + (this.row + 2) * 14;
                        break;
                    case "rightdown":
                        if (vyhodnoceni(prom, naplneni(15,14,224,1,210,224)))
                            prom = prom - (this.row + 2) * 16;
                        break;
                }
                this.row --;
                if (buttons.get(this.index).getText().equals(znak2))
                    this.row = 0;

                else if (buttons.get(prom).getText().equals(znak1))
                    this.row = 0;

            }
            else if (buttons.get(this.index).getText().equals("")){
                int prom = countIndex(site,this.index);
                if (prom > 0 && prom < 225 && buttons.get(prom).getText().equals(znak1)){
                    this.row ++;
                }
                prom = countIndex(site, prom);
                if (prom > 0 && prom < 225 && buttons.get(prom).getText().equals(znak1)){
                    this.row ++;
                }

            }
        }
        if (this.index > 224 || this.index < 0 || this.index == index)
            this.row = 0;
        return this;
    }

    /**
     * Metoda coundIndex počítá index na kerém budeme testovat další tlačítko
     * Rozhoduje se tak pomocí switche
     * @param site string, který určuje jakým směrem se index bude pohybovat
     * @param index index v ArrayListu od kterého budeme další index počítat
     * @return metoda vrací vypočítaný index
     */
    private int countIndex(String site, int index){
        switch (site){
            case "left":
                if (vyhodnoceni(index, naplneni(15,0,210)))
                    index --;
                break;
            case "right":
                if (vyhodnoceni(index, naplneni(15, 14,224)))
                    index ++;
                break;
            case "up":
                if (vyhodnoceni(index, naplneni(1,0,14)))
                    index = index -15;
                break;
            case "down":
                if (vyhodnoceni(index, naplneni(1,210,224)))
                    index = index +15;
                break;
            case "leftup":
                if (vyhodnoceni(index, naplneni(1,0,14,15,0,210)))
                    index = index - 16;
                break;
            case "leftdown":
                if (vyhodnoceni(index, naplneni(15,0,210,1,210,224)))
                    index = index + 14;
                break;
            case "rightup":
                if (vyhodnoceni(index, naplneni(15,14,224,1,0,14)))
                    index = index - 14;
                break;
            case "rightdown":
                if (vyhodnoceni(index, naplneni(15,14,224,1,210,224)))
                    index = index + 16;
                break;
        }
        return index;
    }

    /**
     * Metoda slouží k přístupu k privátní proměnné index této třídy
     * @return vrací proměnnou index
     */
    public int getIndex(){
        return this.index;
    }

    /**
     * Metoda slouží k přístupu k privátní proměnné row této třídy
     * @return vrací proměnnou row
     */
    public int getRow(){
        return this.row;
    }
}

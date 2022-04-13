package AI;

import javax.swing.*;
import java.util.ArrayList;

public class CheckAI {
    private int row = 0;
    private int index;
    public CheckAI check(int index, ArrayList<JButton> buttons, String znak, String site){
        this.index = index;
        this.index = countIndex(site);
        if (this.index >= 0 && this.index <= 224) {
            if (buttons.get(this.index).getText().equals(znak)) {
                row++;
                check(this.index, buttons, znak, site);
            }
            else if (buttons.get(this.index).getText().equals("O")) {
                int prom = this.index;
                switch (site) {
                    case "left":
                        prom = prom + (prom + 2);
                        break;
                    case "right":
                        prom = prom - (this.row + 2);
                        break;
                    case "up":
                        prom = prom + (this.row + 2) * 15;
                        break;
                    case "down":
                        prom = prom - (this.row + 2) * 15;
                        break;
                    case "leftup":
                        prom = prom + (this.row + 2) * 16;
                        break;
                    case "leftdown":
                        prom = prom - (this.row + 2) * 14;
                        break;
                    case "rightup":
                        prom = prom + (this.row + 2) * 14;
                        break;
                    case "rightdown":
                        prom = prom - (this.row + 2) * 16;
                        break;
                }
                if (buttons.get(this.index).getText().equals("O"))
                    this.row = 0;

                else if (buttons.get(prom).getText().equals("X"))
                    this.row = 0;

            }
            /*else if (buttons.get(this.index).getText().equals("")){
                int prom = countIndex(site);
                if (buttons.get(prom).getText().equals(znak)){
                    this.row ++;
                    this.index = prom;
                }
                int prom2 = countIndex(site);
                if (buttons.get(prom2).getText().equals(znak)){
                    this.row ++;
                    this.index = prom;
                }

            }*/
        }
        return this;
    }
    private int countIndex(String site){
        int index = this.index;
        switch (site){
            case "left": index --;
                break;
            case "right": index ++;
                break;
            case "up": index = index -15;
                break;
            case "down": index = index +15;
                break;
            case "leftup": index = index - 16;
                break;
            case "leftdown": index = index + 14;
                break;
            case "rightup": index = index - 14;
                break;
            case "rightdown": index = index + 16;
                break;
        }
        return index;
    }

    public int getIndex(){
        return this.index;
    }

    public int getRow(){
        return this.row;
    }
}

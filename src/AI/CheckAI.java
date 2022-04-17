package AI;

import javax.swing.*;
import java.util.ArrayList;

public class CheckAI {
    private int row = 0;
    private int index;
    private String znak1;
    private String znak2;
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
        if (this.index >= 0 && this.index <= 224) {
            if (buttons.get(this.index).getText().equals(znak1)) {
                row++;
                check(this.index, buttons, znak1, site);
            }
            else if (buttons.get(this.index).getText().equals(znak2)) {
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
        if (this.index > 224 || this.index < 0)
            this.row = 0;
        return this;
    }
    private int countIndex(String site, int number){
        int index = number;
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

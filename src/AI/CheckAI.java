package AI;

import javax.swing.*;
import java.util.ArrayList;

public class CheckAI {
    private int row = 0;
    private String site;
    private int index;
    public CheckAI check(int index, ArrayList<JButton> buttons, String znak, String site){
        this.site = site;
        this.index = index;
        switch (site){
            case "left": this.index --;
            break;
            case "right": this.index ++;
            break;
            case "up": this.index = this.index -15;
            break;
            case "down": this.index = this.index +15;
            break;
            case "leftup": this.index = this.index - 16;
                break;
            case "leftdown": this.index = this.index + 14;
                break;
            case "rightup": this.index = this.index - 14;
                break;
            case "rightdown": this.index = this.index + 16;
                break;
        }
        if (buttons.get(this.index).getText().equals(znak)){
            row ++;
            check(this.index, buttons,znak,site);
            //System.out.println(index);
        }
        return this;
    }

    public int getIndex(){
        return this.index;
    }

    public String getSite(){
        return this.site;
    }

    public int getRow(){
        return this.row;
    }
}

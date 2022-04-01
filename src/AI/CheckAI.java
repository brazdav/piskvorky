package AI;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class CheckAI {
    private int row = 0;
    private String site;
    public CheckAI check(int index, ArrayList<JButton> buttons, String znak, String site){
        this.site = site;
        switch (site){
            case "left": index --;
            break;
            case "right": index ++;
            break;
            case "up": index = index -15;
            break;
            case "down": index = index +15;
            break;
        }
        if (buttons.get(index).getText().equals(znak)){
            row ++;
            check(index, buttons,znak,site);
            //System.out.println(index);
        }
        //System.out.println("row: " + row + " site: " + site);
        return this;
    }

    public String getSite(){
        return this.site;
    }

    public int getRow(){
        return this.row;
    }
}

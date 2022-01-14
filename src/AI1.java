import javax.swing.*;
import java.util.ArrayList;
import java.util.Random;


public class AI1 extends JButton implements FirstTurn {
    int pocet = 0;
    Random random = new Random();
    public void obrana (String znak, int poradi, ArrayList<JButton> buttons, String strana){
        pocet ++;
        System.out.println(poradi);
        Vykresleni obj = new Vykresleni();

        if (pocet == 2){
            switch (strana){
                case "leva": poradi--;
                obj.vykresleni(buttons.get(poradi));
            }

        }
        else {
            obj.vykresleni(buttons.get(random.nextInt(225)));
        }
    }
}

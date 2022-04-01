package Rozhrani;

import javax.swing.*;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Rozhraní First turn kontroluje a nastvuje kdo začne hrát
 * Implementujeme toto rozhraní do tříd: Start, Client, Piskvorky, Vykresleni a MyButtons
 * Obsahuje 3 metody
 * @author Vojtěch Brázda
 * @version 1.0.0
 *
 */
public interface FirstTurn{
    AtomicBoolean player1_turn = new AtomicBoolean(true);
    AtomicBoolean server_turn = new AtomicBoolean(true);
    AtomicBoolean player_turn = new AtomicBoolean(true);
    Random random = new Random();
    JLabel textfield = new JLabel();

    /**
     * Meotda firstTurn nastavuje podle čísla které je přiřazeno k znaku, zda má daný znak začít hrát
     * Pokud je náhodně vygenerované číslo rovno 1 tak začne hrát X pokud je rovno 2 tak začne hrát 0
     * Nastavujeme hodnotu v labelu nad hracím polem
     */
    static void firstTurn(){

        if(random.nextInt(2)==1){
            player1_turn.set(true);
            textfield.setText("X turn");
        }
        else{
            player1_turn.set(false);
            textfield.setText("O turn");
        }
    }

    /**
     * Meotda firstTurn nastavuje podle čísla které je přiřazeno k znaku, zda má daný znak začít hrát
     * Pokud je náhodně vygenerované číslo rovno 1 tak začne hrát X pokud je rovno 2 tak začne hrát 0
     * Pracuje pouze s LAN
     * Nastavujeme hodnotu v labelu nad hracím polem
     */
    static void firstTurnLan(){
        if(random.nextInt(2)==1){
            server_turn.set(false);
        }
        else{
            server_turn.set(true);
        }
    }

    /**
     * Meotda firstTurn nastavuje podle čísla které je přiřazeno k znaku, zda má daný znak začít hrát
     * Pokud je náhodně vygenerované číslo rovno 1 tak začne hrát X pokud je rovno 2 tak začne hrát 0
     * Pracuje s AI
     * Nastavujeme hodnotu v labelu nad hracím polem
     */
    static void firstTurnAI(){
        if(random.nextInt(2)==1){
            player_turn.set(true);
        }
        else{
            player_turn.set(true);
        }
    }

}

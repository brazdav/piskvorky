import javax.swing.*;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

interface FirstTurn{
    AtomicBoolean player1_turn = new AtomicBoolean(true);//proměnná potřebná k rozpoznání hráče, který je momentálně na tahu
    Random random = new Random();//objekt typu random
    JLabel textfield = new JLabel();//objekt typu jlabel pro zobrazování hráče, který právě hraje

    static void firstTurn(){//metoda po dvou sekundách z objektu random získá buď 1 nebo 2 a podle toho se rozhodne, kdo bude hrát první
        try {
            Thread.sleep(2000);//stopnutí vlákna na 2 sekundy
        }catch (InterruptedException e){//ošetření
            e.printStackTrace();
        }
        if(random.nextInt(2)==1){//podmínka vyhodnocuje, jaké ze dvou čísel bylo vygenerováno
            player1_turn.set(true);//nastavení proměnné
            textfield.setText("X turn");//nastavení textu na objektu
        }
        else{
            player1_turn.set(false);//nastavení proměnné
            textfield.setText("O turn");//nastavení textu na objektu
        }
    }

}

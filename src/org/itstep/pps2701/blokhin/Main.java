package org.itstep.pps2701.blokhin;

import javax.swing.*;

/*
    Структурные паттерны - паттерн Компоновщик (Composite)
    Структура данных для хранения информации о государствах и их административных частях и городах

 */

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {
            new MainFrame("Управление административными единицами");
        });
    }
}

package org.itstep.pps2701.blokhin;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(()-> {
            new MainFrame("Управление административными единицами");
        });
    }
}

package sv.edu.unab.Main;

import sv.edu.unab.Formularios.FrmStreamEjemplo;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frm=new JFrame("Estudiantes de la escuela Jerez");
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setContentPane(new FrmStreamEjemplo().pnlRoot);
        frm.setLocationRelativeTo(null);
        frm.pack();
        frm.setVisible(true);

    }
}

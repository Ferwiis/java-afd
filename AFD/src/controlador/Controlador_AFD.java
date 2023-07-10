package controlador;

import static javax.swing.JFrame.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.text.*;
import modelo.*;
import vista.*;

public class Controlador_AFD implements ActionListener {

    private final AFD automata;
    private final VAFD vista;

    public Controlador_AFD(AFD automata, VAFD vista) {
        this.automata = automata;
        this.vista = vista;
        registrarOyentes();
    }

    private void registrarOyentes() {
        this.vista.jTbBorrar.addActionListener(this);
        this.vista.jbValidarReal.addActionListener(this);
        this.vista.jbValidarEntero.addActionListener(this);
        this.vista.jbValidarNotCient.addActionListener(this);
        this.vista.jbValidarEmail.addActionListener(this);
    }

    public void iniciarVentanaPrincipal() {
        vista.setResizable(false);
        vista.setDefaultCloseOperation(EXIT_ON_CLOSE);
        vista.setLocationRelativeTo(null);
        vista.jCbReal.setEnabled(false);
        vista.jCbEntero.setEnabled(false);
        vista.jCbNotCient.setEnabled(false);
        vista.jCbEmail.setEnabled(false);
        vista.jbValidarReal.setFocusPainted(false);
        vista.jbValidarEntero.setFocusPainted(false);
        vista.jbValidarNotCient.setFocusPainted(false);
        vista.jbValidarEmail.setFocusPainted(false);
        vista.jTbBorrar.setFocusPainted(false);
        vista.jTxPnInformacion.setText("\n\n\n                             Sin información");
        ajustarTextoDeInformacion(18, StyleConstants.ALIGN_CENTER);
        vista.setVisible(true);
    }

    private void ajustarTextoDeInformacion(int tamaño, int estilo) {
        SimpleAttributeSet aSet = new SimpleAttributeSet();
        StyleConstants.setFontFamily(aSet, "Segoe UI Symbol");
        StyleConstants.setFontSize(aSet, tamaño);
        StyleConstants.setAlignment(aSet, estilo);
        StyledDocument doc = vista.jTxPnInformacion.getStyledDocument();
        doc.setCharacterAttributes(0, doc.getLength(), aSet, true);
        vista.jTxPnInformacion.setParagraphAttributes(aSet, true);
    }

    public void ejecutarAutomata(JCheckBox check, JTextField campo_texto, String msg, int tipoCadena) {
        automata.setCadenaIngresada(campo_texto.getText());
        if (automata.verificarCadena(tipoCadena) == true) {
            JOptionPane.showMessageDialog(vista, "¡La cadena ingresada " + msg + "!");
            if (automata.getTipoCadenaError() == tipoCadena) {
                vista.jTxPnInformacion.setText("\n\n\nSin información");
                ajustarTextoDeInformacion(18, StyleConstants.ALIGN_CENTER);
            }
            check.setSelected(true);
        } else {
            automata.generarInformeError(tipoCadena);
            vista.jTxPnInformacion.setText(automata.getInformeError());
            ajustarTextoDeInformacion(12, StyleConstants.ALIGN_LEFT);
            JOptionPane.showMessageDialog(vista, "¡La cadena ingresada NO " + msg + "!");
            if (check.isSelected()) {
                check.setSelected(false);
            }
        }
        automata.setCadenaIngresada(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(vista.jTbBorrar)) {
            vista.txReal.setText("");
            vista.txEntero.setText("");
            vista.txNotCient.setText("");
            vista.txEmail.setText("");
            vista.jCbReal.setSelected(false);
            vista.jCbEntero.setSelected(false);
            vista.jCbNotCient.setSelected(false);
            vista.jCbEmail.setSelected(false);
            vista.jTbBorrar.setSelected(false);
            vista.jTxPnInformacion.setText("\n\n\nSin información");
            ajustarTextoDeInformacion(18, StyleConstants.ALIGN_CENTER);
            JOptionPane.showMessageDialog(vista, "¡TODOS LOS CAMPOS BORRADOS!");
        }
        
        if (e.getSource().equals(vista.jbValidarEntero)) {
            ejecutarAutomata(vista.jCbEntero, vista.txEntero, "ES ENTERA", 1);
        }
        if (e.getSource().equals(vista.jbValidarReal)) {
            ejecutarAutomata(vista.jCbReal, vista.txReal, "ES REAL", 2);
        }
        if (e.getSource().equals(vista.jbValidarNotCient)) {
            ejecutarAutomata(vista.jCbNotCient, vista.txNotCient, "ES DE NOTACIÓN CIENTÍFICA", 3);
        }
        if (e.getSource().equals(vista.jbValidarEmail)) {
            ejecutarAutomata(vista.jCbEmail, vista.txEmail, "ES EMAIL", 4);
        }
    }
}

package modelo;

import vista.*;
import controlador.*;

public class Main {

    public static void main(String args[]) {
        new Controlador_AFD(new AFD(), new VAFD()).iniciarVentanaPrincipal();
    }
}

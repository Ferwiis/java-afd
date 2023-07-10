package modelo;

public class AFD {

    private String estado;
    private final String[] nombresDeAutomatas;
    private char caracter;
    private boolean estadoAcepta;
    private boolean caracter_legible_automata;
    private int tipoCadenaError;
    private int indice;
    private String informe_error;
    private String cadena_ingresada;

    public AFD() {
        this.nombresDeAutomatas = new String[]{"ENTERO", "REAL", "NOTACIÓN CIENTÍFICA", "EMAIL"};
    }

    public String getCadenaIngresada() {
        return cadena_ingresada;
    }

    public void setCadenaIngresada(String cadena_ingresada) {
        this.cadena_ingresada = cadena_ingresada;
    }

    public String getInformeError() {
        return informe_error;
    }

    public int getTipoCadenaError() {
        return tipoCadenaError;
    }

    public void generarInformeError(int tipoCadena) {
        informe_error = "\n\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
        informe_error += "\nAutómata ";
        String sub_mensaje;
        if (caracter_legible_automata) {
            sub_mensaje = "La cadena presentada no se pudo terminar de leer, a causa de que no hay mas caracteres que leer luego del caracter \'" + caracter + "\' que sean aceptados por el estado actual \'" + estado + "\' del respectivo autómata";
            informe_error += nombresDeAutomatas[tipoCadena - 1] + ": " + sub_mensaje;
        } else {
            indice++;
            sub_mensaje = "La cadena presentada no se pudo terminar de leer, a causa de que el caracter (" + indice + ") \'" + caracter + "\' para el estado actual \'" + estado + "\', NO ES UN CARACTER PROCESABLE.";
            informe_error += nombresDeAutomatas[tipoCadena - 1] + ": " + sub_mensaje;
        }
        informe_error += "\n\n---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n";
        tipoCadenaError = tipoCadena;
    }

    public boolean verificarCadena(int tipoCadena) {
        estadoAcepta = false;
        caracter_legible_automata = false;
        boolean validar = estadoAcepta;
        switch (tipoCadena) {
            case 1:
                validar = automataEntero();
                break;
            case 2:
                validar = automataReal();
                break;
            case 3:
                validar = automataNotCient();
                break;
            case 4:
                validar = automataEmail();
                break;
        }
        return validar;
    }

    private boolean automataEntero() {
        estado = "A";
        for (indice = 0; indice < cadena_ingresada.length(); indice++) {
            caracter = cadena_ingresada.charAt(indice);
            switch (estado) {
                case "A":
                    if (caracter == '+') {
                        estado = "B";
                        caracter_legible_automata = true;
                    } else if (caracter == '-') {
                        estado = "C";
                        caracter_legible_automata = true;
                    } else if (Character.isDigit(caracter)) {
                        estado = "D";
                        caracter_legible_automata = true;
                        estadoAcepta = true;
                    } else {
                        return estadoAcepta;
                    }
                    break;
                case "B":
                    if (Character.isDigit(caracter)) {
                        estado = "D";
                        estadoAcepta = true;
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "C":
                    if (Character.isDigit(caracter)) {
                        estado = "D";
                        estadoAcepta = true;
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "D":
                    if (Character.isDigit(caracter)) {
                        estado = "D";
                    } else {
                        caracter_legible_automata = false;
                        estadoAcepta = false;
                        return estadoAcepta;
                    }
                    break;
            }
        }
        return estadoAcepta;
    }

    private boolean automataReal() {
        estado = "A";
        for (indice = 0; indice < cadena_ingresada.length(); indice++) {
            caracter = cadena_ingresada.charAt(indice);
            switch (estado) {
                case "A":
                    if (caracter == '+') {
                        estado = "B";
                        caracter_legible_automata = true;
                    } else if (caracter == '-') {
                        estado = "C";
                        caracter_legible_automata = true;
                    } else if (Character.isDigit(caracter)) {
                        estado = "D";
                        caracter_legible_automata = true;
                    } else {
                        return estadoAcepta;
                    }
                    break;
                case "B":
                    if (Character.isDigit(caracter)) {
                        estado = "D";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "C":
                    if (Character.isDigit(caracter)) {
                        estado = "D";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "D":
                    if (Character.isDigit(caracter)) {
                        estado = "D";
                    } else if (caracter == '.') {
                        estado = "E";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "E":
                    if (Character.isDigit(caracter)) {
                        estado = "F";
                        estadoAcepta = true;
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "F":
                    if (Character.isDigit(caracter)) {
                        estado = "F";
                    } else {
                        caracter_legible_automata = false;
                        estadoAcepta = false;
                        return estadoAcepta;
                    }
                    break;
            }
        }
        return estadoAcepta;
    }

    private boolean automataNotCient() {
        estado = "A";
        for (indice = 0; indice < cadena_ingresada.length(); indice++) {
            caracter = cadena_ingresada.charAt(indice);
            switch (estado) {
                case "A":
                    if (caracter == '+') {
                        estado = "B";
                        caracter_legible_automata = true;
                    } else if (caracter == '-') {
                        estado = "C";
                        caracter_legible_automata = true;
                    } else if (Character.isDigit(caracter)) {
                        estado = "D";
                        caracter_legible_automata = true;
                    } else {
                        return estadoAcepta;
                    }
                    break;
                case "B":
                    if (Character.isDigit(caracter)) {
                        estado = "D";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "C":
                    if (Character.isDigit(caracter)) {
                        estado = "D";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "D":
                    if (Character.isDigit(caracter)) {
                        estado = "D";
                    } else if (caracter == '.') {
                        estado = "F";
                    } else if (caracter == 'e') {
                        estado = "G";
                    } else if (caracter == 'E') {
                        estado = "H";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "F":
                    if (Character.isDigit(caracter)) {
                        estado = "I";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "G":
                    if (caracter == '+') {
                        estado = "J";
                    } else if (caracter == '-') {
                        estado = "K";
                    } else if (Character.isDigit(caracter)) {
                        estado = "L";
                        estadoAcepta = true;
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "H":
                    if (caracter == '+') {
                        estado = "J";
                    } else if (caracter == '-') {
                        estado = "K";
                    } else if (Character.isDigit(caracter)) {
                        estado = "L";
                        estadoAcepta = true;
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "I":
                    if (Character.isDigit(caracter)) {
                        estado = "I";
                    } else if (caracter == 'e') {
                        estado = "G";
                    } else if (caracter == 'E') {
                        estado = "H";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "J":
                    if (Character.isDigit(caracter)) {
                        estado = "L";
                        estadoAcepta = true;
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "K":
                    if (Character.isDigit(caracter)) {
                        estado = "L";
                        estadoAcepta = true;
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "L":
                    if (Character.isDigit(caracter)) {
                        estado = "L";
                    } else {
                        caracter_legible_automata = false;
                        estadoAcepta = false;
                        return estadoAcepta;
                    }
                    break;
            }
        }
        return estadoAcepta;
    }

    private boolean automataEmail() {
        estado = "A";
        for (indice = 0; indice < cadena_ingresada.length(); indice++) {
            caracter = cadena_ingresada.charAt(indice);
            switch (estado) {
                case "A":
                    if (Character.isLetter(caracter) && Character.isLowerCase(caracter)) {
                        estado = "B";
                        caracter_legible_automata = true;
                    } else {
                        return estadoAcepta;
                    }
                    break;
                case "B":
                    if (Character.isLetter(caracter) && Character.isLowerCase(caracter)) {
                        estado = "C";
                    } else if (Character.isDigit(caracter)) {
                        estado = "D";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "C":
                    if (Character.isLetter(caracter) && Character.isLowerCase(caracter)) {
                        estado = "E";
                    } else if (Character.isDigit(caracter)) {
                        estado = "F";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "D":
                    if (Character.isLetter(caracter) && Character.isLowerCase(caracter)) {
                        estado = "E";
                    } else if (Character.isDigit(caracter)) {
                        estado = "F";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "E":
                    if (Character.isLetter(caracter) && Character.isLowerCase(caracter)) {
                        estado = "G";
                    } else if (Character.isDigit(caracter)) {
                        estado = "H";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "F":
                    if (Character.isLetter(caracter) && Character.isLowerCase(caracter)) {
                        estado = "G";
                    } else if (Character.isDigit(caracter)) {
                        estado = "H";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "G":
                    if (Character.isLetter(caracter) && Character.isLowerCase(caracter)) {
                        estado = "I";
                    } else if (Character.isDigit(caracter)) {
                        estado = "J";
                    } else if (caracter == '.') {
                        estado = "K";
                    } else if (caracter == '_') {
                        estado = "L";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "H":
                    if (Character.isLetter(caracter) && Character.isLowerCase(caracter)) {
                        estado = "I";
                    } else if (Character.isDigit(caracter)) {
                        estado = "J";
                    } else if (caracter == '.') {
                        estado = "K";
                    } else if (caracter == '_') {
                        estado = "L";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "I":
                    if (Character.isLetter(caracter) && Character.isLowerCase(caracter)) {
                        estado = "I";
                    } else if (Character.isDigit(caracter)) {
                        estado = "J";
                    } else if (caracter == '.') {
                        estado = "K";
                    } else if (caracter == '_') {
                        estado = "L";
                    } else if (caracter == '@') {
                        estado = "M";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "J":
                    if (Character.isLetter(caracter) && Character.isLowerCase(caracter)) {
                        estado = "I";
                    } else if (Character.isDigit(caracter)) {
                        estado = "J";
                    } else if (caracter == '.') {
                        estado = "K";
                    } else if (caracter == '_') {
                        estado = "L";
                    } else if (caracter == '@') {
                        estado = "M";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "K":
                    if (Character.isLetter(caracter) && Character.isLowerCase(caracter)) {
                        estado = "I";
                    } else if (Character.isDigit(caracter)) {
                        estado = "J";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "L":
                    if (Character.isLetter(caracter) && Character.isLowerCase(caracter)) {
                        estado = "I";
                    } else if (Character.isDigit(caracter)) {
                        estado = "J";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "M":
                    if (Character.isLetter(caracter) && Character.isLowerCase(caracter)) {
                        estado = "N";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "N":
                    if (Character.isLetter(caracter) && Character.isLowerCase(caracter)) {
                        estado = "O";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "O":
                    if (Character.isLetter(caracter) && Character.isLowerCase(caracter)) {
                        estado = "P";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "P":
                    if (Character.isLetter(caracter) && Character.isLowerCase(caracter)) {
                        estado = "Q";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "Q":
                    if (Character.isLetter(caracter) && Character.isLowerCase(caracter)) {
                        estado = "R";
                    } else if (caracter == '.') {
                        estado = "S";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "R":
                    if (Character.isLetter(caracter) && Character.isLowerCase(caracter)) {
                        estado = "T";
                    } else if (caracter == '.') {
                        estado = "S";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "S":
                    if (Character.isLetter(caracter) && Character.isLowerCase(caracter)) {
                        estado = "U";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "T":
                    if (Character.isLetter(caracter) && Character.isLowerCase(caracter)) {
                        estado = "V";
                    } else if (caracter == '.') {
                        estado = "S";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "U":
                    if (Character.isLetter(caracter) && Character.isLowerCase(caracter)) {
                        estado = "W";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "V":
                    if (Character.isLetter(caracter) && Character.isLowerCase(caracter)) {
                        estado = "X";
                    } else if (caracter == '.') {
                        estado = "S";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "W":
                    if (Character.isLetter(caracter) && Character.isLowerCase(caracter)) {
                        estado = "Y";
                        estadoAcepta = true;
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "X":
                    if (caracter == '.') {
                        estado = "S";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "Y":
                    if (Character.isLetter(caracter) && Character.isLowerCase(caracter)) {
                        estado = "Z";
                    } else if (caracter == '.') {
                        estado = "A1";
                        estadoAcepta = false;
                    } else {
                        caracter_legible_automata = false;
                        estadoAcepta = false;
                        return estadoAcepta;
                    }
                    break;
                case "Z":
                    if (Character.isLetter(caracter) && Character.isLowerCase(caracter)) {
                        estado = "B1";
                    } else if (caracter == '.') {
                        estado = "A1";
                        estadoAcepta = false;
                    } else {
                        caracter_legible_automata = false;
                        estadoAcepta = false;
                        return estadoAcepta;
                    }
                    break;
                case "A1":
                    if (Character.isLetter(caracter) && Character.isLowerCase(caracter)) {
                        estado = "C1";
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "B1":
                    if (caracter == '.') {
                        estado = "A1";
                        estadoAcepta = false;
                    } else {
                        caracter_legible_automata = false;
                        estadoAcepta = false;
                        return estadoAcepta;
                    }
                    break;
                case "C1":
                    if (Character.isLetter(caracter) && Character.isLowerCase(caracter)) {
                        estado = "D1";
                        estadoAcepta = true;
                    } else {
                        caracter_legible_automata = false;
                        return estadoAcepta;
                    }
                    break;
                case "D1":
                    caracter_legible_automata = false;
                    estadoAcepta = false;
                    return estadoAcepta;
            }
        }
        return estadoAcepta;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vueltaatras;

/**
 *
 * @author Usuario
 */
import java.util.Scanner;

public class VueltaAtras {

    int n;
    int[] solucionFinal;

    public VueltaAtras(int n) {
        this.n = n;
        this.solucionFinal = new int[n * n];
    }

    public void backTraking() {
        for (int i = 0; i < this.solucionFinal.length; i++) {
            this.solucionFinal[i] = n * n + 1;
        }
        int[] solucion = new int[n * n];
        backTraking(solucion, 0);
        imprimirMatriz();
    }

    /**
     * Metodo Princial en el cual se aplica el algoritmo de Backtraking para
     * encontrar las combinaciones
     *
     * @param solucion Arreglo con la mejor Solucion Actual
     * @param etapa El nivel de profundidad en el que se encuentra el algoritmo
     */
    private void backTraking(int solucion[], int etapa) {
        if (etapa > (n * n) - 1) {
            return; // No se encontro Solucion
        }
        int numero = 1;
        do {
            solucion[etapa] = numero; // Se selecciona una nueva opcion
            if (validarPosicionFila(solucion, etapa) && validaPosicionColumna(solucion, etapa) && validarPosicionDiagonal(solucion, etapa)) {
                if (etapa == solucion.length - 1) {
                    actualizarSolucion(solucion); // Se actualiza la solucion si la nueva es mejor
                    validarFinal(etapa);
                } else {
                    backTraking(solucion, etapa + 1); // Se realiza la llamada recursiva para bajar un nivel mas en el arbol
                }
            }
            numero++;
        } while (solucion[etapa] < (n + n));

    }

    /**
     * Permite Validar que en ninguna columna se repitan numeros
     *
     * @param solucion Mejor Solucion Actual
     * @param k Posicion del valor que se esta validando
     * @return Verdadero si el valor es valido para la columna
     */
    private boolean validaPosicionColumna(int[] solucion, int k) {
        for (int i = 0; i < k; i++) {
            for (int j = i + n; j < solucion.length; j = j + n) {
                if (solucion[i] == solucion[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Permite Validar que en ninguna fila se repitan numeros
     *
     * @param solucion Mejor Solucion Actual
     * @param k Posicion del valor que se esta validando
     * @return Verdadero si el valor es valido para la fila
     */
    private boolean validarPosicionFila(int[] solucion, int k) {
        for (int i = 0; i < solucion.length; i = i + n) {
            if ((i + n) > k) {
                for (int j = i; j < k; j++) {
                    if (solucion[k] == solucion[j]) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Permite Validar que en ninguna diagonal se repitan numeros
     *
     * @param solucion Mejor Solucion Actual
     * @param k Posicion que se esta validando
     * @return Verdadero si el valor es valido para la diagonal
     */
    private boolean validarPosicionDiagonal(int[] solucion, int k) {
        int filaI = 0, colJ = 0, filaJ = 0, colI = k;
        for (int i = n; i < solucion.length; i = i + n) { // Se obtiene la fila en el array de K
            if (k >= i) {
                filaI++; // Fila donde se encuentra la varable K en el arreglo
            } else {
                break;
            }
        }
        if (k >= n) {
            colI = k % n; // Se obtienen la Columna en el array de K
        }
        for (int i = 0; i < k; i++) {
            if (Math.abs(filaI - filaJ) == Math.abs(colI - colJ) && solucion[k] == solucion[i]) {
                return false;
            }
            colJ++;
            if (i >= n - 1 && (i + 1) % n == 0) {
                filaJ++;
                colJ = 0;
            }
        }
        return true;
    }

    /**
     * Permite realizar la actualizacion del arraglo solucion
     *
     * @param solucion Array con la mejor solucion actual
     */
    private void actualizarSolucion(int solucion[]) {
        int sumaInicial = 0, sumaFinal = 0;
        for (int i = 0; i < solucion.length; i++) {
            sumaInicial += solucion[i];
            sumaFinal += this.solucionFinal[i];
        }
        /*for (int i = 0; i < solucion.length; i++) {
         System.err.print("[" + solucion[i] + "]");
         }
         System.err.println("");*/
        if (sumaInicial < sumaFinal) {
            this.solucionFinal = (int[]) solucion.clone();
//            for (int i = 0; i < solucionFinal.length; i++) {
//                System.out.print("[" + solucionFinal[i] + "]");
//            }
//            System.out.println("");
        }
    }

    private void imprimirMatriz() {
        for (int i = 0; i < this.solucionFinal.length; i = i + n) {
            for (int j = i; j < i + n; j++) {
                System.out.print("[" + this.solucionFinal[j] + "]");
            }
            System.out.println("");
        }
    }

    private boolean validarFinal(int etapa) {
        for (int i = 0; i < solucionFinal.length; i++) {
            if (solucionFinal[i] > etapa) {
                return false;
            }
        }
        return true;
    }

//    public static void main(String[] args) {
//        System.out.println("Ingrese la cantidad n de la matriz");
//        Scanner s = new Scanner(System.in);
//        int n = s.nextInt();
//        VueltaAtras sumaMinima = new VueltaAtras(n);
//        sumaMinima.backTraking();
//
//    }
}

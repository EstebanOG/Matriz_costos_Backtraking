/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vueltaatras;

import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class SumMin {

    private int[][] matriz;
    private int[] solucion_temp;
    private int[] solucion;
    private int costo,costo_temp, numMax = 0, tamMatriz;
    
    //Constructor
    public SumMin() {
        ingresarDatos();
    }
    
    //Método imprimir matriz de costes
    public void imprimir() {
        System.out.println("Matriz de costes:");
        System.out.print("X ");
        for (int i = 0; i< matriz.length;i++)
            System.out.print(i+1+" ");
        System.out.println("");
        for (int i = 0; i < matriz.length; i++) {
            System.out.print((char) (97 + i)+ " ");
            for (int j = 0; j < matriz.length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("");

    }
    
    //Método calcular costo
    public void calcular_costo() {
        costo_temp = 0;
        //Se suman los valores y se guardan en costo_temp
        for (int i = 0; i < solucion_temp.length; i++) {
            costo_temp += matriz[i][solucion_temp[i]];
        }
        
        /*En caso de costo_temp ser menor que costo, se reemplaza el valor del
        costo y se copia el arreglo solucion_temp en el arreglo solucion */
        if (costo_temp < costo) {
            this.costo = costo_temp;
            this.solucion = (int[]) solucion_temp.clone();
        }
    }
    
    //Algoritmo Vuelta atrás
    public void vueltaAtras(int etapa) {
        for (int i = 0; i < matriz.length; i++) {
            if (aceptable(i, etapa)) {
                this.solucion_temp[i] = etapa;
                if (etapa == (matriz.length - 1)) {
                    calcular_costo();
                } else {
                    vueltaAtras(etapa + 1);
                }
                solucion_temp[i] = (numMax + 1);
            }

        }
    }
    
    //Se valida si es una posición válida
    public boolean aceptable(int i, int etapa) {
        /*Se valida que no ninguna persona tenga esa tarea asignada, es decir
        se validan las filas*/
        for (int j = 0; j < solucion_temp.length; j++) {
            if (solucion_temp[j] == etapa) {
                return false;
            }
        }
        /*Se valida que la tarea aun no este asignana a ninguna persona, es decir
        se validan las columnas*/
        if (solucion_temp[i] != (numMax + 1)) {
            return false;
        }
        
        return true;
    }
    
    /*Metódo que llama al método imprimir, inicia el backtraking e imprime el
    resultado*/
    public void resolver() {
        imprimir();
        vueltaAtras(0);
        System.out.println("Asignación de tareas con clientes:");
        for (int i = 0; i < solucion.length; i++) {
            System.out.println((char) (97 + i) + " -> " + (solucion[i] + 1));
        }
        System.out.println("Coste total:" + costo);

    }
    //Método de captura los datos de la matriz de costes.
    public void ingresarDatos() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Ingrese el número de personas y tareas: ");
        this.tamMatriz = reader.nextInt();

        this.matriz = new int[tamMatriz][tamMatriz];
        this.solucion_temp = new int[tamMatriz];
        this.solucion = new int[tamMatriz];

        for (int i = 0; i < tamMatriz; i++) {
            for (int j = 0; j < tamMatriz; j++) {
                System.out.print("Ingrese la tarifa de la persona " + (char) (97 + i) + " para la tarea " + (j + 1) + ": ");
                matriz[i][j] = reader.nextInt();

                if (i == 0 && j == 0) {
                    this.numMax = matriz[i][j];
                } else {
                    if (matriz[i][j] > numMax) {
                        this.numMax = matriz[i][j];
                    }
                }
            }
        }
        System.out.println("");
        this.costo = (numMax * tamMatriz) + 1;
        for (int i = 0; i < solucion_temp.length; i++) {
            solucion_temp[i] = numMax + 1;
        }
    }
    
    public static void main(String[] args) {
        SumMin sumMin = new SumMin();
        sumMin.resolver();
    }
}

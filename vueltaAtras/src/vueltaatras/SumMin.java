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
    private int costo, dimX, dimY, costo_temp, numMax = 0;

    ;

    public SumMin() {
        ingresarDatos();
    }

    public void imprimir() {
        System.out.println("Matriz de costes:");
        for (int i = 0; i < dimX; i++) {
            for (int j = 0; j < dimY; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("");

//        for (int i = 0; i < solucion_temp.length; i++) {
//            System.out.print(solucion_temp[i] + " ");
//        }
//        System.out.println("");
    }

    public void calcular_costo() {
        costo_temp = 0;
        for (int i = 0; i < solucion_temp.length; i++) {
            costo_temp += matriz[i][solucion_temp[i]];
        }
//        System.out.println("Costo =" + costo_temp);

        if (costo_temp < costo) {
            this.costo = costo_temp;
            this.solucion = (int[]) solucion_temp.clone();
        }
    }

    public void vueltaAtras(int etapa) {
        for (int i = 0; i < dimY; i++) {
            if (aceptable(i, etapa)) {
                this.solucion_temp[i] = etapa;
                if (etapa == (dimY - 1)) {
                    //imprimir();
                    calcular_costo();
                } else {
                    vueltaAtras(etapa + 1);
                }
                solucion_temp[i] = (numMax + 1);
                //matriz[i][etapa]=0;
            }

        }
    }

    public boolean aceptable(int i, int etapa) {
        //Validar filas
        for (int j = 0; j < dimY; j++) {
            if (solucion_temp[j] == etapa) {
                return false;
            }
        }
        //Validar columnas
        if (solucion_temp[i] != (numMax + 1)) {
            return false;
        }

        return true;
    }

    public void resolver() {
        imprimir();
        vueltaAtras(0);
        System.out.println("Asignación de tareas con clientes:");
        for (int i = 0; i < solucion.length; i++) {
            System.out.println((char) (97 + i) + " -> " + (solucion[i] + 1));
        }
        System.out.println("Coste total:" + costo);

    }

    public void ingresarDatos() {
        Scanner reader = new Scanner(System.in);
        System.out.println("Ingrese el número de personas");
        this.dimX = reader.nextInt();

        System.out.println("Ingrese el número de tareas");
        this.dimY = reader.nextInt();

        this.matriz = new int[dimX][dimY];
        this.solucion_temp = new int[dimY];
        this.solucion = new int[dimY];

        for (int i = 0; i < dimX; i++) {
            for (int j = 0; j < dimY; j++) {
                System.out.print("Ingrese la tarifa de la persona " + (char) (97 + i) + " para la tarea " + (j + 1) + ":");
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
        
        this.costo = (numMax*dimY)+1;
        for (int i = 0; i < solucion_temp.length; i++) {
            solucion_temp[i] = numMax + 1;
        }
    }

    public static void main(String[] args) {
        SumMin sumMin = new SumMin();
        sumMin.resolver();
    }
}

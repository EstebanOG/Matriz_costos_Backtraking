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
    private int costo = 9999;
    private int costo_temp;

    public SumMin(int tamMatriz) {
        this.matriz = new int[tamMatriz][tamMatriz];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matriz[i][j] = (int) (Math.random() * 20 + 1);;
            }
        }
        this.solucion_temp = new int[tamMatriz];
        this.solucion = new int[tamMatriz];
        for (int i = 0; i < solucion_temp.length; i++) {
            solucion_temp[i] = 99;
        }
        
    }

    public void imprimir() {
        System.out.println("");
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("");

        for (int i = 0; i < solucion_temp.length; i++) {
            System.out.print(solucion_temp[i] + " ");
        }
        System.out.println("");
    }
    
    public void calcular_costo(){
        costo_temp = 0;
        for(int i = 0; i<solucion_temp.length;i++){
            costo_temp += matriz[i][solucion_temp[i]];
        }
        System.out.println("Costo ="+costo_temp);
        
        if(costo_temp<costo){
            this.costo = costo_temp;
            this.solucion = (int []) solucion_temp.clone();
        }
    }

    public void vueltaAtras(int etapa) {
        for (int i = 0; i < matriz.length; i++) {
            if (aceptable(i, etapa)) {
                this.solucion_temp[i] = etapa;
                if (etapa == (matriz.length - 1)) {
                    imprimir();
                    calcular_costo();
                } else {
                    vueltaAtras(etapa + 1);
                }
                solucion_temp[i] = 99;
                //matriz[i][etapa]=0;
            }

        }
    }

    public boolean aceptable(int i, int etapa) {
        //Validar filas
        for (int j = 0; j < solucion_temp.length; j++) {
            if (solucion_temp[j] == etapa) {
                return false;
            }
        }
        //Validar columnas
        if (solucion_temp[i] != 99) {
            return false;
        }

        return true;
    }

    public void resolver() {
        imprimir();
        vueltaAtras(0);
        System.out.println("Mejor costo:"+costo);
        for(int i = 0;i<solucion.length;i++)
            System.out.print(solucion[i]+" ");
    }

    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.println("Ingrese la dimensión de la matriz cuadrada");
        int dim = reader.nextInt();
        SumMin sumMin = new SumMin(dim);
        sumMin.resolver();
    }
}

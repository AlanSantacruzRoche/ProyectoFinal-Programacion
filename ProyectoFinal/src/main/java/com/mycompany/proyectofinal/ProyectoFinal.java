/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectofinal;

/**
 *
 * @author alans
 */
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProyectoFinal {

    public static void main(String[] args) {
        Object[] botones = {"crear nueva", "usar vieja"};
        boolean work = false;
        boolean modo;
        ArrayList<String> palabras = new ArrayList();
        String archivo;
            int figura = JOptionPane.showOptionDialog(null, "Desea crear nueva sopa o usar una vieja?","opcion",JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,null, botones, botones[0]);
            switch(figura){
                case 0:
                    do{
                        archivo = JOptionPane.showInputDialog("inserte el nombre del archivo que desea crear");
                        work = GeneradorSopa.crearArchivo(archivo);
                    }while(!work);
                    work = false;
                    do{
                        String archivoPalabras = JOptionPane.showInputDialog("inserte el nombre del archivo donde se encuentran las palabras");
                        palabras = GeneradorSopa.leerPalabras(archivoPalabras);
                    }while(palabras.get(0) == null);
                    modo = GeneradorSopa.revisarPalabras(palabras);
                    GeneradorSopa.crearSopa(palabras, archivo, modo);
                    break;
                case 1:
                    do{
                        archivo = JOptionPane.showInputDialog("inserte el nombre del archivo");
                        work = GeneradorSopa.cargarCuadricula(archivo);
                    }while (!work);
                    break;
                default:JOptionPane.showMessageDialog(null, "ha ocurrido un problema", "error", JOptionPane.INFORMATION_MESSAGE);
            }
    }
}

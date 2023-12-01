/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectofinal;

/**
 *
 * @author alans
 */
import java.awt.Frame;
import java.awt.TextArea;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class GeneradorSopa {
    public static boolean cargarCuadricula(String archivo){
        String[][] datos;
        File file = new File("folder/" + archivo+".txt");
        try (Scanner scanner = new Scanner(file)) {
            ArrayList<String[]> filas = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                String[] caracteres = linea.split("");
                filas.add(caracteres);
            }

            datos = new String[filas.size()][];
            filas.toArray(datos);
            
            Frame f = new Frame();
            TextArea cuadricula = new TextArea();
            String linea = "";
            for(int i = 0; i <15; i++){
                for(int j = 0; j < 15; j++){
                    linea = linea + datos[i][j] + "\t";
                }
                linea = linea + "\n";
                cuadricula.append(linea);
                linea = "";
            }
            cuadricula.setBounds(10, 30, 800, 300);
            f.add(cuadricula);
            f.setSize(800, 400);    
            f.setLayout(null);    
            f.setVisible(true);
            return true;
        } catch (FileNotFoundException e){
            JOptionPane.showMessageDialog(null, "el archivo seleccionado no existe", "error", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
    }
    
    public static boolean crearArchivo(String archivo){
        try{
            File file = new File("folder/"+archivo+".txt");
            if(file.createNewFile()){
                JOptionPane.showMessageDialog(null, "se creo el archivo", "error", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }else{
                JOptionPane.showMessageDialog(null, "el archivo seleccionado ya existe", "error", JOptionPane.INFORMATION_MESSAGE);
                return false;
            }
        }catch(IOException e){
        JOptionPane.showMessageDialog(null, "ha ocurrido un problema", "error", JOptionPane.INFORMATION_MESSAGE);
        return false;
        }
    }
    
    public static ArrayList<String> leerPalabras(String archivo){
        File file = new File("folder/" + archivo+".txt");
        try (Scanner scanner = new Scanner(file)) {
            ArrayList<String> filas = new ArrayList<>();
            while (scanner.hasNextLine()) {
                String linea = scanner.nextLine();
                filas.add(linea);
            }
            for(int i = 0; i < filas.size(); i++){
                System.out.println(filas.get(i));
            }
            return filas;
        }catch(IOException e){
            JOptionPane.showMessageDialog(null, "el archivo elegido no existe", "error", JOptionPane.INFORMATION_MESSAGE);
        return null;
        }
    }
    
    public static boolean revisarPalabras(ArrayList<String> palabras){
        String linea = palabras.get(0);
        String[] caracteres = linea.split("");
        return !(caracteres[1].equals("0") || caracteres[0].equals("1") || caracteres[0].equals("2") || caracteres[0].equals("3") || caracteres[0].equals("4") || caracteres[0].equals("5") || caracteres[0].equals("6") || caracteres[0].equals("7")|| caracteres[0].equals("8") || caracteres[0].equals("9"));
    }
    
    public static void crearSopa(ArrayList<String> palabras, String archivo,boolean modo){
        char[][] sopa = new char[15][15];
        Random random = new Random();

        if (modo) {
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    sopa[i][j] = (char) ('a' + random.nextInt(26));
                }
            }
        } else {
            for (int i = 0; i < 15; i++) {
                for (int j = 0; j < 15; j++) {
                    sopa[i][j] = (char) ('0' + random.nextInt(10));
                }
            }
        }

        for (String palabra : palabras) {
            int direccion = random.nextInt(3);
            int fila, columna;

            switch (direccion) {
                case 0:  // Horizontal
                    if(palabra.length() > 10){
                        JOptionPane.showMessageDialog(null, "la palabra " + palabra + " tiene mas de 10 palabras", "error", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    fila = random.nextInt(sopa.length);
                    columna = random.nextInt(sopa.length - palabra.length() + 1) + palabra.length() - 1;
                    for (int i = 0; i < palabra.length(); i++) {
                        sopa[fila][columna - i] = palabra.charAt(i);
                    }
                    break;

                case 1:  // Vertical
                    if(palabra.length() > 10){
                        JOptionPane.showMessageDialog(null, "la palabra " + palabra + " tiene mas de 10 palabras", "error", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    fila = random.nextInt(sopa.length - palabra.length() + 1);
                    columna = random.nextInt(sopa.length);
                    for (int i = 0; i < palabra.length(); i++) {
                       sopa[fila + i][columna] = palabra.charAt(i);
                    }       
                    break;

                default:  // Diagonal
                    if(palabra.length() > 10){
                        JOptionPane.showMessageDialog(null, "la palabra " + palabra + " tiene mas de 10 palabras", "error", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    fila = random.nextInt(sopa.length - palabra.length() + 1) + palabra.length() - 1;
                    columna = random.nextInt(sopa.length - palabra.length() + 1) + palabra.length() - 1;
                    for (int i = 0; i < palabra.length(); i++) {
                        sopa[fila - i][columna - i] = palabra.charAt(i);
                    }       
                    break;
            }
        }
        
        File file = new File("folder/" + archivo+".txt");
        try (FileWriter fw = new FileWriter(file)){
            for(int i = 0; i < 15; i++){
                for(int j = 0; j<15; j++){
                    fw.write(sopa[i][j]);
                }
                fw.write("\n");
            }
        }catch (IOException e){
            
        }
        
        
        Frame f = new Frame();
        TextArea cuadricula = new TextArea();
        String linea = "";
        for(int i = 0; i <15; i++){
            for(int j = 0; j < 15; j++){
                linea = linea + sopa[i][j] + "\t";
            }
            linea = linea + "\n";
            cuadricula.append(linea);
            linea = "";
        }
        cuadricula.setBounds(10, 30, 800, 300);
        String[][] sopas = new String[15][15];
        for(int i = 0; i<15; i++){
            for(int j = 0; j<15; j++){
                sopas[i][j] =""+sopa[i][j];
            }
        }
        String coordenadas;
        for(String palabra : palabras){
            coordenadas = mostrarCoordenadas(palabra, sopas);
                cuadricula.append(coordenadas);
        }
        f.add(cuadricula);
        f.setSize(800, 400);    
        f.setLayout(null);    
        f.setVisible(true);
    }
    
    public static String mostrarCoordenadas(String palabra, String[][] sopa){
        if (sopa == null) {
            JOptionPane.showMessageDialog(null, "Error: La cuadrícula no se ha cargado correctamente");
            return null;
        }

        boolean encontrada = false;
        int fila = 0;
        int columna = 0;
        String direccion = "";
        for (int i = 0; i < sopa.length; i++) {
            for (int j = 0; j < sopa[i].length; j++) {
                if (sopa[i][j].equalsIgnoreCase(String.valueOf(palabra.charAt(0)))) {
                    if (buscarEnDireccion(palabra, i, j, 1, 0, sopa)) {
                        fila = i;
                        columna = j;
                        direccion = "arriba a abajo";
                        encontrada = true;
                        break;
                    } else if (buscarEnDireccion(palabra, i, j, 0, -1, sopa)) {
                        fila = i;
                        columna = j;
                        direccion = "derecha a izquierda";
                        encontrada = true;
                        break;
                    } else if (buscarEnDireccion(palabra, i, j, -1, -1, sopa)) {
                        fila = i;
                        columna = j;
                        direccion = "diagonal arriba a la izquierda";
                        encontrada = true;
                        break;
                    }
                }
            }
            if (encontrada) {
                return "La palabra '" + palabra + "' se encontró en la fila " + (fila + 1) + ", columna " + (columna + 1) + " en dirección " + direccion + ".\n";
            }
        }

        if (!encontrada) {
            return null;
        }
        return null;
    }
    
    private static boolean buscarEnDireccion(String palabra, int fila, int columna, int dirFila, int dirColumna, String[][] sopa) {
        for (int i = 1; i < palabra.length(); i++) {
            int nuevaFila = fila + i * dirFila;
            int nuevaColumna = columna + i * dirColumna;
            if (nuevaFila < 0 || nuevaFila >= sopa.length || nuevaColumna < 0 || nuevaColumna >= sopa[nuevaFila].length
                    || !sopa[nuevaFila][nuevaColumna].equalsIgnoreCase(String.valueOf(palabra.charAt(i)))) {
                return false;
            }
        }
        return true;
    }
}

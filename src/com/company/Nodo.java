/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company;

import java.util.ArrayList;

public  class Nodo  implements  Comparable<Nodo>{
    /*Nodo pensado para ser usado por el algoritmo A estrella.*/
    private int id;//Identificador
    private String nombre;
    private double latitud, longitud;//Equivalente a (x,y) respectivamente
    private String [] adyacentes;//Lista de nodos con los que esta concectado.

    /*Pesos para el algoritmo*/
    private double pesoGn=0;
    private double pesoHn=0;
    private double pesoFn=0;
    private Nodo padre;//predecesor

    /*Variables opcionales. Utiles para por ejemplo aplicar A* a un mapa de metro*/
    private boolean traspasable=true;//Si es un nodo por el que se puede cruzar.
    private String linea;
    private ArrayList<Integer> lineas;
    private int tiempoCambioNodo;



    public Nodo(int id, String nombre, double latitud, double longitud, int minTransbordo, String linea){
        this.id = id;
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
        this.tiempoCambioNodo = minTransbordo;
        this.linea =  linea;
        this.lineas = new ArrayList<>();

    }



    public int compareTo(Nodo a){
        return Double.compare(this.getPesoFn(), a.getPesoFn());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public double getLongitud() {
        return longitud;
    }

    public String[] getAdyacentes() {
        return adyacentes;
    }

    public void setAdyacentes(String [] adyacentes) {
        this.adyacentes = adyacentes;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public ArrayList<Integer> getLineas() {
        return lineas;
    }

    public void setLineas(ArrayList<Integer> lineas) {
        this.lineas = lineas;
    }

    public int getTiempoCambioNodo() {
        return tiempoCambioNodo;
    }

    public void setTiempoCambioNodo(int tiempoCambioNodo) {
        this.tiempoCambioNodo = tiempoCambioNodo;
    }

    public boolean isTrasbordo(Nodo siguiente){
        return !(this.getLinea().equals(siguiente.getLinea()));
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public boolean isTraspasable() {
        return traspasable;
    }

    public void setTraspasable(boolean traspasable) {
        this.traspasable = traspasable;
    }

    public double getPesoGn() {
        return pesoGn;
    }

    public void setPesoGn(double pesoGn) {
        this.pesoGn = pesoGn;
    }

    public double getPesoHn() {
        return pesoHn;
    }

    public void setPesoHn(double pesoHn) {
        this.pesoHn = pesoHn;
    }

    public double getPesoFn() {
        return pesoFn;
    }

    public void setPesoFn() {
        this.pesoFn = pesoGn+pesoHn;
    }

    public Nodo getPadre() {
        return padre;
    }

    public void setPadre(Nodo padre) {
        this.padre = padre;
    }



}
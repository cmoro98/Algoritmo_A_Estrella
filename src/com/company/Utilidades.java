package com.company;

public class Utilidades {
    /*Agregar aqui calculos de distancia entre nodos. */

    //distancia entre dos puntos en la Tierra la pasamos a min
    public static double distancia(Nodo e, Nodo b, int velocidadKilometrosHora) {
        double velocidadMetrosporMin = ((velocidadKilometrosHora * 1000) / 60);
        double lat1 = e.getLatitud();
        double lon1 = e.getLongitud();
        double lat2 = b.getLatitud();
        double lon2 = b.getLongitud();
        final int R = 6371; // Radio de la tierra.
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // pasamos a metros.
        double height = 0;
        distance = Math.pow(distance, 2) + Math.pow(height, 2);
        distance = Math.sqrt(distance);
        //System.out.println(e.getNombre()+"("+e.getLongitud()+","+e.getLatitud()+")"+"-->"+b.getNombre()+"("+b.getLongitud()+","+b.getLatitud()+")"+"Distancia="+distance);
        return (distance / velocidadMetrosporMin);
    }


     //distancia entre dos puntos en linea recta Metodo alternativo.
      public double distanciaRectaEuclidiana(Nodo a,Nodo b){
        double diferenciaX = a.getLatitud() - b.getLatitud();
        double diferenciaY = b.getLongitud() - b.getLongitud();
        return Math.sqrt(Math.pow(diferenciaX, 2) + Math.pow(diferenciaY, 2));
    }
}

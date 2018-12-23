
package com.company;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

import static com.company.Utilidades.distancia;

//Algoritmo A*
/*Dado que lo que en este ejercicio se pretende ser didáctico, se explicaran cosas
 * redundantes para el programador conocedor.  */

/*Diferencia entre algoritmo Path Finding y Dijkstra.
 Esta fue la primera duda que tuve.
  Alg Dijkstra:1959
    Todos los nodos positivos
    Pasa por todos los nodos.
    Siempre encuentra solución si existe.
  Alg A*:1968
    Evolución al alg de Dijkstra.
    Utiliza un método heurístico.(Metodo prueba error.)
    Solución más rápida.
    Siempre encuentra solución si es admisible.

* */
public class Aestrella {

    /*La lista abierta es una lista con prioridad. Eligiremos de la lista el elemento
    mas cercano.El mas prioritario.
    * */
    private PriorityQueue<Nodo> listaAbierta = new PriorityQueue<>();//Nodos a evaluar.
    private ArrayList<Nodo> listaCerrada = new ArrayList<>();//Nodos ya evaluados


    private HashMap<String, Nodo> mapa;
    private int velocidadDeLosNodos=60;

    private ArrayList<String> camino;//aqui metemos el camino como serie de strings.


    public Aestrella(HashMap<String, Nodo> mapa) {

        this.mapa = mapa;
    }

    /*Resumen: Aplica el algoritmo A* dado un inicio y un final
    * Return: Nodo meta. Recorrer sus padres para llegar a inicio. (Lista enlazada )o null en caso de fallo.*/
    public Nodo  algoritmo(String inicioS, String metaS) {
        listaAbierta.clear();
        listaCerrada.clear();

        Nodo inicio = mapa.get(inicioS);
        Nodo meta = mapa.get(metaS);

        if (meta.getNombre().equals(inicio.getNombre())) {//ENCONTRADO FIN .
            camino=getListaEstaciones(meta,inicio);
            return meta;

        }

        double pesoGnactual;

        inicio.setPesoGn(0);
        inicio.setPesoHn(Utilidades.distancia(inicio, meta, velocidadDeLosNodos) );
        inicio.setPesoFn();
        this.listaAbierta.add(inicio);//añadir nodo inicio a lista abierta

        while (!listaAbierta.isEmpty()) {
            //Paso 1:añadir Primer nodo de lista abierta a lista cerrada
            Nodo actual = listaAbierta.peek();
            listaCerrada.add(listaAbierta.remove());
            //Paso 2 Recorremos los nodos adyacentes.
            if(actual.getAdyacentes()==null){return null;}
            for (String k : actual.getAdyacentes()) {
                Nodo adyacente = mapa.get(k);


                //Paso 3: Comprobamos si meta alcanzada.
                if (meta.getNombre().equals(k)) {//ENCONTRADO FIN .
                    adyacente.setPadre(actual);
                    camino= getListaEstaciones(adyacente,inicio);
                    return adyacente;
                }

                //Paso 4: Comprobamos si el nodo se puede atravesar
                if (adyacente.isTraspasable()) {
                    //Paso 5: Comprobar si es un trasbordo(Opcional). Util para A* orientado a un plano de metro.
                    if (actual.isTrasbordo(adyacente)) {
                        adyacente.setPesoGn(adyacente.getPesoGn() + adyacente.getTiempoCambioNodo());
                        adyacente.setPesoFn();
                    }
                    //Paso 6: Si el Nodo está en la lista cerrada, le ignoramos.
                    if (!listaCerrada.contains(adyacente)) {
                        //Paso 7:Si la lista abierta contiene al nodo adyacente
                        if (listaAbierta.contains(adyacente)) {
                            //Si ya esta en lista abierta,testeamos la nueva g(adyacente) y la comparamos con su anterior g(adyacente)
                            pesoGnactual = actual.getPesoGn() + distancia(actual, adyacente, velocidadDeLosNodos);
                            if (pesoGnactual < adyacente.getPesoGn()) {
                                adyacente.setPesoGn(pesoGnactual);
                                adyacente.setPesoFn();
                                adyacente.setPadre(actual);
                            }
                        } else {//Paso 7b Si el nodo no esta ni en la abierta ni cerrada
                            //calcular peso y añadir de forma ordenada
                            adyacente.setPadre(actual);
                            adyacente.setPesoGn((adyacente.getPesoGn() + actual.getPesoGn() + distancia(actual, adyacente, velocidadDeLosNodos)));
                            adyacente.setPesoHn(distancia(adyacente, meta, velocidadDeLosNodos));
                            adyacente.setPesoFn();
                            listaAbierta.add(adyacente);
                        }
                    }

                }

            }
        }
        return null;
    }

    private ArrayList<String> getListaEstaciones(Nodo una, Nodo inicio) { //recorremos hacia atras
        ArrayList<String> res = new ArrayList<>();
        while (una.getPadre() != null && !una.getPadre().getNombre().equals(inicio.getNombre())) {
            res.add(una.getNombre());
            una = una.getPadre();
        }
        res.add(una.getNombre());
        res.add(una.getPadre().getNombre());
        return res;
    }

    /*Calculo Pesos*/
    /* Calculo g(n)
     *  Peso del nodo actual al inicio.
     *
     * Calculo h*(n) ~heuristica
     *  Peso del nodo actual al final.
     *  Asegurar admisibilidad--> Distancia heuristica nodo actual-final<=Distancia real nodo actual-final
     *  La heuristica ha de ser lo mas parecida a la distancia real. Para que el algoritmo sea más eficiente.
     *
     * Calculo f(n)=g(n)+h(n)
     *
     * Recuerda que el peso pueden ser minutos,distancia, o lo que quieras.
     * */

    /*Metodo util para devolver un String con los nombres de los caminos.
    * */
    public ArrayList algoritmoDevuelvecamino(String inicioS, String metaS){
        algoritmo(inicioS,metaS);
        return camino;
    }





}

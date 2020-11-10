package Estructuras;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author User
 * @param <T>
 */
public class RowMatriz<T> extends ListaSimple<T> {
    private RowMatriz<T> next;
    private int columns;

    public RowMatriz(int columns) {
        this.next=null;
        this.columns=columns;
        for(int i=0;i<columns;i++){
            this.addFirst();            
        }     
    }

    public RowMatriz<T> getNext() {
        return next;
    }

    public void setNext(RowMatriz<T> next) {
        this.next = next;
    }
    
    public void rellenar(T Dato){
        NodoListasimple<T> aux=this.getHead();
        while(aux!=null){
            aux.setData(Dato);
        }
        
    }
    
   
    
}

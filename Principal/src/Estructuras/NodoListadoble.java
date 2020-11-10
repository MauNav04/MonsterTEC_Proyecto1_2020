package Estructuras;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Estructuras.Nodo;

/**
 *
 * @author User
 * @param <T>
 */
public class NodoListadoble<T> extends Nodo<T>{
    private T data;    
    private NodoListadoble<T> next;
    private NodoListadoble<T> previus;
    
    /**
     * Constructor del Estructuras.Nodo vacio
     */
    public NodoListadoble(){
        next=null;        
    }
    /**
     * Método del constructor del Estructuras.Nodo con un dato
     * @param data
     */
    public NodoListadoble(T data){
        this.next=null;
        this.data=data;
    }
    /**
     * Método para obtener el dato almacenado en el Estructuras.Nodo
     * @return 
     */
    @Override
    public T getData(){
        return this.data; 
       
    }
    /**
     * Insertar dato en un nodo;
     * @param data 
     */
    public void setData(T data) {
        this.data = data;
    }
    /**
     * remplaza un Dato en el nodo y devuelve el dato anterior
     * @param data
     * @return pData
     */
    public T replaceData(T data){
        T pData=this.getData();
        this.data = data;
        return pData;
    }
    /**
     * Metodo para obtener el siguiente nodo
     * @return next
     * 
     */

    @Override
    public NodoListadoble<T> getNext() {
        return next;
    }
    
    
    /**
     * Método para establecer el nodo siguiente 
     * @param Next 
     */
    

    public void setNext(NodoListadoble<T> Next){
            this.next= Next;
           
    }
    /**
     * devuelve el nodo anterior
     * @return previus
     */
    public NodoListadoble<T> getPrevius() {
        return previus;
    }
     /**
     * Inserta un nodo previo
     * @param previus
     */


    public void setPrevius(NodoListadoble<T> previus) {
        this.previus = previus;
    }
    
    
}

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
public class ListaCircular<T>{
    private NodoListasimple<T> head;
    private NodoListasimple<T> tail;
    private int len;
   
    /**
     * Constructor de lista 
     *  
     */
    public ListaCircular(){
        head=null;
        tail=null;
        len=0;        
    }

    public NodoListasimple<T> getTail() {
        return tail;
    }
    
    /**
     * 
     * @return Retorna la cabeza de la Lista
     */

    public NodoListasimple<T> getHead() {
        return head;
    }
    
    /**
     * Método para obtener el largo de la lista
     * @return 
     */
    public int getlen(){
        return this.len;
    }

    /**
     * Metodo para hacer un print de los elementos dwe la lista 
     */
    public void print(){
        NodoListasimple<T> aux= head;
        while(aux.getNext()!=this.head){
            System.out.println(aux.getData());
            aux=aux.getNext();
            
        }
        System.out.println(aux.getData());
    }
    public NodoListasimple<T> getNodo(int pos){
        if(pos==0){
            return this.head;
        }
        else{
            NodoListasimple<T> aux=this.head;
            for(int i = 0 ; i<pos; i++){
                aux=aux.getNext();
            }
            return aux;
        }
        
    }
   
    /**
     * Metodo para conseguir el valor de una posición de la lista
     * @param pos
     * @return 
     */
    public T getInfo(int pos){
        if (pos==0){
            return this.head.getData();
        }
        else{
            NodoListasimple<T> aux=this.head;
            for(int i=0; i<pos ; i++){
                aux = aux.getNext();
            }
            return aux.getData();
        }

    }
    /**
     * Metodo para agregar un espacio vacio al inicio de la lista
     */
 
    public void addFirst(){
        if (this.head==null){
            this.head= new NodoListasimple<>();

        }
        else{
            NodoListasimple<T> temp= this.head;
            this.head= new NodoListasimple<>();
            this.head.setNext(temp);
        }
        len++;
    }
    /**
     * Metodo para agregar un dato al inicio de la lista
     * @param dato 
     */
    public void addFirst(T dato){
        if (this.head==null){
            NodoListasimple<T> temp = new NodoListasimple<>(dato);
            this.head = temp;
            this.tail = temp;
            temp.setNext(head);
            
        }
        else {
            NodoListasimple<T> temp = this.head;
            this.head = new NodoListasimple<>(dato);
            this.head.setNext(temp);
            this.tail.setNext(this.head);
            

        }
        len++;
    }
    
    
    
    public void addLast(T dato){
        if (this.head==null){
            this.addFirst(dato);
        }
        else{
            NodoListasimple<T> temp = this.tail;
            this.tail = new NodoListasimple<>(dato);
            this.tail.setNext(head);
            temp.setNext(this.tail);
            len++;
        }
    }

    /**
     * Metodo para agregar un dato a una posicion dada de la lista

     * @param pos 
     * @param dato 
     */

    public void setData(T dato,int pos){
        
        if (pos==0){
           addFirst(dato);         
        }
        else if (this.len%pos==0){
            addLast(dato);           
        }
        else{
            NodoListasimple<T> aux=this.head;
            for(int i=0;i<pos;i++){
                aux=aux.getNext();
            }
            aux.setData(dato);
          
        }
    
    }
    /**
     * Metodo para buscar si un dato esta en la lista
     * @param dato
     * @return retorna un boolean segun si esta o no el dato
     */
    public boolean in(T dato){
        NodoListasimple<T> aux=head;
        while (aux != head){
            if (aux.getData().equals(dato)){
                System.out.println(aux.getData());
                return true;
            }
            aux=aux.getNext();  
        }
        return false;
    }
    /**
     * Metodo para eliminar el primer elemento 
     */
    public void removeFirst(){
        this.head=this.head.getNext();
        len--;
    }
    public void removeLast(){
        NodoListasimple<T> aux=this.head;
        while(aux.getNext()!=this.head){
            aux=aux .getNext();
        }
        aux.setNext(this.head);
        len--;
    }
    /**
     * Metodo para eliminar un elemento en una posicion dada
     * @param pos 
     */
    public void remove(int pos){
        if (pos==0){
            removeFirst();
        }
        else if (this.len%pos==0){
            removeLast();           
        }
        else{
            int cont=1;
            NodoListasimple<T> aux=this.head;
            while (cont<pos){
                cont++;
                aux= aux.getNext();
            }
            aux.setNext(aux.getNext().getNext());
            len--;
        }
    }  

}

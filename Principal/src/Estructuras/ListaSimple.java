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
public class ListaSimple<T>{
    private NodoListasimple<T> head;
    private int len;
   
    /**
     * Constructor de lista 
     *  
     */
    public ListaSimple(){
   
        head=null;
        len=0;        
    }

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

    public int getLen() {
        return len;
    }

    /**
     * Metodo para hacer un print de los elementos dwe la lista 
     */
    public void print(){
        NodoListasimple<T> aux= head;
        while(aux !=null){
            System.out.println(aux.getData());
            aux=aux.getNext();
            
        }
    
    }
    public NodoListasimple<T> getNodo(int pos){
        NodoListasimple<T> aux=this.head;
        int cont=0;
        while(aux!=null){
            if(cont==pos){
                return aux;
            }
            aux=aux.getNext();
            cont++;
        }
    return null;
    }
   
    /**
     * Metodo para conseguir el valor de una posición de la lista
     * @param i
     * @return 
     */
    public T getInfo(int i){
        if (i== 0){
            return this.head.getData();
        }
        NodoListasimple<T> aux=this.head;
        int cont=0;
        while(aux!=null){
            if(cont==i){
                return aux.getData();
            }
            aux=aux.getNext();
            cont++;
        }
    return null;
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
            this.head= new NodoListasimple<>(dato);           
        }
        else{
            NodoListasimple<T> temp= this.head;
            this.head= new NodoListasimple<>(dato);
            this.head.setNext(temp);
        }
        len++;
    }
    
    
    public void addLast(T dato){
        if (this.head==null){
            this.addFirst(dato);
        }else{
            NodoListasimple<T> aux= this.head;
            while(aux.getNext()!=null){
                aux=aux.getNext();
            }
            aux.setNext(new NodoListasimple<>(dato));
        }
        len++;
    }

    /**
     * Metodo para agregar un dato a una posicion dada de la lista
     * @param i 
     * @param dato 
     */

    public void addPos(int i,T dato){
        
        if (i==0){
           addFirst(dato);
           return;
        }
        if (i>=this.len){
            return ;
        }
        int pos = 1;
        NodoListasimple<T> aux=this.head;
        while(pos<i){
            aux=aux.getNext();
            pos++;
        }
        NodoListasimple<T> temp=aux.getNext();
        aux.setNext(new NodoListasimple<>(dato));
        aux.getNext().setNext(temp);
        len++;
    
    }
    /**
     * Metodo para buscar si un dato esta en la lista
     * @param dato
     * @return retorna un boolean segun si esta o no el dato
     */
    public boolean in(T dato){
        NodoListasimple<T> aux=head;
        while (aux != null){
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
    private void removeFirst(){
        this.head=this.head.getNext();
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
        if (pos>len){
            System.out.println("indice inexistente");
            return;
        }
        int cont=1;
        NodoListasimple<T> aux=this.head;
        while (cont<pos){
            cont++;
            aux= aux.getNext();
        }
        if (aux.getNext().getNext() == null){
            aux.setNext(null);
        }
//        aux.setNext(aux.getNext().getNext());
        len--;
    }

    public void deleteAll(){
        head = null;
        len = 0;
    }

    public void myShuffle(){
        for (int i = 0; i < len; i++){
            int s = i + (int)(Math.random() * (len - i ));

            T temp = this.getInfo(s);
            this.getNodo(s).setData(this.getInfo(i));
            this.getNodo(i).setData(temp);
        }
    }
    
    public void replaceDato(T dato,int pos){
        NodoListasimple<T> aux = this.head;
        for(int i=0; i<pos ; i++){
            aux= aux.getNext();
        }
        aux.setData(dato);
        
    }
    
    public NodoListasimple<T> getLast(){
        NodoListasimple<T> aux = this.head;
        while(aux.getNext()!=null){
            aux = aux.getNext();
            
        }
        
        return aux;
    }
            

    
}

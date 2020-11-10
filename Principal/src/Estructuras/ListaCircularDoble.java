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
public class ListaCircularDoble<T> {
    private NodoListadoble<T> head;
    private NodoListadoble<T> tail;
    private int len;

    public ListaCircularDoble() {
        this.head = null;
        this.tail= null;
        this.len = 0;
    }
    private void configure(){
        this.tail.setNext(head);
        this.head.setPrevius(tail);
    }
        
    
    
    /**
     * retorna la cabeza de la lista
     * @return head
     */
    public NodoListadoble<T> getHead() {
        return head;
    }
    
    /**
     * Ingresa la cabeza de la lista
     * @return int len: Retorna el largo de la lista
     */
    public int getLen() {
        return len;
    }

    public NodoListadoble<T> getTail() {
        return tail;
    }


    
     /**
     * Metodo para hacer un print de los elementos dwe la lista 
     */
    public void print(){
        Nodo<T> aux= head;
        while(aux !=this.tail){
            System.out.println(aux.getData());
            aux=aux.getNext();                     
        }      
    }
    
     /**
     * Metodo para hacer un print de atras para adelante (invrtido) de los elementos dwe la lista 
     */
    public void pintinvr(){
        Nodo<T> aux= this.tail;
        while(aux !=null){
            System.out.println(aux.getData());
         //   aux=aux.getPrevius();           
        }         
    }
    
    /**
     * Metodo para conseguir el nodo de una posición de la lista
     * @param pos
     * @return NodoListaDoble
     */
    public NodoListadoble<T> getNodo(int pos){
        if (pos==0){
            return this.head;
        }
        else{
            NodoListadoble<T> aux=this.head;
            for(int i =0 ; i<pos ;i++){
                aux = aux.getNext();
            }
            return aux;
        }
    
    }
   
    /**
     * Metodo para conseguir el valor de una posición de la lista
     * @param i
     * @return T
     */
    public T getInfo(int i){
        Nodo<T> aux=this.head;
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
     * Metodo para agregar un dato al inicio de la lista
     * @param dato 
     */
    public void addFirst(T dato){
        if (this.head==null){
            NodoListadoble<T> temp = new NodoListadoble<>(dato);
            this.head = temp;
            this.tail = temp;
            temp.setNext(head);
            
        }
        else {
            NodoListadoble<T> temp = this.head;
            this.head = new NodoListadoble<>(dato);
            this.head.setNext(temp);
            temp.setPrevius(this.head);
            this.head.setPrevius(this.tail);
            this.tail.setNext(this.head);
            

        }
        len++;
    }
    
     /**
     * Metodo para agregar un dato al final de la Lista
     * @param dato 
     */
    public void addLast(T dato){
        if (this.head==null){
           this.addFirst(dato);
          
        }
        else{
            NodoListadoble<T> temp =this.tail;
            this.tail= new NodoListadoble<>(dato);
            this.tail.setPrevius(temp);
            temp.setNext(this.tail);
            this.head.setPrevius(this.tail);
            this.tail.setNext(this.head);
                     
        }
        len++;
        configure();
        
    }
    
    /**
     * Metodo para eliminar el primer elemento 
     */
    private void removeFirst(){
        this.head=(NodoListadoble<T>) this.head.getNext();
        len--;
        configure();
    }
    
    /**
     * Metodo para eliminar el ultimo elemento 
     */
    private void removeLast(){
        this.tail=(NodoListadoble<T>) this.tail.getPrevius();
        len--;
        configure();
    }
    
    public void addPos(int i,T dato){
        
        if (i==0){
           addFirst(dato);
           return;
        }
        else if (i>this.len){
            System.out.println("El indice excede el largo de la lista"); 
            return;
        }
        else if (i==this.len){
            this.addLast(dato);
            return;
        }
        else{
            int pos = 1;
            NodoListadoble<T> aux=this.head;
            while(pos<i){
                aux=(NodoListadoble<T>) aux.getNext();
                pos++;
            }
            Nodo<T> temp=aux.getNext();
            aux.setNext(new NodoListadoble(dato));
            aux.getNext().setNext(temp);
            aux.getNext().setPrevius(aux);
            temp.setPrevius(aux.getNext());


            len++;
        }configure();
    
    }
        /**
     * Metodo para eliminar un elemento en una posicion dada
     * @param pos 
     */
    public void remove(int pos){
        if (pos==0){
            this.removeFirst();
        }
        else if(pos==len-1){
            this.removeLast();
        }
       
        else if (pos>=len){
            System.out.println("indice inexistente");
        }
        else if (pos<(this.getLen())/2 ){
            NodoListadoble<T> aux=this.head;
            for (int cont=0;cont<pos; cont++){              
                aux= (NodoListadoble<T>) aux.getNext();
            }
            aux.getPrevius().setNext(aux.getNext());
            aux.getNext().setPrevius(aux.getPrevius());
            len--;
        }
        else if(pos>(this.getLen())/2 ){
          NodoListadoble<T> aux=this.head;  
          for (int cont=len-1;cont>pos; cont--){              
               aux= (NodoListadoble<T>) aux.getPrevius();
          }
          aux.getPrevius().setNext(aux.getNext());
          aux.getNext().setPrevius(aux.getPrevius());
          len--; 
        }configure();






    }

    public void remove(T dato){
        NodoListadoble aux = new NodoListadoble();
        int n=0;
        while (aux.getData()!=dato && n!=this.len){
            aux = aux.getNext();
            n++;
        }
        if (aux.getData()==dato){
            NodoListadoble uxN= aux.getNext();
            uxN.setPrevius(aux.getPrevius());
            aux.getPrevius().setNext(uxN);


        }

    }
    
    
}

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
public class Nodo<T> {
    private T data;
    private Nodo<T> next;
    public Nodo(){
        this.data= null;
        this.next = null;
    }
    

    public T getData() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Nodo<T> getNext() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setNext(Nodo<T> next) {
        this.next = next;
    }
    
    public void setPrevius(Nodo<T> node){
        
    }
    
    public Nodo<T> getPrevius(){
        return null;
    }
    
}

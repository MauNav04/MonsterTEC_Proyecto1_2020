package Structures;

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

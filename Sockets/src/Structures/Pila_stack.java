package Structures;


public class Pila_stack<T>{
    private NodoListasimple<T> head;

    public Pila_stack(NodoListasimple<T> head) {
        this.head = head;
    }

    public Pila_stack() {
        this.head = null;
    }

    public NodoListasimple<T> peek() {
        return head;
    }

    public void push(T dato) {
        if (head==null){
            this.head = new NodoListasimple<>();
        }else{
            NodoListasimple<T> aux= this.head;
            this.head = new NodoListasimple<T>(dato);
            this.head.setNext(aux);
        }

    }

    public NodoListasimple<T> pop() {
        NodoListasimple<T> aux= this.head;
        this.head=this.head.getNext();
        return aux;
    }

    

}


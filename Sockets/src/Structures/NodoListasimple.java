package Structures;

public class NodoListasimple<T> extends Nodo<T> {



    private T data;
    private NodoListasimple<T> next;

    /**
     * Constructor del Estructuras.Nodo vacio
     */
    public NodoListasimple(){
        next=null;
    }
    /**
     * Método del constructor del Estructuras.Nodo con un dato
     * @param data
     */
    public NodoListasimple(T data){
        this.next=null;
        this.data=data;
    }
    /**
     * Método para obtener el dato almacenado en el Estructuras.Nodo
     * @return
     */
    public T getData(){
        return this.data;

    }
    /**
     * Método para establecer el nodo siguiente
     * @param Next
     */

    public void setNext(NodoListasimple<T> Next){
        this.next= Next;

    }

    @Override
    public void setData(T data) {
        this.data = data;
    }

    /**
     * @return next
     */


    @Override
    public NodoListasimple<T> getNext() {
        return next;
    }





}

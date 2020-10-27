public class LinkedLists {

    protected Node first;
    private int size;

    public LinkedLists() {
        //La lsita se inicia con el primer elemento en null
        first = null;
    }

    /**
     * Inserta un elemento al inicio de una lista enlazada
     *
     * @param o Objecto genérico a insertar por medio de un nuevo nodo
     */
    public void insert(Object o) {
        Node tmp = new Node(o, null);
        tmp.setNext(first);
        first = tmp;
        this.size++;
    }

    /**
     * Añade un nuevo elemento al final de la lista.
     * @param object
     */
    public void lastInsert (Object object){
        Node tmp = this.first;
        Node current =  new Node (object, null);
        if (tmp != null){
            while (tmp.getNext() != null){
                tmp = tmp.getNext();
            }
            current.setNext(null);
            tmp.setNext(current);
        }
        else{
            current.setNext(first);
            first = current;
        }

        this.size ++;
    }

    /**
     * Devuelve el objeto contenido en cierta posición de la lista
     * @param n posición del elemento
     * @return el objeto en esa posición
     */
    public Object get(int n) {
        Node tmp = first;
        for (int i = 0; i < n; i++) {
            tmp = tmp.getNext();
        }
        return tmp.getInfo();
    }

    public int getSize() {
        return size;
    }


}


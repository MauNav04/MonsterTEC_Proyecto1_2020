public class LinkedLists {
    //Attributes
    private String_Node Node;
    private String_Node head;
    private String_Node first;
    private int id;
    private int amount;

    //Methods
    public LinkedLists (){
        this.head = new String_Node();
        //this.first = this.head;
        this.id = 0;
        this.amount = 0;

    }

    public void addItem(String item){
        this.Node = new String_Node();
        this.head.setNext(Node);
        this.Node.setInfo(item);
        this.amount = ++amount;
    }

    public String getItem(int pos){
        String_Node currentNode = this.head;
        for(int i=0; i<this.amount; i++){
            if(i == pos){
                return currentNode.getINfo();
            }
            else{
                currentNode.setNext(this.head.getNext());
                currentNode = currentNode.getNext();
            }
        }
        return currentNode.getINfo();
    }
}

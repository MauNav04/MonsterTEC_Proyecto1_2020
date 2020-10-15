public class String_Node {
    //Attributes
    private String info;
    private int id ;
    private String_Node nextNode;

    //Methods
    public void setInfo(String newInfo){
        this.info = newInfo;
    }

    public String getINfo(){
        System.out.println(this.info);
        return this.info;
    }

    public void setNext(String_Node nexOne){
        this.nextNode = nexOne;
    }

    public String_Node getNext(){
        return this.nextNode;
    }
}

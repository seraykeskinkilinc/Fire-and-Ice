public class LocatioanNode {
    public Object object;
    public  LocatioanNode next;

    public LocatioanNode(Object dataToAdd){
        object = dataToAdd;
        next = null;
    }
    public Object getObject() {
        return object;
    }

    public void setObject(Object oguz) {
        this.object = oguz;
    }

    public LocatioanNode getNext() {
        return next;
    }

    public void setNext(LocatioanNode next) {
        this.next = next;
    }



}

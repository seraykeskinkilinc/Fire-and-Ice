public class Stack {
    private int top;
    private Object[] elements;
    public Stack (int capacity)
    {
        top=-1;
        elements = new Object[capacity];
    }
    public int size(){

        return top+1;
    }
    public boolean isFull() {
        return elements.length == size();
    }
    public  boolean isEmpty(){

        return top == -1;
    }
    public void push(Object data){
        if(isFull())
            System.out.println("stack overflow");
        else{

            elements[++top]= data; // top once artırıp sonra ısleme koyuyor
        }
    }
    public  Object pop(){
        if (isEmpty()){
            System.out.println("stack is empty");
            return  null;
        }
        else{
            Object returnData = elements[top];
            elements[top]= null;
            top--;
            return returnData;

        }
    }
    public Object peek() {
        if(isEmpty()){
            System.out.println("stack is empty");
            return null;
        }
        else {
            return elements[top];
        }
    }
}

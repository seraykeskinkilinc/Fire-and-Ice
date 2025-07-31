
import java.util.Random;

public class CircularQueue {

    private int rear ,front;
    private Object[] elements;

    CircularQueue(int capacity){
        elements = new Object[capacity];
        rear = -1;
        front = 0;

    }
    void enqueue(Object data){
        if(isFull()){
            System.out.println("stack overflow");
        }
        else{
            rear = (rear+1)%(elements.length);
            elements[rear]=data;
        }
    }
    Object dequeue(){
        if(isEmpty()){
            System.out.println("stack overflow");
            return null;
        }
        else{

            Object rerturnData = elements[front];
            elements[front] = null;
            front=(front+1)%elements.length;
            return rerturnData;
        }
    }
    Object peek(){
        if(isEmpty()){
            System.out.println("queue empty");
            return null;
        }
        else {
            return elements[front];
        }
    }
    boolean isEmpty(){
        return elements[front]== null;
    }
    boolean isFull(){
        return (front ==(rear+1) % elements.length && elements[front] != null && elements[rear] != null );
    }
    int size(){
        if(elements[front]== null){
            return 0;
        }
        else {
            if(rear >= front){
                return rear- front+1;
            }
            else {
                return elements.length - (front-rear)+1;
            }
        }
    }
    public void inputqueue(){
        while(!isFull()){
            Random random = new Random();
            int randomNum = random.nextInt(40);

            if (randomNum <= 5) {
                enqueue('1');
            }
            if (randomNum > 5 && randomNum <= 10) {
                enqueue('2');
            }
            if (randomNum > 10 && randomNum <= 15) {
                enqueue('3');
            }
            if (randomNum > 15 && randomNum <= 21) {
                enqueue('-');
            }
            if (randomNum > 21 && randomNum <= 27) {
                enqueue('@');
            }
            if (randomNum > 27 && randomNum <= 40) {
                enqueue('C');
            }
        }
    }
}






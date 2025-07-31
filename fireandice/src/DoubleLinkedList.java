
public class DoubleLinkedList {

	 Node head;
	 Node tail;

	public DoubleLinkedList() {
		head = null;
		tail = null;
	}

	public void add(Object s) {
        Node newNode = new Node(s);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setPrev(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
    }
	public int size() {

		int count = 0;
		if(head==null)
		{
			System.out.println("Linkedlist is empty");
		}
		else {
			Node temp=head;
			while(temp!=null)
			{
				count++;
				temp=temp.getNext();
			}
		}
		return count;
	}

	public void display()
	{
		if(head==null)
		{
			System.out.println("LinkedList is empty");
		}
		else {
			Node temp=head;
			while(temp!=null)
			{
				System.out.println(temp.getData()+" ");
				temp=temp.getNext();
			}
			System.out.println();
		}
	}
	
	public void deleteLast() {
	    if (head == null) {

	        return;
	    } else if (head == tail) {
	   
	        head = null;
	        tail = null;
	    } else {
	        tail = tail.getPrev();
	        tail.setNext(null);
	    }
	}
	
	
}

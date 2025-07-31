import java.security.PublicKey;

public class Node {
	Object data;
	Node link;
	private Object privdata;
	private Node prev;
	private Node next;

	public Node(Object dataToAdd) {
		data = dataToAdd;
		link = null;
		prev = null;
		next = null;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Node getLink() {
		return link;
	}

	public void setLink(Node link) {
		this.link = link;
	}

	public Node getPrev() {
		return prev;
	}

	public void setPrev(Node prev) {
		this.prev = prev;
	}

	public Node getNext() {
		return next;
	}

	public void setNext(Node next) {
		this.next = next;
	}
}
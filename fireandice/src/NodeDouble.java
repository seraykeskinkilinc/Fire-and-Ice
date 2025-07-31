import java.security.PublicKey;

public class NodeDouble {
	Object data;
	NodeDouble link;
	private Object privdata;
	private NodeDouble prev;
	private NodeDouble next;

	public NodeDouble(Object dataToAdd) {
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

	public NodeDouble getLink() {
		return link;
	}

	public void setLink(NodeDouble link) {
		this.link = link;
	}

	public NodeDouble getPrev() {
		return prev;
	}

	public void setPrev(NodeDouble prev) {
		this.prev = prev;
	}

	public NodeDouble getNext() {
		return next;
	}

	public void setNext(NodeDouble next) {
		this.next = next;
	}
}
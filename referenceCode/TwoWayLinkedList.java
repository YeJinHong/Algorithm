package referenceCode;

public class TwoWayLinkedList {
	static class Node{
		int value;
		Node next;
	}
	static class List{
		int size;
		Node front;
		Node back;
	}
	void setNode(Node cur, int value) {
		cur.value = value;
	}
	void next(Node cur) {
		cur = cur.next;
	}
	void pushBack(List list, int value) {
		Node newNode = new Node();
		newNode.value = value;
		newNode.next = null;
		
		if(list.size == 0) {
			list.front = newNode;
			list.back = newNode;
		}
		else {
			list.back.next = newNode;
			list.back = newNode;
		}
		
		list.size++;
	}
	void pushFront(List list, int value) {
		Node newNode = new Node();
		newNode.value = value;
		newNode.next = null;
		
		if(list.size==0) {
			list.front = newNode;
			list.back = newNode;
		} else {
			newNode.next = list.front;
			list.front = newNode;
		}
		
		list.size++;
	}
	Node popFront(List list) {
		Node result;
		result = list.front;
		if(list.size == 1) {
			list.front = null;
			list.back = null;
		} else {
			list.front = list.front.next;
		}
		list.size--;
		return result;
	}
	Node popBack(List list) {
		Node result = list.back;
		if(list.size == 1) {
			list.front = null;
			list.back = null;
		} else {
			Node preNode = null;
			for(Node oldNode = list.front; oldNode.next != null; next(oldNode)) {
				preNode = oldNode;
			}
			preNode.next = null;
			list.back = preNode;
		}
		list.size--;
		
		return result;
	}
	
	void clear(List list) {
		while(list.size > 0) {
			popFront(list);
		}
	}
	
	// curNode를 삭제. list에 반드시 있는 노드라는 전제인듯함.
	void delNode(List list, Node preNode, Node curNode) {
		if(preNode == null) {
			next(curNode);
			list.front = null;
			list.front = curNode;
		} else {
			preNode.next = curNode.next;
		}
		list.size--;
	}
	// curNode 위치 다음에 value값을 가지는 노드 추가
	void insertNode(List list, Node curNode, int value) {
		Node newNode = new Node();
		newNode.value = value;
		newNode.next = curNode.next;
		curNode.next = newNode;
		list.size++;
	}
	
	void pringList(List list) {
		for(Node oldNode = list.front; oldNode != null ; next(oldNode)) {
			System.out.print(oldNode.value);
		}
		System.out.println();
	}
}

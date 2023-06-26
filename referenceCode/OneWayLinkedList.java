package referenceCode;

public class OneWayLinkedList {
	Node list;
	Node[] nodes = new Node[5000];
	int nCnt; // 어디까지 사용했는가
	
	static class Node{
		int value; // 값
		boolean deleted; // 삭제 여부
		Node next; // 다음 노드
	}
	
	// 리스트 초기화
	void init() {
		nCnt = 0;
	}
	
	// 새로운 노드 할당 
	Node alloc(int value){
		nodes[nCnt].value = value;
		nodes[nCnt].deleted = false;
		nodes[nCnt].next = null;
		return nodes[nCnt++];
	}
	
	// 리스트에 주어진 값을 추가
	Node append(int value) {
		Node current = list;
		Node newNode = alloc(value);
		if(current == null) // 첫 시작이다.
			return list = newNode;
		
		while(current.next != null) { // 마지막까지 이동
			current = current.next;
		}
		return current.next = newNode;
	}
	
	// 리스트에서 주어진 값을 탐색
	Node search(int value) {
		Node current = list;
		if(current == null) return null;
		while(current != null) {
			if(current.value == value) {
				return current;
			}
			current = current.next;
		}
		return null;
	}
	
	// 리스트에서 주어진 값을 삭제
	Node remove(int value) {
		Node pre = null;
		Node current = list;
		if(current == null) return null;
		while(current != null) { // 찾을 때까지, null이 아닐 때까지 next로 이동한다
			if(current.value == value) { 
				if(pre == null)
					list = current.next; // 맨 앞의 값이라면 list값을 그 다음으로 설정한다. 결과적으로 맨 앞을 가리킨다.
				else
					pre.next = current.next; // 이전 노드와 다음 노드를 연결해준다.
				current.deleted = true;
				return current;
			}
			pre = current; 
			current = current.next;
		}
		return null;
	}
	
	// 리스트에서 주어진 값을 다른 값으로 대체
	Node update(int value, int newValue) {
		Node current = search(value);
		if(current != null) {
			current.value = newValue;
		}
		return current;
	}
}

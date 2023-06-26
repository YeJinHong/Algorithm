package referenceCode;

// Chaining Hash Table
public class Hash {
	
	// djb2 : hash함수 중 간략하면서도 무작위 분포를 만드는데 뛰어나다고 알려져 있음
	long djb2(char[] str) {
		long hash = 5387;
		for(char ch : str) {
			hash = ((hash << 5) + hash) + ch; // hash*33 + ch
		}
		return hash;
	}
	
	static final int MAX_N = 10000;
	static final int MAX_LEN = 10;
	
	class Node{
		char str[] = new char[MAX_LEN];
		int data;
		Node next;
	}
	
	static int node_count = 0;
	Node[] nodes = new Node[MAX_N];
	
	Node new_node(char[] str, int data) {
		nodes[node_count].str = str.clone();
		nodes[node_count].data = data;
		nodes[node_count].next = null;
		
		return nodes[node_count++];
	}
	
	class HashMap{
		static final int TABLE_SIZE = 1 << 12;
		static final int DIV = TABLE_SIZE - 1;
		
		Node[] hash_table;
		
		void init() {
			hash_table = new Node[TABLE_SIZE];
			node_count = 0;
		}
		
		// str의 앞에 있는 노드를 찾는다.
		Node get_pre_node(char str[]) {
			// hash table에서 str이 속한 리스트의 첫 노드를 찾는다.
			Node pre_node = hash_table[(int) (djb2(str) & DIV)];
			while(pre_node.next != null && String.valueOf(pre_node.next.str).equals(String.valueOf(str))) {
				pre_node = pre_node.next;
			}
			return pre_node;
		}
		
		// str에 이미 어떤 값이 있는 경우 data로 교체.
		void insert(char[] str, int data) {
			Node pre_node = get_pre_node(str);
			if(pre_node.next == null) {
				pre_node.next = new_node(str, data);
			} else {
				pre_node.next.data = data; // ?
			}
		}
		
		void remove(char[] str) {
			Node pre_node = get_pre_node(str);
			if(pre_node.next != null) {
				pre_node.next = pre_node.next.next;
			}
		}
		
		Node get(char[] str) {
			return get_pre_node(str).next;
		}
	}
	
	// hashf(String str) 문자열을 받아서 0~4998 사이 해쉬값 반환
	
	// _add(String str) 문자열을 받아서 hash 테이블에 삽입
	// _search(String str) 문자열의 개수를 반환
	// _remove(String str) 문자열을 모두 삭제
	// _update(String str, String str2) 문자열 str을 str2로 치환

}

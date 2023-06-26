package boj;

import java.util.Scanner;

public class BOJ_2606_MemoryPoolLinkedList {
	static Node Head[];
	static int nCnt;
	
	static class Node {
		int value;
		Node next;
		public Node(int value, Node next) {
			super();
			this.value = value;
			this.next = next;
		}
	}
	
	static void addNode(int p, int c) {
		Node node = new Node(c, null);
		node.next = Head[p].next;
		Head[p].next = node;
	}
	
	static int queue[];
	static boolean visit[];
	static int wp, rp; // writePoint, readPoint
	static int ans = 0;
	
	static void bfs() {
		int out;
		
		wp = rp = 0;
		
		queue[wp++] = 1;
		visit[1] = true;
		
		while(wp > rp) {
			// 나온 것. 현재 노드의 번호.
			out = queue[rp++];
			
			// 현재 노드에 이어진 것들을 확인한다. 
			for(Node p = Head[out].next; p != null; p = p.next) {
				if(visit[p.value] == false) {
					visit[p.value] = true;
					ans++;
					queue[wp++] = p.value;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int pairCount = sc.nextInt();
		nCnt = 0;
		Head = new Node[N+1];
		visit = new boolean[N+1];
		
		for(int i = 1; i <= N; i++) {
			Head[i] = new Node(i, null);
		}
		
		while(pairCount-- > 0) {
			int p = sc.nextInt();
			int c = sc.nextInt();
			addNode(p, c);
			addNode(c, p);
		}
		
		queue = new int[N*N];
		
		bfs();
		
		System.out.println(ans);
	}
}

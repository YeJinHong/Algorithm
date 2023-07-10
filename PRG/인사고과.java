package PRG;

import java.util.LinkedList;
import java.util.PriorityQueue;

// https://school.programmers.co.kr/learn/courses/30/lessons/152995?language=java

class Solution {
	class Score {
		int index, A, B;

		public Score(int index, int A, int B) {
			this.index = index;
			this.A = A;
			this.B = B;
		}

		public String toString() {
			return "[" + this.index + "]" + this.A + ", " + this.B;
		}
	}
	
	// 다른 임의의 사원보다 두 점수의 합이 모두 낮은 경우 True
	public boolean check(LinkedList<Score> highList, Score score) {
		for(Score high : highList) {
			if(high.A > score.A && high.B > score.B)
				return true;
		}
		return false;
	}

	public int solution(int[][] scores) {
		int answer = -1;
		// 두 점수의 합이 높은 순으로 정렬
		PriorityQueue<Score> pq = new PriorityQueue<>((s1, s2) -> (s2.A + s2.B) - (s1.A + s1.B));

		for (int i = 0; i < scores.length; i++) {
			Score score = new Score(i, scores[i][0], scores[i][1]);
			pq.add(score);
		}
		
		Score old = new Score(-1, -1, -1);
		LinkedList<Score> highList = new LinkedList<>();
		highList.add(new Score(-1, -1, -1));
		int rank = 0;
		int sameRankCount = 1;
		
		while(!pq.isEmpty()) {
			Score score = pq.poll();
//			System.out.println(score);
			// 인센티브를 받을 자격이 없는 경우
			// score보다 점수의 합이 높은 리스트에서 검사한다.
			// 어떤 사원이 다른 임의의 사원보다 두 점수가 모두 낮은 경우 => 임의의 사원은 두 점수의 합이 높은 사원 중에 있다.
			if(check(highList, score)) {
				highList.add(score);
				continue;
			}
			
			
			// 내 앞 순서와 비교한다. 인센티브를 받을 수 있으나 동급이 있는 경우
			if((old.A + old.B) == (score.A + score.B)) {
				sameRankCount++;
			} else { 
				rank += sameRankCount;
				sameRankCount = 1;
			}
			
			if(score.index == 0) {
				answer = rank;
				break;
			}
			
			highList.add(score);
			old = score;
			
		}

		return answer;
	}
}
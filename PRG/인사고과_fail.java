package PRG;

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
		
		LinkedList<Score> highList = new LinkedList<>();
		highList.add(new Score(-1, -1, -1));
		int rank = 0;
		int sameRankCount = 1;
		
		while(!pq.isEmpty()) {
			Score score = pq.poll();
//			System.out.println(score);
			// 인센티브를 받을 자격이 없는 경우
			if(check(highList, score)) {
				continue;
			}
			
			Score last = highList.getLast();
			// 인센티브를 받을 수 있으나 동급이 있는 경우
			if((last.A + last.B) == (score.A + score.B)) {
				sameRankCount++;
			} else {
				rank += sameRankCount;
				sameRankCount = 1;
			}
			
			if(score.index == 0) {
				answer = rank;
				break;
			}
		}

		return answer;
	}
}
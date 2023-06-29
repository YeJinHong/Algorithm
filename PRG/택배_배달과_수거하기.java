package PRG;

import java.util.ArrayDeque;

// https://school.programmers.co.kr/learn/courses/30/lessons/150369

class Solution {
	class Home {
		int distance;
		int count;

		public Home(int distance, int count) {
			this.distance = distance;
			this.count = count;
		}

		public String toString() {
			return "distacne : " + distance + " count : " + count;
		}
	}

	public long solution(int cap, int n, int[] deliveries, int[] pickups) {
		long answer = 0;

		// 남은 집들 중 가장 먼 곳에서부터 필요한 택배양을 최대 cap개 들고간다.
		// 끝에 도착할 때 모든 택배를 내려놓는다.
		// 돌아올때 끝에서부터 최대한 가져온다.

		ArrayDeque<Home> dq_del = new ArrayDeque<>();
		ArrayDeque<Home> dq_pick = new ArrayDeque<>();
		dq_del.add(new Home(0, 0)); // 구색 맞추기용
		dq_pick.add(new Home(0, 0));
		for (int i = 0; i < n; i++) {
			if (deliveries[i] != 0)
				dq_del.add(new Home((i + 1), deliveries[i]));
			if (pickups[i] != 0)
				dq_pick.add(new Home((i + 1), pickups[i]));
		}

		while (dq_del.size() > 1 || dq_pick.size() > 1) {
			// 배달할 곳과 수거할 곳 중 더 멀리있는 쪽이 현재 단계의 이동거리가 된다.
			int dist = Math.max(dq_del.peekLast().distance, dq_pick.peekLast().distance);
			int count_del = 0;
			int count_pick = 0;

			// 가면서 배달할 리스트를 확인한다.
			while (dq_del.size() > 1) {
				Home to_delivery = dq_del.peekLast();
				// 배달할 양이 트럭 공간보다 크다면, 남는 개수만큼만 들고 나른다.
				if (count_del + to_delivery.count > cap) {
					to_delivery.count -= cap - count_del;
					count_del = cap;
					break;
				}

				// 배달할 양을 충분히 트럭에 담을 수 있다면, 다음 배달할 리스트에서 제외한다.
				dq_del.pollLast();
				count_del += to_delivery.count;
			}

			// 돌아오면서 회수할 리스트를 확인한다.
			while (dq_pick.size() > 1) {
				Home to_pickup = dq_pick.peekLast();
				// 회수할 양이 트럭공간보다 크다면, 남는 개수만큼만 들고 나른다.
				if (count_pick + to_pickup.count > cap) {
					to_pickup.count -= cap - count_pick;
					count_pick = cap;
					break;
				}

				// 회수할 양을 충분히 트럭에 담을 수 있다면, 다음 회수할 리스트에서 제외한다
				dq_pick.pollLast();
				count_pick += to_pickup.count;
			}

			answer += dist * 2;
		}

		return answer;
	}
}


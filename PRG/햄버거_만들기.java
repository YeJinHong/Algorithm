package PRG;

import java.util.LinkedList;

// https://school.programmers.co.kr/learn/courses/30/lessons/133502#

class Solution {
	public int solution(int[] ingredient) {
		int answer = 0;

		// 1이 나오면 3회 검사한다 2, 3, 1인지
		// 1231이 있다면 앞의 숫자를 기준으로 다시 검사한다.
		LinkedList<Integer> list = new LinkedList<>();
		int index = 0;
		list.add(-1);
		list.add(-1);
		list.add(-1);

		String burger = "1231";
		while (index < ingredient.length) {
			// 123 1231 1

			// 핵심은 덮는 빵이 나왔을 때의 액션.

			// 재료를 놓는다.
			list.add(ingredient[index]);

			// 빵이 아니라면 계속 쌓는다.
			if (list.getLast() != 1) {
				index++;
				continue;
			}

			// 빵이 나왔다면, 뒤에서 4개의 재로가 햄버거와 일치하는지 확인한다.
			// 1 / 2 / 3 / 1
			// index-3 / index-2 / index-1 / index
			StringBuilder sb = new StringBuilder();
			for (int i = list.size() - 4; i < list.size(); i++) {
				sb.append(list.get(i));
			}
			
			// 햄버거가 된다면
			if(sb.toString().contentEquals(burger)) {
				answer++;
				// 햄버거를 만들고 난 후 해당 재료를 제거한다.
				for(int i = 0; i < 4; i++)
					list.pollLast();
			}
			index++;
		}

		return answer;
	}
}

// 시간 초과
//class Solution {
//    public int solution(int[] ingredient) {
//        int answer = 0;
//        StringBuilder sb = new StringBuilder();
//        for(int ingre : ingredient)
//        	sb.append(ingre);
//        String str = sb.toString();
////        System.out.println(str);
//        while(str.contains("1231")) {
//        	int index = str.indexOf("1231");
//        	String next = str.substring(0, index) + str.substring(index+4, str.length());
////        	System.out.println(str+" => "+next);
//        	answer++;
//        	str = next;
//        }
//        return answer;
//    }
//}

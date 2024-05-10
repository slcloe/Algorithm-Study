import java.io.*;
import java.util.*;

public class Main_bj_29737 {

    /*
    접근 방식 : 우선순위에 따른 정렬 문제로, 사람과 그 사람에 대한 스트릭을 저장해야하기 때문에 3차원 배열을 사용해야겠다고 생각함.
               100*100*7은 공간복잡도 상에서 충분히 괜찮을 것이라고 고려
    주안점
    1. 1 => 2 => 3 => 4 => 이름 오름차순의 순서로 정렬하되 이름을 제외한 다른 것이 다 같다면 출력 순위 값은 똑같아야 함.
    2. 최장 스트릭은 F로 시작하거나 끝날 수 없으므로 현재 계산한 값을 'O'를 만났을 때만 최종적으로 저장시켜야 함.
    3. 한 친구의 최장 스트릭이 여럿일 때는 스트릭 프리즈 횟수가 더 낮은 것을 고르는데, 날짜가 더 우선순위가 떨어지기 때문임.
       만약 프리즈 횟수가 같다면 자동적으로 전에 저장됐던 최장 스트릭이 우선순위임.
     */

    static int N, W;
    static char[][][] streak;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        // 최장 스트릭의 스트릭 프리즈 개수
        // 최장 스트릭의 시작 날짜
        // 스트릭 프리즈를 제외한 스트릭 실패 날짜 수

        // 각 친구의 스트릭 배열
        streak = new char[N][7][W];

        PriorityQueue<String> friend = new PriorityQueue<>();
        Map<Integer, String> nameMap = new HashMap<>();
        Map<String, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String F = br.readLine();
            idxMap.put(F, i);
            nameMap.put(i, F);
            friend.offer(F);
            for (int j = 0; j < 7; j++) {
                String S = br.readLine();
                for (int k = 0; k < W; k++) {
                    streak[i][j][k] = S.charAt(k);
                }
            }
        }

        // 가장 긴 스트릭의 길이가 같다면, 아니라면 오름차순
        // 가장 긴 스트릭 내의 스트릭 프리즈 수가 같다면, 아니라면 오름차순
        // 가장 긴 스트릭을 시작한 날짜가 같다면, 아니라면 오름차순
        // 총 실패한 날짜가 같다면, 아니라면 오름차순
        // 이름 오름차순
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (o1, o2) -> o2[1] == o1[1] ? (o1[3] == o2[3] ? (o1[2] == o2[2] ? (o1[4] == o2[4] ? o1[0]
                - o2[0] : o1[4] - o2[4]) : o1[2] - o2[2]) : o1[3] - o2[3]) : o2[1] - o1[1]);
        int dict = 1;
        while(!friend.isEmpty()) {
            String nowFriend = friend.poll();
            int idx = idxMap.get(nowFriend);
            int[] streakInfo = longestStreak(dict++, idx);
            pq.offer(streakInfo);
        }
        StringBuilder sb = new StringBuilder();
        rank(nameMap, pq, sb);
        System.out.print(sb.toString());
    }

    static void rank(Map<Integer, String> nameMap, PriorityQueue<int[]> pq, StringBuilder sb) {
        int order = 1;
        List<int[]> same = new ArrayList<>();
        while(!pq.isEmpty()) {
            if(same.isEmpty()) same.add(pq.poll());
            else {
                int[] first = same.get(0);
                int[] cur = pq.poll();
                if(first[1] == cur[1] && first[2] == cur[2] && first[3] == cur[3] && first[4] == cur[4]) {
                    same.add(cur);
                } else {
                    for(int[] arr : same) {
                        String name = nameMap.get(arr[5]);
                        sb.append(order).append(". ").append(name).append("\n");
                    }
                    same.clear();
                    same.add(cur);
                    order++;
                }
            }
        }
        for(int[] arr : same) {
            String name = nameMap.get(arr[5]);
            sb.append(order).append(". ").append(name).append("\n");
        }
    }

    static int[] longestStreak(int dict, int idx) {
        // 가장 긴 스트릭 크기
        int max = 0;
        // 가장 긴 스트릭이 시작한 날짜
        int startDate = 0;
        // 가장 긴 스트릭에서 스트릭 프리즈를 사용한 수
        int freezeCount = 0;
        // 총 실패한 수
        int failureCount = 0;

        // 현재 스트릭 수
        int now = 0;
        // 현재 스트릭의 시작 날짜
        int nowDate = 0;
        // 현재 스트릭의 스트릭 프리즈 횟수
        int nowFreeze = 0;

        // 스트릭이 깨지지 않았을 때, 현재까지의 now 종합
        int resultNow = 0;
        // 스트릭이 깨지지 않았을 때, 현재까지의 freeze 종합
        int resultFreeze = 0;

        for (int i = 0; i < W; i++) {
            for (int j = 0; j < 7; j++) {
                if (streak[idx][j][i] == 'O') {
                    if (now == 0) nowDate = 7 * i + j + 1;
                    resultNow = ++now;
                    resultFreeze = nowFreeze;
                } else if (streak[idx][j][i] == 'X') {
                    failureCount++;
                    // 만약 현재까지의 최장 스트릭이 max보다 크다면 대입
                    if (max < resultNow) {
                        max = resultNow;
                        startDate = nowDate;
                        freezeCount = resultFreeze;
                        // 현재까지의 최장 스트릭이 max와 같지만, freezeCount는 더 작다면 대입
                        // 시작 날짜보다 freezeCount가 더 우선순위에 있기 때문
                    } else if (max == resultNow && freezeCount > resultFreeze) {
                        freezeCount = resultFreeze;
                        startDate = nowDate;
                    }
                    now = 0;
                    nowFreeze = 0;
                    resultNow = 0;
                    resultFreeze = 0;
                } else {
                    if (now > 0) nowFreeze++;
                }
            }
        }
        if (max < resultNow) {
            max = resultNow;
            startDate = nowDate;
            freezeCount = resultFreeze;
        } else if (max == resultNow && freezeCount > resultFreeze) {
            freezeCount = resultFreeze;
            startDate = nowDate;
        }
        return new int[]{dict, max, startDate, freezeCount, failureCount, idx};
    }
}

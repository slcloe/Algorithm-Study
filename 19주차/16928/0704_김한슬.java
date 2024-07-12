import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
[문제 풀이]

1. ladder, snack 정보는 hashmap에 저장하여 조회 시 성능 o(1) 로
2. pq 를 사용 
    정렬 기준 :  1) depth 오름차순 2) 현재 위치 내림차순
3. 현재 위치 기준으로 1 ~ 6을 더한값 방문
4. visited 배열을 사용하여 이전에 방문한 위치는 재방문 하지 않음.
5. 다음 위치가 100인 순간 반복문 break 후 정답 출력

 */

public class Main {

    static int N, M;
    static HashMap<Integer, Integer> ladder, snack;
    static boolean v[];

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        v = new boolean[101];

        ladder = new HashMap<>();
        snack = new HashMap<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            ladder.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            snack.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[0] == o2[0]) return -Integer.compare(o1[1], o2[1]);
            else return Integer.compare(o1[0], o2[0]);
        }); // depth position

        pq.offer(new int[] {0, 1});
        v[1] = true;

        int result = 0;
        while(!pq.isEmpty()){
            int[] cur = pq.poll();

            int next = 0;
            for(int i = 1; i <= 6; i++) {
                next = cur[1] + i;

                if (next == 100) {
                    result = cur[0] + 1;
                    break;
                }
                if (v[next]) continue;
                
                if (ladder.containsKey(next))
                    next = ladder.get(next);
                if (snack.containsKey(next)) 
                    next = snack.get(next);

                if (v[next]) continue;
                v[next] = true;
                v[cur[1] + i] = true;
                pq.offer(new int[] {cur[0] + 1, next});
            }

            if (next == 100) break;
        }
        System.out.println(result);
    }
}


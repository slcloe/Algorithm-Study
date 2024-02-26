import java.io.*;
import java.util.*;
public class 0227_곽희웅 {

    /*
    접근 방식 : 1. 단순 dfs 및 방문 처리로 했을 때 실패
               2. 위상 정렬을 dfs로 구현하려 진입 차수가 0인 문제를 방문한 후 다음 풀면 좋은 문제들의 차수를 -1하면서
                  현재 값과 크기를 비교하여 작은 것들만 담았는데, 끝까지 탐색할 때까지 계속하여 비교해줘야 하는 N^2이 돼버림
               3. PriorityQueue를 사용하여 문제에서 3 조건을 만족하고, 진입 차수를 줄여가며 0인 문제는 pq에 넣음으로써 2 조건을 만족함
    자료 구조 : PriorityQueue를 사용하여 가장 문제 번호가 작은 문제부터 완료할 수 있도록 하였고, List 배열을 통해 문제들간의 관계 설정

    주안점
    1. 단순 방문 처리만 한다면 중복되는 수가 먼저 풀어야 하는 수를 2개 이상 가질 때 오류가 발생
    2. dfs로 구현할 때는 현재 수와의 크기를 비교한 3 조건을 유의해야 함
    */

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<Integer>[] g = new List[N+1];
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int[] v = new int[N+1];

        for(int i=1; i<=N; i++) {
            g[i] = new ArrayList<>();
        }
        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int first = Integer.parseInt(st.nextToken());
            int second = Integer.parseInt(st.nextToken());
            g[first].add(second);
            v[second]++;
        }
        for(int i=1; i<=N; i++) {
            if(v[i] == 0) pq.offer(i);
        }
        while(!pq.isEmpty()) {
            int target = pq.poll();
            sb.append(target).append(" ");
            for(int cur : g[target]) {
                if(--v[cur] == 0) pq.offer(cur);
            }
        }
        System.out.print(sb.toString().trim());
    }
}

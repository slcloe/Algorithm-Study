package week3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 풀이방식
 *
 * 1트
 * dfs
 * 완전 탐색을 사용함.
 * 하지만 시간 초과가 나서 weight 과 현재까지 갱신된 result의 조건문을 추가했지만
 * 6% 까지 가고 실패
 *
 * 2트
 * bfs
 * 완전 탐색을 사용함.
 * 하지만 bfs 는 모든 경우에 대한 visited 배열이 필요하기 때문에
 * 메모리 초과 남.
 *
 * 3트
 * MST
 * 다익스트라를 써서 구현해보려고 했지만 실패 ㅠ
 */

public class week3_13905_0314_김한슬 {
    static int N, M;
    static int s, e;
    static ArrayList<int []>[] g;
    static boolean[] v;
    static int result = Integer.MIN_VALUE;
    static void dfs(int x, int weight){
        if (weight <= result) return;
        if (x == e){
            result = Math.max(result, weight);
            return;
        }

        for(int[] cur : g[x]){
            if (v[cur[0]]) continue;
            v[cur[0]] = true;
            dfs(cur[0], Math.min(weight, cur[1]));
            v[cur[0]] = false;
        }
    }


    static class road{
        int cur;
        int weight;
//        boolean[] v;

        road(int cur, int weight){
            this.cur = cur;
            this.weight = weight;
//            v = new boolean[N + 1];
//            for (int i = 0; i < N; i++) {
//                this.v[i] = visited[i];
//            }
//            this.v[cur] = true;
        }
    }
    static int bfs(){
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> (o2[1] - o1[1]));

        boolean[] visited = new boolean[N + 1];
        int[] maxEdge = new int[N + 1];
        Arrays.fill(maxEdge, Integer.MIN_VALUE);
        maxEdge[s] = 0;
        pq.offer(new int[]{s, maxEdge[s]});

        while(!pq.isEmpty()){
            int[] cur = pq.poll();
            visited[cur[0]] = true;

            if (cur[0] == e)
                return cur[1];

            for(int[] edge : g[cur[0]]){
                if (!v[edge[0]] && maxEdge[edge[0]] < maxEdge[cur[0]] + edge[1]){
                    maxEdge[edge[0]] = maxEdge[cur[0]] + edge[1];
                    pq.offer(new int[] {edge[0], maxEdge[edge[0]]});
                }

                pq.offer(new road(cur[0], Math.min(r.weight, cur[1])));
            }
        }
        return -1;
    }

//    static void MST(){
//        int[] minEdge = new int[N];
//        boolean[] visit = new boolean[N];
//
//        Arrays.fill(minEdge, Integer.MAX_VALUE);
//
//
//
//
//    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        s = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        g = new ArrayList[N + 1];
        for (int i = 0; i <= N; i++) {
            g[i] = new ArrayList<>();
        }
        v = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            g[to].add(new int[] {from, weight});
            g[from].add(new int[] {to, weight});
        }

//        v[s] = true;
//        dfs(s, Integer.MAX_VALUE);
        System.out.println(bfs());
    }
}

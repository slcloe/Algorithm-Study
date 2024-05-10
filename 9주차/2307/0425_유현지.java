/*
 * | 활용 알고리즘 | Dijkstra
 *
 * | 활용 자료구조 | PriorityQueue, ArrayList
 *
 * | 접근 방법 |
 *  1. 원래 그래프 상태로 다익스트라를 통해 1부터 N까지 가는 최단 시간을 구한다
 *     1-1. 다익스트라를 수행하며 최단 경로까지 가는 데에 직전 정점을 갱신한다
 *  2. 최단 경로를 이루는 간선들에 대해 하나씩 없다고 가정하며 다익스트라를 수행한다
 *  3. 각 케이스마다 최대 지연 시간을 갱신한다
 *     3-1. 도착지까지 갈 수 없는 경우라면 -1로 갱신한 후 즉시 종료한다
 */

package a2404.study.week9;

import java.io.*;
import java.util.*;

public class bj_g1_2307_도로검문 {
    static int[] arrival, parent;
    static boolean[] v;
    static List<int[]>[] graph;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        arrival = new int[N+1];
        parent = new int[N+1];
        v = new boolean[N+1];

        // set initial value
        for(int n=1; n<=N; n++){
            graph[n] = new ArrayList();
            arrival[n] = Integer.MAX_VALUE;
        }

        // make graph
        for(int m=0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            graph[a].add(new int[] {b, t});
            graph[b].add(new int[] {a, t});
        }

        // get initial shortest path
        Dijkstra(-1, -1);
        int origin = arrival[N];

        // get the shortest path without one edge of the initial shortest path
        int idx = N;
        int answer = 0;
        while(parent[idx] > 0){
            for(int n=1; n<=N; n++){
                arrival[n] = Integer.MAX_VALUE;
                v[n] = false;
            }
            Dijkstra(idx, parent[idx]);

            // don't have to proceed
            if(arrival[N]==Integer.MAX_VALUE) {
                answer = -1;
                break;
            }
            answer = Math.max(answer, arrival[N]-origin);
            idx = parent[idx];
        }
        System.out.println(answer);
    }

    static void Dijkstra(int idx, int pIdx){
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1]-o2[1]);
        pq.offer(new int[] {1, 0});
        arrival[1] = 0;

        while(!pq.isEmpty()){
            int vertex = pq.peek()[0];
            int time = pq.peek()[1];
            pq.poll();
            if(v[vertex]){ continue; }
            v[vertex] = true;

            for(int[] neighbor: graph[vertex]){
                int nVertex = neighbor[0];
                int nTime = neighbor[1];
                if(v[nVertex] || (vertex==idx && nVertex==pIdx) || (vertex==pIdx && nVertex==idx) || arrival[nVertex] < time + nTime){ continue; }
                if(idx == -1){ parent[nVertex] = vertex; }
                arrival[nVertex] = time + nTime;
                pq.offer(new int[] {nVertex, arrival[nVertex]});
            }
        }
    }
}
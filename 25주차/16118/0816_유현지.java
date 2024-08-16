/*
 * | 활용 알고리즘 | Dijkstra
 *
 * | 접근 방법 |
 *  1. 여우는 기본적인 다익스트라를 수행한다
 *  2. 늑대는 2차원 배열로 만들어 조건에 따라 다익스트라를 수행한다
 *     2-1. 번갈아가며 속도가 2배, 1/2배로 바뀌므로 다음 지점까지의 누적 거리를 구할 때 유의한다
 */

package a2408.study.week25;

import java.io.*;
import java.util.*;

public class bj_g1_16118_달빛_여우 {
    static int N, M, MAX_VALUE = 999_999_999;
    static int[] fox;
    static double[][] wolf;
    static ArrayList<int[]>[] graph;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N+1];
        fox = new int[N+1];
        wolf = new double[2][N+1];
        for(int n=1; n<=N; n++){ graph[n] = new ArrayList<>(); }
        for(int m=0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            graph[a].add(new int[] {b, d});
            graph[b].add(new int[] {a, d});
        }

        runFox();
        runWolf();
        int answer = 0;
        System.out.println(Arrays.toString(fox));
        System.out.println(Arrays.toString(wolf[0]));
        System.out.println(Arrays.toString(wolf[1]));
        for(int n=2; n<=N; n++){
            if(fox[n] < Math.min(wolf[0][n], wolf[1][n])){ answer++; }
        }
        System.out.println(answer);
    }

    static void runFox(){
        Arrays.fill(fox, MAX_VALUE);
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1]-o2[1]);
        pq.offer(new int[] {1, 0});
        fox[1] = 0;

        while(!pq.isEmpty()){
            int[] now = pq.poll();
            int n = now[0];
            int d = now[1];
            if(fox[n] < d){ continue; }
            for(int[] g: graph[n]){
                int nn = g[0];
                int nc = g[1];
                if(fox[nn] <= fox[n] + nc){ continue; }
                fox[nn] = fox[n] + nc;
                pq.offer(new int[] {nn, fox[nn]});
            }
        }
    }

    static void runWolf(){
        for(int n=0; n<=1; n++) {
            Arrays.fill(wolf[n], MAX_VALUE);
        }
        PriorityQueue<double[]> pq = new PriorityQueue<>((o1, o2) -> (int) (o1[2]-o2[2]));
        pq.offer(new double[] {0, 1, 0});
        wolf[0][1] = 0;

        while(!pq.isEmpty()) {
            double[] now = pq.poll();
            int t = (int) now[0];
            int n = (int) now[1];
            double d = now[2];
            if(wolf[t][n] < d){ continue; }
            int nt = 1 - t;
            for (int[] g : graph[n]) {
                int nn = (int) g[0];
                int nd = (int) g[1];
                double nextDist = d + (t == 1 ? nd*2 : nd/2.0);
                if(wolf[nt][nn] <= nextDist){ continue; }
                wolf[nt][nn] = nextDist;
                pq.offer(new double[]{nt, nn, nextDist});
            }
        }
    }
}

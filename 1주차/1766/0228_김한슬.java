package week1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
public class week1_1766_0228_김한슬 {

    static int N, M;
    static int[] inDegree;
    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        inDegree = new int[N + 1];
        ArrayList<Integer>[] g = new ArrayList[N + 1];

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (int i = 1; i <= N; i++) {
            g[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            g[a].add(b);
            inDegree[b]++;
        }
        for (int i = 1; i <= N ; i++) {
            if(inDegree[i] == 0) pq.offer(i);
        }
        while(!pq.isEmpty()) {
            int cur = pq.poll();
            sb.append(cur).append(" ");
            for(int a : g[cur]){
                inDegree[a]--;
                if(inDegree[a] == 0) pq.offer(a);
            }
        }
        System.out.println(sb.toString());
    }
}



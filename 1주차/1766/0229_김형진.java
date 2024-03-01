package week1;

import java.io.*;
import java.util.*;

// 문제집
public class BOJ1766 {
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<List<Integer>> solves = new ArrayList<>();
        int[] inDegree = new int[N+1];

        for (int i = 0; i <= N; i++) {
            solves.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            solves.get(start).add(end);
            inDegree[end] ++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if(inDegree[i]==0){
                pq.offer(i); // 풀수있는것만
            }
        }

        StringBuilder sb= new StringBuilder();
        while(!pq.isEmpty()){
            int current = pq.poll();

            sb.append(current).append(" ");

            for(int target : solves.get(current)){
                inDegree[target] --;
                if(inDegree[target] == 0){
                    pq.offer(target); // 풀수있어
                }
            }
        }

        System.out.println(sb);
    }
}

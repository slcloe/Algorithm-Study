package a2403.week2;

/*
 * | 활용 알고리즘 | Dijkstra
 * | 접근 방법 |   
 *  1. 출발지에서부터 다익스트라 시작
 *  2. g/h중 하나에 도착할 경우, 해당 정점에서 다음에 가야 할 곳운 h/g여야 함
 *     맞다면 답에 추가한 다음 테스트 케이스 종료, 틀리다면 즉시 종료
 */

import java.io.*;
import java.util.*;

public class bj_g2_9370_미확인_도착지 {
    static class Node{
        int num;
        boolean v;
        ArrayList<int[]> related;

        public Node(int num){
            this.num = num;
            this.v = false;
            this.related = new ArrayList<>();
        }

        @Override
        public String toString(){
            StringBuilder sb = new StringBuilder(num+" | ");
            for(int[] r: related){
                sb.append(r[0]).append("[").append(r[1]).append("] ");
            }
            return sb.toString();
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());
        Node[] circles;
        for(int tc=0; tc<TC; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int T = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int G = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());

            circles = new Node[N+1];
            for(int n=0; n<=N; n++){ circles[n] = new Node(n); }

            for(int m=0; m<M; m++){
                st = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(st.nextToken());
                int B = Integer.parseInt(st.nextToken());
                int D = Integer.parseInt(st.nextToken());
                circles[A].related.add(new int[] {B, D});
                circles[B].related.add(new int[] {A, D});
            }
            for(Node c: circles){
                System.out.println(c.toString());
            }
            int[] possible = new int[T];
            for(int t=0; t<T; t++){ possible[t] = Integer.parseInt(br.readLine()); }

        }

    }

    static void dijkstra(int start){

    }
}

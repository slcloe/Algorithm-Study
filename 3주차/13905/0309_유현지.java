package a2403.week2;

/*
 * | 활용 자료구조 | Comparable Class, Priority Queue
 * | 활용 알고리즘 | Union-Find, Kruskal
 * | 접근 방법 |
 *  1. 다리들을 제한 무게 기준으로 내림차순 정렬
 *  2. 사이클을 만들지 않는 경우에 한해 다리를 하나씩 선택
 *  3. 다리를 붙일 때마다 시작 지점에서부터 종료 지점까지 갈 수 있는지 체크
 *  4. 갈 수 있다면 가장 마지막에 붙인 다리의 제한 무게가 정답
 */

import java.io.*;
import java.util.*;

public class bj_g4_13905_세부 {
    static class Bridge implements Comparable<Bridge>{
        int i1;
        int i2;
        int w;

        public Bridge(int i1, int i2, int w){
            this.i1 = i1;
            this.i2 = i2;
            this.w = w;
        }

        @Override
        public int compareTo(Bridge o){
            return o.w - this.w;
        }

        @Override
        public String toString(){
            return "| "+i1+" - "+i2+" | "+w+" |";
        }
    }
    static int[] p;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = 0;
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        p = new int[N+1];
        for(int n=0; n<=N; n++){ p[n] = n; }

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        PriorityQueue<Bridge> pq = new PriorityQueue<>();
        for(int m=0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int h1 = Integer.parseInt(st.nextToken());
            int h2 = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            pq.offer(new Bridge(h1, h2, k));
        }

        while(!pq.isEmpty()){
            Bridge now = pq.poll();
            if(find(now.i1) == find(now.i2)){ continue; }
            union(now.i1, now.i2);
            if(find(S) == find(E)){
                answer = now.w;
                break;
            }
        }
        System.out.println(answer);
    }

    static int find(int x){
        if(p[x] == x){
            return x;
        }
        return p[x] = find(p[x]);
    }

    static void union(int x, int y){
        if(find(x) == find(y)){ return; }
        p[find(y)] = find(x);
    }
}

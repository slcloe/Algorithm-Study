package a2403;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class d09_bj_g4_13905_세부 {
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

        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        PriorityQueue<Bridge> pq = new PriorityQueue<>();
        for(int n=0; n<=N; n++){ p[n] = n; }
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

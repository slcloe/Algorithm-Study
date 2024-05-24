package solved;

import java.io.*;
import java.util.*;


public class BOJ_18405 {
    static int N,K,S,X,Y;
    static int[][] map;

    static int[] di= {-1, 0, 1, 0};
    static int[] dj= { 0, 1, 0, -1};
    static class Virus implements Comparable<Virus>{
        int i;
        int j;
        int n;
        int s;

        public Virus(int n, int i, int j, int s) {
            this.n = n;
            this.i = i;
            this.j = j;
            this.s = s;
        }

        @Override
        public int compareTo(Virus v){
            return this.s==v.s ? this.n- v.n : this.s-v.s;
        }
    }

    static void BFS(){
        PriorityQueue<Virus> q = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < K; j++) {
                if(map[i][j]>0){
                    q.offer(new Virus(map[i][j],i,j,0));
                }
            }
        }
        while(!q.isEmpty()){
            Virus cur = q.poll();
            int nn = cur.n;
            int ii = cur.i;
            int jj = cur.j;
            int ss = cur.s;

            if(ss==S) break;
//            System.out.println(ss+"초 : "+nn+",("+ii+","+jj+")");

            for (int d = 0; d < 4; d++) {
                int ni = ii + di[d];
                int nj = jj + dj[d];

                if(ni>=0 && ni<N && nj>=0 && nj<K && map[ni][nj] == 0){
                    map[ni][nj] = nn;
                    q.offer(new Virus(nn,ni,nj,ss+1));
                }
            }
        }
    }
    public static void main(String[] args)throws IOException {
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int cnt = 0;
        map = new int[N][K];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                cnt ++;
            }
        }
        st= new StringTokenizer(br.readLine());
        S= Integer.parseInt(st.nextToken());
        X= Integer.parseInt(st.nextToken());
        Y= Integer.parseInt(st.nextToken());
        BFS();

        System.out.println(map[X-1][Y-1]);
    }
}

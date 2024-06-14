/*
 * | 활용 알고리즘 | 구현
 */
package a2406.study.week16;

import java.util.*;
import java.io.*;

public class bj_g5_21610_마법사_상어와_비바라기 {
    static int N, M;
    static int [] dirR = {0, -1, -1, -1, 0, 1, 1 ,1},
            dirC = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[][] map;
    static List<int[]> cloud = new ArrayList<int[]>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            for(int nn=0; nn<N; nn++) map[n][nn] = Integer.parseInt(st.nextToken());
        }
        cloud.add(new int[]{N-2, 0}); cloud.add(new int[]{N-2, 1});
        cloud.add(new int[]{N-1, 0}); cloud.add(new int[]{N-1, 1});

        for(int m=0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken())-1;
            int s = Integer.parseInt(st.nextToken());
            bibaraki(d, s);
        }
        int answer = 0;
        for(int n=0; n<N; n++) for(int nn=0; nn<N; nn++) answer += map[n][nn];
        System.out.println(answer);
    }

    private static void bibaraki(int d, int s){
        moveCloud(d, s);
        waterCopy();
        makeCloud();
    }

    private static void moveCloud(int d, int s) {
        for(int[] c: cloud){
            for(int ss=0; ss<s; ss++){
                c[0] = (c[0] + dirR[d]+N)%N;
                c[1] = (c[1] + dirC[d]+N)%N;
            }
            map[c[0]][c[1]]++;
        }
    }

    private static void waterCopy() {
        for(int[] c: cloud){
            for(int d=1; d<8; d+=2){
                int dr = c[0] + dirR[d];
                int dc = c[1] + dirC[d];
                if(0<=dr&&dr<N && 0<=dc&&dc<N && map[dr][dc]!=0) map[c[0]][c[1]]++;
            }
        }
    }

    private static void makeCloud() {
        boolean[][] isCloud = new boolean[N][N];
        for(int[] c: cloud) isCloud[c[0]][c[1]] = true;
        cloud.clear();
        for(int nr=0; nr<N; nr++){
            for(int nc=0; nc<N; nc++){
                if(map[nr][nc]>=2 && !isCloud[nr][nc]){
                    map[nr][nc] -= 2;
                    cloud.add(new int[]{nr, nc});
                }
            }
        }
    }
}

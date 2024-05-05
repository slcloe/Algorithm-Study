/*
 * | 활용 알고리즘 | Kruskal
 *
 * | 활용 자료구조 | PriorityQueue
 *
 * | 접근 방법 |
 *  1. 각 간선을 우선 순위 큐에 넣는다
 *     우선 순위는 문을 여는 날 기준 오름차순
 *  2. 크루스칼 알고리즘으로 학원을 들르는 경로를 찾는다
 *  3. 찾는 과정에서 간선이 연결될 때마다 두 과정을 반복한다
 *     3-1. 현재 일자를 증가시킨다
 *     3-2. 해당 간선의 디저트 노점이 문 여는 날이 현재 일자와 동일한지 확인한다
 *  4. 동일하지 않다면 그 날 키위새가 쓰러지므로 cnt를 출력한다
 *  5. 마지막까지 동일하다면 그 다음날 키위새가 쓰러지므로 cnt+1을 출력한다
 */

package a2405.study.week11;

import java.io.*;
import java.util.*;

public class bj_g3_27945_슬슬_가지를_먹지_않으면_죽는다 {
    static int[] parent;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->o1[2]-o2[2]);;
        parent = new int[N+1];
        for(int n=1; n<=N; n++){ parent[n] = n; }
        for(int m=0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            pq.offer(new int[]{x, y, z});
        }
        int cnt = 0;
        while(!pq.isEmpty()){
            int[] now = pq.poll();
            int x = now[0];
            int y = now[1];
            int z = now[2];
            if(union(x, y)){
                if(z != ++cnt){
                    System.out.println(cnt);
                    return;
                }
            }
        }
        System.out.println(cnt+1);
    }

    static int find(int x){
        if(parent[x] == x){ return x; }
        return parent[x] = find(parent[x]);
    }

    static boolean union(int x, int y){
        int px = find(x), py = find(y);
        if(px == py){ return false; }
        parent[px] = py;
        return true;
    }
}

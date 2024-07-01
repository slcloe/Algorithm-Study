/*
 * | 활용 알고리즘 | BFS
 *
 * | 활용 자료구조 | Queue
 *
 * | 접근 방법 |
 *  1. 크기가 101인 보드를 만든다
 *  2. 뱀, 사다리 칸은 이동해야 할 칸을 저장한다
 *  3. 1번 칸, 0번 이동을 초기값으로 Queue에 넣는다
 *  4. BFS로 주사위 1~6까지를 처리한
 */

package a2407.study.week19;

import java.io.*;
import java.util.*;

public class bj_g5_16928_뱀과_사다리_게임 {
    public static void main(String[] args) throws Exception{
        int[] board = new int[101];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for(int n=0; n<N; n++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x] = y;
        }
        for(int m=0; m<M; m++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            board[u] = v;
        }
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offerLast(new int[] {1, 0});
        board[0] = board[1] = -1;
        while(!queue.isEmpty()){
            int[] now = queue.pollFirst();
            if(now[0] == 100){
                System.out.println(now[1]);
                return;
            }
            for(int d=1; d<=6; d++){
                int nd = now[0] + d;
                if(100 < nd){ break; }
                if(0 < board[nd]){ nd = board[nd]; }
                if(board[nd] == -1){ continue; }
                queue.offerLast(new int[] {nd, now[1]+1});
                board[nd] = -1;
            }
        }
    }
}

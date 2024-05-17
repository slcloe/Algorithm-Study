/*
 * | 활용 알고리즘 | BFS
 *
 * | 활용 자료구조 | PriorityQueue
 *
 * | 접근 방법 |
 *  1. (0, 0)에서 (N-1, N-1)까지 가는 방법은 오른쪽으로, 아래로 출발하는 경우 두가지이다
 *  2. 각각의 방향부터 시작하는 BFS를 수행한다
 *  3. BFS를 수행하며 이전 방향과 같은 방향으로 이동하면 100원, 다른 방향으로 이동하면 600원을 더한다
 *  4. (N-1, N-1)에 도달하면 최소 비용을 갱신한다
 */

package a2405.study.week12;

import java.util.*;

public class pr_3_67259_경주로_건설 {
    static int N, minPrice = Integer.MAX_VALUE;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};

    public int solution(int[][] board) {
        int answer = 0;
        N = board.length;
        BFS(0, 0, 2, 0, board);
        BFS(0, 0, 1, 0, board);
        answer = minPrice;
        return answer;
    }

    static boolean isOut(int r, int c){
        return r<0 || N<=r || c<0|| N<=c;
    }

    static void BFS(int R, int C, int D, int P, int[][] board){
        boolean[][][] v = new boolean[N][N][4];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)->o1[3]-o2[3]);
        pq.offer(new int[] {R, C, D, P});
        v[R][C][D] = true;

        while(!pq.isEmpty()){
            int[] now = pq.poll();
            int r = now[0];
            int c = now[1];
            int prevD = now[2];
            int price = now[3];
            if(minPrice <= price){ continue; }
            if(r==N-1 && c==N-1){
                minPrice = Math.min(minPrice, price);
                continue;
            }
            for(int d=0; d<4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(isOut(nr, nc) || board[nr][nc] == 1 || v[nr][nc][d]){ continue; }
                int priceNew = prevD == d? price + 100 : price + 600;
                pq.offer(new int[] {nr, nc, d, priceNew});
            }
            v[r][c][prevD] = true;
        }
    }
}
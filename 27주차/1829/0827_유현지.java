/*
 * | 활용 알고리즘 | BFS
 *
 * | 접근 방법 |
 *  1. 색칠되어 있으면서 방문하지 않은 영역이면 BFS를 수행한다
 *  2. BFS는 같은 색으로 연결된 영역의 크기를 반환한다
 *  3. BFS를 수행할 때마다 영역의 수를 증가시키고 영역의 최대 크기를 갱신한다
 */

package a2408.study.week27;

import java.util.*;

public class pr_2_1829_카카오프렌즈_컬러링북 {
    static int R, C;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    static boolean[][] visit;

    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        R = m;
        C = n;
        visit = new boolean[R][C];

        for(int r=0; r<R; r++){
            for(int c=0; c<C; c++){
                if(picture[r][c]==0 || visit[r][c]){ continue; }
                numberOfArea++;
                maxSizeOfOneArea = Math.max(maxSizeOfOneArea, BFS(picture, r, c));
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }

    static boolean isOut(int r, int c){
        return r<0 || R<=r || c<0 || C<=c;
    }

    static int BFS(int[][] picture, int rr, int cc){
        int size = 1;
        int n = picture[rr][cc];
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.offerLast(new int[] {rr, cc});
        visit[rr][cc] = true;

        while(!queue.isEmpty()){
            int[] now = queue.pollFirst();
            int r = now[0];
            int c = now[1];
            for(int d=0; d<4; d++){
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(isOut(nr, nc) || visit[nr][nc] || picture[nr][nc]!=n){ continue; }
                queue.offerLast(new int[] {nr, nc});
                visit[nr][nc] = true;
                size++;
            }
        }
        return size;
    }
}

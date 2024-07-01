/*
 * | 활용 알고리즘 | BFS
 *
 * | 활용 자료구조 | Queue, ArrayList
 *
 * | 접근 방법 |
 *  1. 미방문 숫자 칸에 대해 BFS를 수행한다
 *  2. 각 섬에서 지낼 수 있는 기간을 ArrayList에 저장한다
 *  3. ArrayList가 비었을 경우 -1, 비어 있지 않을 경우 정렬하여 반환한다
 */

package a2407.study.week19;

import java.util.*;

public class pr_2_154540_무인도_여행 {
    public Integer[] solution(String[] maps) {
        Integer[] answer = {-1};

        int R = maps.length;
        int C = maps[0].length();
        int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
        boolean[][] visit = new boolean[R][C];
        ArrayList<Integer> list = new ArrayList<>();

        for(int r=0; r<R; r++){
            for(int c=0; c<C; c++){
                char character = maps[r].charAt(c);
                int num = character - '0';
                if(1 <= num && num <= 9 && !visit[r][c]){
                    ArrayDeque<int[]> queue = new ArrayDeque<>();
                    queue.offerLast(new int[] {r, c, num});
                    visit[r][c] = true;
                    int total = 0;

                    while(!queue.isEmpty()){
                        int[] now = queue.pollFirst();
                        total += now[2];
                        for(int d=0; d<4; d++){
                            int nr = now[0] + dr[d];
                            int nc = now[1] + dc[d];
                            if(nr<0 || R<=nr || nc<0 || C<=nc || maps[nr].charAt(nc)=='X' || visit[nr][nc]){ continue; }
                            int nf = maps[nr].charAt(nc) - '0';
                            queue.offerLast(new int[] {nr, nc, nf});
                            visit[nr][nc] = true;
                        }
                    }
                    list.add(total);
                }
            }
        }

        if(!list.isEmpty()){
            System.out.println(list);
            answer = list.toArray(new Integer[0]);
            Arrays.sort(answer);
        }

        return answer;
    }
}

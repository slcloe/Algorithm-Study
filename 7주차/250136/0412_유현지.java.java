/*
 * | 활용 알고리즘 | BFS
 *
 * | 활용 자료구조 | Queue, HashSet
 *
 * | 접근 방법 |
 *  1. 맵의 가로(m) 크기의 일차원 정수 배열(이하 total_amount)을 선언한다
 *     해당 배열은 각 열에 시추관을 내렸을 때 얻을 수 있는 석유의 총량을 저장한다
 *  2. 맵을 돌며 미방문 석유를 발견할 때마다 석유 영역을 BFS로 탐색한다
 *  3. BFS를 수행하는 과정에서 두 가지를 갱신한다
 *     3-1. 해당 석유 영역이 얼마만큼의 크기인지(increase)
 *     3-2. 해당 석유 영역이 어떤 열에 걸쳐져 있는지(HashSet에 방문한 열 add)
 *  4. 전체 맵에 대해 2번 과정을 완료한 다음, total_amount의 최댓값이 답이 된다
 */

package a2404.study.week7;

import java.util.*;

public class pr_2_250136_석유_시추 {

    class Solution {
        int n, m;
        int[] total_amount, dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
        boolean[][] v;

        public int solution(int[][] land) {
            int answer = 0;
            n = land.length;
            m = land[0].length;
            total_amount = new int[m];
            v = new boolean[n][m];

            for(int r=0; r<n; r++){
                for(int c=0; c<m; c++){
                    if(land[r][c] == 1 && !v[r][c]){
                        BFS(land, r, c);
                    }
                }
            }

            for(int i=0; i<m; i++){
                answer = Math.max(answer, total_amount[i]);
            }

            return answer;
        }
        private void BFS(int[][] land, int r, int c){
            int amount = 1;
            ArrayDeque<int[]> queue = new ArrayDeque();
            HashSet<Integer> columns = new HashSet();

            v[r][c] = true;
            queue.offerLast(new int[] {r, c});
            columns.add(c);

            while(!queue.isEmpty()){
                int[] now = queue.pollFirst();
                int nowR = now[0];
                int nowC = now[1];

                for(int d=0; d<4; d++){
                    int nr = nowR + dr[d];
                    int nc = nowC + dc[d];

                    if(nr<0 || n<=nr || nc<0 || m<=nc || land[nr][nc] == 0 || v[nr][nc]){ continue; }
                    v[nr][nc] = true;
                    queue.offerLast(new int[] {nr, nc});
                    columns.add(nc);
                    amount++;
                }
            }
            for(int column: columns){
                total_amount[column] += amount;
            }
        }
    }
}

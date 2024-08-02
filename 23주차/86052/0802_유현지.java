/*
 * | 활용 자료구조 | Priority Queue, Node Array
 *
 * | 접근 방법 |
 *  1. 방향, 4방향 방문 여부를 저장하는 노드를 만든다
 *  2. 모든 노드에 대해 모든 방향에서 빛을 쏴서 사이클 길이를 체크한다
 *  3. 사이클이 완성되었다면 우선순위 큐에 사이클 길이를 넣는다
 *  4. 사이클의 개수는 우선순위 큐의 사이즈, 우선순위 큐를 하나씩 poll 해서 배열에 넣는다
 */

package a2407.study.week23;

import java.util.*;

public class pr_2_86052_빛의_경로_사이클 {
    static class Node{
        char direction;
        boolean[] visit;

        public Node(char direction){
            this.direction = direction;
            this.visit = new boolean[4];
        }
    }

    static PriorityQueue<Integer> pq = new PriorityQueue<>();
    static Node[][] map;
    static int R, C;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};

    public int[] solution(String[] grid) {
        R = grid.length;
        C = grid[0].length();
        map = new Node[R][C];
        for(int r=0; r<R; r++){
            for(int c=0; c<C; c++){
                map[r][c] = new Node(grid[r].charAt(c));
            }
        }

        for(int r=0; r<R; r++){
            for(int c=0; c<C; c++){
                for(int d=0; d<4; d++){
                    if(map[r][c].visit[d]){ continue; }
                    travel(r, c, d);
                }
            }
        }

        int S = pq.size();
        int[] answer = new int[S];
        for(int s=0; s<S; s++){
            answer[s] = pq.poll();
        }
        return answer;
    }

    static void travel(int rr, int cc, int D){
        int length = 0, r = rr, c = cc, d = D;
        while(!map[r][c].visit[d]){
            map[r][c].visit[d] = true;
            length++;
            switch(map[r][c].direction){
                case 'L':
                    d = (4 + d - 1) % 4;
                    break;
                case 'R':
                    d = (4 + d + 1) % 4;
                    break;
            }
            r = (R + r + dr[d]) % R;
            c = (C + c + dc[d]) % C;
        }
        pq.offer(length);
    }
}

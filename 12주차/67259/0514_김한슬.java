import java.util.*;

/*
문제 풀이

1. 우선순위 큐를 사용하여 이전 방향과 price 를 저장해 price 오름차순으로 탐색 진행
2. 방향 규칙 : idx % 2 == 0 가로 이동 / idx % 2 == 1 세로 이동 
3. 이후 먼저 N-1, N-1 에 도착하면 함수 종료 후 값 리턴

** 주의 사항 **
1트에서는 80점 나옴
이유) 해당 위치의 최소값이 다음 위치의 최소값을 보장하지 않는다.
    이유는 가중치가 +100 or +600 이기 때문에 500이상 차이가 나지 않으면 언제든지 최소값이 아닌 값을 가지고 갈 수 있기 때문임.
해결 방법) price 배열 2차원 -> 3차원 으로 세팅
            가로/세로방향으로 움직였을 때의 최소값을 따로 저장함.
*/

class Solution {
    
    int[][][] price;
    int N;
    int[] dx = {0, 1, 0, -1};
    int[] dy = {1, 0, -1, 0};
    
    void cal(int[][] board) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (o1, o2) -> {return Integer.compare(o1[3] , o2[3]);}
        );
        
        price[0][0][0] = price[1][0][0] = 0;
        if(board[0][1] == 0) {
            pq.offer(new int[]{0, 1, 0, 100});
        } 
        
        if(board[1][0] == 0) {  
            pq.offer(new int[]{1, 0, 1, 100});
        }
        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int x = cur[0];
            int y = cur[1];
            
            if (cur[3] >= price[cur[2] % 2][x][y])
                continue;
            else
                price[cur[2] % 2][x][y] = cur[3];
            
            if(x == y && x == N - 1) break;
            
            for(int i = 0; i < 4; i++ ) {
                int tx = x + dx[i];
                int ty = y + dy[i];
                
                if (tx < 0 || tx >= N || ty < 0 || ty >= N) continue;
                if (board[tx][ty] == 1) continue;
                if (cur[2] % 2 == i % 2) {
                    pq.offer(new int[] {tx, ty, i, cur[3] + 100});
                } else {
                    pq.offer(new int[] {tx, ty, i, cur[3] + 600});
                }
            }
            
        }
    };    
    
    public int solution(int[][] board) {
        N = board.length;
        price = new int[2][board.length][board.length];
        
        for(int i = 0;i < board.length; i++){
            Arrays.fill(price[0][i], Integer.MAX_VALUE);
            Arrays.fill(price[1][i], Integer.MAX_VALUE);
        }
        
        cal(board);
        
        return Math.min(price[0][N-1][N-1], price[1][N-1][N-1]);
    }
}
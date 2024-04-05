import java.util.*;
class Solution {
    
    /*
    접근 방식 : 백준 다리 만들기 문제와 비슷한 아이디어로 풀이.
               최대 500x500 크기이며, 방문 배열을 활용하여 시간 복잡도를 고려함
               
    주안점
    1. newLand에 같은 자원을 공유하는 땅마다 고유한 숫자를 부여
    2. map에 해당 땅에 속한 자원의 양을 기록
    3. 입력값은 밖에서 바로 사용할 수 없음
    */
    
    static int[][] newLand, map;
    static boolean[][] v;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int N, M;
    public int solution(int[][] land) {
        N = land.length;
        M = land[0].length;
        map = new int[N][M];
        newLand = new int[N][M];
        v = new boolean[N][M];
        int mark = 2;

        for(int i=0; i<N; i++) newLand[i] = land[i];
        
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(!v[i][j] && land[i][j] == 1) {
                    // 같은 자원을 공유하는 땅을 모두 찾기
                    List<int[]> checkList = check(i, j);
                    int checkCount = checkList.size();
                    // 해당 땅에 고유 번호를 부여하고 총량을 기록
                    for(int[] cur : checkList) {
                        map[cur[0]][cur[1]] = checkCount;
                        newLand[cur[0]][cur[1]] = mark;
                    }
                    mark++;
                }
            }
        }
        
        int max = Integer.MIN_VALUE;
        for(int i=0; i<M; i++) {
            Set<Integer> set = new HashSet<>();
            int sum = 0;
            for(int j=0; j<N; j++) {
                // Set을 활용해서 만약 땅일 때, 아직 밟은 땅이 아니라면 set에 추가해준 후 총량을 더함
                if(newLand[j][i] > 0) {
                    if(!set.contains(newLand[j][i])) {
                        set.add(land[j][i]);
                        sum += map[j][i];
                    }
                }
            }
            max = Math.max(max, sum);
        }
        return max;
    }
    
    static List<int[]> check(int x, int y) {
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[] {x, y});
        v[x][y] = true;
        List<int[]> checkList = new ArrayList<>();
        while(!dq.isEmpty()) {
            int[] cur = dq.poll();
            checkList.add(cur);
            for(int i=0; i<4; i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];
                if(nx >= 0 && ny >= 0 && nx < N && ny < M && !v[nx][ny] && newLand[nx][ny] == 1) {
                    dq.offer(new int[] {nx, ny});
                    v[nx][ny] = true;
                }
            }
        }
        return checkList;
    }
}
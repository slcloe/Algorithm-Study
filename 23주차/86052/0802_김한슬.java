import java.util.*;

/*
[문제풀이]
1. visit 배열로 watCnt를 사용했다. 
    wayCnt[i][j][k];
    i, j : 맵 위치
    k : 방문한 방향에 대한 index
2. callX, callY를 사용해서 범위 외의 이동이 대한 케이스를 처리함
3. 한번 같은 방향으로 방문한 장소는 같은 사이클을 돌기 때문에 다시 계산해야할 필요가 없다.

*/

class Solution {
    
    ArrayList<Integer> list = new ArrayList<>();
    
    int N, M;
    
    int dx[] = {0, -1, 0, 1};
    int dy[] = {-1, 0, 1, 0};
    
    char[][] arr;
    int wayCnt[][][];
    
    int callCycle(int x, int y, int way) {
        int i = x;
        int j = y;
        int result = 0;
        while(wayCnt[i][j][way] == 0) {
            wayCnt[i][j][way] = ++result;
            
            if (arr[i][j] =='R') {
                way += 1;
                way %= 4;
            }
            if (arr[i][j] == 'L'){
                way += 3;
                way %= 4;
            }
            i = callX(i, way);
            j = callY(j, way);
        }
        
        return result;
    }
    
    int callX(int x, int way) {
        int result = x + dx[way];
        
        if (0 > result)
            return N - 1;
        else if (result >= N)
            return 0;
        else return result;
    }
    
    int callY(int y, int way) {
        int result = y + dy[way];
        
        if (0 > result)
            return M - 1;
        else if (result >= M)
            return 0;
        else return result;
    }
    
    public int[] solution(String[] grid) {
        N = grid.length;
        M = grid[0].length();
        arr = new char[N][];
        wayCnt = new int[N][M][4];
        
        for(int i = 0; i < N; i++) {
            arr[i] = grid[i].toCharArray();
        }
        
        for (int i = 0; i< N; i++) {
            for(int j = 0;j < M; j++) {
                for(int k = 0; k < 4; k++) {
                    if (wayCnt[i][j][k] == 0)
                        list.add(callCycle(i, j, k));
                }
            }
        }
        
        int[] answer = list.stream()
            .mapToInt(Integer::intValue)
            .toArray();
        
        Arrays.sort(answer);
        return answer;
    }
}

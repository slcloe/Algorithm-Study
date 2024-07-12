import java.util.*;

/*
[문제풀이]

1. String 맵을 toCharArray 함수를 사용하여 2차원 char array로 변환
2. X가 아니고 방문하지 않은 장소에 대해 dfs 배열 수행
3. 4방탐색으로 한 섬에 대해 방문
4. list sort 로 오름차순 정렬 실행


** ArrayList 를 array로 바꾸는 과정 암기 ** 
*/


class Solution {
    
    char[][] map;
    boolean[][] v;
    int n , m;
    int[] dx = { 0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    
    int dfs(int x, int y) {
        int result = map[x][y] - '0';
        v[x][y] = true;
        
        for(int i = 0; i < dx.length; i++) {
            int tx = dx[i] + x;
            int ty = dy[i] + y;
            
            if (tx < 0 || tx >= n || ty < 0 || ty >= m) continue;
            if (v[tx][ty]) continue;
            if (map[tx][ty] == 'X') continue;
            result += dfs(tx, ty);
        }
        return result;
    }
    
    public int[] solution(String[] maps) {
        n = maps.length; m = maps[0].length();
        map = new char[maps.length][maps[0].length()];
        v = new boolean[maps.length][maps[0].length()];
        
        for(int i = 0 ;i< maps.length; i++){
            map[i] = maps[i].toCharArray();
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        
        for (int i = 0;i < n; i++) {
            for(int j = 0; j< m; j++) {
                if (v[i][j]) continue;
                if (map[i][j] == 'X') continue;
                list.add(dfs(i, j));
            }
        }
        
        if (!list.isEmpty())
            Collections.sort(list);
        else list.add(-1);
        
        
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}

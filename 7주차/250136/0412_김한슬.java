import java.util.*;
import java.io.*;


/*
문제 풀이

1. dfs 를 돌면서 석유가 있는 그룹에 대해 인덱싱을 한다.
2. 한 그룹이 몇 개의 칸으로 이루어져있는지 liter 저장한다.
3. 석유 시추 방향으로 찔렀을 때 만나는 석유 그룹의 인덱스를 set에 넣는다. ( 중복 제거를 위해 set 사용)
4. set 을 iterator 을 사용하여 전체를 조회하고, liter에 저장된 값을 더해 1번 시추할 때 얻을 수 있는 석유의 양을 계산한다.
5. 총 획득 석유량의 최대값을 계산한다.

*/

class Solution {
    
    boolean v[][];
    int[][] map;
    int[] liter;
    int[] dx =  {0, 0, 1, -1};
    int[] dy = {1, -1, 0, 0};
    int m, n;
    int result = 0;
    
    public int solution(int[][] land) {
        int answer = 0;
        
        n = land.length;
        m = land[0].length;
        map = new int[n][m];
        v = new boolean[n][m];
        liter = new int[m * n / 2 + 1];
        
        for (int i = 0; i < n; i++) {
            for(int j = 0;j < m; j++){
                map[i][j] = land[i][j];
            }
        }
        calOil();
        calMaxOil();
        
        return result;
    }
    
     void calOil() {
        int index = 1;
        int cnt = 0;
        
        for (int i = 0 ;i < n; i++) {
            for (int j = 0;j < m;j++) {
                if (map[i][j] == 1 && !v[i][j]) {
                    map[i][j] = index;
                    cnt = dfs(i , j);
                    // System.out.println("cnt : " + cnt);
                    liter[index++] = cnt; 
                }
            }
        }
    }
    
    void calMaxOil() {
        for(int i = 0; i < m; i++) {
            HashSet<Integer> set = new HashSet<>();
            
            for(int j = 0;j < n; j++){
                if (map[j][i] != 0)
                    set.add(map[j][i]);
            }
            
            Iterator iter = set.iterator();	
            int max = 0;
            while(iter.hasNext()) {
                max += liter[(int)iter.next()];
            }
            result = Math.max(result, max);
        }
    }
    
    int dfs(int x, int y){
        v[x][y] = true;
        int num = 1;
        
        for(int i = 0; i < 4; i++){
            int tx = x + dx[i];
            int ty = y + dy[i];
            
            if (tx < 0 || tx >= n || ty < 0 || ty >= m) continue;
            if (map[tx][ty] == 0) continue;
            if (v[tx][ty]) continue;
            
            map[tx][ty] = map[x][y];
            num += dfs(tx, ty);
            
        }
        
        return num;
    }
    

}

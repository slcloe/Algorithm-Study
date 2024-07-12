import java.util.*;

/*
문제풀이
1. 맨해튼 거리가 2이하일 경우만 검사하면 된다.

/*
    *
  * - *
* - 0 - *
  * - *
    *

*/

2. 멘해튼 거리 == 1
    사람이면 무조건 거리두기를 위반한것
    벽이면 OK ( 더이상 볼것도 없음 )
    빈자리면 의심해봐야함.
3. 멘해튼 거리 == 2
    (2) 번에서 빈자리여서 의심될 경우 해당 단계를 거치게 됨.
    원래 자리로 부터 같은 방향으로 한칸 더 간 상태에 사람이 있다면 OK
    하지만 그 외에 다른 사람이 존재한다면 거리 두기 위반.
*/

class Solution {
    
    int[] dx = { 0, 0, 1, -1};
    int[] dy = { 1, -1, 0, 0};
    
    public int distance(String[] str) {
        char[][] arr = new char[5][5];
        int result = 1;
        
        for(int i = 0; i < 5; i++) {
            arr[i] = str[i].toCharArray();
        }
        
        for(int i = 0; i < 5 && result == 1; i++) {
            for (int j = 0 ; j < 5 && result == 1; j++) {
                if (arr[i][j] == 'P') {
                    for (int k = 0; k < 4; k++) {
                        int tx = dx[k] + i;
                        int ty = dy[k] + j;
                        
                        if (tx < 0 || tx >= 5 || ty < 0 || ty >= 5) continue;
                        if (arr[tx][ty] == 'X') continue;
                        if (arr[tx][ty] == 'P') {
                            result = 0; break;
                        }
                        
                        int person = 0;
                        for (int l = 0; l < 4; l++) {
                            int ttx = tx + dx[l];
                            int tty = ty + dy[l];
                            
                            if (ttx < 0 || ttx >= 5 || tty < 0 || tty >= 5) continue;
                            if (arr[ttx][tty] == 'P') person++;
                            
                        }
                        
                        if (person != 1) result = 0;
                    }
                
                }
                
            }
        }
        
        
        
        return result;
    }
    

    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        
        int i = 0;
        for (String[] place : places) {
            
            answer[i++] = distance(place);
        }
        
        return answer;
    }
}



import java.util.*;

class Solution {
    
    /*
    접근 방식 : 번갈아가며 칸을 채워야하고, 다음에 어떤 것을 채워야하는지 / 지금까지 몇 개가 채워져있는지 기록해야하기 때문에
               dfs를 사용하여 풀이
               
    주안점
    1. 완성됐을 때가 답인 테스트케이스도 존재하기 때문에, 완성되어서 return하는 코드는 답 확인 코드 다음 실행해야 함
    2. 조금이라도 시간 줄이려고 현재 O와 X의 개수를 들고 다니며, 답의 개수와 동일할 때만 비교
    */
    
    static char[][] map, originMap;
    static int oCount, xCount, answer;
    public int solution(String[] board) {
        oCount = 0;
        xCount = 0;
        originMap = new char[3][3];
        map = new char[3][3];
        for(int i=0; i<3; i++) {
            char[] now = board[i].toCharArray();
            originMap[i] = now;
            for(int j=0; j<3; j++) {
                map[i][j] = '.';
                if(now[j] == 'O') oCount++;
                else if(now[j] == 'X') xCount++;
            }
        }
        answer = 0;
        dfs(0, 0, 0);
        return answer;
    }
    
    static void check() {
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(originMap[i][j] != map[i][j]) return;
            }
        }
        answer = 1;
    }
    
    static boolean isEnded() {
        // 가로 확인
        int[][] horizontal = new int[3][2];
        // 세로 확인
        int[][] vertical = new int[2][3];
        // 대각선 확인 \ /
        int leftoDia = 0;
        int leftxDia = 0;
        int rightoDia = 0;
        int rightxDia = 0;
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(map[i][j] == '.') continue;
                if(i == j) {
                    if(map[i][j] == 'O') leftoDia++;
                    else leftxDia++;
                }
                
                if(i + j == 2) {
                    if(map[i][j] == 'O') rightoDia++;
                    else rightxDia++;
                }
                
                if(map[i][j] == 'O') {
                    horizontal[i][0]++;
                    vertical[0][j]++;
                } else {
                    horizontal[i][1]++;
                    vertical[1][j]++;
                }
            }
        }
        
        for(int i=0; i<3; i++) {
            for(int j=0; j<2; j++) {
                if(horizontal[i][j] == 3) return true;
                if(vertical[j][i] == 3) return true;
            }
        }
        if(leftoDia == 3 || leftxDia == 3 || rightoDia == 3 || rightxDia == 3) return true;
        return false;
    }
    
    static void dfs(int O, int X, int order) {
        if(O == oCount && X == xCount) check();
        if(isEnded() || answer == 1) return;
        
        char ox = order % 2 == 1 ? 'X' : 'O';
        if(ox == 'O') O++;
        else X++;
        for(int i=0; i<3; i++) {
            for(int j=0; j<3; j++) {
                if(map[i][j] == '.') {
                    map[i][j] = ox;
                    dfs(O, X, order+1);
                    map[i][j] = '.'; 
                }
            }
        }
    }
}
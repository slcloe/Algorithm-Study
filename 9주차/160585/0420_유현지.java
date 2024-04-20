/*
 * | 접근 방법 |
 *  1. 불가능한 경우의 수를 세워 해당 경우에만 0을, 나머지는 1을 리턴한다.
 *     1-1. O의 개수가 X의 개수 보다 적을 경우
 *     1-2. O의 개수가 X의 개수 보다 2개 이상 많을 경우
 *     1-3. 빙고가 완성 됐을 때
 *          1-3-1. O로 완성했으면서, O의 개수가 X보다 1 많지 않은 경우
 *          1-3-2. X로 완성했으면서, O의 개수와 X의 개수가 다른 경우
 */

package a2404.study.week9;

public class pr_2_160585_혼자서_하는_틱택토 {
    public int solution(String[] board) {
        int answer = 1;

        char[][] arr = new char[3][3];
        int totalCntO = 0, totalCntX = 0;
        int cnt1O = 0, cnt1X = 0;
        int cnt2O = 0, cnt2X = 0;

        // count O, X
        for(int r=0; r<3; r++){
            arr[r] = board[r].toCharArray();
            for(int c=0; c<3; c++){
                if(arr[r][c] == 'O'){ totalCntO++; }
                else if(arr[r][c] == 'X'){ totalCntX++; }
            }
        }
        if(totalCntO < totalCntX || totalCntO - totalCntX > 1){ return 0; }

        // horizontal, vertical
        for(int r=0; r<3; r++){
            cnt1O = 0; cnt1X = 0;
            cnt2O = 0; cnt2X = 0;
            for(int c=0; c<3; c++){
                if(arr[r][c] == 'O'){ cnt1O++; }
                else if(arr[r][c] == 'X'){ cnt1X++; }
                if(arr[c][r] == 'O'){ cnt2O++; }
                else if(arr[c][r] == 'X'){ cnt2X++; }
            }
            if((cnt1O==3 && totalCntO!=totalCntX+1) ||
                    (cnt1X==3 && totalCntO!=totalCntX) ||
                    (cnt2O==3 && totalCntO!=totalCntX+1) ||
                    (cnt2X==3 && totalCntO!=totalCntX)){ return 0; }
        }

        // diagonal
        cnt1O = 0; cnt1X = 0;
        cnt2O = 0; cnt2X = 0;
        for(int r=0; r<3; r++){
            if(arr[r][r] == 'O'){ cnt1O++; }
            else if(arr[r][r] == 'X'){ cnt1X++; }
            if(arr[r][2-r] == 'O'){ cnt2O++; }
            else if(arr[r][2-r] == 'X'){ cnt2X++; }
        }
        if((cnt1O==3 && totalCntO!=totalCntX+1) ||
                (cnt1X==3 && totalCntO!=totalCntX) ||
                (cnt2O==3 && totalCntO!=totalCntX+1) ||
                (cnt2X==3 && totalCntO!=totalCntX)){ return 0; }

        return answer;
    }
}

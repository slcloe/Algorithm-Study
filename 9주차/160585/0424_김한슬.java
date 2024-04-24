/*
문제풀이 
- 정상적 틱텍토 결정 조건
1. O & X 의 수
    O 의 수는 항상 X 의 수보다 크거나 같다.
    O 와 X 의 차이는 2 이상을 넘어갈 수 없다.

2. 각 O, X 의 빙고 수 
    O & X 의 빙고는 동시에 존재할 수 없다. ( O 빙고 1개 X 빙고 1개 안됨 )
    O 빙고라면 즉시 종료되어야 하므로 (O의 수 > X의 수)
    X 빙고라면 즉시 종료되어야 하므로 (O의 수 == X의 수)


- 빙고체크 
    직선 ) O X 의 (x, y) 의 빈도수를 저장하여 해당 값이 3이면 빙고.
    대각선 ) 직접 따로 처리함
*/

class Solution {
    int a = 0, b = 0;
    int[][] a_cnt, b_cnt;
    
    int calBingo(int[][] arrays){
        int bingo = 0;
        for(int[] arr : arrays){
            for(int num : arr){
                if (num == 3) bingo++;
            }
        }
        return bingo;
    }
    
    public int solution(String[] board) {
        int result = 1;
        
        a_cnt = new int[3][2];
        b_cnt = new int[3][2];
        
        for (int i = 0;i < 3; i++){
            String line = board[i];
            for (int j = 0;j < 3; j++){
                char ch = line.charAt(j);
                if (ch == 'O'){
                    a++;
                    a_cnt[i][0]++;
                    a_cnt[j][1]++;
                }
                else if (ch == 'X'){
                    b++;
                    b_cnt[i][0]++;
                    b_cnt[j][1]++;
                }
            }
        }

        int bingo_a = calBingo(a_cnt);
        int bingo_b = calBingo(b_cnt);
        
        if (board[0].charAt(0) == board[1].charAt(1) 
            && board[0].charAt(0) == board[2].charAt(2) 
            && board[2].charAt(2) != '.'){
            if (board[2].charAt(2) == 'O') bingo_a++;
            else bingo_b++;
        }
        
        if (board[0].charAt(2) == board[1].charAt(1) 
            && board[0].charAt(2) == board[2].charAt(0) 
            && board[2].charAt(0) != '.'){
            if (board[2].charAt(0) == 'O') bingo_a++;
            else bingo_b++;
        }
        
        if (a - b >= 2 || b > a) 
            result = 0;
        else {       
            if (bingo_a > 0 && bingo_b > 0) 
                result = 0;
            else if (bingo_a > 0) {
                if (a > b) result = 1;
                else result = 0;
            }
            else if (bingo_b > 0){
                if (a == b) result = 1;
                else result = 0;
            }
        }
        return result;
    }
}

/*
 * | 활용 알고리즘 | DFS
 *
 * | 접근 방법 |
 *  1. 각 테스트 케이스에 대해 아래의 DFS 탐색을 진행한다
 *  2. 맵을 돌며 핀을 찾는다
 *  3. 핀의 사방을 탐색하여 다음 칸(이하 n)이 범위 체크 및 핀인지 체크한다
 *     핀이 연달아 있어야 그 방향으로 이동할 수 있기 때문에 수행하는 과정
 *  4. 만약 연달아 핀이 있다면, 한 칸 더 가서 그 칸(이하 nn)이 빈칸인지 체크한다
 *     다음 칸으로 건너뛰어 이동할 수 있어야 하기 때문에 수행하는 과정
 *  5. 3~4번의 과정이 가능하다면 현재칸과 n은 빈칸으로, nn은 핀으로 세팅한 상태에서 DFS를 수행한다
 *  6. 현재칸과 n을 핀으로,nn은 빈칸으로 복구시킨 다음 2~5의 과정을 반복하며 남은 핀의 최소 개수를 갱신한다
 */

package a2404.study.week8;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class bj_g4_9207_페그_솔리테어 {
    static int H = 5, W = 9, minPin, minMove;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t=0; t<T; t++){
            minPin = Integer.MAX_VALUE;
            minMove = Integer.MAX_VALUE;
            char[][] board = new char[H][W];
            int totalPin = 0;
            for(int r=0; r<H; r++){
                board[r] = br.readLine().toCharArray();
                for(int c=0; c<W; c++){
                    if(board[r][c] == 'o'){ totalPin++; }
                }
            }
            DFS(board, totalPin, 0);
            sb.append(minPin).append(" ").append(minMove).append("\n");
            br.readLine();
        }
        System.out.println(sb);
    }

    static void DFS(char[][] board, int totalPin, int totalMove){
        boolean isProceeding = false;
        for(int r=0; r<H; r++){
            for(int c=0; c<W; c++){
                if(board[r][c] == 'o'){
                    for(int d=0; d<4; d++){
                        int nr = r + dr[d];
                        int nc = c + dc[d];
                        if(outBoundary(nr, nc) || board[nr][nc]!='o'){ continue; }
                        int nnr = nr + dr[d];
                        int nnc = nc + dc[d];
                        if(outBoundary(nnr, nnc) || board[nnr][nnc]!='.'){ continue; }
                        board[r][c] = '.';
                        board[nr][nc] = '.';
                        board[nnr][nnc] = 'o';
                        DFS(board, totalPin-1, totalMove+1);
                        board[r][c] = 'o';
                        board[nr][nc] = 'o';
                        board[nnr][nnc] = '.';
                        isProceeding = true;
                    }
                }
            }
        }
        if(!isProceeding){
            if(totalPin < minPin){
                minPin = totalPin;
                minMove = totalMove;
            }
        }
    }

    static boolean outBoundary(int r, int c){
        return r<0 || H<=r || c<0 || W<=c;
    }
}

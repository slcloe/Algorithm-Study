/*
 * | 활용 자료구조 | ArrayList, Queue
 *
 * | 접근 방법 |
 *  1. 종수 아두이노(이하 종수이노)의 위치와 미친 아두이노의 좌표를 저장한다
 *  2. 종수이노가 움직일 때마다 발생하는 일들을 착실하게 구현한다
 *
 * | 주의 사항 |
 *  1. 미친 아두이노가 이동할 때 기존 칸과 새로 이동하는 칸의 처리를 명확히 해준다
 *  2. 한 칸에 여러 미친 아두이노가 있을 때 처리를 제대로 해줘야 한다
 *     2-1. 미친 아두이노들을 조회하며 빈칸으로 바꾸고 방문 처리를 해준다
 *     2-2. 방문하려는 칸이 이미 방문한(미친 아두이노가 있는) 칸이면 폭파시킬 칸의 좌표를 Queue에 저장한다
 *     2-3. 폭파시킬 칸의 좌표를 조회하며 좌표가 일치하는 미친 아두이노들을 폭파시킨다
 */

package a2403.week5;

import java.io.*;
import java.util.*;

public class bj_g3_8972_미친_아두이노 {
    static int R, C;
    static int[] jongsu,
            dr = {1, 1, 1, 0, 0, 0, -1, -1, -1},
            dc = {-1, 0, 1, -1, 0, 1, -1, 0, 1};
    static char[][] board;
    static List<int[]> robots = new ArrayList<>();

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        for(int r=0; r<R; r++){
            board[r] = br.readLine().toCharArray();
            for(int c=0; c<C; c++){
                if(board[r][c] == '.'){ continue; }
                if(board[r][c] == 'R'){ robots.add(new int[] {r, c}); }
                else{ jongsu = new int[] {r, c}; }
            }
        }
        String directions = br.readLine();
        for(int step=0; step<directions.length(); step++){
            if(!play(directions.charAt(step) - '1')){
                System.out.println("kraj "+(step+1));
                return;
            }
        }
        for(int r=0; r<R; r++){
            for(int c=0; c<C; c++){
                sb.append(board[r][c]);
            } sb.append("\n");
        }
        System.out.println(sb);
    }

    static boolean play(int direction){

        // 1. jongsu moves
        board[jongsu[0]][jongsu[1]] = '.';
        jongsu[0] += dr[direction];
        jongsu[1] += dc[direction];

        // 1-1. if jongsu moved to crazy robot
        if(board[jongsu[0]][jongsu[1]] == 'R'){ return false; }
        board[jongsu[0]][jongsu[1]] = 'I';

        // 2. crazy robots move
        boolean[][] v = new boolean[R][C];
        ArrayDeque<int[]> duplicated = new ArrayDeque<>();
        for(int[] robot: robots){
            board[robot[0]][robot[1]] = '.';

            robot[0] = robot[0] < jongsu[0]? robot[0]+1: robot[0] > jongsu[0]? robot[0]-1: robot[0];
            robot[1] = robot[1] < jongsu[1]? robot[1]+1: robot[1] > jongsu[1]? robot[1]-1: robot[1];

            if(board[robot[0]][robot[1]] == 'I'){ return false; }
            if(v[robot[0]][robot[1]]){ duplicated.offerLast(new int[] {robot[0], robot[1]}); }
            else{ v[robot[0]][robot[1]] = true; }
        }

        while(!duplicated.isEmpty()){
            int[] now = duplicated.pollFirst();
            for(int r=robots.size()-1; r>=0; r--){
                if(now[0] == robots.get(r)[0] && now[1] == robots.get(r)[1]){ robots.remove(r); }
            }
        }

        for(int[] robot: robots){ board[robot[0]][robot[1]] ='R'; }

        return true;
    }
}

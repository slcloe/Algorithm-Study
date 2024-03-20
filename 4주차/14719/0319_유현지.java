/*
 * | 접근 방법 |
 *  1. boolean 이차원 배열에 입력값을 저장한다
 *     블록이 있는 영역만 true로 설정한다
 *  2. 각 row에 대해 고일 수 있는 빗물의 양을 구해서 합한다
 *     2-1. 처음으로 벽을 만나면 flag를 true로 설정한다
 *     2-2. flag==true && !world[h][w]인 경우는 cnt를 증가시킨다
 *          flag가 true이면서 빈칸인 경우
 *     2-2. flag==true && world[h][w]인 경우는 답에 cnt를 더하고 cnt를 0으로 초기화한다
 *          flag가 true이면서 블록인 경우
 *          cnt는 이전 블록과 현재 블록 사이에 고일 수 있는 빗물의 양이다
 *
 *
 * | 주의 사항 |
 *  1. 전에 풀었던 문제인데, 그때는 flag==true && world[h][w]인 경우에 flag를 false로 설정해서 오류가 발생했었다
 *     하나의 flag에 대한 케이스의 종료는 다음 케이스의 시작을 의미하므로 해당 과정은 불필요하다
 */

package a2403.week3;

import java.io.*;
import java.util.*;

public class bj_g5_14719_빗물 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int answer = 0;
        boolean[][] world = new boolean[H][W];
        st = new StringTokenizer(br.readLine());
        for(int w=0; w<W; w++){
            int height = Integer.parseInt(st.nextToken());
            for(int h=0; h<height; h++){ world[h][w] = true; }
        }
        for(int h=0; h<H; h++){
            boolean flag = false;
            int cnt = 0;
            for(int w=0; w<W; w++){
                if(!flag && world[h][w]){ flag = true; }
                if(!flag){ continue; }
                if(world[h][w]){
                    answer += cnt;
                    cnt = 0;
                    continue;
                }
                cnt++;
            }
        }
        System.out.println(answer);
    }
}

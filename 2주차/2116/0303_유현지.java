package a2403.week1;

/*
 * 1. 1번 주사위 밑면 결정 == 모든 주사위의 밑면 결정
 *    -> 1번 주사위의 밑면이 1~6일 때의 경우 탐색
 * 2. 밑면 숫자 -> 밑면 숫자 인덱스 -> 윗면 숫자 인덱스 -> 윗면 숫자 찾기
 * 3. 밑면, 윗면을 제외한 옆면 중 최대값 찾기
 * 3. 하나의 주사위에 대해 옆면 최대값을 넘겨주며 마지막 주사위까지 탐색
 */

import java.io.*;
import java.util.*;

public class bj_g5_2116_주사위_쌓기 {
    static int N, answer = -1;
    static int[][] dices;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dices = new int[N][6];
        for(int n=0; n<N; n++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int d=0; d<6; d++){
                dices[n][d] = Integer.parseInt(st.nextToken());
            }
        }
        for(int d=1; d<=6; d++){ dice(0, d, 0); }
        System.out.println(answer);
    }

    static void dice(int now, int floorNum, int sum){
        if(now == N){
            answer = Math.max(answer, sum);
            return;
        }
        int floorIdx = findIdxByNum(now, floorNum);
        int ceilingIdx = findOppositeIdx(floorIdx);
        int ceilingNum = dices[now][ceilingIdx];
        int maxNum = 6;
        for(int d=6; d>0; d--){
            if(d == floorNum || d == ceilingNum){ continue; }
            maxNum = d;
            break;
        }
        dice(now+1, ceilingNum, sum+maxNum);
    }

    static int findIdxByNum(int now, int num){
        for(int d=0; d<6; d++){
            if(dices[now][d] == num){ return d; }
        }
        return -1;
    }

    static int findOppositeIdx(int idx){
        switch (idx){
            case 0: return 5;
            case 1: return 3;
            case 2: return 4;
            case 3: return 1;
            case 4: return 2;
            case 5: return 0;
        }
        return -1;
    }
}
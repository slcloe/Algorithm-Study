package a2403.week1;

/*
 * 더 진행 중이던 코드 싸피컴에 두고옴...
 * | 접근 방법 |
 * 좌표가 (n,n)(0<=n)인 경우, 해당 좌표 값은 해당 레벨의 최대 수
 * 각 레벨을 n이라고 했을 때 좌표 (n,n)의 값은 (2n+1)^2
 * 시작 좌표의 r, c와 종료 좌표의 r, c 중 최댓값을 구해 최고 레벨로 설정한다
 * 해당 값부터 배열 채우기가 완료될 때까지 최고 레벨을 하나씩 감소시키며 진행한다
 * 해당 레벨의 최대값 좌표(n,n)부터 시계 방향으로 돌며 범위를 체크한다
 * 싸피컴에 있던 코드에서는 각 단계 별 좌표와 값 출력까지는 성공, 해당 값들을 정답 배열 인덱스에 매핑 시키는 과정 진행 중
 * */

import java.io.*;
import java.util.*;

public class bj_g3_1022_소용돌이_예쁘게_출력하기 {
    static int r1, c1, r2, c2;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());
        int maxStep = Math.max(Math.abs(r1), Math.max(Math.abs(c1), Math.max(Math.abs(r2), Math.abs(c2))));
//        System.out.println(maxStep);
        int[][] result = new int[r2 - r1 + 1][c2 - c1 + 1];
        for(int step=maxStep; step>=0; step--){
            int sR = step;
            int sC = step;
            System.out.println();
            System.out.println(">>>>>>>>>>>>>> step "+step);
            for(int n=0; n<2*step; n++){
//                System.out.print("("+sR+" "+(sC)+") ");
                if(outPaper(sR, sC--)){ continue; }
                System.out.println("["+sR+", "+sC+"] is able! ");

            }
                System.out.println();
            for(int n=0; n<2*step; n++){
//                System.out.print("("+(sR-1)+" "+sC+") ");
                if(outPaper(sR--, sC)){ continue; }
                System.out.println("["+sR+", "+sC+"] is able! ");
            }
                System.out.println();
            for(int n=0; n<2*step; n++){
//                System.out.print("("+sR+" "+(sC+1)+") ");
                if(outPaper(sR, sC++)){ continue; }
                System.out.println("["+sR+", "+sC+"] is able! ");
            }
                System.out.println();
            for(int n=0; n<2*step; n++){
//                System.out.print("("+(sR+1)+" "+sC+") ");
                if(outPaper(sR++, sC)){ continue; }
                System.out.println("["+sR+", "+sC+"] is able! ");
            }
                System.out.println();
        }
    }

    static boolean outPaper(int r, int c){
        return r < r1 || r2 < r || c < c1 || c2 < c;
    }
}

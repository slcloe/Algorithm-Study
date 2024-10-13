/*
 * | 접근 방법 |
 *  1. 오븐의 각 칸에 대해 위의 칸이 아래 칸보다 작다면 아래 칸의 크기를 위의 칸의 크기로 봐도 무방하다
 *  2. 위의 법칙에 따라 oven 배열을 재정립한다
 *  3. 오븐의 가장 아래 칸부터 피자 도우가 들어갈 수 있는지 검증한다
 */

package a2409.study.week28;

import java.io.*;
import java.util.*;

public class bj_g5_1756_피자_굽기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int D = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] oven = new int[D];
        int[] pizzas = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int d=0; d<D; d++){
            oven[d] = Integer.parseInt(st.nextToken());
            if(0 < d && oven[d-1] < oven[d]){
                oven[d] = oven[d-1];
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int n=0; n<N; n++){
            pizzas[n] = Integer.parseInt(st.nextToken());
        }
        if(oven[0] < pizzas[0]){
            System.out.println(0);
            return;
        }
        int from = D-1;
        int answer = 0;
        int cnt = 0;
        for(int n=0; n<N; n++){
            for(int d=from; 0<=d; d--){
                if(oven[d] < pizzas[n]){
                    continue;
                }
                from = d - 1;
                answer = d + 1;
                cnt++;
                break;
            }
        }
        if(cnt < N){
            System.out.println(0);
        }
        else{
            System.out.println(answer);
        }
    }
}

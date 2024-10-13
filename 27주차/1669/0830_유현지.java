/*
 * | 접근 방법 |
 *  1. 왼쪽, 오른쪽 끝 값이 1이어야 하기 때문에, 결과적으로 정규분포 형태일 것이라 생각했다
 *     1-1. -1, 0, 1만큼 가능하지만 -1은 이전 값에서의 0이기 때문에 고려하지 않아도 된다
 *  2. 왼쪽, 오른쪽에 더하는 값을 배열로 설정, 1로 초기화한다
 *  3. 번갈아가며 키의 차이가 현재 늘릴 키보다 크면 늘릴 키를 1 증가시킨다
 *  4. 키 차이에서 현재 늘릴 키를 빼고, 이것이 0이 되면 멈춘다
 */

package a2408.study.week27;

import java.io.*;
import java.util.*;

public class bj_g5_1669_멍멍이_쓰다듬기 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());
        int diff = Y - X;
        if(diff <= 2){
            System.out.println(diff);
            return;
        }
        diff -= 2;
        int answer = 2;
        int[] num = new int[2];
        Arrays.fill(num, 1);
        for(int i=0; 0<diff; i++){
            int now = i % 2;
            if(num[now] < diff){
                num[now]++;
            }
            diff -= num[now];
            answer++;
        }
        System.out.println(answer);
    }
}

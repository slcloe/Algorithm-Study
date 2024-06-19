/*
 * | 활용 자료구조 | circular queue
 *
 * | 접근 방법 |
 *  1. d+1 사이즈 원형 큐를 만든다
 *  2. N일 동안 아래 과정을 반복한다
 *     2-1. 원형 큐를 회전시킨다
 *     2-2. 새로 만들어질 짚신벌레의 수를 갱신한다
 *          기존 짚신벌레의 수 + a - b
 *     2-3. d의 짚신벌레들은 수명이 다했으므로 0으로 초기화한다
 *  3. N일 후 배열의 짚신벌레들의 총합을 구한다
 */

package a2406.study.week17;

import java.io.*;
import java.util.*;

public class bj_g3_2560_짚신벌레 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int front = 0;
        int end = d+1;
        int bug = 0;
        int[] days = new int[end];
        days[0] = 1;
        for(int n=0; n<N; n++){
            a = (end + a - 1) % end;
            b = (end + b - 1) % end;
            d = (end + d - 1) % end;
            front = (end + front - 1) % end;
            bug = (1000 + bug + days[a] - days[b]) % 1000;
            days[front] = bug;
            days[d] = 0;
        }
        int answer = 0;
        for(int i=0; i<end; i++){
            answer = (answer + days[i]) % 1000;
        }
        System.out.println(answer);
    }
}

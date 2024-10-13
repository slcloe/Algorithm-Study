package com.example.algo;

import java.util.*;
import java.io.*;
public class Main_bj_3079 {

    /*

    접근 방식 : 처음에는 정렬 후 탐색하는 문제인줄 알았지만, 계속 메모리 초과가 발생하여 이분탐색을 활용하여 풀이.
    주안점
    1. 가장 오래 걸리는 시간은 (가장 오랜 시간이 걸리는 심사대*M)이기 때문에 최대값을 설정 후 이분 탐색을 진행.
    2. 각 심사대에서 맡을 수 있는 사람의 수를 구한 후 min / max 값을 정해줄 때 if문의 부등호에 유의.

     */

    static int N, M;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        long[] audit = new long[N];
        for(int i=0; i<N; i++) audit[i] = Long.parseLong(br.readLine());
        Arrays.sort(audit);

        find(audit);
    }

    static void find(long[] audit) {
        long min = 0L;
        long max = M * audit[N-1];
        while(max > min) {
            long mid = (min + max) / 2;

            long total = 0L;
            for(long aud : audit) {
                long now = mid / aud;
                total += now;

                if(total >= M) {
                    max = mid;
                    break;
                }
            }
            if(total < M) {
                min = mid + 1;
            }
        }
        System.out.print(min);
    }
}

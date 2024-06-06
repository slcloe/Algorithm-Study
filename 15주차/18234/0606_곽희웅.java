package com.example.algo;

import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_bj_18234 {

    /*
    접근 방식 : 처음엔 dp인줄알고 접근했다가, 도저히 모르겠어서 알고리즘 분류 확인 후 solve

    주안점
    1. 당근에 최대한 많은 영양제를 주어야 맛있게 먹을 수 있기 때문에 N개의 당근은 모두 먹을 것
    2. 그렇다면 (T - N)일동안은 모든 당근에 영양제를 주는 것이 최적의 해라고 판단함
    3. (T - N)만큼 영양제를 곱한 값을 더하여 현재 값에 저장하고, PriorityQueue를 사용하여 증가폭 기준 오름차순 정렬
    4. 그 후 N일동안 증가폭이 작은 당근부터 먹어치우는데, 앞 당근을 먹을 동안 뒷 당근은 영양제를 더 받았기 때문에 (N-count--)만큼 곱하여 더함
     */

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        PriorityQueue<long[]> pq = new PriorityQueue<>((o1, o2) -> Long.compare(o1[0], o2[0]));

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            long now = Long.parseLong(st.nextToken());
            long factor = Long.parseLong(st.nextToken());
            pq.offer(new long[] {factor, now});
        }

        long result = 0L;
        int diff = T - N;
        int count = N;
        while(!pq.isEmpty()) {
            long[] cur = pq.poll();
            result += cur[1] + (cur[0] * (diff + N - count--));
        }
        System.out.print(result);
    }
}

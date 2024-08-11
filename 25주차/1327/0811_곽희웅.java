package com.example.algo;

import java.io.*;
import java.util.*;
public class Main_bj_1327 {

    /*

    접근 방식 : 처음 Set을 활용한 풀이 방식을 고안했을 때는, 공간 복잡도를 고려하기 위해 dfs로 구현했지만, 결국 8!의 범위를 넘어서지 않으니 bfs로 구현하는 것이 맞았음

    주안점
    1. 종료 조건이 명확하지 않기 때문에 Set, Map 등의 자료 구조를 활용하여 종료 조건을 구성
    2. 결국 N-K보다 큰 index는 뒤집을 수 없기 때문에 시간을 아끼자

     */
    static int N, K, minCount;
    static boolean isSucceeded;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<N; i++) arr[i] = Integer.parseInt(st.nextToken());

        minCount = Integer.MAX_VALUE;
        isSucceeded = false;

        bfs(arr);

        if(isSucceeded) System.out.print(minCount == Integer.MAX_VALUE ? 0 : minCount);
        else System.out.print(-1);
    }

    static void bfs(int[] arr) {
        Deque<int[]> dq = new ArrayDeque<>();
        Deque<Integer> dqCount = new ArrayDeque<>();
        Set<String> set = new HashSet<>();

        int limit = N - K;
        dq.offer(arr);
        dqCount.offer(0);
        while(!dq.isEmpty()) {
            int[] cur = dq.poll();
            int curCount = dqCount.poll();

            if(isAsc(cur)) {
                isSucceeded = true;
                minCount = Math.min(minCount, curCount);
                return;
            }

            String curArray = combineArray(cur);
            if(set.contains(curArray)) continue;
            set.add(curArray);

            for(int i=0; i<=limit; i++) {
                // 배열 뒤집기
                dq.offer(reverseArray(cur, i));
                dqCount.offer(curCount+1);
            }
        }
    }

    // 배열 String 변환
    static String combineArray(int[] arr) {
        StringBuilder sb = new StringBuilder();
        for(int cur : arr) sb.append(cur);
        return sb.toString();
    }

    // 배열 뒤집기
    static int[] reverseArray(int[] arr, int idx) {
        int[] temp = new int[N];
        for(int i=0; i<idx; i++) temp[i] = arr[i];
        int count = idx;
        for(int i=idx+K-1; i>=idx; i--) {
            temp[count++] = arr[i];
        }
        for(int i=idx+K; i<N; i++) temp[i] = arr[i];

        return temp;
    }

    // 오름차순 검증
    static boolean isAsc(int[] arr) {
        for(int i=1; i<=N; i++) {
            if(arr[i-1] != i) {
                return false;
            }
        }
        return true;
    }
}

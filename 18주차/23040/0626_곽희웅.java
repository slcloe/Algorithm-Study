package com.example.algo;

import java.io.*;
import java.util.*;
public class 0626_곽희웅 {

    /*
    접근 방식 : 결국 B 부터 시작하는 R의 개수를 세는 것이라 생각하여 구현했지만, 역시나 시간초과,,, unsolved로 제출 후 다시 풀어봐야겠음
     */

    static int N, max;
    static List<Integer>[] g;
    static char[] type;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        g = new List[N+1];
        for(int i=1; i<=N; i++) g[i] = new ArrayList<>();

        for(int i=0; i<N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            g[a].add(b);
            g[b].add(a);
        }

        type = new char[N+1];
        String types = br.readLine();
        for(int i=1; i<=N; i++) type[i] = types.charAt(i-1);

        max = 0;
        for(int i=1; i<=N; i++) {
            if(type[i] == 'B') bfs(i);
        }
        System.out.println(max);
    }

    static void bfs(int n) {
        Deque<Integer> dq = new ArrayDeque<>();
        boolean[] v = new boolean[N+1];
        // 처음 n이 검은색이니 빨간색인 것들을 우선 찾기
        for(int cur : g[n]) {
            if(type[cur] == 'R') {
                dq.offer(cur);
                v[cur] = true;
            }
        }

        while(!dq.isEmpty()) {
            max++;
            int now = dq.poll();
            for(int cur : g[now]) {
                if(!v[cur] && type[cur] == 'R') {
                    dq.offer(cur);
                    v[cur] = true;
                }
            }
        }

    }
}

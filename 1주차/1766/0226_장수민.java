import java.io.*;
import java.util.*;

class Main {
    // 위상 정렬(Topological sort)을 사용
    // 작은 숫자일수록 풀기 쉬우므로 우선순위큐 활용

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] inDegree = new int[N + 1];
        ArrayList<ArrayList<Integer>> arrayList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            arrayList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int prev = Integer.parseInt(st.nextToken());
            int next = Integer.parseInt(st.nextToken());

            arrayList.get(prev).add(next);
            inDegree[next]++;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) pq.offer(i);
        }

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            int curr = pq.poll();
            sb.append(curr + " ");

            for (int next : arrayList.get(curr)) {
                inDegree[next]--;
                if (inDegree[next] == 0) pq.offer(next);
            }
        }

        System.out.println(sb.toString());
        br.close();
    }
}

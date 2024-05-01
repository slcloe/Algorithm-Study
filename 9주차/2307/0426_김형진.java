package club9;

// 도로검문
// 조건
// 1. 두지점을 잇는 도로는 단 하나
// 2. 노드번호는 1~N의 연속된 정수
// 3. 용의자는 1번으로 진입해서 N번으로 가야한다
// 4. 용의자는 최대한 빨리 나가려 하고, 경찰은 최대한 지연시키고자 한다
// 5. 간선의 비용은 항상 양수
// 6. 탈출 못할 시 -1

// 정리
// 1. 용의자는 1에서 N까지 최소 비용으로 가야한다 이것이 다익스트라
// 2. 용의자의 최단경로를 이루는 간선들을 하나씩 소거하는 경우를 만든다
// 3. 각각의 경우에서의 다익스트라를 다시 실행한다. 여기서 가장 최장 경로를 구하면 될듯하다

// 실패 -> 메모리 초과
import java.io.*;
import java.util.*;

public class BOJ_2307 {
    static int N, M;
    static int INF = Integer.MAX_VALUE;
    static List<List<Node>> map = new ArrayList<>();
    static boolean[][] edgeRemoved;

    static class Node implements Comparable<Node> {
        int num, cost;

        public Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        edgeRemoved = new boolean[N+1][N+1];

        for (int i = 0; i <= N; i++)
            map.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            map.get(a).add(new Node(b, cost));
            map.get(b).add(new Node(a, cost));
        }

        // 검문소 없다 치고 최단 경로 먼저 구하기
        int shortest = dijkstra(1, N);

        int max_shortest = 0;

        // 간선 하나씩 제거
        for (int from = 1; from <= N; from++) {
            for (Node edge : new ArrayList<>(map.get(from))) {
                int to = edge.num;

                // 간선 제거
                edgeRemoved[from][to] = true;
                edgeRemoved[to][from] = true;

                int currentDelay = dijkstra(1, N);

                if (currentDelay == INF) {
                    max_shortest = -1;
                    break;
                } else {
                    max_shortest = Math.max(max_shortest, currentDelay - shortest);
                }

                // 제거한 간선을 다시 추가
                edgeRemoved[from][to] = false;
                edgeRemoved[to][from] = false;
            }
        }

        System.out.println(max_shortest);
    }

    static int dijkstra(int start, int end) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        dist[start] = 0;
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (visited[current.num]) continue;
            visited[current.num] = true;

            for (Node node : map.get(current.num)) {
                if (!edgeRemoved[current.num][node.num] && dist[node.num] > dist[current.num] + node.cost) {
                    dist[node.num] = dist[current.num] + node.cost;
                    pq.offer(new Node(node.num, dist[node.num]));
                }
            }
        }
        return dist[end];
    }
}


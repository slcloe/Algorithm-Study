import java.io.*;
import java.util.*;
public class Main_14938 {
    public static void main(String[] args) throws IOException {
        final int INF = (int) 1e9;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        int[] distance = new int[n];
        int[] item = new int[n];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            item[i] = Integer.parseInt(st.nextToken());
        }

        int ans_max = 0;
        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a - 1).add(new Node(b - 1, c));
            graph.get(b - 1).add(new Node(a - 1, c));
        }

        for (int i = 0; i < n; i++) {
            int start = i;
            Arrays.fill(distance, INF);
            dijkstra(start, distance, graph);
            int sum = 0;
            for (int j = 0; j < n; j++) {
                if (j == start) {
                    sum += item[j];
                    continue;
                }
                if (distance[j] == INF) {
                    continue;
                }
                if (distance[j] <= m) {
                    sum += item[j];
                }
            }
            ans_max = Math.max(ans_max, sum);
        }
        System.out.println(ans_max);
    }

    public static void dijkstra(int start, int[] distance, ArrayList<ArrayList<Node>> graph) {
        final int INF = (int) 1e9;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        distance[start] = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int dist = node.distance;
            int now = node.index;
            if (distance[now] < dist) continue;
            for (int i = 0; i < graph.get(now).size(); i++) {
                int cost = distance[now] + graph.get(now).get(i).distance;
                if (cost < distance[graph.get(now).get(i).index]) {
                    distance[graph.get(now).get(i).index] = cost;
                    pq.offer(new Node(graph.get(now).get(i).index, cost));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int index;
        int distance;

        public Node(int index, int distance) {
            this.index = index;
            this.distance = distance;
        }

        @Override
        public int compareTo(Node o) {
            return this.distance - o.distance;
        }
    }
}

import java.io.*;
import java.util.*;

class 0315_장수민 {
    static int N, M, T, S, G, H;
    static boolean[] visited;
    static boolean[] candidatePossible;
    static boolean passed = false;
    static int[] distance;
    static ArrayList<Node>[] vertex;
    static ArrayList<Integer> answers;
    static int mustCost;
    static ArrayList<Integer> candidates;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < t; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            input(br, st);
            solve();
            output();
        }
    }

    static void solve() {
        for (int i = 0; i < candidates.size(); i++){
            int end = candidates.get(i);
            int originalDist = dijkstra(S, end);

            // 무조건 들러야 하는 목적지를 포함하는지 판별해야 함.
            int mustDist = mustCost + Math.min(dijkstra(S, G) + dijkstra(H, end), dijkstra(S, H) + dijkstra(G, end));
            if (originalDist == mustDist) answers.add(end);
        }
    }

    static void output() {
        Collections.sort(answers);
        for(int i=0; i < answers.size(); i++) System.out.print(answers.get(i) + " ");
        System.out.println();
    }

    static int dijkstra(int start, int end){
        PriorityQueue<Node> nodes = new PriorityQueue<>(((o1, o2) -> o1.cost - o2.cost));
        distance = new int[N+1];
        for(int i = 1; i <= N; i++){
            distance[i] = Integer.MAX_VALUE;
        }
        distance[start] = 0;
        nodes.add(new Node(start, 0));
        while (!nodes.isEmpty()) {
            Node nowNode = nodes.poll();
            for (int i = 0; i < vertex[nowNode.dest].size(); i++){
                Node nextNode = vertex[nowNode.dest].get(i);
                if (distance[nextNode.dest] > distance[nowNode.dest] + nextNode.cost) {
                    distance[nextNode.dest] = distance[nowNode.dest] + nextNode.cost;
                    nodes.add(new Node(nextNode.dest, distance[nextNode.dest]));
                }
            }
        }
        return distance[end];
    }

    static void input(BufferedReader br, StringTokenizer st) throws IOException {
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        answers = new ArrayList<>();
        candidates = new ArrayList<>();

        vertex = new ArrayList[N+1];
        for(int i = 0;i <= N; i++) vertex[i] = new ArrayList<>();
        for(int i = 0;i < M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            vertex[start].add(new Node(dest, cost));
            vertex[dest].add(new Node(start, cost));
            if((dest == G && start == H)|| (dest == H && start == G)) mustCost = cost;
        }
        for(int i = 0; i < T; i++){
            candidates.add(Integer.parseInt(br.readLine()));
        }
    }

    class canGo{
        int node;
        boolean can;

        public canGo(int candidate, boolean b) {
            this.node = candidate;
            this.can = b;
        }
    }

    class Node{
        int dest;
        int cost;

        public Node(int to, int cost) {
            this.dest = to;
            this.cost = cost;
        }
    }
}
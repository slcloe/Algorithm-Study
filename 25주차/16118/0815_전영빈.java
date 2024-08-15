import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
    달빛여우와 달빛늑대 각각에 대해 다익스트라를 통해 모든 정점에 대한 최단비용을 찾으면 된다.
    다만, 달빛늑대는 특정 정점에 방문하는 순간에 두 배의 속도로 올 수도 있고 절반의 속도로 올 수도 있다.
    이를 고려해서 다익스트라를 전개하자.

    추가적으로 간선의 가중치가 1일 경우 두 배의 속도를 계산하기 위해 2로 나누기를 해버리면 간선의 가중치가 0이 된다.
    미리 간선의 길이를 두 배 늘려놓는 것으로 해결했다.
 */

class Edge {
    int vertex;
    int weight;

    public Edge(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}

class Element implements Comparable<Element>{
    int vertex;
    int dist;
    int ability;

    public Element(int vertex, int dist, int ability) {
        this.vertex = vertex;
        this.dist = dist;
        this.ability = ability;
    }

    @Override
    public int compareTo(Element o) {
        return this.dist - o.dist;
    }
}

public class 달빛여우_0815_전영빈 {

    static int N;
    static int M;
    static List<Edge>[] graph;
    static int[] distFox;
    static int[][] distWolf;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N];
        distFox = new int[N];
        distWolf = new int[2][N];

        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
            distFox[i] = Integer.MAX_VALUE;
            distWolf[0][i] = Integer.MAX_VALUE;
            distWolf[1][i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken()) * 2;

            graph[a].add(new Edge(b, d));
            graph[b].add(new Edge(a, d));
        }

        dijkFox();
        dijkWolf();

        int sol = 0;
        for (int i = 1; i < N; i++) {
            if (distFox[i] < Math.min(distWolf[0][i], distWolf[1][i])) {
                sol++;
            }
        }

        System.out.println(sol);
    }

    static void dijkFox() {
        distFox[0] = 0;
        PriorityQueue<Element> heap = new PriorityQueue<>();
        heap.offer(new Element(0, 0, 0));

        while (!heap.isEmpty()) {
            Element cur = heap.poll();

            if (cur.dist > distFox[cur.vertex]) {
                continue;
            }

            for (Edge e : graph[cur.vertex]) {
                if (distFox[cur.vertex] + e.weight < distFox[e.vertex]) {
                    distFox[e.vertex] = distFox[cur.vertex] + e.weight;
                    heap.offer(new Element(e.vertex, distFox[e.vertex], 0));
                }
            }
        }
    }

    static void dijkWolf() {
        distWolf[0][0] = 0;
        PriorityQueue<Element> heap = new PriorityQueue<>();
        heap.offer(new Element(0, 0, 0));

        while (!heap.isEmpty()) {
            Element cur = heap.poll();

            if (cur.dist > distWolf[cur.ability][cur.vertex]) {
                continue;
            }

            int nextAbility = 1 - cur.ability;
            for (Edge e : graph[cur.vertex]) {
                int realWeight = (cur.ability == 0 ? e.weight / 2 : e.weight * 2);

                if (distWolf[cur.ability][cur.vertex] + realWeight < distWolf[nextAbility][e.vertex]) {
                    distWolf[nextAbility][e.vertex] = distWolf[cur.ability][cur.vertex] + realWeight;
                    heap.offer(new Element(e.vertex, distWolf[nextAbility][e.vertex], nextAbility));
                }
            }
        }
    }
}

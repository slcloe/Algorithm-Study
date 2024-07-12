import java.io.*;
import java.util.*;

public class boj_23040 {
    static int[] parents;

    static int find(int a) {
        if (parents[a] < 0) return a;
        return parents[a] = find(parents[a]);
    }
    static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return;

        int larger = parents[a] < parents[b] ? a : b;
        int smaller = parents[a] < parents[b] ? b : a;
        parents[larger] += parents[smaller];
        parents[smaller] = larger;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        parents = new int[n + 1];
        Arrays.fill(parents, -1);

        List<Integer>[] edges = new List[n + 1];
        for (int i = 1; i <= n; i++) edges[i] = new ArrayList<>();

        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            edges[a].add(b);
            edges[b].add(a);
        }

        boolean[] colors = new boolean[n + 1];
        String tmp = br.readLine();
        for (int i = 1; i <= n; i++) {
            colors[i] = tmp.charAt(i - 1) == 'R';
        }

        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        Set<Integer> visited = new HashSet<>();
        visited.add(1);

        while (!q.isEmpty()) {
            int current = q.poll();
            for (int next : edges[current]) {
                if (visited.contains(next)) continue;
                visited.add(next);
                if (colors[current] && colors[next]) {
                    union(current, next);
                }
                q.add(next);
            }
        }

        long answer = 0;
        for (int i = 1; i <= n; i++) {
            if (colors[i]) continue;

            visited = new HashSet<>();
            for (int next : edges[i]) {
                if (!colors[next] || visited.contains(find(next))) continue;
                visited.add(find(next));
                answer += -parents[find(next)];
            }
        }

        System.out.println(answer);
    }
}

import java.util.*;
class Solution {
    
    /*
    접근 방식 : 게리맨더링이 생각나는 문제. 트리를 만든 후에 방문 처리를 해놓고 탐색 후 비교.
    
    주안점
    1. 차이를 구할 때, n - firstGroup을 해서 절대값을 구하면
       firstGroup이 작을 때는 괜찮지만, 클 때는 edge case가 발생.
       그래서 n - firstGroup * 2하는 것이 정확함
    */

    static List<Integer>[] g;
    public int solution(int n, int[][] wires) {
        g = new List[n+1];
        for(int i=1; i<=n; i++) g[i] = new ArrayList<>();

        for(int i=0; i<wires.length; i++) {
            g[wires[i][0]].add(wires[i][1]);
            g[wires[i][1]].add(wires[i][0]);
        }

        int minDiff = n;
        for(int i=0; i<wires.length; i++) {
            int firstGroup = bfs(wires[i], n);
            minDiff = Math.min(minDiff, Math.abs(n - firstGroup * 2));
        }

        return minDiff;
    }

    static int bfs(int[] wire, int n) {
        boolean[] v = new boolean[n+1];
        Deque<Integer> dq = new ArrayDeque<>();
        dq.offer(wire[0]);
        v[wire[1]] = true;
        int count = 0;
        while(!dq.isEmpty()) {
            int node = dq.poll();
            count++;
            v[node] = true;
            List<Integer> nowG = g[node];
            for(int next : nowG) {
                if(!v[next]) dq.offer(next);
            }
        }
        return count;
    }
}
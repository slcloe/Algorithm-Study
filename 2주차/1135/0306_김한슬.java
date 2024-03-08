package week2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 1. children ArrayList 에 부모와 자식간의 관계를 정의한다.
 * 2. 자식들의 소요시간을 구한 후 가장 오래 걸리는 자식부터 먼저 할당해준다.
 * 3. 이때 최대값을 구하면 된다.
 *
 * 예외 ) 처음에는 자식의 깊이 혹은 자식의 개수에 따라 소요시간이 절대적으로 결정된다고 생각했다.
 *       하지만 그것들은 소요시간의 결정요소가 될 수 없기 때문에 직접 확인해보고 어떤 자식을 먼저 방문할 것인지 결정해야한다.
 *
 *       소요시간이 가장 작은 자식을 먼저 방문했다고 해서 부모노드의 최소 소요시간이 결정되지 않는다.
 *       그것보다 부모노드의 자식 수 자체가 더 많게 되면 부모노드의 최소 소요시간은 부모도느 자식 수에 의해 결정된다.
 */


public class week2_1135_0306_김한슬 {

    static int[] tree;
    static ArrayList<Integer>[] children;
    static int N;
    static int dfs(int num){
        int cnt = 0;
        int result = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2)-> (o2[1] - o1[1]));

        for(int child : children[num]){
            pq.offer(new int[] {child, dfs(child)});
        }

        while (!pq.isEmpty()){
            int[] cur = pq.poll();
            result = Math.max(result, cur[1] + ++cnt);
        }

        return result;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine(), " ");

        tree = new int[N];
        children = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            children[i] = new ArrayList<>();
        }

        tree[0] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < N; i++) {
            tree[i] = Integer.parseInt(st.nextToken());
            children[tree[i]].add(i);
        }

        System.out.println(dfs(0));
    }
}

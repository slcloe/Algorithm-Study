import java.io.*;
import java.util.*;
public class 0405_곽희웅 {

    /*
    접근 방식 : 처음에는 리프 노드의 부모 노드들을 1, 그 후로 중간에 건너뛰면서 1씩 더해줬는데, 시간초과 발생.
               가장 작은 서브트리부터 구하여 위로 올라오며 더해줌

    주안점
    1. 입력값은 루트 노드부터 주어지지 않는다.
    2. 내가 얼리어답터가 아닐 때, 내 친구들은 모두 얼리어답터여야 함
    3. 탐색할 때, 부모 노드인 경우를 제외해주어야 함
    4. 리프 노드를 가지고 있을 때와 내가 얼리어답터가 아니고 자식 중에 얼리어답터가 한 명이라도 아니라면 내가 얼리어답터여야 함
     */

    static boolean[] v, isEA;
    static List<Integer>[] g;
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        g = new List[N+1];
        for(int i=1; i<=N; i++) {
            g[i] = new ArrayList<>();
        }

        // 양방향 그래프 입력
        for(int i=0; i<N-1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int N1 = Integer.parseInt(st.nextToken());
            int N2 = Integer.parseInt(st.nextToken());
            g[N1].add(N2);
            g[N2].add(N1);
        }

        // 루트 노드부터 안으로 들어가며 탐색, 서브트리마다 최소의 얼리어답터 수 구하기
        v = new boolean[N+1];
        isEA = new boolean[N+1];
        System.out.println(dfs(1));
    }

    static int dfs(int num) {
        // 부모 노드를 제외하기 위한 입력처리
        v[num] = true;
        // 해당 노드를 루트 노드로 하는 서브트리의 얼리어답터 최소 값
        int sum = 0;
        // 내가 리프 노드를 가지고 있는지에 대한 여부
        boolean flag = false;
        // 내가 리프 노드인지에 대한 여부
        boolean leafFlag = false;
        for(int n : g[num]) {
            // 만약 부모 노드가 아니라면
            if(!v[n]) {
                // 자식 노드가 있기 때문에 true
                leafFlag = true;
                int result = dfs(n);
                // 리프 노드를 가지고 있다면 나는 무조건 얼리어답터이기 때문에 true
                if(result == -1) flag = true;
                else sum += result;
            }
        }
        v[num] = false;
        // 만약 내가 리프 노드라면 -1 리턴
        if(!leafFlag) return -1;

        // 만약 내가 자식 노드 중 리프 노드를 가지고 있다면 얼리어답터가 되며 sum+1 리턴
        if(flag) {
            isEA[num] = true;
            return sum+1;
        }
        else {
            for(int n : g[num]) {
                // 내가 리프 노드도 아니고, 자식 노드 중 리프 노드도 없을 때
                // 만약 자식 중 얼리어답터가 아닌 노드가 있다면 얼리어답터가 되며 sum+1 리턴
                if(!v[n] && !isEA[n]) {
                    isEA[num] = true;
                    return sum+1;
                }
            }
            return sum;
        }
    }
}

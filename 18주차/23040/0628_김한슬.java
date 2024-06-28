import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

/*
문제 풀이 :

B 정점을 root 로 시작해 R 정점의 개수를 모두 더한다.
dfs 로 구현했을 때 -> N^2 시간초과

R 정점의 개수를 반복적으로 구하지 않아야함. => 빨간색 정점이 모여져 있는 개수를 구해야함.

[ dfs + union ] 사용
1. 그래프 입력
2. B 정점을 root 로 R 정점들을 탐색할때 union-find로 묶어서 R 정점끼리 그룹 수 갱신

*** N 범위를 잘 체크해서 정답 MAX 값 까지 꼭꼭 체크하자 ㅠㅠ ***


 */


public class Main {

    static int N;
    static ArrayList<Integer> g[];
    static boolean[] v;
    static char strs[];
    static long dp[];
    static long findNutella(int root) {
        long result = 0;

        for(int e : g[root]) {
            if (strs[e - 1] == 'B') continue;

            if (dp[e] == 0) {
                dp[e] = findNutellaDfs(e);
                result += dp[e];
            }
            else
                result += findDp(e);
//            System.out.println(result);
        }
//        System.out.println(Arrays.toString(dp));
        return result;
    }

    static long findDp(int e) {
        if (dp[e] < 0) return dp[e] = findDp((int)-dp[e]);
        else return dp[e];
    }

    static int findNutellaDfs(int i) {
        int res = 1;
        v[i] = true;

        for (int e : g[i]) {
            if (v[e]) continue;
            if (strs[e - 1] == 'B') continue;
            dp[e] = -i;
            res += findNutellaDfs(e);
        }
        return res;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        N = Integer.parseInt(br.readLine());
        g = new ArrayList[N + 1];
        dp = new long[N + 1];

        for (int i = 0; i <= N; i++) {
            g[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
           StringTokenizer st = new StringTokenizer(br.readLine());
           int a = Integer.parseInt(st.nextToken());
           int b = Integer.parseInt(st.nextToken());

           g[a].add(b);
           g[b].add(a);
        }

        strs = br.readLine().toCharArray();

        long result = 0;
        v = new boolean[N + 1];

        for (int i = 0; i < N; i++) {
            if (strs[i] == 'B'){
                v[i + 1] = true;
                result += findNutella(i + 1);
            }
        }

        System.out.println(result);
    }
}


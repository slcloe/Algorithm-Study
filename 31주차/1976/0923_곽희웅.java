import java.util.*;
import java.io.*;
public class Main_bj_1976 {
    /*

    접근 방식 : N의 최대값이 200이며 단순히 노드와 노드간의 연결 여부를 따지는 문제이기 때문에 플루이드-워셜을 활용하여 풀이.

     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] map = new int[N+1][N+1];
        for(int i=1; i<=N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j=1; j<=N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
            map[i][i] = 1;
        }

        for(int k=1; k<=N; k++) {
            for(int i=1; i<=N; i++) {
                for(int j=1; j<=N; j++) {
                    if(map[i][k] == 1 && map[k][j] == 1) map[i][j] = 1;
                }
            }
        }

        st = new StringTokenizer(br.readLine(), " ");
        int S = Integer.parseInt(st.nextToken());
        boolean flag = false;
        for(int i=1; i<M; i++) {
            int now = Integer.parseInt(st.nextToken());
            if(map[S][now] != 1) {
                flag = true;
                break;
            }
            S = now;

        }

        if(!flag) System.out.print("YES");
        else System.out.print("NO");
    }
}

/*
 * | 활용 알고리즘 | Floyd-Warshall
 *
 * | 접근 방법 |
 *  1. Floyd-Warshall을 활용해 모든 점에서 한 점으로 갈 때의 최소 거리를 구한다
 *  2. 각각의 정점에서 출발했을 때에 대해 아래 과정을 수행한다
 *     2-1. 다른 정점까지의 거리가 M 이하이면 해당 정점이 보유한 아이템의 수를 더한다
 *     2-2. 하나의 출발 정점에 대해 얻을 수 있는 아이템 수의 최대값을 갱신한다
 */

package a2405.study.week14;

import java.io.*;
import java.util.*;

public class bj_g4_14938_서강그라운드 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] items = new int[N+1];
        for(int n=1; n<=N; n++){
            items[n] = Integer.parseInt(st.nextToken());
        }

        int[][] graph = new int[N+1][N+1];
        for(int r=1; r<=N; r++){
            for(int c=1; c<=N; c++){
                graph[r][c] = 9_999_999;
            }
        }
        for(int r=0; r<R; r++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            graph[a][b] = l;
            graph[b][a] = l;
        }

        for(int k=1; k<=N; k++){
            for(int i=1; i<=N; i++){
                for(int j=1; j<=N; j++){
                    if(i==j){
                        graph[i][j] = 0;
                        continue;
                    }
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }

        int answer = 0;
        for(int n=1; n<=N; n++){
            int sum = 0;
            for(int m=1; m<=N; m++){
                if(graph[n][m]<=M){
                    sum += items[m];
                }
            }
            answer = Math.max(answer, sum);
        }
        System.out.println(answer);
    }
}

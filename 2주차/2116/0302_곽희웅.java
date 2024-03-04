import java.io.*;
import java.util.*;
public class 0302_곽희웅 {

    /*
    접근 방식 : 주사위 기둥 옆 면들의 합 중 최대값을 구하는 문제이기 때문에 bfs, dfs 큰 차이가 없다고 생각하고
               최대값을 더 간단한 수식으로 구현할 수 있는 dfs로 구현
    자료 구조 : 윗 면과 아래 면의 인덱스를 매핑하기 위해 HashMap을 사용

    주안점
    1. 다음 놓을 주사위의 면을 구할 때 주사위 윗 면의 인덱스가 아닌 윗면의 값과 비교해야 하는 것을 주의.
    2. dfs를 사용하지 않아도 3중 for문으로 [맨 첫 주사위(0~5) - 다음 주사위의 인덱스들 - 주사위의 면] 구하면
       시간이 더 줄어들 수 있을 것으로 보임
    */

    static int N, dice[][], max;
    static Map<Integer, Integer> map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dice = new int[N][6];
        map = new HashMap<>();
        map.put(0, 5);
        map.put(1, 3);
        map.put(2, 4);
        map.put(3, 1);
        map.put(4, 2);
        map.put(5, 0);
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j=0; j<6; j++) {
                dice[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        max = Integer.MIN_VALUE;
        for(int i=0; i<6; i++) {
            // 다음 주사위 번호, 주사위 윗 면의 인덱스, 총 합
            dfs(1, i, 0);
        }
        System.out.println(max);
    }

    static void dfs(int idx, int num, int sum) {
        sum += calc(idx-1, num);
        if(idx >= N) {
            max = Math.max(max, sum);
            return;
        }
        for(int i=0; i<6; i++) {
            if(dice[idx-1][num] == dice[idx][i]) {
                int backside = map.get(i);
                dfs(idx+1, backside, sum);
            }
        }
    }

    static int calc(int idx, int num) {
        int backside = map.get(num);
        int max = 0;
        for(int i=0; i<6; i++) {
            if(i != num && i != backside) max = Math.max(max, dice[idx][i]);
        }
        return max;
    }
}

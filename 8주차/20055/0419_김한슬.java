import java.io.*;
import java.util.*;


/*
문제풀이

1. 벨트 회전 : 배열 회전이 아니라, 배열의 첫번째 인덱스의 값을 바꿈
    idx += N - 1;
    idx %= N;
2. moveBot : 봇을 한칸씩 이동한다.
     a. 벨트 회전 후 내려가는 위치에 봇이 존재한다면 삭제
     b. 먼저 추가했던 봇 순서대로 봇 이동 시작
     c. 추가 후에 내리는 위치에 봇이 존재한다면 봇 삭제
3. addBot : 배열의 첫번째 인덱스에 봇을 추가여부 확인 후 봇 추가
4. 벨트의 내구도가 0 이 될때마다 K--을 하여 K == 0이 된다면 결과 출력 후 종료


 */
public class Main {

    static int N, K;
    static int[] belt;
    static int idx = 0;
    static ArrayList<Integer> robotIdx = new ArrayList<>();
    static boolean v[];

    static void moveBot() {
        idx += N - 1; // belt 회전
        idx %= N;

        if (v[(idx + N/2 - 1) % N]){
            int j = robotIdx.indexOf((idx + N/2 - 1) % N);
            if (j != -1){
                robotIdx.remove(j);
                v[(idx + N/2 - 1) % N] = false;
            }
        }

        for (int i = 0; i < robotIdx.size(); i++) {
            int pos = robotIdx.get(i);
            if (!validBotPos(pos + 1)) continue;
            v[pos] = false;
            v[(pos + 1) % N] = true;
            belt[(pos + 1) % N]--;
            if (belt[(pos + 1) % N] == 0) K--;
            robotIdx.remove(i);
            robotIdx.add(i, (pos + 1) % N);

            if (v[(idx + N/2 - 1) % N]){
                int j = robotIdx.indexOf((idx + N/2 - 1) % N);
                if (j != -1){
                    robotIdx.remove(j);
                    i--;
                    v[(idx + N/2 - 1) % N] = false;
                }
            }
        }
    }

    static void addBot() {
        if (!validBotPos(idx)) return;
        v[idx] = true;
        belt[idx]--;
        if (belt[idx] == 0) K--;
        robotIdx.add(idx);
    }

    static boolean validBotPos(int newIndex){
        if (belt[newIndex % N] == 0) return false;
        return !v[newIndex % N];
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        N *= 2;
        belt = new int[N];
        v = new boolean[N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0;i < N; i++){
//            sb.append(st.nextToken());
            belt[i] = Integer.parseInt(st.nextToken());
        }

        int step = 0;
        while (K > 0) {
//            System.out.println(Arrays.toString(belt));

            moveBot();
            addBot();
            step++;
//            System.out.println(Arrays.toString(belt));
//            System.out.println();
        }

        System.out.println(step);
    }
}

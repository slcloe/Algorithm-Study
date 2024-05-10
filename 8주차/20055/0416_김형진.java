import java.io.*;
import java.util.*;

public class BOJ20055 {

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] belt = new int[2*N];
        boolean[] isRobot = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2*N; i++){
            belt[i] = Integer.parseInt(st.nextToken());
        }

        int cnt = 0;
        while(isOver(belt, K)) {
            int tmp = belt[2*N-1];
            for (int i = 2*N-1; i > 0; i--) {
                belt[i] = belt[i-1];
            }
            belt[0] = tmp;

            for (int i = N-1; i > 0; i--) {
                isRobot[i] = isRobot[i-1];
            }
            isRobot[0] = false;
            isRobot[N-1] = false;

            for (int i = N-2; i >= 0; i--) {
                if(isRobot[i] && !isRobot[i+1] && belt[i+1] > 0) {
                    isRobot[i] = false;
                    isRobot[i+1] = true;
                    belt[i+1]--;
                }
            }

            if(belt[0] > 0) {
                isRobot[0] = true;
                belt[0]--;
            }
            cnt++;
        }
        System.out.println(cnt);

    }

    public static boolean isOver(int[] belt, int K) {
        int count = 0;
        for (int i = 0; i < belt.length; i++) {
            if(belt[i] == 0) count++;
        }
        return count<K;
    }
}
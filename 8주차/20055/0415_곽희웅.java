import java.io.*;
import java.util.*;
public class Main_bj_20055 {
    static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] map = new int[2*N];
        int[] point = new int[2*N];
        st = new StringTokenizer(br.readLine(), " ");
        for(int i=0; i<2*N; i++) point[i] = Integer.parseInt(st.nextToken());

        int process = 0;
        while(check(point) < K) {
            process++;
            rotate(map, point);
            move(map, point);
            if(map[0] == 0 && point[0] > 0) {
                map[0] = 1;
                point[0]--;
            }
        }
        System.out.println(process);
    }

    static void rotate(int[] map, int[] point) {
        int lastPoint = point[2*N-1];
        int lastRobot = map[2*N-1];
        for(int i=2*N-2; i>=0; i--) {
            point[i+1] = point[i];
        }
        point[0] = lastPoint;
        for(int i=2*N-2; i>=0; i--) {
            map[i+1] = map[i];
            if(i == N-2 && map[N-2] == 1) {
                map[i+1] = 0;
            }
        }
        map[0] = lastRobot;
    }

    static void move(int[] map, int[] point) {
        int lastRobot = map[2*N-1];
        for(int i=2*N-2; i>=0; i--) {
            if(map[i] == 1 && map[i+1] == 0 && point[i+1] > 0) {
                map[i+1] = 1;
                map[i] = 0;
                point[i+1]--;
            }
            if(i == N-2 && map[i+1] == 1) {
                map[i+1] = 0;
            }
        }
        if(map[0] == 0 && lastRobot == 1 && point[0] == 0) {
            map[0] = lastRobot;
            point[0]--;
        }
    }

    static int check(int[] point) {
        int cnt = 0;
        for(int i=0; i<2*N; i++) if(point[i] <= 0) cnt++;
        return cnt;
    }
}

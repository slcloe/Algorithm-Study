package week5;

import java.io.*;
import java.util.*;

public class BJ_8972 {
    static int R, C, cnt;
    static char[][] map;
    static int[] Jongsoo;
    static int[] di = {0, 1, 1, 1, 0, 0, 0, -1,-1,-1};
    static int[] dj = {0,-1, 0, 1,-1, 0, 1, -1, 0, 1};

    static ArrayDeque<int[]> robots;
    static ArrayDeque<int[]> wait;
    static void printmap(){
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                System.out.print(map[i][j]+" ");
            }System.out.println();
        }
    }
    static int getDistance(int[] p1, int[] p2){
        return Math.abs(p1[0]-p2[0])+ Math.abs(p1[1]-p2[1]);
    }

    static boolean CrazyArduino(String cmd){
        // 명령어 실행
        for (int i = 0; i < cmd.length(); i++) {
            int current = cmd.charAt(i) -'0';

            // 명령어 대로 방향 이동

            int nextJongsooi = Jongsoo[0] +di[current];
            int nextJongsooj = Jongsoo[1] +dj[current];

            move(Jongsoo,new int[]{nextJongsooi, nextJongsooj});

            Jongsoo[0] = nextJongsooi;
            Jongsoo[1] = nextJongsooj;

            cnt ++;

            // 움직인 방향이 아두이노 있는곳?
            for (int[] robot: robots) {
                if(robot[0] == Jongsoo[0] && robot[1] == Jongsoo[1]) return false;
            }

            // 아두이노 이동
            if(!moveArduino()) return false;
        }
        return true;
    }
    static boolean moveArduino(){
        while(!robots.isEmpty()){
            int[] crazy = robots.poll();
            int minDist = 1000;
            int dist = 0;
            int arduinoD = 0;
            for (int d = 1; d <= 9; d++) {
                dist = getDistance(Jongsoo, crazy);

                minDist = Math.min(dist, minDist);
                if(dist == minDist) arduinoD = d;
            }

            int nextCrazyi = crazy[0] + di[arduinoD];
            int nextCrazyj = crazy[1] + dj[arduinoD];

            move(crazy, new int[] {nextCrazyi, nextCrazyj});

            crazy[0] = nextCrazyi;
            crazy[1] = nextCrazyj;

            if(crazy[0] == Jongsoo[0] && crazy[1] == Jongsoo[1]){
                return false;
            }
            wait.offer(crazy);
        }
        while(!wait.isEmpty()){
            int[] r = wait.poll();
            robots.offer(r);
        }
        return true;
    };

    static void move(int[] before, int[] after){
        char newVal = map[before[0]][before[1]];
        map[before[0]][before[1]] = '.';
        map[after[0]][after[1]] = newVal;
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];

        robots = new ArrayDeque<>();
        wait = new ArrayDeque<>();

        cnt = 0;

        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                char current = line.charAt(j);
                if(current=='R') robots.add(new int[]{i,j});
                if(current=='I') Jongsoo = new int[] {i,j};
                map[i][j] = current;
            }
        }
//        printmap();
//        System.out.println();

        String cmd = br.readLine();
        boolean result = CrazyArduino(cmd);

        if(!result) System.out.print("Kraj "+cnt);
        else{
            printmap();
        }
    }


}

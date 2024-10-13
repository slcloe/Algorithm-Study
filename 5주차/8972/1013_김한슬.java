/*
[문제 풀이]

1. 종수 이동  (pos 변수 이동)
2. 미친아두이노와 충돌 검사
3. 미친아두이노 이동
  : 이동하면서 충돌 검사를 동시에 진행한다.
  : 미친아두이노의 이동위치를 hashmap 에 저장한다. 
    key : String ("x_pos" + " " + "y_pos")
    value : Integer (해당 위치에 있는 아두이노 개수)
4. hashmap을 순회하면서 해당 위치에 1개만 있는 아두이노만 hashSet으로 옮긴다.
*/

import java.io.*;
import java.util.*;

public class Main {
    static char[][] map;
    static int N, M;
    static HashSet<int[]> mops;
    static int[] pos;
    static int[] dx = {1, 1, 1, 0, 0, 0, -1, -1, -1};
    static int[] dy = {-1, 0, 1, -1, 0, 1, -1, 0, 1};

    static String getSolution(int[] cmds) {
        for (int i = 0; i < cmds.length; i++) {

            // 1 종수 이동
            pos[0] = dx[--cmds[i]] + pos[0];
            pos[1] = dy[cmds[i]] + pos[1];

            // 2 충돌 검사
            if (map[pos[0]][pos[1]] == 'R')
                return "kraj " + (i + 1);

            // 3 아두이노 이동 및 충돌 검사
            map = new char[N][M];
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    map[j][k] = '.';
                }
            }

            if (!moveCrazy()) {
                return "kraj " + (i + 1);
            }
        }

        return "";
    }

    static boolean moveCrazy() {
        HashMap<String, Integer> mopPos = new HashMap<>();

        for(int[] mop : mops) {
            int tx = mop[0], ty = mop[1];

            if (pos[0] < mop[0]) {
                tx--;
            } else if (pos[0] > mop[0]) {
                tx++;
            }

            if (pos[1] < mop[1]) {
                ty--;
            } else if (pos[1] > mop[1]) {
                ty++;
            }

            if (tx == pos[0] && ty == pos[1]) return false; // 충돌

            String key = tx + " " + ty;

            if (mopPos.containsKey(key)) {
                mopPos.put(key, mopPos.get(key) + 1);
            } else {
                mopPos.put(key , 1);
            }
        }


        mops = new HashSet<>();
        for (String key : mopPos.keySet()) {
            if (mopPos.get(key) == 1) {
                int[] tmpPos = Arrays.stream(key.split(" "))
                        .mapToInt(Integer::parseInt).toArray();
                map[tmpPos[0]][tmpPos[1]] = 'R';
                mops.add(new int[] {tmpPos[0], tmpPos[1]});
            }
        }

        return true;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        pos = new int[2];
        mops = new HashSet<>();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            map[i] = str.toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'I'){
                    pos[0] = i;
                    pos[1] = j;
                }
                if (map[i][j] == 'R') {
                    mops.add(new int[] {i, j});
                }
            }
        }

        String cmd = br.readLine();
        int[] cmds = Arrays.stream(cmd.split(""))
                .mapToInt(Integer::parseInt).toArray();


        String sol = getSolution(cmds);

        if (sol.equals("")) {
            map[pos[0]][pos[1]] = 'I';

            StringBuilder sb = new StringBuilder();
            for(char[] arrs : map) {
                for(char ch : arrs) {
                    sb.append(ch);
                }
                sb.append('\n');
            }
            System.out.println(sb.toString());
        } else {
            System.out.println(sol);
        }

    }
}

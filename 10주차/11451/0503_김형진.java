package club10;

import java.util.*;
import java.io.*;

class Packman {
    int i1, j1, i2, j2;
    StringBuilder sb;

    Packman(int i1, int j1, int i2, int j2, StringBuilder sb) {
        this.i1 = i1;
        this.j1 = j1;
        this.i2 = i2;
        this.j2 = j2;
        this.sb = sb;
    }
}

public class BOJ_11451 {
    static int result;
    static String route;
    static int N, M;
    static final int[] di = {-1, 0, 1, 0};
    static final int[] dj = {0, 1, 0, -1};
    static final char[] dir = {'N', 'E', 'S', 'W'};
    static char[][] map;

    static int OOB(int n, int d) {
        if (d == 0 && n == -1) return N - 1;
        if (d == 1 && n == M) return 0;
        if (d == 2 && n == N) return 0;
        if (d == 3 && n == -1) return M - 1;
        return n;
    }

    static boolean isWall(int i, int j) {
        return map[i][j] == 'X';
    }

    static boolean isGhost(int i, int j) {
        return map[i][j] == 'G';
    }

    static void BFS(Packman PM) {
        Queue<Packman> q = new ArrayDeque<>();
        boolean[][][][] visited = new boolean[N][M][N][M];

        q.offer(PM);
        visited[PM.i1][PM.j1][PM.i2][PM.j2] = true;

        while (!q.isEmpty()) {
            Packman P = q.poll();

            int i1 = P.i1;
            int j1 = P.j1;
            int i2 = P.i2;
            int j2 = P.j2;
            StringBuilder sb = new StringBuilder(P.sb);

            if (i1 == i2 && j1 == j2) {
                if (result > sb.length()) {
                    result = sb.length();
                    route = sb.toString();
                    return;
                }
            }

            for (int d = 0; d < 4; d++) {
                int curLen = sb.length();
                int ni1 = OOB(i1 + di[d], d);
                int nj1 = OOB(j1 + dj[d], d);
                int ni2 = OOB(i2 + di[d], d);
                int nj2 = OOB(j2 + dj[d], d);

                if (isGhost(ni1, nj1) || isGhost(ni2, nj2) || visited[ni1][nj1][ni2][nj2]) continue;

                boolean isWall1 = isWall(ni1, nj1);
                boolean isWall2 = isWall(ni2, nj2);

                if (isWall1 && isWall2) continue;

                if (isWall1) {
                    ni1 = i1;
                    nj1 = j1;
                }
                if (isWall2) {
                    ni2 = i2;
                    nj2 = j2;
                }
                if (visited[ni1][nj1][ni2][nj2]) continue;

                if (ni1 == i1 && nj1 == j1 && ni2 == i2 && nj2 == j2) continue;
                visited[ni1][nj1][ni2][nj2] = true;
                sb.append(dir[d]);
                q.offer(new Packman(ni1, nj1, ni2, nj2, new StringBuilder(sb)));

                sb.setLength(curLen);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int tc = 0; tc < T; tc++) {
            result = Integer.MAX_VALUE;
            route= "";
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new char[N][M];

            int i1 = 0, j1 = 0, i2 = 0, j2 = 0;
            for (int i = 0; i < N; i++) {
                String line = br.readLine();
                for (int j = 0; j < M; j++) {
                    map[i][j] = line.charAt(j);
                    if (map[i][j] == 'P') {
                        if (i1 == 0 && j1 == 0) {
                            i1 = i;
                            j1 = j;
                        } else {
                            i2 = i;
                            j2 = j;
                        }
                    }
                }
            }

            BFS(new Packman(i1, j1, i2, j2, new StringBuilder()));
            if (result == Integer.MAX_VALUE) {
                System.out.println("IMPOSSIBLE");
            } else {
                System.out.println(result + " " + route);
            }
        }
    }
}


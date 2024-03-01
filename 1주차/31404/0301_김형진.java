package week1;

//(좌표 이동/ 방향전환 / 규칙표 전환 생략)
//        DFS 이용해서 현재 좌표(R,C)와 방향을 들고다님
//        3차원 방문처리 배열 v에 좌표와 방향을 같이 기록
//        deque에도 따로 저장. 먼지를 치우게 되면 deque에 기록된 배열에 재방문 할 수 있도록 다시 미방문 처리
//        총 움직인 횟수 - 먼지가 없는 곳을 훑어간 횟수를 구해야함
//        => 실패(overflow)
import java.io.*;
import java.util.*;

public class BOJ31404 {
    static int H, W, R, C, D;

    // 0 90 180 270
    static int[] di = {-1, 0, 1, 0};
    static int[] dj = { 0, 1, 0, -1};
    static int dust;
    static int empty;
    static int move;
    static void clean(int R, int C, int D, int[][] ruleA, int[][] ruleB, boolean[][] lab, boolean[][][]v, boolean AB, ArrayDeque<int[]> visited){
        if(!isInMap(R,C)){
            return;
        }
        // 같은 방향 같은 좌표로 들어온 경우
        if(v[R][C][D] && !lab[R][C]){
            return;
        }
        // 현재 위치와 방향을 방문처리
        v[R][C][D] = true;
        visited.offer(new int[] {R,C,D});
        move ++;
        if(lab[R][C]){  // 현재 칸에 먼지?
            while(!visited.isEmpty()){ // 여태 지나온 곳들을 재방문할 수 있도록 미방문 처리
                int[] nv = visited.poll();
                v[nv[0]][nv[1]][nv[2]] = false;
            }
            lab[R][C] = false; // 먼지를 제거
            AB = true;  // 제거했으면 규칙표 A를
            dust ++;
            empty = 0;// 먼지 하나 제거완료
        }else{          // 먼지가 없으면
            AB = false; // 규칙표 B로 전환
            empty ++;
            // 방문 기록
        }

        // 규칙표 대로 회전합니다
        int nD = AB ? (ruleA[R][C]): (ruleB[R][C]);
        D = (D+nD)%4;

        // 바라보는 방향으로 한칸 전진
        int nR = R + di[D];
        int nC = C + dj[D];
        clean(nR, nC, D, ruleA, ruleB, lab, v, AB, visited);
    }

    static boolean isInMap(int i, int j){
        return (0<=i && i<H && 0<=j && j<W);
    }
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st= new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        int[][] ruleA= new int[H][W];
        int[][] ruleB= new int[H][W];

        boolean[][] lab= new boolean[H][W];
        boolean[][][] v  = new boolean[H][W][4];

        move = 0;
        // 처음엔 먼지로 뒤덮여있음
        for (int i = 0; i < H; i++) {
            Arrays.fill(lab[i], true);
        }

        // 규칙표 A
        for (int i = 0; i < H; i++) {
            String line = br.readLine();
            for (int j = 0; j < W; j++) {
                ruleA[i][j] = line.charAt(j)-48;
            }
        }
        // 규칙표 B
        for (int i = 0; i < H; i++) {
            String line = br.readLine();
            for (int j = 0; j < W; j++) {
                ruleB[i][j] = line.charAt(j)-48;
            }
        }

        ArrayDeque<int[]> visited = new ArrayDeque<>();
        clean(R,C,D, ruleA, ruleB,lab,v, false,visited);
        System.out.println(move-empty);
    }
}

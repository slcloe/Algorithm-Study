package a2402.study.week5;

/*
 * [[ 미해결 ]]
 * 구상
 *  - 각 칸의 경우를 3개로 나눈다
 *      0 | 먼지 O
 *          먼지 제거 -> 규칙표 A 참조해서 회전 -> 1칸 전진
 *      1 | 먼지 X, 처음 청소된 상태(규칙표 A만 참조 완료)
 *          규칙표 B 참조해서 회전 -> 1칸 전진
 *      2 | 먼지 X, 규칙표 B까지 참조 완료
 *          이동하려는 칸도 상태 2일 경우 더 탐색할 필요가 없으므로 종료
 *  - 더 제거할 먼지가 있는지 판별하는 부분에서 문제 발생하는 듯 함, 보완 예정 🥲
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_g3_31404_아리스_청소합니다_Easy {
    static int H, W, R, C, D;
    static int[] dr = {-1, 0, 1, 0}, dc = {0, 1, 0, -1};
    static int[][] A, B;
    static boolean[][] room;

    static boolean isOut(int r, int c){
        return 0 > r || r >= H || 0 > c || c >= W;
    }

    static void printRoom(){
        for(int r=0; r<H; r++){
            for(int c=0; c<W; c++){ System.out.print(room[r][c]? "O": "X"); }
            System.out.println();
        }
    }

    static boolean canGo(int r, int c, int d){
        if(!room[r][c]){ return true; }
        int nr = r + dr[d];
        int nc = c + dc[d];
        if (isOut(r, c)){ return false; }
        int nd = room[r][c]? (d + B[nr][nc]) % 4: (d + A[nr][nc]) % 4;
        return canGo(nr, nc, nd);
    }

    static int goAlice(){
        int cnt = 0;
        while(true){
            cnt++;
            System.out.println();
            System.out.println(">>"+cnt);
            System.out.println("Alice:("+R+","+C+"), D:"+D+" , dust:"+(room[R][C]? "O": "X"));
            if(!canGo(R, C, D)){ return cnt; }
            if(!room[R][C]){ room[R][C] = true; }
            D = room[R][C]? (D + B[R][C]) % 4: (D + A[R][C]) % 4;
            R += dr[D];
            C += dc[D];
            if (isOut(R, C)){ return cnt; }
        }
        
        
//        while(true) {
//            cnt++;
//            System.out.println();
//            System.out.println(">> "+cnt);
//            System.out.println("Alice:("+R+","+C+"), status:"+room[R][C]);
//            switch (room[R][C]) {
//                case 0:
//                    room[R][C]++;
//                    System.out.print(D+" + "+A[R][C]+"(A)"+" -> ");
//
//                    // try A
//                    // 1) rotate
//                    D = (D + A[R][C]) % 4;
//                    System.out.println(D);
//                    // 2) move
//                    R += dr[D];
//                    C += dc[D];
//                    System.out.println("Alice:("+R+","+C+"), status:"+room[R][C]);
//                    // 3) check boundary
//                    if (isOut(R, C)) { return cnt; }
//                    break;
//                case 1:
//                    room[R][C]++;
//                    System.out.print(D+" + "+B[R][C]+"(B)"+" -> ");
//                    // try B
//                    // 1) rotate
//                    D = (D + B[R][C]) % 4;
//                    System.out.println(D);
//                    // 2) move
//                    R += dr[D];
//                    C += dc[D];
//                    System.out.println("Alice:("+R+","+C+"), status:"+room[R][C]);
//                    // 3) check boundary
//                    if (isOut(R, C)) { return cnt; }
//                    break;
//            }
//        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        A = new int[H][W];
        B = new int[H][W];
        room = new boolean[H][W];
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        for(int r=0; r<H; r++){
            char[] line = br.readLine().toCharArray();
            for(int c=0; c<W; c++){ A[r][c] = line[c] - '0'; }
        }
        for(int r=0; r<H; r++){
            char[] line = br.readLine().toCharArray();
            for(int c=0; c<W; c++){ B[r][c] = line[c] - '0'; }
        }
        System.out.println(goAlice());
//        System.out.println(H+" "+W);
//        System.out.println(R+" "+C+" "+D);
//        for(int r=0; r<H; r++){
//            for(int c=0; c<W; c++){ System.out.print(A[r][c]); }
//            System.out.println();
//        }
//        for(int r=0; r<H; r++){
//            for(int c=0; c<W; c++){ System.out.print(B[r][c]); }
//            System.out.println();
//        }
    }
}

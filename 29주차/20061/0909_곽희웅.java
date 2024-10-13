package com.example.algo;

import java.util.*;
import java.io.*;
public class Main_bj_20061 {

    /*

    접근 방식 : 단순 구현 문제, 조건을 꼼꼼히 체크하며 진행하여 풀이
    주안점
    1. 점수는 경계에 걸쳐서 없어질 때 얻는 것이 아닌 꽉 차서 없어질 때 얻어지는 것.
    2. 1 or 2줄 사라질 때 그대로 옮긴 후 초기화하는 순서를 유의할 것.

     */
    static boolean[][] blue, green;
    static List<int[]> blocks, nowBlocks;
    static List<Integer> fullBlocks;
    static int isCombined, totalScore, deleteCount;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        blocks = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            blocks.add(new int[]{
                Integer.parseInt(st.nextToken())
                , Integer.parseInt(st.nextToken())
                , Integer.parseInt(st.nextToken())});
        }

        // blue, green
        blue = new boolean[4][6];
        green = new boolean[4][6];

        totalScore = 0;
        play(0);

        int existTile = 0;
        for(int i=0; i<4; i++) {
            for(int j=0; j<6; j++) {
                if(blue[i][j]) existTile++;
                if(green[i][j]) existTile++;
            }
        }

        System.out.println(totalScore);
        System.out.print(existTile);
    }

    private static void play(int idx) {
        if(idx == blocks.size()) return;
        nowBlocks = new ArrayList<>();

        setBlueBlock(blocks.get(idx));
        if(isCombined == 1) playOne(blue);
        else if(isCombined == 2) playBlueTwoGreenThree(blue);
        else playBlueThreeGreenTwo(blue);

        setGreenBlock();
        if(isCombined == 1) playOne(green);
        else if(isCombined == 2) playBlueThreeGreenTwo(green);
        else playBlueTwoGreenThree(green);

        play(idx+1);
    }

    private static void playOne(boolean[][] target) {
        int newY = -1;
        if(isCombined == 1) {
            for (int i=1; i<6; i++) {
                if (target[nowBlocks.get(0)[0]][i]) {
                    newY = i-1;
                    break;
                }
            }

            if (newY == -1) newY = 5;
            target[nowBlocks.get(0)[0]][newY] = true;

            checkFullBlocks(target);
            calcDeleteCount(target);

            // 블럭이 1열에서 멈추면 5열 삭제 후 모두 이동
            if (deleteCount == 1) {
                for (int i=5; i>=1; i--) {
                    for (int j=0; j<4; j++) target[j][i] = target[j][i-1];
                }
                for(int i=0; i<4; i++) target[i][0] = false;
            }
        }
    }

    private static void playBlueTwoGreenThree(boolean[][] target) {
        int newY = -1;
        for(int i=0; i<5; i++) {
            if(!target[nowBlocks.get(0)[0]][i] && !target[nowBlocks.get(0)[0]][i+1]) {
                newY = i;
            } else break;
        }

        target[nowBlocks.get(0)[0]][newY] = true;
        target[nowBlocks.get(0)[0]][newY+1] = true;
        checkFullBlocks(target);
        calcDeleteCount(target);

        // 0이면 2줄 삭제
        if(deleteCount == 2) {
            for(int i=5; i>=2; i--) {
                for(int j=0; j<4; j++) {
                    target[j][i] = target[j][i-2];
                }
            }
            for(int i=0; i<=1; i++) {
                for(int j=0; j<4; j++) {
                    target[j][i] = false;
                }
            }
        } else if(deleteCount == 1) {
            for(int i=5; i>=1; i--) {
                for(int j=0; j<4; j++) {
                    target[j][i] = target[j][i-1];
                }
            }
            for(int i=0; i<4; i++) target[i][0] = false;
        }
    }

    private static void playBlueThreeGreenTwo(boolean[][] target) {
        int newY = -1;
        for(int i=0; i<6; i++) {
            if(!target[nowBlocks.get(0)[0]][i] && !target[nowBlocks.get(1)[0]][i]) {
                newY = i;
            } else break;
        }

        target[nowBlocks.get(0)[0]][newY] = true;
        target[nowBlocks.get(1)[0]][newY] = true;

        checkFullBlocks(target);
        calcDeleteCount(target);

        if(deleteCount == 1) {
            for(int i=5; i>=1; i--) {
                for(int j=0; j<4; j++) {
                    target[j][i] = target[j][i-1];
                }
            }
            for(int i=0; i<4; i++) target[i][0] = false;
        }
    }

    private static void checkFullBlocks(boolean[][] target) {
        fullBlocks = new ArrayList<>();
        // 블럭이 가득 찼는지 확인
        for(int i=0; i<6; i++) {
            int blockCount = 0;
            for(int j=0; j<4; j++) {
                if(target[j][i]) blockCount++;
            }
            if(blockCount == 4) fullBlocks.add(i);
        }

        totalScore += fullBlocks.size();

        // 가득 찼다면 삭제 후 모두 이동
        int order = 5;
        boolean[][] temp = new boolean[4][6];
        for(int i=5; i>=0; i--) {
            if(fullBlocks.contains(i)) continue;
            for(int j=0; j<4; j++) temp[j][order] = target[j][i];
            order--;
        }
        for(int i=0; i<6; i++) {
            for(int j=0; j<4; j++) {
                target[j][i] = temp[j][i];
            }
        }
    }

    private static void setBlueBlock(int[] now) {
        nowBlocks = new ArrayList<>();
        isCombined = 1;
        nowBlocks.add(new int[] {now[1], now[2]});
        if(now[0] == 2) {
            isCombined = 2;
            nowBlocks.add(new int[] {now[1], now[2]+1});
        }
        if(now[0] == 3) {
            isCombined = 3;
            nowBlocks.add(new int[] {now[1]+1, now[2]});
        }
    }

    private static void setGreenBlock() {
        int newX = 3 - nowBlocks.get(0)[1];
        int newY = nowBlocks.get(0)[0];
        int nextX = -1;
        int nextY = -1;
        if(nowBlocks.size() > 1) {
            nextX = 3 - nowBlocks.get(1)[1];
            nextY = nowBlocks.get(1)[0];
        }

        nowBlocks.clear();
        nowBlocks.add(new int[] {newX, newY});
        if(isCombined != 1) nowBlocks.add(new int[] {nextX, nextY});
    }

    private static void calcDeleteCount(boolean[][] target) {
        deleteCount = 0;
        for(int i=0; i<2; i++) {
            for(int j=0; j<4; j++) {
                if(target[j][i]) {
                    deleteCount++;
                    break;
                }
            }
        }
    }
}

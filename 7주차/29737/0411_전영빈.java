import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
    어떤 알고리즘이나 자료구조를 이용하는 문제는 아닌 것 같고
    루프문도 최대 7 * 100 * 100 번 밖에 돌지 않아서 그냥 조건문을 떡칠하여 풀어보았다.
    빠뜨린 케이스가 있는 듯.
 */

public class 브실이잔디가좋아_0411_전영빈 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        List<Object[]> streak = new ArrayList<>();

        for (int seq = 0; seq < N; seq++) {
            String name = br.readLine();
            int condition1 = 0;
            int condition2 = 0;
            int condition3 = 0;
            int condition4 = 0;

            char[][] solved = new char[7][W];
            for (int i = 0; i < 7; i++) {
                char[] temp = br.readLine().toCharArray();
                for (int j = 0; j < W; j++) {
                    solved[i][j] =temp[j];
                }
            }

            int tc1 = 0;
            int tc2 = 0;
            int tc3 = 0;
            int tc4 = 0;

            int freezeCount = 0;

            for (int j = 0; j < W; j++) {
                for (int i = 0; i < 7; i++) {
                    if (solved[i][j] == 'O') {
                        if (tc1 == 0) {
                            tc3 = j * 7 + i;
                        }
                        tc1++;
                        tc2 += freezeCount;
                        freezeCount = 0;
                    }
                    else if (solved[i][j] == 'X') {
                        tc4++;

                        if (tc1 > condition1) {
                            condition1 = tc1;
                            condition2 = tc2;
                            condition3 = tc3;
                        }

                        tc1 = 0;
                        tc2 = 0;
                        tc3 = 0;
                        freezeCount = 0;

                    } else {
                        if (tc1 != 0) {
                            freezeCount++;
                        }
                    }
                }
            }

            if (tc1 > condition1) {
                condition1 = tc1;
                condition2 = tc2;
                condition3 = tc3;
            }

            condition4 = tc4;

            streak.add(new Object[]{name, condition1, condition2, condition3, condition4});
        }

        streak.sort((o1, o2) -> {
            String t1 = String.valueOf(o1[0]);
            String t2 = String.valueOf(o2[0]);
            return t1.compareTo(t2);
        });

        streak.sort((o1, o2)-> {
            return ((int) o1[4] - (int)o2[4]);
        });

        streak.sort((o1, o2)-> {
            return ((int) o1[3] - (int)o2[3]);
        });

        streak.sort((o1, o2)-> {
            return ((int) o1[2] - (int)o2[2]);
        });

        streak.sort((o1, o2)-> {
            return -((int) o1[1] - (int)o2[1]);
        });

        int count = 0;
        int previous1 = -1;
        int previous2 = -1;
        int previous3 = -1;
        int previous4 = -1;
        for (int i = 0; i < streak.size(); i++) {
            if (previous1 != (int) streak.get(i)[1] ||
                previous2 != (int) streak.get(i)[2] ||
                previous3 != (int) streak.get(i)[3] ||
                previous4 != (int) streak.get(i)[4]
            ) {
                count++;
                previous1 = (int) streak.get(i)[1];
                previous2 = (int) streak.get(i)[2];
                previous3 = (int) streak.get(i)[3];
                previous4 = (int) streak.get(i)[4];
            }
            System.out.printf("%d. %s\n", count, streak.get(i)[0]);
            System.out.printf("%d %d %d %d\n", (int) streak.get(i)[1], (int) streak.get(i)[2], (int) streak.get(i)[3], (int) streak.get(i)[4]);
        }
    }

}

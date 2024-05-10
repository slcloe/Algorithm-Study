import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
    우선 세 명의 조합을 구하기 위해 우선 정렬한 뒤 한 명을 고정시켜 놓고 투 포인터를 이용해
    선택된 선택의 바로 뒤 학생부터 가장 마지막 학생까지 범위를 좁혀가며 탐색했다.
    문제의 핵심은 같은 코딩 실력을 가진 학생이라도 다른 학생으로 간주되어야 한다는 것이다.
    이를 고려하지 않으면 right 포인터에 해당 하는 점수가 여러 명일 경우 문제가 발생한다.
    left 학생과 right 학생을 포함한 합이 0이 된다고 일반적인 투 포인터를 적용하듯이 count를 1만큼 추가하면
    left 학생에게 가능한 다른 경우의 수가 배제된다.
    따라서 count를 right 점수를 가진 학생의 수만큼 더해주어야 한다.
 */

public class 합이0_0406_전영빈 {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] number = new int[N];
        int[] count = new int[20001];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            number[i] = Integer.parseInt(st.nextToken());
            count[number[i] + 10000]++;
        }

        Arrays.sort(number);

        long sol = 0L;

        for (int i = 0; i < N-2; i++) {
            int left = i + 1;
            int right = N - 1;

            int object = -number[i];

            while (left < right) {
                int temp = number[left] + number[right];

                if (temp < object) {
                    left++;
                } else if (temp > object) {
                    right--;
                } else {
                    if (number[left] == number[right]) {
                        int c = right-left+1;
                        sol += (long) c *(c-1)/2;
                        break;

                    } else {
                        int rc = count[number[right] + 10000];
                        sol += rc;

                        left++;
                    }
                }
            }
        }

        System.out.println(sol);
    }
}

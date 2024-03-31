import java.io.*;
import java.util.*;
public class Main_bj_3151 {

    /*
    접근 방식 : 최대 개수가 10_000개이므로 단순 반복문은 시간 초과가 날 것으로 고려, 정렬 후 투 포인터 방식을 통해 구현

    주안점
    1. 10_000 C 3은 int형 범위를 벗어나므로 long형으로 바꾸어줘야 함
    2. Map을 사용해서 같은 숫자가 몇 개 나왔는지 체크하고, v 배열을 통해 현재 기준으로 한 숫자의 중복 여부 개수를 체크
    3. 결국 시작 수와 left가 같은 경우, left와 right가 같은 경우, 숫자가 중복으로 나올 경우를 고려해주어야 함
    */


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] nums = new int[N];
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        Arrays.sort(nums);
        int[] v = new int[20001];
        long count = 0;
        for(int i=0; i<N-2; i++) {
            int start = nums[i];
            v[start+10000]++;
            int left = i+1;
            int right = N-1;
            while(right > left) {
                if(start + nums[left] + nums[right] == 0) {
                    if(nums[left] == nums[right]) {
                        int bothNum = map.get(nums[left]) - v[nums[left]+10000];
                        count += ((long) bothNum * (bothNum - 1)) / 2;
                        break;
                    }
                    int leftNum = map.get(nums[left]) - v[nums[left]+10000];
                    int rightNum = map.get(nums[right]);

                    count += (long) leftNum * rightNum;
                    left += leftNum;
                    right--;
                } else if(start + nums[left] + nums[right] > 0) right--;
                else left++;
            }
        }
        System.out.println(count);
    }
}

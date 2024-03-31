package week5;
import java.util.*;

public class Solution {
    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        HashMap<String, Integer> indexMap = new HashMap<>();
        HashMap<String, String> referralMap = new HashMap<>();

        for (int i = 0; i < enroll.length; i++) {
            indexMap.put(enroll[i], i);
            referralMap.put(enroll[i], referral[i]);
        }

        for (int i = 0; i < seller.length; i++) {
            String currentSeller = seller[i];
            int salesAmount = amount[i] * 100;

            while (!currentSeller.equals("-")) {
                int income = salesAmount / 10;
                int selfIncome = salesAmount - income;

                if (income < 1) {
                    answer[indexMap.get(currentSeller)] += salesAmount;
                    break;
                } else {
                    answer[indexMap.get(currentSeller)] += selfIncome;
                    currentSeller = referralMap.get(currentSeller);
                    salesAmount = income;
                }

                if (currentSeller == null || currentSeller.equals("-")) break;
            }
        }

        return answer;
    }
    public static void main(String[] args) {
        Solution s = new Solution();
//
//        s.solution();
    }
}




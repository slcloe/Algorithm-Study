package week2;

import java.util.*;
import java.io.*;
public class BOJ9935 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        String bomb = br.readLine();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < line.length(); i++) {
            stack.push(line.charAt(i));
            int boom = 0;
            if(stack.size()>=bomb.length()){
                for (int j = 0; j < bomb.length(); j++) {
                    if(stack.get(stack.size()-bomb.length()+j).equals(bomb.charAt(j))){
                        boom ++;
                    }
                }
                if(boom == bomb.length()){
                    for (int j = 0; j < bomb.length(); j++) {
                        stack.pop();
                    }
                }
            }
        }
        if(stack.isEmpty()) System.out.println("FRULA");

        StringBuilder sb = new StringBuilder();
        for (char c:
             stack) {
            sb.append(c);
        }

        System.out.println(sb);
    }
}
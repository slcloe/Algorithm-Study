package a2409.study.week28;

import java.io.*;
import java.util.*;

public class bj_g4_2179_비슷한_단어 {

    private static class Word implements Comparable<Word> {
        int id;
        String word;

        public Word(int id, String word) {
            this.id = id;
            this.word = word;
        }

        @Override
        public int compareTo(Word o){
            if(word.equals(o.word)){
                return id = o.id;
            }
            return word.compareTo(o.word);
        }

        @Override
        public String toString() {
            return
                id + " " + word;
        }
    }

    private static boolean findWord(List<Word> words, String word){
        for (Word value : words) {
            if (value.word.equals(word)) {
                return true;
            }
        }
        return false;
    }

    private static void print(String[] arr, int N){
        for(int n=0; n<N; n++){
            System.out.println(arr[n]+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Word> words = new ArrayList<>();

        for(int n=0; n<N; n++){
            String word = br.readLine();
            if(findWord(words, word)){
                continue;
            }
            words.add(new Word(n, word));
        }

        System.out.println(words);
        Collections.sort(words);
        System.out.println(words);



    }
}

/*
4
abe
abcd
abe
abe

* */
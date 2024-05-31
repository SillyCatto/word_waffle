package core;

import utils.FileIO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class WordPicker {
    private final ArrayList<String> wordList;
    private static String answer = "default";

    public WordPicker(String filePath){
        wordList = new ArrayList<>();
        FileIO wordListFile = new FileIO(FileIO.File.READER, filePath);
        try {
            String word = wordListFile.readLine();
            while (word != null){
                wordList.add(word.toUpperCase());
                word = wordListFile.readLine();
            }
        } catch (IOException e){
            System.out.println("Error reading file");
            e.printStackTrace();
        } finally {
            wordListFile.close();
        }
    }


    public void generateWord(){
        Random rnd = new Random();
        answer = wordList.get(rnd.nextInt(wordList.size()));
        System.out.println("current answer is: " + answer);
    }


    public static String getAnswer(){
        return answer;
    }
}

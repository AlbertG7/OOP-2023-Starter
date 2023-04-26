package ie.tudublin;

import java.util.ArrayList;
import java.util.Random;
import processing.core.PApplet;

public class DANI extends PApplet {
    ArrayList<Word> words = new ArrayList<>();
    Random random = new Random();

    public void settings() {
        size(1000, 1000);
    }

	
	

    String[] sonnet;

    public void setup() {
        colorMode(HSB);
        loadFile("small.txt");
        printModel();
        sonnet = writeSonnet();
    }

    public void keyPressed() {
        if (key == ' ') {
            sonnet = writeSonnet();
        }
    }

    float off = 0;

    public void draw() {
        background(0);
        fill(255);
        noStroke();
        textSize(20);
        textAlign(CENTER, CENTER);

        int lineHeight = 24;
        int startingY = height / 2 - (lineHeight * sonnet.length) / 2;

        for (int i = 0; i < sonnet.length; i++) {
            text(sonnet[i], width / 2, startingY + (i * lineHeight));
        }
    }



    public void loadFile(String filename) {
        String[] lines = loadStrings(filename);

        for (String line : lines) {
            String[] wordsInLine = split(line, ' ');

            for (int i = 0; i < wordsInLine.length - 1; i++) {
                String currentWord = wordsInLine[i].replaceAll("[^\\w\\s]", "").toLowerCase();
                String nextWord = wordsInLine[i + 1].replaceAll("[^\\w\\s]", "").toLowerCase();

                Word word = findWord(currentWord);

                if (word == null) {
                    word = new Word(currentWord);
                    words.add(word);
                }

                Follow follow = word.findFollow(nextWord);

                if (follow == null) {
                    follow = new Follow(nextWord, 1);
                    word.addFollow(follow);
                } else {
                    follow.setCount(follow.getCount() + 1);
                }
            }
        }
    }

    public Word findWord(String str) {
        for (Word word : words) {
            if (word.getWord().equals(str)) {
                return word;
            }
        }
        return null;
    }

    public void printModel() {
        for (Word word : words) {
            System.out.print(word.getWord() + ": ");
            for (Follow follow : word.getFollows()) {
                System.out.print(follow.getWord() + "(" + follow.getCount() + ") ");
            }
            System.out.println();
        }
    }

    public String[] writeSonnet() {
        String[] sonnet = new String[14];

        for (int i = 0; i < 14; i++) {
            StringBuilder line = new StringBuilder();

            Word word = words.get(random.nextInt(words.size()));
            for (int j = 0; j < 8; j++) {
                line.append(word.getWord()).append(" ");

                ArrayList<Follow> follows = word.getFollows();
                if (follows.isEmpty()) {
                    break;
                }

                Follow follow = follows.get(random.nextInt(follows.size()));
                word = findWord(follow.getWord());
            }
            sonnet[i] = line.toString().trim();
        }

        return sonnet;
	}
	
	
	
}


    

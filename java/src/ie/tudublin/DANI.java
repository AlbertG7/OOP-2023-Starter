package ie.tudublin;

import java.util.ArrayList;

import processing.core.PApplet;

public class DANI extends PApplet {
    ArrayList<Word> model = new ArrayList<>();

    public void settings() {
        size(1000, 1000);
    }

    String[] sonnet;

    public String[] writeSonnet() {
        ArrayList<String> sonnet = new ArrayList<>();
    
        // start with a random word
        Word currentWord = model.get((int) random(model.size()));
        sonnet.add(currentWord.getWord());
    
        // generate 13 lines of sonnet
        for (int i = 0; i < 13; i++) {
            // select the next word based on its probability
            String nextWord = currentWord.getNextWord();
            sonnet.add(nextWord);
    
            // update the current word
            currentWord = findWord(nextWord);
        }
    
        // convert the ArrayList to a String array and return it
        return sonnet.toArray(new String[0]);
    }
    

    public void setup() {
        colorMode(HSB);
        loadFile("text.txt");
        printModel();
    }

    public void keyPressed() {

    }

    float off = 0;

    public void draw() {
        background(0);
        fill(255);
        noStroke();
        textSize(20);
        textAlign(CENTER, CENTER);
    }

    public void loadFile(String filename) {
        String[] lines = loadStrings(filename);

        for (String line : lines) {
            String[] words = split(line, ' ');

            for (int i = 0; i < words.length - 1; i++) {
                String w1 = words[i].replaceAll("[^\\w\\s]", "").toLowerCase();
                String w2 = words[i + 1].replaceAll("[^\\w\\s]", "").toLowerCase();

                Word wordObj = findWord(w1);
                if (wordObj == null) {
                    wordObj = new Word(w1);
                    model.add(wordObj);
                }

                Follow followObj = wordObj.findFollow(w2);
                if (followObj == null) {
                    followObj = new Follow(w2, 1);
                    wordObj.addFollow(followObj);
                } else {
                    followObj.setCount(followObj.getCount() + 1);
                }
            }
        }
    }

    public Word findWord(String str) {
        for (Word w : model) {
            if (w.getWord().equals(str)) {
                return w;
            }
        }
        return null;
    }

    public void printModel() {
        for (Word w : model) {
            System.out.print(w.getWord() + ": ");
            ArrayList<Follow> follows = w.getFollows();
            for (Follow f : follows) {
                System.out.print(f.getWord() + "(" + f.getCount() + ") ");
            }
            System.out.println();
        }
    }
}


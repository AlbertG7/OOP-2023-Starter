package ie.tudublin;

import java.util.ArrayList;
import java.util.Random;

public class Word {
    private String word;
    private ArrayList<Follow> follows;
    private Random random = new Random();

    // Constructor
    public Word(String word) {
        this.word = word;
        this.follows = new ArrayList<>();
    }

    // Accessor method for word
    public String getWord() {
        return this.word;
    }

    // Accessor method for follows
    public ArrayList<Follow> getFollows() {
        return this.follows;
    }

    // Mutator method for word
    public void setWord(String word) {
        this.word = word;
    }

    // Add a Follow object to the follows ArrayList
    public void addFollow(Follow follow) {
        this.follows.add(follow);
    }

    // Find a Follow object in the follows ArrayList
    public Follow findFollow(String str) {
        for (Follow follow : this.follows) {
            if (follow.getWord().equals(str)) {
                return follow;
            }
        }
        return null;
    }

    // Get the next word based on the probability of occurrence
    public String getNextWord() {
        int totalCount = 0;
        for (Follow follow : follows) {
            totalCount += follow.getCount();
        }

        int randomCount = random.nextInt(totalCount);
        int currentCount = 0;

        for (Follow follow : follows) {
            currentCount += follow.getCount();
            if (currentCount > randomCount) {
                return follow.getWord();
            }
        }

        return follows.get(follows.size() - 1).getWord();
    }

    // toString method
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Word { word: \"" + this.word + "\", follows: [\n");
        for (Follow follow : this.follows) {
            sb.append("  ").append(follow.toString()).append(",\n");
        }
        sb.append("]}");
        return sb.toString();
    }
}

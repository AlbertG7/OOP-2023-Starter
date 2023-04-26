package ie.tudublin;

import java.util.ArrayList;

public class Word {
    private String word;
    private ArrayList<Follow> follows;

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

package ie.tudublin;

public class Follow {
    private String word;
    private int count;

    // Default constructor
    public Follow() {
        this.word = "";
        this.count = 0;
    }

    // Constructor with parameters
    public Follow(String word, int count) {
        this.word = word;
        this.count = count;
    }

    // Accessor method for word
    public String getWord() {
        return this.word;
    }

    // Accessor method for count
    public int getCount() {
        return this.count;
    }

    // Mutator method for word
    public void setWord(String word) {
        this.word = word;
    }

    // Mutator method for count
    public void setCount(int count) {
        this.count = count;
    }

    // toString method
    public String toString() {
        return "Follow { word: \"" + this.word + "\", count: " + this.count + " }";
    }
}

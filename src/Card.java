public class Card {
    private String value;
    private String type;

    public Card(String value, String type) {
        this.value = value;
        this.type = type;
    }

    public int getValue(){
        // A-J-Q-K
        if ("AJQK".contains(value)){
            if (value == "A") return 11;
            return 10;
        }
        // 2-10
        return Integer.parseInt(value);
    }

    public boolean isAce(){
        return value == "A";
    }

    @Override
    public String toString() {
        return value + "-" + type;
    }
}

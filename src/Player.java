import java.util.ArrayList;
import java.util.List;

public class Player {
    private List<Card> playerHand;
    private int playerSum;
    private int playerAceCount;

    public Player() {
        this.playerHand = new ArrayList<>();
        this.playerSum = 0;
        this.playerAceCount = 0;
    }

    public List<Card> getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(List<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public int getPlayerSum() {
        return playerSum;
    }

    public void setPlayerSum(int playerSum) {
        this.playerSum = playerSum;
    }

    public int getPlayerAceCount() {
        return playerAceCount;
    }

    public void setPlayerAceCount(int playerAceCount) {
        this.playerAceCount = playerAceCount;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerHand=" + playerHand +
                ", playerSum=" + playerSum +
                ", playerAceCount=" + playerAceCount +
                '}';
    }
}

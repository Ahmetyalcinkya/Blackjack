import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private List<Card> dealerHand;
    private int dealerSum;
    private int dealerAceCount;

    public Dealer() {
        this.dealerHand = new ArrayList<>();
        this.dealerSum = 0;
        this.dealerAceCount = 0;
    }

    public List<Card> getDealerHand() {
        return dealerHand;
    }

    public int getDealerSum() {
        return dealerSum;
    }

    public int getDealerAceCount() {
        return dealerAceCount;
    }

    public void setDealerSum(int dealerSum) {
        this.dealerSum = dealerSum;
    }

    public void setDealerAceCount(int dealerAceCount) {
        this.dealerAceCount = dealerAceCount;
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "dealerHand=" + dealerHand +
                ", dealerSum=" + dealerSum +
                ", dealerAceCount=" + dealerAceCount +
                '}';
    }
}

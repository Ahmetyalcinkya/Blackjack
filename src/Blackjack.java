import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Blackjack {
    private int width;
    private int height;
    private List<Card> deck;
    private Card hiddenCard;
    public Blackjack(int width, int height, Dealer dealer, Player player) {
        this.width = width;
        this.height = height;
        startGame(dealer, player);
    }
    public void startGame(Dealer dealer, Player player){
        //deck
        buildDeck();
        shuffleDeck();

        int aceCountForDealer = 0;
        int aceCountForPlayer = 0;

        //dealer
        hiddenCard = deck.remove(deck.size()-1);
        dealer.setDealerSum(hiddenCard.getValue());
        aceCountForDealer += hiddenCard.isAce() ? 1 : 0;
        dealer.setDealerAceCount(aceCountForDealer);

        Card card = deck.remove(deck.size()-1);
        dealer.setDealerSum(dealer.getDealerSum() + card.getValue());
        aceCountForDealer += card.isAce() ? 1 : 0;
        dealer.setDealerAceCount(dealer.getDealerAceCount() + aceCountForDealer);
        dealer.getDealerHand().add(card);

        //player
        for(int i = 0; i < 2; i++){
            card = deck.remove(deck.size()-1);
            player.setPlayerSum(player.getPlayerSum() + card.getValue());
            aceCountForPlayer += card.isAce() ? 1 : 0;
            player.setPlayerAceCount(player.getPlayerAceCount() + aceCountForPlayer);
            player.getPlayerHand().add(card);
        }
    }
    public void buildDeck(){
        deck = new ArrayList<>();

        for(int i = 0; i < Constants.TYPES.length; i++){
            for(int j =0; j < Constants.VALUES.length; j++){
                Card card = new Card(Constants.VALUES[j], Constants.TYPES[i]);
                deck.add(card);
            }
        }
    }
    public void shuffleDeck(){
        Random random = new Random();

        for (int i = 0; i < deck.size(); i++){
            int j = random.nextInt(deck.size());
            Card currentCard = deck.get(i);
            Card randomCard = deck.get(j);
            deck.set(i, randomCard);
            deck.set(j, currentCard);
        }
    }
    public List<Card> getDeck() {
        return deck;
    }

    public Card getHiddenCard() {
        return hiddenCard;
    }

    public int reducePlayerAce(Player player){
        while (player.getPlayerSum() > 21 && player.getPlayerAceCount() > 0){
            player.setPlayerSum(player.getPlayerSum() - 10);
            player.setPlayerAceCount(player.getPlayerAceCount() - 1);
        }
        return player.getPlayerSum();
    }
    public int reduceDealerAce(Dealer dealer) {
        while (dealer.getDealerSum() > 21 && dealer.getDealerAceCount() > 0) {
            dealer.setDealerSum(dealer.getDealerSum() - 10);
            dealer.setDealerAceCount(dealer.getDealerAceCount() - 1);
        }
        return dealer.getDealerSum();
    }
}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {

        int boardWidth = 600;
        int boardHeight = boardWidth;
        int cardWidth = 110;
        int cardHeight = 154;

        Dealer dealer = new Dealer();
        Player player = new Player();
        Blackjack blackjack = new Blackjack(boardWidth, boardHeight, dealer, player);


        JFrame frame = new JFrame("Blackjack");
        JPanel buttonPanel = new JPanel();
        JButton hitButton = new JButton("Hit");
        JButton stayButton = new JButton("Stay");
        JPanel panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g){
                super.paintComponent(g);

                //hidden card
                try{
                    Image hiddenCardImg = new ImageIcon(getClass().getResource("./Cards/BACK.png")).getImage();
                    if(!stayButton.isEnabled()){
                        hiddenCardImg = new ImageIcon(getClass().getResource(blackjack.getHiddenCard().getImagePath())).getImage();
                    }
                    g.drawImage(hiddenCardImg, 20 , 20, cardWidth, cardHeight, null);

                    //dealer's hand
                    for (int i = 0 ; i < dealer.getDealerHand().size(); i++){
                        Card card = dealer.getDealerHand().get(i);
                        Image cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
                        g.drawImage(cardImg, cardWidth + 25 + (cardWidth + 5) * i, 20, cardWidth, cardHeight, null);
                    }

                    //player's hand
                    for (int i = 0; i < player.getPlayerHand().size(); i++){
                        Card card = player.getPlayerHand().get(i);
                        Image cardImg = new ImageIcon(getClass().getResource(card.getImagePath())).getImage();
                        g.drawImage(cardImg, 20 + (cardWidth + 5) * i, 320, cardWidth, cardHeight, null);
                    }

                    if (!stayButton.isEnabled()){
                        dealer.setDealerSum(blackjack.reduceDealerAce(dealer));
                        player.setPlayerSum(blackjack.reducePlayerAce(player));
                        System.out.println("STAY: ");
                        System.out.println(dealer.getDealerSum());
                        System.out.println(player.getPlayerSum());

                        String message = "";
                        if(player.getPlayerSum() > 21 ){
                            message = "You lose !";
                        }else if(dealer.getDealerSum() > 21) {
                            message = "You win !";
                        } else if (player.getPlayerSum() == dealer.getDealerSum()) {
                            message = "Tie !";
                        } else if (player.getPlayerSum() > dealer.getDealerSum()) {
                            message = "You win !";
                        } else if (player.getPlayerSum() < dealer.getDealerSum()) {
                            message = "You lose !";
                        }

                        g.setFont(new Font("Arial", Font.PLAIN, 32));
                        g.setColor(Color.WHITE);
                        g.drawString(message, 220, 250);
                    }

                } catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        };

        frame.setVisible(true);
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(53,101,77));
        frame.add(panel);

        hitButton.setFocusable(false);
        stayButton.setFocusable(false);
        buttonPanel.add(hitButton);
        buttonPanel.add(stayButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        hitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Card card = blackjack.getDeck().remove(blackjack.getDeck().size()-1);
                player.setPlayerSum(player.getPlayerSum() + card.getValue());
                int aceCount = card.isAce() ? 1 : 0;
                player.setPlayerAceCount(player.getPlayerAceCount() + aceCount);
                player.getPlayerHand().add(card);
                if (blackjack.reducePlayerAce(player) > 21){
                    hitButton.setEnabled(false);
                }
                panel.repaint();
            }
        });
        stayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                hitButton.setEnabled(false);
                stayButton.setEnabled(false);

                while (dealer.getDealerSum() < 17){
                    Card card = blackjack.getDeck().remove(blackjack.getDeck().size() -1 );
                    dealer.setDealerSum(dealer.getDealerSum() + card.getValue());
                    int aceCount = card.isAce() ? 1 : 0;
                    dealer.setDealerAceCount(dealer.getDealerAceCount() + aceCount);
                    dealer.getDealerHand().add(card);
                }
                panel.repaint();
            }
        });
        panel.repaint();
    }
}
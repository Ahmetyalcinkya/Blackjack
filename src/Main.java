import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        int boardWidth = 600;
        int boardHeight = boardWidth;

        JFrame frame = new JFrame("Blackjack");
        JPanel panel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JButton hitButton = new JButton("Hit");
        JButton stayButton = new JButton("Stay");

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
        frame.add(buttonPanel);

        Blackjack blackjack = new Blackjack(boardWidth, boardHeight);

//        blackjack.shuffleDeck();

    }
}
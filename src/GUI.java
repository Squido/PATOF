import java.awt.*;

import javax.swing.*;

@SuppressWarnings("serial")
public class GUI extends JFrame {
	private JPanel board = new JPanel();
	private JPanel hand = new JPanel();
	private JPanel opphand = new JPanel();
	private JPanel card = new Card(1);
	
	public GUI(){
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setBackground(Color.white);
		this.setLayout(new BorderLayout());
		this.setBounds(0, 0, 1024, 768);
		this.setVisible(true);
		
		hand.setBackground(Color.GRAY);
		hand.setPreferredSize(new Dimension(1024, 768/5));
		board.setBackground(Color.LIGHT_GRAY);
		opphand.setBackground(Color.GRAY);
		opphand.setPreferredSize(new Dimension(1024, 768/5));
		
		hand.add(card);
		
		this.add(opphand, BorderLayout.BEFORE_FIRST_LINE);
		this.add(hand, BorderLayout.AFTER_LAST_LINE);
		this.add(board);
		
	}
	
}

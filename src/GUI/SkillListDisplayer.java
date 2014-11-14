package GUI;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

public class SkillListDisplayer extends JPanel {
	JList<String> list;
	Card card;
	SkillListDisplayer sLD;
	public SkillListDisplayer(Card c,DefaultListModel<String> skillz){
		sLD = this;
		this.setBounds(0,0,1024,768);
		card=c;
		card.getGameWindow().getPane().add(this);
		setOpaque(false);
		list=new JList<String>(skillz);
		list.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				System.out.print(list.getSelectedValue());
				card.getGameWindow().getPane().remove(list);
				card.getGameWindow().getPane().remove(sLD);
				card.getGameWindow().getPane().repaint();
				//c.getGameWindow().controller.costamDlaDanejAkcji
			}
		});
		addMouseListener(new MouseAdapter(){
			@Override
			public void mouseClicked(MouseEvent e){
				card.getGameWindow().getPane().remove(list);
				card.getGameWindow().getPane().remove(sLD);
				card.getGameWindow().getPane().repaint();
			}
		});
	}
	public void setListBounds(int x,int y,int width,int height){
		list.setBounds(x,y,width,height);
	}
	public JList getList(){
		return list;
	}
}

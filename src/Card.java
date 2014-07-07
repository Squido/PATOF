import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.util.*;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class Card extends JPanel {
	private String name;
	private int att, hp, def, cost;
	private boolean magic, dist, counter;
	private BufferedImage img, border;
	List<Skill> skillz = new ArrayList<Skill>();

	public Card(int id) {
		File file = new File("cards.txt");
		Scanner in = null;
		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < id; i++) {
			in.nextLine();
		}
		String tmp = in.nextLine() + "\n";
		char[] line = tmp.toCharArray();
		int i = 0;
		while (!(line[i] >= '0' && line[i] <= '9')) {
			name = name + line[i];
			i++;
		}
		setAtt(line[i] - 48);
		i += 2;
		setDef(line[i] - 48);
		i += 2;
		setHp(line[i] - 48);
		i += 2;
		setCost(line[i] - 48);
		i += 2;
		setMagic(line[i] == 't');
		i += 2;
		setDist(line[i] == 't');
		i += 2;
		tmp = "";
		while (line[i] != ' ' && line[i] != '\n') {
			tmp = tmp + line[i];
			i++;
		}
		if (line[i - 1] == '\n') {
			i--;
		}
		int j = 0;
		while (line[i] != '\n') {
			j = line[i] - 48;
			i++;
			if (line[i] != ' ') {
				j = j * 10 + (line[i] - 48);
			}
			skillz.add(SkillFactory.getSkill(SkillList.get(i)));
			j = 0;
			i++;
		}
		setCounter(false);
		try {
			this.img = ImageIO.read(new File(tmp));
		} catch (IOException ex) {
		}
		try {
			border = ImageIO.read(new File("s_border.jpg"));
		} catch (IOException ex) {
		}
		this.setPreferredSize(new Dimension(100, 140));
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(border, 0, 0, null);
		g.drawImage(img, 30, 30, null);
	}

	public int getAtt() {
		return att;
	}

	public void setAtt(int att) {
		this.att = att;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public boolean isMagic() {
		return magic;
	}

	public void setMagic(boolean magic) {
		this.magic = magic;
	}

	public boolean isDist() {
		return dist;
	}

	public void setDist(boolean dist) {
		this.dist = dist;
	}

	public boolean isCounter() {
		return counter;
	}

	public void setCounter(boolean counter) {
		this.counter = counter;
	}
}

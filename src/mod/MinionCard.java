package mod;

import java.io.*;
import java.util.*;

public class MinionCard extends Card {

	public MinionCard(int id) {
		File file = new File("cards.txt");
		Scanner in = null;
		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		in.nextLine();
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
		int j = gettingValue(i, line);
		setAtt(j);
		i += String.valueOf(j).length() + 1;
		j = gettingValue(i, line);
		setDef(j);
		i += String.valueOf(j).length() + 1;
		j = gettingValue(i, line);
		setHp(j);
		this.maxHp = hp;
		i += String.valueOf(j).length() + 1;
		j = gettingValue(i, line);
		setCost(j); 
		i += 2;
		setMagic(line[i] == 't');
		i += 2;
		setDist(line[i] == 't');
		i += 2;
		imagePath = "";
		while (line[i] != ' ' && line[i] != '\n') {
			imagePath = imagePath + line[i];
			i++;
		}
		tmp = "";
		while (line[i] != ' ' && line[i] != '\n') {
			tmp = tmp + line[i];
			i++;
		}
		if (line[i - 1] == '\n') {
			i--;
		}
		j = 0;
		while (line[i] != '\n') {
			j = line[i] - '0';
			i++;
			if (line[i] != ' ') {
				j = j * 10 + (line[i] - '0');
			}
			skillz.add(SkillFactory.getSkill(i));
			j = 0;
			i++;
		}
		setCounter(false);
	}

	@Override
	public void attack(Card defender) {
		// apply other attack effects
		defender.defend(this);
	}

	@Override
	public void defend(Card attacker) {
		// apply other defense effects
		int dmg = attacker.getAtt();
		if (!attacker.isMagic()) {
			dmg -= this.getDef();
		}
		this.setHp(this.getHp() - dmg);
		if (counter && hp > 0 && !attacker.isDist()) {
			attacker.setHp(attacker.getHp() - (att - attacker.getDef()));
		}
	}

	public void heal(int amount) {
		if (hp + amount > maxHp) {
			hp = maxHp;
		} else {
			hp += amount;
		}
	}

	private int gettingValue(int i, char[] line) {
		int j = 0;
		while (line[i] != ' ') {
			j = j * 10 + (line[i] - '0');
			i++;
		}
		return j;
	}
}

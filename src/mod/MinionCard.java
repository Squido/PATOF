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
		while (!Character.isWhitespace(line[i])) {
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
		int j = 0;
		while (line[i] != '\n') {
			j = line[i] - 48;
			i++;
			if (line[i] != ' ') {
				j = j * 10 + (line[i] - 48);
			}
			skillz.add(SkillFactory.getSkill(i));
			j = 0;
			i++;
		}
		setCounter(false);
	}
}

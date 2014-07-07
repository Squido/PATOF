import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SkillList {
	private static List<String> list = new ArrayList<String>();

	public SkillList() {
		File file = new File("cards.txt");
		Scanner in = null;
		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String tmp, name;
		char[] line;
		while (in.hasNextLine()) {
			name = "";
			tmp = in.nextLine() + "\n";
			line = tmp.toCharArray();
			for (int i = 0; line[i] != ';'; i++) {
				name += line[i];
			}
			list.add(name);
		}
	}

	public static String get(int i) {
		return list.get(i);
	}
}

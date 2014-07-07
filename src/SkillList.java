import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class SkillList {
	private static List<String> list = new ArrayList<String>();

	public SkillList() {
		File file = new File("cards.txt"); //skills.txt
		Scanner in = null;
		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		while (in.hasNextLine()) {
			list.add(in.nextLine());
		}
	}

	public static String get(int i) {
		return list.get(i);
	}
}

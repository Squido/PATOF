public abstract class SkillFactory {
	public static Skill getSkill(String name){
		Skill s = null;
		try {
			s = (Skill) Class.forName(name).newInstance();
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
		return s;
	}
}

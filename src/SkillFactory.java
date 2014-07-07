public abstract class SkillFactory {
	public static Skill getSkill(int id){
		String name = SkillList.get(id);
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

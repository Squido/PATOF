package mod;

import java.util.*;

public abstract class Card {
	protected String name;
	protected int att, hp, def, cost;
	protected boolean magic, dist, counter;
	protected List<Skill> skillz = new ArrayList<Skill>();

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

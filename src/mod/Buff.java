package mod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Buff {
	private String atribute;
	private int value;
	private Card target;
	Method set, get;

	public Buff(String atribute, int value, Card target) {
		this.setAtribute(atribute);
		this.value = value;
		this.target = target;
		try {
			set = Card.class.getMethod("set" + atribute);
			get = Card.class.getMethod("get" + atribute);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}

	public void apply() {
		int x = 0;
		try {
			x = (int) get.invoke(target) + value;
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e1) {
			e1.printStackTrace();
		}
		try {
			set.invoke(target, x);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public void de_apply(Card card) {
		int x = 0;
		try {
			x = (int) get.invoke(card) - value;
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e1) {
			e1.printStackTrace();
		}
		try {
			set.invoke(card, x);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public String getAtribute() {
		return atribute;
	}

	public void setAtribute(String atribute) {
		this.atribute = atribute;
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}

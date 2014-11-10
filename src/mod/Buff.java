package mod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Buff {
	private Atributes atribute;
	private int value;
	private boolean ok, does_it_change_anything;
	private Card target;
	Method set, get;

	public Buff(Atributes atribute, int value, Card target) {
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

	public Buff(Atributes atribute, boolean ok, Card target) {
		this.setAtribute(atribute);
		this.ok = ok;
		this.target = target;
		try {
			set = Card.class.getMethod("set" + atribute);
			get = Card.class.getMethod("get" + atribute);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		try {
			if((boolean)get.invoke(target)==ok){
				does_it_change_anything = false;
			} else {
				does_it_change_anything = true;
			}
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	public void apply() {
		if (atribute.type() && does_it_change_anything == true) {
			try {
				set.invoke(target, ok);
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				e.printStackTrace();
			}
		} else {
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
	}

	public void de_apply(Card card) {
		value = -value;
		ok = !ok;
		apply();
	}

	public Atributes getAtribute() {
		return atribute;
	}

	public void setAtribute(Atributes atribute) {
		this.atribute = atribute;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
}

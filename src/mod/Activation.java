package mod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cont.GameController;

public class Activation {
	// to bedzie dodawane do active skilli karty
	public Activation(String whenDoesItActivate){
		Method add = null;
		try {
			add = GameController.class.getMethod("add" + whenDoesItActivate.toUpperCase());
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		try {
			add.invoke(GameController.class, this);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	public void activate() {
		try {
			this.wait(); //for signal from GUI
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}

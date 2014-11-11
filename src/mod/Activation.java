package mod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import cont.GUIController;
import cont.Turn;

public class Activation {
	// to bedzie dodawane do active skilli karty
	public Activation(String whenDoesItActivate){
		Method add = null;
		try {
			add = Turn.class.getMethod("add" + whenDoesItActivate.toUpperCase());
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
		try {
			add.invoke(GUIController.getTurn(), this);
		} catch (IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	public void activate() {
		
	}

}

package cont;

import java.util.*;

import mod.Activation;

public class Turn extends Thread {
	private static int counter;
	private static List<Activation> b_activations;
	private static List<Activation> m_activations;
	private static List<Activation> e_activations;

	public Turn() {
		counter = 1;
		b_activations = new LinkedList<Activation>();
		m_activations = new LinkedList<Activation>();
		e_activations = new LinkedList<Activation>();
	}

	public void run() {
		// before drawing a card activations
		synchronized (this) {
			for (Activation i : b_activations) {
				i.activate();
			}

			// draw
			GameController.getPlayer(0).draw();
			GameController.getPlayer(1).draw();

			// before placement activations
			for (Activation i : m_activations) {
				i.activate();
			}
		}
		// placement
		// skills and targets (na zmiane?)
		// fight result
		// end turn activations
		synchronized (this) {
			for (Activation i : e_activations) {
				i.activate();
			}
		}
	}

	public static int getTurnNo() {
		return counter;
	}

	public static void addB(Activation a) {
		b_activations.add(a);
	}

	public static void addM(Activation a) {
		m_activations.add(a);
	}

	public static void addE(Activation a) {
		e_activations.add(a);
	}

	public static void removeActivation(Activation a) {
		b_activations.remove(a);
		m_activations.remove(a);
		e_activations.remove(a);
	}
}

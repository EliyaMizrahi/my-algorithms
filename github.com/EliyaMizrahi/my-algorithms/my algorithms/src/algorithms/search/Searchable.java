package algorithms.search;

import java.util.ArrayList;

/**
 * The Searchable<T> is an Interface class that has following methods.
 * @author Eliya Mizrahi
 * @version 1.0
 * @since 26-08-2015
 * @param <T>
 */
public interface Searchable<T> {
	
	public State<T> getStartState();
	public State<T> getGoalState();
	public ArrayList<State<T>> getAllPossibleStates(State<T> s);
	public double getMoveCost();

}

package algorithms.search;

/**
 * The Heuristic<T> is an Interface class that has following method.
 * @author Eliya Mizrahi
 * @version 1.0
 * @since 26-08-2015
 * @param <T>
 */
public interface Heuristic<T> {
   double distanceCalculate(State<T> currState, State<T> goalState);
}

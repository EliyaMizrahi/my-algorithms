package algorithms.search;

/**
 * The Searcher<T> is an Interface class that has following methods.
 * @author Eliya Mizrahi
 * @version 1.0
 * @since 26-08-2015
 * @param <T>
 */
public interface Searcher<T> {
	public Solution<T> search (Searchable<T> s);
	public int getNumberOfNodesEvaluated();
}

package algorithms.search;

/**
 * The Astar<T> program implements an application that use A* algorithm
 * Astar consist from heuristic
 * @author Eliya Mizrahi
 * @version 1.0
 * @since 26-08-2015
 * 
 */
public class Astar<T> extends BFS<T> {

	private Heuristic<T> heuristic;

	/**
	 * Constructor
	 * @param h- represent heuristic
	 */
	public Astar(Heuristic<T> h) {
		this.heuristic=h;
	}
	
	@Override
	/**
	 * This method is used to calculate the sum of the result from the BFS class and
	 * the sum of heuristic distance multiply edge
	 * @param state
	 * @param search- represent a Searchable
	 * @return double this returns the total sum
	 */
	protected double calculateCost(State<T> state, Searchable<T> search)
	{
		return super.calculateCost(state, search)+
				(heuristic.distanceCalculate(state, search.getGoalState()) * search.getMoveCost());
	}
}

package algorithms.search;

import algorithms.mazeGenerators.Position;

/**
 * The MazeManhattanDistance program implements an application that
 * calculate the cost using Manhattan Distance Heuristic and implements Heuristic interface 
 * @author Eliya Mizrahi
 * @version 1.0
 * @since 26-08-2015
 *
 */
public class MazeManhattanDistance implements Heuristic<Position> {

	@Override
	/**
	 * This method is used to calculate the distance from current state to the goal state.
	 * The calculate is the total sum of each pivot distance.
	 * @param currState- represent the current state
	 * @param goalState- represent the goal state
	 * @return double this return total distance calculate
	 */	
	public double distanceCalculate(State<Position> currState, State<Position> goalState) {
		double result = 0, sum = 0;
		Position posStart = currState.getState();
		Position posEnd = goalState.getState();

		//the distance of Y axis
		if (posStart.getY() < posEnd.getY()) {
			sum = posEnd.getY() - posStart.getY();
			result += sum;
		} else {
			sum = posStart.getY() - posEnd.getY();
			result += sum;
		}
		//the distance of X axis
		if (posStart.getX() < posEnd.getX()) {
			sum = posEnd.getX() - posStart.getX();
			result += sum;
		} else {
			sum = posStart.getX() - posEnd.getX();
			result += sum;
		}
		//the distance of Z axis
		if (posStart.getZ() < posEnd.getZ()) {
			sum = posEnd.getZ() - posStart.getZ();
			result += sum;
		} else {
			sum = posStart.getZ() - posEnd.getZ();
			result += sum;
		}
		return result;
	}

}

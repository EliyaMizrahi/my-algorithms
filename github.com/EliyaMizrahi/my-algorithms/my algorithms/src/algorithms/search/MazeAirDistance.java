package algorithms.search;

import algorithms.mazeGenerators.Position;
/**
 * The MazeAirDistance program implements an application that
 * calculate the cost using Air Distance Heuristic and implements Heuristic interface 
 * @author Eliya Mizrahi
 * @version 1.0
 * @since 26-08-2015
 *
 */

public class MazeAirDistance implements Heuristic<Position> {

	@Override
	/**
	 * This method is used to calculate the distance from current state to the goal state.
	 * The calculate is the sum of each pivot distance squared.
	 * @param currState- represent the current state
	 * @param goalState- represent the goal state
	 * @return double this return the root of total distance calculate
	 */
	public double distanceCalculate(State<Position> currState, State<Position> goalState) {
		double result = 0, y = 0, x = 0, z = 0;
		Position posStart = currState.getState();
		Position posEnd = goalState.getState();

		//the distance of Y axis
		if (posStart.getY() < posEnd.getY()) {
			y = posEnd.getY() - posStart.getY();
		} else {
			y = posStart.getY() - posEnd.getY();
		}
		//the distance of X axis
		if (posStart.getX() < posEnd.getX()) {
			x = posEnd.getX() - posStart.getX();
		} else {
			x = posStart.getX() - posEnd.getX();
		}
		//the distance of Z axis
		if (posStart.getZ() < posEnd.getZ()) {
			z = posEnd.getZ() - posStart.getZ();
		} else {
			z = posStart.getZ() - posEnd.getZ();
		}

		result = Math.pow(y, 2) + Math.pow(x, 2) + Math.pow(z, 2);
		return Math.sqrt(result);
	}

}

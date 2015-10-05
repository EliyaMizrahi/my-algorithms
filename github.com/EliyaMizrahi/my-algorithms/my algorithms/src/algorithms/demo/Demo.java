package algorithms.demo;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Maze3dGenerator;
import algorithms.mazeGenerators.MyMaze3dGenerator;
import algorithms.mazeGenerators.Position;
import algorithms.search.Astar;
import algorithms.search.BFS;
import algorithms.search.CommonSearcher;
import algorithms.search.MazeAirDistance;
import algorithms.search.MazeManhattanDistance;
import algorithms.search.MazeSearchable;
import algorithms.search.Solution;

/**
 * The Demo program implements an application that consist from main method,
 * using with reference method (run)
 * @author Eliya Mizrahi
 * @version 1.0
 * @since 26-8-2015
 *
 */

public class Demo {

	/**
	 * This method is used to: create a maze3d according to MyMazeGenerator.
	 * print the maze. solve the maze with BFS algorithm. solve the maze with
	 * AStar algorithm, using each heuristic of calculate and print the
	 * solution. print how many state each algorithm create.
	 */
	public void run() {
		Maze3dGenerator mGenerator = new MyMaze3dGenerator(3, 3, 3);
		Maze3d maze = mGenerator.generate(3, 3, 3);
		maze.print();
		MazeSearchable mSearchable = new MazeSearchable(maze);
		CommonSearcher<Position> cSearcher = new BFS<Position>();
		Solution<Position> solution = cSearcher.search(mSearchable);
		int bfsState = cSearcher.getNumberOfNodesEvaluated();
		System.out.println("The number of noads evaluated in BFS is: " + bfsState);
		solution.printStack();
		cSearcher = new Astar<Position>(new MazeManhattanDistance());
		solution = cSearcher.search(mSearchable);
		int manhattanState = cSearcher.getNumberOfNodesEvaluated();
		System.out.println("The number of noads evaluated in AStar Manhattan distance is: " + manhattanState);
		solution.printStack();
		cSearcher = new Astar<Position>(new MazeAirDistance());
		solution = cSearcher.search(mSearchable);
		int airDistanceState = cSearcher.getNumberOfNodesEvaluated();
		System.out.println("The number of noads evaluated in AStar Air distance is: " + airDistanceState);
		solution.printStack();
	}
}

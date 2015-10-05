package algorithms.mazeGenerators;

/**
 * The AbstractGeneralMaze program implements an application that organize all
 * the common methods of different mazes create and implement the methods of the
 * Maze3dGenerator interface.
 * @author Eliya Mizrahi
 * @version 1.0
 * @since 09-09-2015
 */
public abstract class Maze3dGeneratorAbstract implements Maze3dGenerator {

	public abstract Maze3d generate(int ySize, int xSize, int zSize);

	/**
	 * This method is used to measure the time it take to create a maze from the
	 * beginning
	 * 
	 * @return string, this returns string of the total time
	 */
	@Override
	public String measureAlgorithmTime(int ySize, int xSize, int zSize) {
		long start = System.currentTimeMillis();
		generate(ySize, xSize, zSize);
		long end = System.currentTimeMillis();
		return String.valueOf(end - start);
	}

}

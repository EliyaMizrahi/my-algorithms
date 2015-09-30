package algorithms.mazeGenerators;

/**
 * The Maze3dGenerator is an Interface class that has following methods.
 * 
 * @author Eliya Mizrahi
 * @version 1.0
 * @since 09-09-2015
 */

public interface Maze3dGenerator {
	public Maze3d generate(int ySize, int xSize, int zSize);

	public String measureAlgorithmTime(int ySize, int xSize, int zSize);
}

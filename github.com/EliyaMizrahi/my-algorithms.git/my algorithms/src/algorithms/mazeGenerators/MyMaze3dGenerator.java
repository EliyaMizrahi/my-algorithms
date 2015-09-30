package algorithms.mazeGenerators;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Random;
import java.util.Stack;

/**
 * The MyMaze3dGenerator program implements an application that create 
 * Maze3d by Recursive Backtracker algorithm.
 * The MyMaze3dGenerator consist from maze3d.
 *  
 * @author Eliya Mizrahi
 * @version 1.0
 * @since 09-09-2015
 */

public class MyMaze3dGenerator extends Maze3dGeneratorAbstract {

	private static final int VISITED = 0;
	private static final int WALL = 1;
	private static final int UNVISITED_CELLS = 2;

	private Maze3d myMaze;

	/**
	 * Constructor
	 * @param y
	 * @param x
	 * @param z
	 */
	public MyMaze3dGenerator(int y, int x, int z) {
		this.myMaze = new Maze3d(y, x, z, WALL);

	}

	/**
	 * This method is use to create maze3d by Recursive Backtracker algorithm.
	 * 
	 * @param sizeY
	 * @param sizeX
	 * @param sizeZ
	 * @return Maze3d- This return the maze
	 */
	@Override
	public Maze3d generate(int sizeY, int sizeX, int sizeZ) {
		sizeY = sizeY * 2 + 1;
		sizeX = sizeX * 2 + 1;
		sizeZ = sizeZ * 2 + 1;

		fillTheEmptyCells(sizeY, sizeX, sizeZ);
		myMaze.setStartPosition(startOrEndPosition(sizeY, sizeX, sizeZ));
		myMaze.setGoalPosition(startOrEndPosition(sizeY, sizeX, sizeZ));
		Position startPos = myMaze.getStartPosition();
		Position endPos = myMaze.getGoalPosition();
		
		while (startPos.equals(endPos)) {
			myMaze.setGoalPosition(startOrEndPosition(sizeY, sizeX, sizeZ));
			endPos = myMaze.getGoalPosition();
		}
		myMaze.setCell(startPos.getY(), startPos.getX(), startPos.getZ(), VISITED);
		
		Hashtable<Position, LinkedList<Position>> hash = new Hashtable<Position, LinkedList<Position>>();
		LinkedList<Position> neighboursList= new LinkedList<Position>();
		Position tempPos;
		for (int i = 1; i < myMaze.getSizeY(); i += 2) {
			for (int j = 1; j < myMaze.getSizeX(); j += 2) {
				for (int k = 1; k < myMaze.getSizeZ(); k += 2) {
					tempPos = new Position(i, j, k);
					neighboursList = getNeighbors(tempPos);
					// just if the neighbors is in the shell
					if (i == 1 || j == 1 || k == 1 || i == myMaze.getSizeY() - 2 || j == myMaze.getSizeX() - 2
							|| k == myMaze.getSizeZ() - 2)
						if (specialNeighbor(startPos, i, j, k)) {
						       neighboursList.add(startPos);
						      }
						      if (specialNeighbor(endPos, i, j, k)) {
						       neighboursList.add(endPos);
						      }
						hash.put(tempPos, neighboursList);
				}
			}
		}
		
		neighboursList = new LinkedList<Position>();
		tempPos = createNeighbor(startPos);
		neighboursList.add(tempPos);
		hash.put(startPos, neighboursList);

		neighboursList = new LinkedList<Position>();
		tempPos = createNeighbor(endPos);
		neighboursList.add(tempPos);
		hash.put(endPos, neighboursList);

		Stack<Position> cellStack = new Stack<Position>();
		cellStack.push(startPos);
		Position currPos = startPos;
		Random rand = new Random();

		while (!cellStack.isEmpty()) {
			LinkedList<Position> neighborsList = hash.get(currPos);
			int sizeOfNeighbors = neighborsList.size();
			myMaze.setCell(currPos.getY(), currPos.getX(), currPos.getZ(), VISITED);
			if (sizeOfNeighbors > 0) {
				int choose = rand.nextInt(sizeOfNeighbors);
				Position prevPos = currPos;
				currPos = neighborsList.remove(choose);
				hash.get(currPos).remove(prevPos);
				if(myMaze.getCell(currPos.getY(), currPos.getX() , currPos.getZ())== VISITED){
					currPos=prevPos;
					continue;
				}
				cellStack.push(currPos);
				breakWall(prevPos, currPos);
			} else {
				cellStack.pop();
				if (!cellStack.isEmpty()) {
					currPos = cellStack.peek();
				}
			}
		}
		return myMaze;
	}

	/**
	 * This method is use to initialize the empty cells by the value 2
	 * @param sizeY
	 * @param sizeX
	 * @param sizeZ
	 */
	private void fillTheEmptyCells(int sizeY, int sizeX, int sizeZ) {
		for (int i = 1; i < sizeY - 1; i++) {
			for (int j = 1; j < sizeX - 1; j++) {
				for (int k = 1; k < sizeZ - 1; k++) {
					if (i % 2 != 0 && j % 2 != 0 && k % 2 != 0)
						myMaze.setCell(i, j, k, UNVISITED_CELLS);
				}
			}
		}
	}

	/**
	 * This method is use to extract start position and end position randomly.
	 * @param sizeY
	 * @param sizeX
	 * @param sizeZ
	 * @return Position- This return the wanted position
	 */
	private Position startOrEndPosition(int sizeY, int sizeX, int sizeZ) {
		Position p = new Position(sizeY, sizeX, sizeZ);
		p.randPosition(sizeY, sizeX, sizeZ);
		return p;

	}

	/**
	 * This method is use to get the neighbors of some cell
	 * @param pos
	 * @return LinkedList- This return an updated list.
	 */
	private LinkedList<Position> getNeighbors(Position pos) {
		Position tempPos;
		LinkedList<Position> list = new LinkedList<Position>();
		if (pos.getX() + 2 < myMaze.getSizeX() - 1) {
			tempPos = new Position(pos.getY(), pos.getX() + 2, pos.getZ());
			list.add(tempPos);
		}
		if (pos.getX() - 2 > 0) {
			tempPos = new Position(pos.getY(), pos.getX() - 2, pos.getZ());
			list.add(tempPos);
		}
		if (pos.getY() + 2 < myMaze.getSizeY() - 1) {
			tempPos = new Position(pos.getY() + 2, pos.getX(), pos.getZ());
			list.add(tempPos);
		}
		if (pos.getY() - 2 > 0) {
			tempPos = new Position(pos.getY() - 2, pos.getX(), pos.getZ());
			list.add(tempPos);
		}
		if (pos.getZ() + 2 < myMaze.getSizeZ() - 1) {
			tempPos = new Position(pos.getY(), pos.getX(), pos.getZ() + 2);
			list.add(tempPos);
		}
		if (pos.getZ() - 2 > 0) {
			tempPos = new Position(pos.getY(), pos.getX(), pos.getZ() - 2);
			list.add(tempPos);
		}
		return list;
	}

	/**
	 * This method is use to break walls- initialize cells that their value is 1 by 0
	 * @param pos1
	 * @param pos2
	 */
	private void breakWall(Position pos1, Position pos2) {
		if (!pos1.equals(pos2)) {
			if (pos1.getX() == pos2.getX() && pos1.getY() == pos2.getY()) {
				if (pos1.getZ() > pos2.getZ()) {
					myMaze.setCell(pos1.getY(), pos1.getX(), pos1.getZ() - 1, VISITED);
				} else if (pos1.getZ() < pos2.getZ()) {
					myMaze.setCell(pos1.getY(), pos1.getX(), pos1.getZ() + 1, VISITED);
				}
			} else if (pos1.getX() == pos2.getX() && pos1.getZ() == pos2.getZ()) {
				if (pos1.getY() > pos2.getY()) {
					myMaze.setCell(pos1.getY() - 1, pos1.getX(), pos1.getZ(), VISITED);
				} else if (pos1.getY() < pos2.getY()) {
					myMaze.setCell(pos1.getY() + 1, pos1.getX(), pos1.getZ(), VISITED);
				}
			} else if (pos1.getY() == pos2.getY() && pos1.getZ() == pos2.getZ()) {
				if (pos1.getX() > pos2.getX()) {
					myMaze.setCell(pos1.getY(), pos1.getX() - 1, pos1.getZ(), VISITED);
				} else if (pos1.getX() < pos2.getX()) {
					myMaze.setCell(pos1.getY(), pos1.getX() + 1, pos1.getZ(), VISITED);
				}
			}

		}
	}

	/**
	 * This method is use to find special neighbor- start or end position.
	 * @param p
	 * @param i
	 * @param j
	 * @param k
	 * @return boolean- This return true or false if the neighbor is special
	 */
	private boolean specialNeighbor(Position p, int i, int j, int k) {
		if (j == p.getX() && k == p.getZ()) {
			if (i + 1 == p.getY() || i - 1 == p.getY()) {
				return true;
			}
		}
		if (i == p.getY() && k == p.getZ()) {
			if (j + 1 == p.getX() || j - 1 == p.getX()) {
				return true;
			}
		}
		if (i == p.getY() && j == p.getX()) {
			if (k + 1 == p.getZ() || k - 1 == p.getZ()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This method is use to create neighbors to the start or end position
	 * @param p
	 * @return Position- This return the wanted position
	 */
	private Position createNeighbor(Position p) {
		Position tempPos;
		if (p.getY() == 0) {
			tempPos = new Position(p.getY() + 1, p.getX(), p.getZ());
			return tempPos;
		}
		if (p.getY() == myMaze.getSizeY() - 1) {
			tempPos = new Position(p.getY() - 1, p.getX(), p.getZ());
			return tempPos;
		}
		if (p.getX() == 0) {
			tempPos = new Position(p.getY(), p.getX() + 1, p.getZ());
			return tempPos;
		}
		if (p.getX() == myMaze.getSizeX() - 1) {
			tempPos = new Position(p.getY(), p.getX() - 1, p.getZ());
			return tempPos;
		}
		if (p.getZ() == 0) {
			tempPos = new Position(p.getY(), p.getX(), p.getZ() + 1);
			return tempPos;
		}
		if (p.getZ() == myMaze.getSizeZ() - 1) {
			tempPos = new Position(p.getY(), p.getX(), p.getZ() - 1);
			return tempPos;
		}
		return null;
	}
}

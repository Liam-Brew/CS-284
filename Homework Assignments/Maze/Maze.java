
import java.util.ArrayList;
import java.util.Stack;

/**
 * Class that solves maze problems with backtracking.
 * 
 * @author Koffman and Wolfgang
 **/

/**
 * Class that implements recursive methods to solve a maze through backtracking.
 * @author Liam Brew
 * @section CS 284A
 * @pledge I pledge my honor that I have abided by the Stevens Honor System
 *
 */
public class Maze implements GridColors {
	/** The maze */
	private TwoDimGrid maze;

	public Maze(TwoDimGrid m) {
		maze = m;
	}

	/** Wrapper method. */
	public boolean findMazePath() {
		return findMazePath(0, 0); // (0, 0) is the start point.
	}

	/**
	 * Attempts to find a path through point (x, y).
	 * 
	 * @pre Possible path cells are in BACKGROUND color; barrier cells are in
	 *      ABNORMAL color.
	 * @post If a path is found, all cells on it are set to the PATH color; all
	 *       cells that were visited but are not on the path are in the TEMPORARY
	 *       color.
	 * @param x The x-coordinate of current point
	 * @param y The y-coordinate of current point
	 * @return If a path through (x, y) is found, true; otherwise, false
	 */
	public boolean findMazePath(int x, int y) {
		// x = number of columns, y = number of rows

		// within bounds?
		if (x > maze.getNCols() - 1 || y > maze.getNRows() - 1)
			return false;

		// valid coordinates?
		else if (x < 0 || y < 0)
			return false;

		// can we move to this cell?
		else if (maze.getColor(x, y) != GridColors.NON_BACKGROUND)
			return false;

		// exit? --> found solution
		else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
			maze.recolor(x, y, GridColors.PATH);
			return true;
		}

		// if we can move to this cell:
		maze.recolor(x, y, GridColors.TEMPORARY);
		if (findMazePath(x-1, y) || findMazePath(x+1, y) || findMazePath(x, y-1) || findMazePath(x, y+1)) {
			// maybe? investigate further...
			maze.recolor(x,  y, GridColors.PATH);
			return true;
		}
		else
			return false;
	}

	/**
	 * Returns a list of all solutions to the maze.
	 * 
	 * @param x The current x coordinate.
	 * @param y The current y coordinate.
	 * @return A list of PairInts of all solutions to the maze.
	 */
	public ArrayList<ArrayList<PairInt>> findAllMazePaths(int x, int y) {
		ArrayList<ArrayList<PairInt>> result = new ArrayList<>();
		Stack<PairInt> trace = new Stack<>();
		findMazePathStackBased(0, 0, result, trace);
		return result;
	}

	/**
	 * Uses a stack to find all solutions to the maze.
	 * 
	 * @param x      current x position
	 * @param y      current y position
	 * @param result list of successful paths recorded up to now
	 * @param trace  trace of the current path being explored
	 */
	public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace) {
		// out of bounds
		if (x > maze.getNCols() - 1 || y > maze.getNRows() - 1)
			return;

		// illegal arguments (negative coordinates)
		else if (x < 0 || y < 0)
			return;

		// good color?
		else if (maze.getColor(x, y) != GridColors.NON_BACKGROUND)
			return;

		// base case... destination reached
		else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
			// container to store list of successes
			ArrayList<PairInt> list = new ArrayList<PairInt>();

			// creates PairInt of current coordinates
			PairInt current = new PairInt(x, y);

			// adds current to trace stack
			trace.push(current);

			// appends list with trace values
			list.addAll(trace);

			// add the list to the results data
			result.add(list);

			// removes current from trace
			trace.pop();

			// recolors
			maze.recolor(x, y, GridColors.NON_BACKGROUND);
		} else {
			// creates PairInt of current coordinates
			PairInt current = new PairInt(x, y);

			// adds current to trace stack
			trace.push(current);

			// sets cell to temporary color
			maze.recolor(x, y, GridColors.TEMPORARY);

			// recursive calls: analyze the
			findMazePathStackBased(x - 1, y, result, trace);
			findMazePathStackBased(x + 1, y, result, trace);
			findMazePathStackBased(x, y - 1, result, trace);
			findMazePathStackBased(x, y + 1, result, trace);

			maze.recolor(x, y, GridColors.NON_BACKGROUND);
			trace.pop();
		}
	}

	/**
	 * Finds the shortest path in the list of paths.
	 * @param x current x position
	 * @param y current y position
	 * @return shortest path
	 */
	public ArrayList<PairInt> findMazePathMin(int x, int y){
		//creates list of all possible paths
		ArrayList<ArrayList<PairInt>> list = findAllMazePaths(x,y);
		
		//creates and populates size array 
		int[] arr = new int[list.size()];
		for(int i = 0; i < list.size() - 1; i++) {
			arr[i] = list.get(i).size();
		}
		
		//iterates through the array to find the max 
		int max = arr[0];
		int index = 0;
		for(int i = 0; i < arr.length; i++) {
			if(arr[i] > max) {
				max = arr[i];
				index = i;
			}
		}
		
		//returns the max index
		return list.get(index);
	}

	/* <exercise chapter="5" section="6" type="programming" number="2"> */
	public void resetTemp() {
		maze.recolor(TEMPORARY, BACKGROUND);
	}
	/* </exercise> */

	/* <exercise chapter="5" section="6" type="programming" number="3"> */
	public void restore() {
		resetTemp();
		maze.recolor(PATH, BACKGROUND);
		maze.recolor(NON_BACKGROUND, BACKGROUND);
	}
	/* </exercise> */
}
/* </listing> */

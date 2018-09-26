// =============================================================================
/**
 * A <code>Cell</code> keeps track of its own liveness.  It also can determine
 * its own evolution by examining its neighbors and applying its survival rules.
 **/
// =============================================================================



// =============================================================================
public class Cell {
// =============================================================================

    

    // =========================================================================
    /**
     * The specialized constructor.  Create a new, initially-dead cell.
     *
     * @param grid The <code>Grid</code> that contains this cell.
     * @param row The row coordinate of this cell within its <code>Grid</code>.
     * @param column The column coordinate of this cell within its
     *               <code>Grid</code>.
     **/
    public Cell (Grid grid, int row, int column) {

	// Set the initial state to be dead.
	_isAlive = false;

	// Store the grid and the coorindates within that grid.
	_grid = grid;
	_row = row;
	_column = column;

    } // Cell()
    // =========================================================================



    // =========================================================================
    /**
     * Indicate whether this cell is currently dead or alive.
     *
     * @return <code>true</code> if the cell is alive; <code>false</code> if it
     *         is dead.
     **/
    public boolean isAlive () {

	return _isAlive;

    } // isAlive()
    // =========================================================================



    // =========================================================================
    /**
     * Set the cell to be alive.
     **/
    public void makeAlive () {

	_isAlive = true;
	
    } // makeAlive ()
    // =========================================================================



    // =========================================================================
    /**
     * Set the cell to be dead.
     **/
    public void makeDead () {

	_isAlive = false;
	
    } // makeDead ()
    // =========================================================================



    // =========================================================================
    /**
     * Provide the row coordinate of this cell in its <code>Grid</code>.
     *
     * @return The row coordinate of this cell.
     **/
    public int getRow () {

	return _row;

    } // getRow ()
    // =========================================================================




    // =========================================================================
    /**
     * Provide the column coordinate of this cell in its <code>Grid</code>.
     *
     * @return The column coordinate of this cell.
     **/
    public int getColumn () {

	return _column;

    } // getColumn ()
    // =========================================================================



    // =========================================================================
    /**
     * Provide a textual representation of the cell's state.
     *
     * @return One particular character to represent liveness, another to
     *         represent deadness.  The characters chosen depend on the type of
     *         cell, and thus are determined by the subclasses.
     **/
    public String toString () {

	if (_isAlive) {
	    return "+";
	} else {
	    return "-";
	}
	
    }
    // =========================================================================



    // =========================================================================
    /**
     * Method header provided by Scott Kaplan, all else written by Sean Mebust
     * 
     * Traverse the standard neighborhood (the surrounding 8 <code>Cell</code>s
     * in the <code>Grid</code>) and count the number of neighbors that are
     * alive.
     *
     * @return The number of live neighbors in the standard neighborhood.
     **/
    private int countLiveNeighbors() {
        int liveNeighbors = 0; 
        for(int r = (_row-1); r<(_row+2); r++){
            for(int c = (_column-1); c<(_column+2); c++){
                Cell cell = _grid.getCell(r,c);
                if (cell==null){
                } else if (cell._isAlive){
                    if (cell != _grid.getCell(_row,_column)){
                        liveNeighbors++;
                    }
                }
            }
        }
        return liveNeighbors;
    }
    // =========================================================================



    // =========================================================================
    /**
     * Method header provided by Scott Kaplan, all else written by Sean Mebust
     * 
     * Based on its neighbors' states, calculate what this cell's state will be
     * in the <i>next</i> generation.  Here, the Conway rules are that <i>a live
     * cell with 2 or 3 live neighbors remains alive, a dead cell with 3 live
     * neighbors becomes alive, and all other cells will die</i>.
     **/
    public void evolve () {
        int n = countLiveNeighbors();
        if (n<2 || n>3){
            _willBeAlive = false;
        } else if (n==3 && (this.isAlive() != true)){
            _willBeAlive = true;
        } else if ((n==2 || n==3) && this.isAlive()){
            _willBeAlive = true;
        }
    } // evolve ()
    // =========================================================================



    // =========================================================================
    /**
     * Method header provided by Scott Kaplan, all else written by Sean Mebust
     * 
     * Advance to the next generation.  That is, adopt as the current
     * liveness whatever was calculated by <code>evolve()</code>.
     **/
    public void advance () {
        if (this._willBeAlive){
            makeAlive();
        } else {
            makeDead();
        }
    }
    // =========================================================================



    // =========================================================================
    // DATA MEMBERS

    /**
     * The current liveness.
     **/
    boolean _isAlive;

    /**
     * The liveness in the next generation.
     **/
    boolean _willBeAlive;

    /**
     * The <code>Grid</code> that contains this cell.
     **/
    Grid _grid;

    /**
     * The cell's row coordinate within its <code>Grid</code>.
     **/
    int _row;

    /**
     * The cell's column coordinate within its <code>Grid</code>.
     **/
    int _column;
    // =========================================================================



// =============================================================================
} // class Cell
// =============================================================================

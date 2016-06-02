package control;
/**
 * This is the context class of strategy pattern, 
 * its behaviour varies for each level strategy.
 * The strategy object changes the executing algorithm of the context object
 *
 */
public class MoveStrategyContext {

	private MoveStrategy strategy;
	private int position;
	/**
	 * This method access the strategy
	 * @param strategy
	 * Represents the name of move strategy
	 */
	public void setMoveStrategy(MoveStrategy strategy){
		this.strategy = strategy;
	}
	/**
	 * This methods gets the board value and return the cell position for best move
	 * @param btnValue
	 * Represents the board value
	 * @return
	 * returns the cell position for next best move
	 */
	public int getNextMove(String[] btnValue){
		position = strategy.selectMove(btnValue);
		return position;
	}
	
}

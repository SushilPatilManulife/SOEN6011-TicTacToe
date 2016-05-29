package control;

public class MoveStrategyContext {

	private MoveStrategy strategy;
	private int position;
	public void setMoveStrategy(MoveStrategy strategy){
		this.strategy = strategy;
	}
	public int getNextMove(String[] btnValue){
		position = strategy.selectMove(btnValue);
		return position;
	}
	
}

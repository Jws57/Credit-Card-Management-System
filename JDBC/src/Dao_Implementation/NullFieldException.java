package Dao_Implementation;

public class NullFieldException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NullFieldException() {
		super("Your query produced one or more Null fields");
		// TODO Auto-generated constructor stub
	}
}

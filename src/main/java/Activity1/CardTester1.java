package Activity1;
/**
 * This is a class that tests the Card class.
 */
public class CardTester1 {

	/**
	 * The main method in this class checks the Card operations for consistency.
	 *	@param args is not used.
	 */
	public static void main(String[] args) {
		/* *** TO BE IMPLEMENTED IN ACTIVITY 1 *** */
		Card1 one = new Card1("King","Clubs", 13);
		Card1 two = new Card1("Queen","Spade", 12);
		Card1 three = new Card1("Queen","Spade", 12);
		System.out.print(one.suit());
		two.rank();
		three.pointValue();
		System.out.print(two.matches(three));
		System.out.print(one.toString());
	}
}

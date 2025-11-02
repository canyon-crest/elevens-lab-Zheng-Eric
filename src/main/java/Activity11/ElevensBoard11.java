package Activity11;
import java.util.List;
import java.util.ArrayList;

/**
 * The ElevensBoard class represents the board in a game of Elevens.
 */
public class ElevensBoard11 extends Board11 {

	/**
	 * The size (number of cards) on the board.
	 */
	private static final int BOARD_SIZE = 9;

	/**
	 * The ranks of the cards for this game to be sent to the deck.
	 */
	private static final String[] RANKS =
		{"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

	/**
	 * The suits of the cards for this game to be sent to the deck.
	 */
	private static final String[] SUITS =
		{"spades", "hearts", "diamonds", "clubs"};

	/**
	 * The values of the cards for this game to be sent to the deck.
	 */
	private static final int[] POINT_VALUES =
		{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 0, 0, 0};

	/**
	 * Creates a new <code>ElevensBoard</code> instance.
	 */
	 public ElevensBoard11() {
	 	super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
	 }

	/**
	 * Determines if the selected cards form a valid group for removal.
	 * In Elevens, the legal groups are (1) a pair of non-face cards
	 * whose values add to 11, and (2) a group of three cards consisting of
	 * a jack, a queen, and a king in some order.
	 * @param selectedCards the list of the indices of the selected cards.
	 * @return true if the selected cards form a valid group for removal;
	 *         false otherwise.
	 */
	@Override
	public boolean isLegal(List<Integer> selectedCards) {
	    if (selectedCards.size() == 2) {
	        return findPairSum11(selectedCards).size() > 0;
	    } else if (selectedCards.size() == 3) {
	        return findJQK(selectedCards).size() > 0;
	    }
	    return false;
	}

	/**
	 * Determine if there are any legal plays left on the board.
	 * In Elevens, there is a legal play if the board contains
	 * (1) a pair of non-face cards whose values add to 11, or (2) a group
	 * of three cards consisting of a jack, a queen, and a king in some order.
	 * @return true if there is a legal play left on the board;
	 *         false otherwise.
	 */
	@Override
	public boolean anotherPlayIsPossible() {
	    List<Integer> cIndexes = cardIndexes();
	    return findPairSum11(cIndexes).size() > 0 || findJQK(cIndexes).size() > 0;
	}


	/**
	 * Look for an 11-pair in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find an 11-pair.
	 * @return a list of the indexes of an 11-pair, if an 11-pair was found;
	 *         an empty list, if an 11-pair was not found.
	 */
	private List<Integer> findPairSum11(List<Integer> selectedCards) {
	    List<Integer> result = new ArrayList<>();
	    for (int i = 0; i < selectedCards.size(); i++) {
	        int k1 = selectedCards.get(i);
	        for (int j = i + 1; j < selectedCards.size(); j++) {
	            int k2 = selectedCards.get(j);
	            if (cardAt(k1).pointValue() + cardAt(k2).pointValue() == 11) {
	                result.add(k1);
	                result.add(k2);
	                return result; // found a pair
	            }
	        }
	    }
	    return result; // empty list if no pair found
	}

	/**
	 * Look for a JQK in the selected cards.
	 * @param selectedCards selects a subset of this board.  It is list
	 *                      of indexes into this board that are searched
	 *                      to find a JQK group.
	 * @return a list of the indexes of a JQK, if a JQK was found;
	 *         an empty list, if a JQK was not found.
	 */
	private List<Integer> findJQK(List<Integer> selectedCards) {
	    List<Integer> jack = new ArrayList<>();
	    List<Integer> queen = new ArrayList<>();
	    List<Integer> king = new ArrayList<>();

	    for (int k : selectedCards) {
	        String rank = cardAt(k).rank();
	        if (rank.equals("jack")) jack.add(k);
	        else if (rank.equals("queen")) queen.add(k);
	        else if (rank.equals("king")) king.add(k);
	    }

	    if (!jack.isEmpty() && !queen.isEmpty() && !king.isEmpty()) {
	        List<Integer> result = new ArrayList<>();
	        result.add(jack.get(0));
	        result.add(queen.get(0));
	        result.add(king.get(0));
	        return result;
	    }
	    return new ArrayList<>(); 
	}

	/**
	 * Looks for a legal play on the board.  If one is found, it plays it.
	 * @return true if a legal play was found (and made); false othewise.
	 */
	public boolean playIfPossible() {
		/* *** TO BE IMPLEMENTED IN ACTIVITY 11 *** */
		 return playPairSum11IfPossible() || playJQKIfPossible();
	}

	/**
	 * Looks for a pair of non-face cards whose values sum to 11.
	 * If found, replace them with the next two cards in the deck.
	 * The simulation of this game uses this method.
	 * @return true if an 11-pair play was found (and made); false othewise.
	 */
	private boolean playPairSum11IfPossible() {
		  List<Integer> pair = findPairSum11(cardIndexes());
		    if (!pair.isEmpty()) {
		        replaceSelectedCards(pair);
		        return true;
		    }
		    return false;
	}

	/**
	 * Looks for a group of three face cards JQK.
	 * If found, replace them with the next three cards in the deck.
	 * The simulation of this game uses this method.
	 * @return true if a JQK play was found (and made); false othewise.
	 */
	private boolean playJQKIfPossible() {
		  List<Integer> triplet = findJQK(cardIndexes());
		    if (!triplet.isEmpty()) {
		        replaceSelectedCards(triplet);
		        return true;
		    }
		    return false;
}}
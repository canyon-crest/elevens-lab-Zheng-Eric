package Activity2;

import java.util.List;
import java.util.ArrayList;

public class Deck2 {

    private List<Card2> cards;
    private int size;

    public Deck2(String[] ranks, String[] suits, int[] values) {
        cards = new ArrayList<>();
        for (int i = 0; i < ranks.length; i++) {
            for (String suit : suits) {
                cards.add(new Card2(ranks[i], suit, values[i]));
            }
        }
        size = cards.size();
        shuffle();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void shuffle() {
     
    }

    public Card2 deal() {
        if (isEmpty()) return null;
        size--;
        return cards.get(size);
    }

	/**
	 * Generates and returns a string representation of this deck.
	 * @return a string representation of this deck.
	 */
	@Override
	public String toString() {
		String rtn = "size = " + size + "\nUndealt cards: \n";

		for (int k = size - 1; k >= 0; k--) {
			rtn = rtn + cards.get(k);
			if (k != 0) {
				rtn = rtn + ", ";
			}
			if ((size - k) % 2 == 0) {
				// Insert carriage returns so entire deck is visible on console.
				rtn = rtn + "\n";
			}
		}

		rtn = rtn + "\nDealt cards: \n";
		for (int k = cards.size() - 1; k >= size; k--) {
			rtn = rtn + cards.get(k);
			if (k != size) {
				rtn = rtn + ", ";
			}
			if ((k - cards.size()) % 2 == 0) {
				// Insert carriage returns so entire deck is visible on console.
				rtn = rtn + "\n";
			}
		}

		rtn = rtn + "\n";
		return rtn;
	}
}

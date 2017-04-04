package simulatedInput;

public class FakeCards {

	private final static int[] cardNumbers = new int[] { 0, 1234, 21312 };

	private static int idx = 0;

	public static int getNextFakeCardNumber() {
		int next = cardNumbers[idx];
		idx = (idx + 1) % cardNumbers.length;
		return next;
	}

}

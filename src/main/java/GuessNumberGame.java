import java.util.HashMap;
import java.util.Map;

public class GuessNumberGame {

  private static final int MAX_LENGTH = 4;
  private static final String UNFORTUNATELY = "Unfortunately";

  private final Map<Character, Integer> digitWithIndexMap;

  public GuessNumberGame(String answer) {
    verifyLengthOfAnswer(answer);
    verifyDigitsOfAnswer(answer);

    digitWithIndexMap = new HashMap<>();
    for (int i = 0; i < answer.length(); i++) {
      digitWithIndexMap.put(answer.charAt(i), i);
    }
  }

  private void verifyDigitsOfAnswer(String answer) {
    for (char digit : answer.toCharArray()) {
      if (isDigit(digit)) {
        throw new IllegalArgumentException("Only allow digit");
      }
    }
  }

  private boolean isDigit(char digit) {
    return digit < '0' || digit > '9';
  }

  private void verifyLengthOfAnswer(String answer) {
    if (answer.length() != MAX_LENGTH) {
      throw new IllegalArgumentException("Length should be 4");
    }
  }

  public String guess(String input) {
    int matchedCount = 0;
    int containedCount = 0;

    for (int i = 0; i < input.length(); i++) {
      if (digitWithIndexMap.containsKey(input.charAt(i))) {
        if (digitWithIndexMap.get(input.charAt(i)) == i) {
          matchedCount++;
        } else {
          containedCount++;
        }
      }
    }

    if (matchedCount == 0 && containedCount == 0) {
      return UNFORTUNATELY;
    }

    return matchedCount + "A" + containedCount + "B";
  }
}

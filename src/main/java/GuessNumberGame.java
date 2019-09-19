import static java.lang.String.format;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BiPredicate;

public class GuessNumberGame {

  private static final int ANSWER_LENGTH = 4;
  private static final String UNFORTUNATELY = "Unfortunately";
  private static final String CONGRATULATION = "Congratulation";
  private Map<Character, Integer> digitWithPositionMap;

  public GuessNumberGame(String answer) {
    verifyInputLength(answer);
    verifyInputAllDigits(answer);
    verifyInputDuplicate(answer);

    parseIntoMap(answer);
  }

  private void parseIntoMap(String input) {
    this.digitWithPositionMap = new HashMap<>(input.length());
    for (int i = 0; i < input.length(); i++) {
      digitWithPositionMap.put(input.charAt(i), i);
    }
  }

  private void verifyInputLength(String input) {
    if (input.length() != ANSWER_LENGTH) {
      throw new IllegalArgumentException();
    }
  }

  private void verifyInputAllDigits(String input) {
    for (int i = 0; i < input.length(); i++) {
      if (input.charAt(i) < '0' || input.charAt(i) > '9') {
        throw new IllegalArgumentException();
      }
    }
  }

  private void verifyInputDuplicate(String input) {
    Set<Character> existingDigit = new HashSet<>();
    for (int i = 0; i < input.length(); i++) {
      if (existingDigit.contains(input.charAt(i))) {
        throw new IllegalArgumentException();
      } else {
        existingDigit.add(input.charAt(i));
      }
    }
  }

  public String guess(String input) {
    verifyInputLength(input);
    verifyInputAllDigits(input);
    verifyInputDuplicate(input);

    return calculate(input);
  }

  private String calculate(String input) {
    int matchedCount = count(input, this::isMatched);
    if (matchedCount == ANSWER_LENGTH) {
      return CONGRATULATION;
    }

    int containedCount = count(input, this::isContained);
    if (containedCount == 0 && matchedCount == 0) {
      return UNFORTUNATELY;
    }

    return format("%dA%dB", matchedCount, containedCount);
  }

  private int count(String input, BiPredicate<Character, Integer> predicate) {
    int count = 0;
    for (int i = 0; i < input.length(); i++) {
      if (predicate.test(input.charAt(i), i)) {
        count++;
      }
    }
    return count;
  }

  private boolean isContained(Character digit, int index) {
    return digitWithPositionMap.containsKey(digit) && digitWithPositionMap.get(digit) != index;
  }

  private boolean isMatched(Character digit, int index) {
    return digitWithPositionMap.containsKey(digit) && digitWithPositionMap.get(digit) == index;
  }
}

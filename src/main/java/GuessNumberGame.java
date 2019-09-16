public class GuessNumberGame {

  private static final int MAX_LENGTH = 4;
  private static final String UNFORTUNATELY = "Unfortunately";
  private String answer;

  public GuessNumberGame(String answer) {
    this.answer = answer;
    verifyLengthOfAnswer(answer);
    verifyDigitsOfAnswer(answer);
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

    for (int i = 0; i < input.toCharArray().length; i++) {
      for (int j = 0; j < answer.toCharArray().length; j++) {
        if (input.charAt(i) == answer.charAt(j)) {
          if (i == j) {
            matchedCount++;
          } else {
            containedCount++;
          }
        }
      }
    }

    if (matchedCount == 0 && containedCount == 0) {
      return UNFORTUNATELY;
    }

    return matchedCount + "A" + containedCount + "B";
  }
}

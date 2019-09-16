public class GuessNumberGame {

  private static final int MAX_LENGTH = 4;
  private static final String UNFORTUNATELY = "Unfortunately";

  public GuessNumberGame(String answer) {
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
    return UNFORTUNATELY;
  }
}

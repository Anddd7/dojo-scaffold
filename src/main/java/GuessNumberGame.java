public class GuessNumberGame {

  public GuessNumberGame(String answer) {
    for (char digit : answer.toCharArray()) {
      if (digit < '0' || digit > '9') {
        throw new IllegalArgumentException("Only allow digit");
      }
    }
  }
}

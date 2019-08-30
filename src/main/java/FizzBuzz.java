public class FizzBuzz {

  public String answer(int input) {
    String result = "";

    if (isTimesOfOrContains(input, 3)) {
      result += "fizz";
    }
    if (isTimesOfOrContains(input, 5)) {
      result += "buzz";
    }

    return result.isEmpty() ? String.valueOf(input) : result;
  }

  private boolean isTimesOfOrContains(int input, int target) {
    return input % target == 0 || String.valueOf(input).contains(String.valueOf(target));
  }
}

public class FizzBuzz {

  public String answer(int input) {
    String result = "";

    if (input % 3 == 0 || String.valueOf(input).contains("3")) {
      result += "fizz";
    }
    if (input % 5 == 0 || String.valueOf(input).contains("5")) {
      result += "buzz";
    }

    return result.isEmpty() ? String.valueOf(input) : result;
  }
}

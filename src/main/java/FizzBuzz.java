public class FizzBuzz {

  public String answer(int input) {
    if (input % 3 == 0) {
      return "fizz";
    }
    if (input % 5 == 0) {
      return "buzz";
    }
    return String.valueOf(input);
  }
}

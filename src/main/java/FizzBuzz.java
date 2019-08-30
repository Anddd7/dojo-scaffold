public class FizzBuzz {

  public String answer(int input) {

    if (input % 3 == 0) {
      return "fizz";
    }

    return String.valueOf(input);
  }
}

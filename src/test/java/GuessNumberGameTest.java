import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class GuessNumberGameTest {


  @ParameterizedTest(name = "{index} Invalid Answer - [{0}]")
  @CsvSource({
      "a1b2",
      "12345", "123",
      "1232"
  })
  void should_throw_exception_when_init_game_given_answer_is_invalid(String answer) {
    assertThrows(IllegalArgumentException.class, () -> new GuessNumberGame(answer));
  }

  @ParameterizedTest(name = "{index} Invalid Input - [{0}]")
  @CsvSource({
      "a1b2",
      "12345", "123",
      "1232"
  })
  void should_throw_exception_when_guess_given_input_is_invalid(String input) {
    assertThrows(IllegalArgumentException.class, () -> new GuessNumberGame("1234").guess(input));
  }

  @Test
  void should_return_unfortunately_when_guess_given_input_is_not_matched_the_answer() {
    String result = new GuessNumberGame("1234").guess("5678");

    assertThat(result).isEqualTo("Unfortunately");
  }

  @Test
  void should_return_0A1B_when_guess_given_input_contains_1_digit_at_different_position_of_the_answer() {
    String result = new GuessNumberGame("1234").guess("4567");

    assertThat(result).isEqualTo("0A1B");
  }

  @Test
  void should_return_0A2B_when_guess_given_input_contains_2_digits_at_different_position_of_the_answer() {
    String result = new GuessNumberGame("1234").guess("3456");

    assertThat(result).isEqualTo("0A2B");
  }

  @Test
  void should_return_1A0B_when_guess_given_input_matches_1_digit_at_same_position_of_the_answer() {
    String result = new GuessNumberGame("1234").guess("1567");

    assertThat(result).isEqualTo("1A0B");
  }

  @Test
  void should_return_2A0B_when_guess_given_input_matches_2_digits_at_same_position_of_the_answer() {
    String result = new GuessNumberGame("1234").guess("1256");

    assertThat(result).isEqualTo("2A0B");
  }

  @Test
  void should_return_2A2B_when_guess_given_input_matches_2_digits_and_contains_2_digits_of_the_answer() {
    String result = new GuessNumberGame("1234").guess("1243");

    assertThat(result).isEqualTo("2A2B");
  }

  @Test
  void should_return_congratulation_when_guess_given_input_matches_4_digits_at_same_position_of_the_answer() {
    String result = new GuessNumberGame("1234").guess("1234");

    assertThat(result).isEqualTo("Congratulation");
  }
}

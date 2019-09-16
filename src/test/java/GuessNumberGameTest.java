import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GuessNumberGameTest {

  @Test
  void should_throw_exception_when_init_game_given_answer_is_not_numerical() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> new GuessNumberGame("a1b2"));
  }

  @Test
  void should_throw_exception_when_init_game_given_length_of_answer_is_more_than_4() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> new GuessNumberGame("12345"));
  }

  @Test
  void should_throw_exception_when_init_game_given_length_of_answer_is_less_than_4() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> new GuessNumberGame("123"));
  }

  @Test
  void should_return_unfortunately_when_guess_given_input_is_not_matched_answer() {
    String result = new GuessNumberGame("1234").guess("5678");

    assertThat(result).isEqualTo("Unfortunately");
  }

  @Test
  void should_return_1A0B_when_guess_given_input_is_only_matched_1_digit_at_same_position_of_answer() {
    String result = new GuessNumberGame("1234").guess("1567");

    assertThat(result).isEqualTo("1A0B");
  }

  @Test
  void should_return_0A1B_when_guess_given_input_is_only_contained_1_digit_at_different_position_of_answer() {
    String result = new GuessNumberGame("1234").guess("4567");

    assertThat(result).isEqualTo("0A1B");
  }

}

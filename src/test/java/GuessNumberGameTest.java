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
}

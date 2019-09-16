import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class GuessNumberGameTest {

  @Test
  void should_throw_exception_when_init_game_given_answer_is_not_numerical() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> new GuessNumberGame("a1b2"));
  }
}

import static org.assertj.core.api.Java6Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class FizzBuzzTest {

  private static Stream<Arguments> provideValues() {
    return Stream.of(
        Arguments.of(1, "1")
    );
  }

  @ParameterizedTest
  @MethodSource("provideValues")
  public void should_return_expected_value(int input, String expected) {
    FizzBuzz instance = new FizzBuzz();

    String result = instance.answer(input);

    assertThat(result).isEqualTo(expected);
  }
}

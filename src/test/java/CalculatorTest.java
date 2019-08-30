import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CalculatorTest {

  @ParameterizedTest
  @MethodSource("provideValues")
  public void should_be_able_to_run_a_trivial_test_case(Integer value1, Integer value2, Integer expected) {
    int result = Calculator.add(value1, value2);

    assertThat(result).isEqualTo(expected);
  }

  private static Stream<Arguments> provideValues() {
    return Stream.of(
        Arguments.of(1, 1, 2),
        Arguments.of(1, 2, 3),
        Arguments.of(2, 1, 3),
        Arguments.of(2, 2, 4)
    );
  }
}

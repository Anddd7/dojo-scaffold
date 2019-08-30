import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CalculatorTest {

  @Test
  public void should_be_able_to_run_a_trivial_test_case() {
    assertThat(Calculator.add(1, 1)).isEqualTo(2);
  }
}

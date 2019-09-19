
import static com.google.common.base.Charsets.UTF_8;
import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.Collectors.toList;

import com.google.common.io.Files;
import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CanaryTest {

  @ParameterizedTest
  @CsvSource({
      "1,2,3",
      "1,3,4",
      "2,3,5"
  })
  public void should_be_able_to_run_a_trivial_test_case(int first, int second, int reuslt) {
    org.assertj.core.api.Assertions.assertThat(first + second).isEqualTo(reuslt);
  }

  @Test
  public void should_be_able_to_use_guava_capabilities() throws IOException {
    List<Integer> numbers = newArrayList(1, 2, 3, 4, 5);
    List<Integer> doubled = numbers.stream().map(x -> x * 2).collect(toList());
    org.assertj.core.api.Assertions.assertThat(doubled).isEqualTo(newArrayList(2, 4, 6, 8, 10));

    String src = Files.asCharSource(new File("src/test/java/CanaryTest.java"), UTF_8).read();
    org.assertj.core.api.Assertions.assertThat(src.length()).isEqualTo(1661);
  }

  @Test
  public void should_throw_exception_with_assertj() throws IOException {
    org.assertj.core.api.Assertions.assertThatThrownBy(() -> {
      throw new RuntimeException();
    }).isInstanceOf(RuntimeException.class);
  }

  @Test
  public void should_throw_exception_with_junit() throws IOException {
    Assertions.assertThrows(RuntimeException.class, () -> {
      throw new RuntimeException();
    });
  }
}

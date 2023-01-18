package com.lei.xia.jasper;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class CompHelperTester {

  @ParameterizedTest
  @MethodSource("methodSource")
  void should_sort_search_results(String field,
                          Map<String, Comparator<MovementSearchResult>>
                              mapOfComparators,
                          List<MovementSearchResult> fixture,
                          List<MovementSearchResult> expected
                          ) {
    var cut = mapOfComparators.get(field);
    fixture.sort(cut);
    assertThat(fixture).containsSequence(expected);
  }

  static Stream<Arguments> methodSource() {
    var map = CompHelper.comparatorMap();
    return Stream.of(
        Arguments.of("name", map, fixtures(), orderedFixture()),
        Arguments.of("age", map, fixtures(), orderedFixture()),
        Arguments.of("createDate", map, fixtures(), orderedFixture()));
  }

  static List<MovementSearchResult> fixtures() {
    var list = new ArrayList<MovementSearchResult>(3);
    list.add(new MovementSearchResult("A", 1, LocalDate.now()));
    list.add(new MovementSearchResult("B", 2, LocalDate.now()));
    list.add(new MovementSearchResult("C", 3, LocalDate.now()));
    return list;
  }

  static List<MovementSearchResult> orderedFixture() {
    var list = new ArrayList<MovementSearchResult>(3);
    list.add(new MovementSearchResult("C", 3, LocalDate.now()));
    list.add(new MovementSearchResult("B", 2, LocalDate.now()));
    list.add(new MovementSearchResult("A", 1, LocalDate.now()));
    return list;
  }

}
final class CompHelper {
  static Map<String, Comparator<MovementSearchResult>> comparatorMap() {
    return new HashMap<>() {{
      put("name", Comparator.nullsLast(Comparator.comparing(it -> Objects.nonNull(it.getName()) ? it.getName() : null,
          Comparator.nullsLast(Comparator.reverseOrder()))));

      put("age", Comparator.nullsLast(Comparator.comparing(it -> Objects.nonNull(it.getAge()) ? it.getAge() : null,
          Comparator.nullsLast(Comparator.reverseOrder()))));

      put("createDate", Comparator.nullsLast(Comparator.comparing(it -> Objects.nonNull(it.getCreateDate()) ? it.getCreateDate()
              : null,
          Comparator.nullsLast(Comparator.reverseOrder()))));
    }};
  }

}

@Data
@AllArgsConstructor
class MovementSearchResult {
  private String name;
  private Integer age;
  private LocalDate createDate;
}

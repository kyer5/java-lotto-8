package study;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Set Collection에 대한 학습 테스트")
public class SetTest {

    private Set<Integer> numbers;

    @BeforeEach
    void setUp() {
        numbers = new HashSet<>();
        numbers.add(1);
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
    }

    @Nested
    @DisplayName("size()")
    class SizeTest {

        @DisplayName("Set의 size 메서드로 크기를 확인한다.")
        @Test
        void Set의_size_메서드로_크기를_확인한다() {
            // when
            int size = numbers.size();

            // then
            assertThat(size).isEqualTo(3);
        }
    }

    @Nested
    @DisplayName("contains()")
    class ContainsTest {

        @DisplayName("Set의 contains 메서드로 1,2,3의 값이 존재하는지 확인한다. (Before)")
        @Test
        void Set의_contains_메서드로_값의_존재를_확인한다_Before() {
            // when
            boolean isContain1 = numbers.contains(1);
            boolean isContain2 = numbers.contains(2);
            boolean isContain3 = numbers.contains(3);

            // then
            assertThat(isContain1).isEqualTo(true);
            assertThat(isContain2).isEqualTo(true);
            assertThat(isContain3).isEqualTo(true);
        }

        @DisplayName("Set의 contains 메서드로 1,2,3의 값이 존재하는지 확인한다. (After)")
        @ParameterizedTest
        @ValueSource(ints = {1, 2, 3})
        void Set의_contains_메서드로_값의_존재를_확인한다_After(int input) {
            // when
            boolean result = numbers.contains(input);

            // then
            assertTrue(result);
        }

        @DisplayName("1, 2, 3 값은 true / 4, 5 값은 false가 반환된다.")
        @ParameterizedTest
        @CsvSource(value = {"1:true", "2:true", "3:true", "4:false", "5:false"}, delimiter = ':')
        void contains_메서드_결과가_입력값에_따라_다르게_반환된다(int input, boolean expected) {
            // when
            boolean result = numbers.contains(input);

            // then
            assertEquals(expected, result);
        }
    }
}

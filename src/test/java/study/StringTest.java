package study;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("String 클래스에 대한 학습 테스트")
public class StringTest {

    @Nested
    @DisplayName("split()")
    class SplitTest {

        @DisplayName("1,2를 콤마로 split하면 1과 2로 분리된다.")
        @Test
        void 문자열을_콤마로_split하면_각_요소로_분리된다() {
            // given
            String input = "1,2";

            // when
            String[] result = input.split(",");

            // then
            assertThat(result).contains("1", "2");
            assertThat(result).contains("2", "1");
        }

        @DisplayName("1,2를 콤마로 split하면 1과 2가 순서대로 분리된다.")
        @Test
        void 문자열을_콤마로_split하면_각_요소가_순서대로_분리된다() {
            // given
            String input = "1,2";

            // when
            String[] result = input.split(",");

            // then
            assertThat(result).containsExactly("1", "2");
        }

        @DisplayName("1을 콤마로 split하면 [1] 그대로 반환한다.")
        @Test
        void 단일_문자를_split해도_그대로_반환된다() {
            // given
            String input = "1";

            // when
            String[] result = input.split(",");

            // then
            assertThat(result).containsExactly("1");
        }
    }

    @Nested
    @DisplayName("substring()")
    class SubstringTest {

        @DisplayName("(1,2)에서 처음과 마지막 인덱스를 substring 했을 때 1,2를 반환한다.")
        @Test
        void 양쪽_괄호를_substring으로_제거하면_내용만_반환된다() {
            // given
            String input = "(1,2)";

            // when
            String result = input.substring(1, input.length() - 1);

            // then
            assertThat(result).isEqualTo("1,2");
        }
    }

    @Nested
    @DisplayName("charAt()")
    class CharAtTest {

        @DisplayName("abc에서 0~2번째 위치의 문자를 각각 가져온다.")
        @Test
        void 문자열에서_특정_위치의_문자를_charAt으로_가져온다() {
            // given
            String input = "abc";
            
            // when
            char result1 = input.charAt(0);
            char result2 = input.charAt(1);
            char result3 = input.charAt(2);

            // then
            assertThat(result1).isEqualTo('a');
            assertThat(result2).isEqualTo('b');
            assertThat(result3).isEqualTo('c');
        }

        @DisplayName("abc에서 3번째 위치의 문자를 가져오면 예외가 발생한다.")
        @Test
        void 인덱스_범위를_벗어난_위치를_조회하면_예외가_발생한다() {
            // given
            String input = "abc";

            // when & then
            assertThatThrownBy(() -> input.charAt(3))
                    .isInstanceOf(StringIndexOutOfBoundsException.class);
        }
    }
}

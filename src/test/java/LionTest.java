import com.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class LionTest {

    @Mock
    Feline mockFeline;

    private static Stream<Arguments> lionSexProvider() {
        return Stream.of(
                Arguments.of("Самец", true),
                Arguments.of("Самка", false)
        );
    }

    @ParameterizedTest
    @MethodSource("lionSexProvider")
    void lionConstructor_shouldSetManeCorrectly(String sex, boolean expectedHasMane) throws Exception {
        Lion lion = new Lion(sex, mockFeline);
        assertEquals(expectedHasMane, lion.hasMane());
    }


    @ParameterizedTest
    @ValueSource(strings = {"", "Кот", "123", "Male"})
    void lionConstructor_shouldThrowExceptionForInvalidSex(String invalidSex) throws Exception {
        Exception exception = assertThrows(Exception.class, () -> {
            new Lion(invalidSex, mockFeline);
        });

        Assertions.assertEquals("Используйте допустимые значения пола животного - самец или самка",
                exception.getMessage());
    }

    @Test
    void getKittens_shouldReturn1KittenWhenNoArgument() throws Exception {
        Lion lion = new Lion("Самец", mockFeline);
        Mockito.when(mockFeline.getKittens(1)).thenReturn(1);
        Assertions.assertEquals(1, lion.getKittens());

    }
    @Test
    void getKittens_shouldReturnKittensWhenArgument() throws Exception {
        Lion lion = new Lion("Самец", mockFeline);
        Mockito.when(mockFeline.getKittens(5)).thenReturn(5);
        Assertions.assertEquals(5, lion.getKittens(5));

    }

    @Test
    void getFood_shouldReturnMeat() throws Exception {
        Lion lion = new Lion("Самка", mockFeline);
        Mockito.when(mockFeline.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        Assertions.assertEquals(List.of("Животные", "Птицы", "Рыба"), lion.getFood());

    }


    @Test
    void getFood_shouldReturnExceptionWhenIncorrectWorkOfInvokedMethod() throws Exception {
        Lion lion = new Lion("Самец", mockFeline);
        Exception exception = assertThrows(Exception.class, () -> {
            lion.getFood("Динозавр");
        });
        Assertions.assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник",
                exception.getMessage());
    }

}
import com.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CatTest {
    private Cat cat;
    @Mock
    Feline mockFeline;


    @BeforeEach
    void init() {
        cat = new Cat(mockFeline);
    }

    @Test
    void getSound_ShouldReturnMeow() {
        assertEquals("Мяу", cat.getSound());
    }


    @Test
    void getFood_shouldReturnMeat() throws Exception {

        Mockito.when(mockFeline.eatMeat()).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        Assertions.assertEquals(List.of("Животные", "Птицы", "Рыба"), cat.getFood());
        Mockito.verify(mockFeline, Mockito.times(1)).eatMeat();
    }


}


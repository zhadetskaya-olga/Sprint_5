import com.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class FelineTest {

    private Feline feline;

    @BeforeEach
    void init(){
        feline = new Feline();
    }

    @Mock
    Animal animal = new Animal();
    @Spy
    Feline feline_spy = new Feline();

    @Test
    void eatMeat_shouldReturnMeatWhenFeline() throws Exception{
        animal.getFood("Хищник");
        Assertions.assertEquals(List.of("Животные", "Птицы", "Рыба"), feline.eatMeat());
        Mockito.verify(animal, Mockito.times(1)).getFood("Хищник");
    }

    @Test
    void getFamily_shouldReturnFelineFamily(){
        Assertions.assertEquals("Кошачьи", feline.getFamily());
    }

    @ParameterizedTest
    @CsvSource({
            "5",
            "2",
            "1"
    })
    void getKittens_ShouldReturnKittenCountWhenArguments(int args){
        Assertions.assertEquals(feline.getKittens(args), args );
    }

    @Test
    void getKittens_ShouldReturnKittenCount1WhenNoArguments(){
        feline_spy.getKittens(1);
        Assertions.assertEquals(feline.getKittens(), 1 );
    }


}
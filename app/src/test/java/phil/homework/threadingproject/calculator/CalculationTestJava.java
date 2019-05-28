package phil.homework.threadingproject.calculator;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

public class CalculationTestJava {

    @Mock
    Addition addition;

    Calculation calculation;

    public CalculationTestJava(){
        MockitoAnnotations.initMocks(this);
        calculation = new Calculation(addition);
    }

    @BeforeClass
    public static void setupClass(){

    }

    @Before
    public void setup(){

    }

    @Test
    public void add(){
        when(addition.add(1,2)).thenReturn(3);
        assertEquals(15, calculation.addTen(1,2));
    }

}

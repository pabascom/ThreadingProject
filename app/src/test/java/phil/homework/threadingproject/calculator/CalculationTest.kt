package phil.homework.threadingproject.calculator

import org.junit.*

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class CalculationTest {

    var calculation: Calculation? = null

    @Mock
    lateinit var addition: Addition

    companion object{

        @BeforeClass @JvmStatic
        fun setUpClass(){
            println("CalculationTest.setUpClass")
            MockitoAnnotations.initMocks(this)
        }

        @AfterClass @JvmStatic
        fun tearDownClass(){
            println("CalculationTest.tearDownClass")
        }

    }

    @Before
    fun setUp() {
        println("CalculationTest.setUp")
        calculation = Calculation(addition)
    }

    @After
    fun tearDown() {
        println("CalculationTest.tearDown")
        calculation = null

    }

    @Test
    fun add() {
        println("CalculationTest.add")
        //Mock.when(addition.add(1,2))
        assert(calculation?.addTen(1,2) == 13 )
    }

    @Test
    fun subtract() {
        println("CalculationTest.subtract")
        assertEquals(1, calculation?.subtract(2,1))
        assertEquals(2, calculation?.subtract(5,2))
    }
}
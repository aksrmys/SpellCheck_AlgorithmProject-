import static org.junit.Assert.*;
import org.junit.Test;

public class TestCase {

    @Test
    public void testSpellCheck() {
        AlgorithmProjectCheck testcase= new AlgorithmProjectCheck();
        String expected = "aplpe is not valid!!!\nDo you mean(SWAP): apple\nThe minimum number of operations required to convert the entered wrong word to the correct string(SWAP):2\nEnter the word you want from the suggested words: apple\n\n"
                + "thew is not valid!!!\nDo you mean: they--->Penalty point:4\nThe minimum number of operations required to convert the entered wrong word to the correct string:1\nDo you mean: then--->Penalty point:4\nThe minimum number of operations required to convert the entered wrong word to the correct string:1\nDo you mean: them--->Penalty point:5\nThe minimum number of operations required to convert the entered wrong word to the correct string:1\nEnter the word you want from the suggested words: then\n\n"
                + "qell is not valid!!!\nDo you mean: well--->Penalty point:1\nThe minimum number of operations required to convert the entered wrong word to the correct string:1\nDo you mean: sell--->Penalty point:1\nThe minimum number of operations required to convert the entered wrong word to the correct string:1\nDo you mean: cell--->Penalty point:2\nThe minimum number of operations required to convert the entered wrong word to the correct string:1\nDo you mean: tell--->Penalty point:4\nThe minimum number of operations required to convert the entered wrong word to the correct string:1\nDo you mean: bell--->Penalty point:4\nThe minimum number of operations required to convert the entered wrong word to the correct string:1\nDo you mean: yell--->Penalty point:5\nThe minimum number of operations required to convert the entered wrong word to the correct string:1\nDo you mean: hell--->Penalty point:5\nThe minimum number of operations required to convert the entered wrong word to the correct string:1\nEnter the word you want from the suggested words: tell\n\n\n"
                + "Sum of penalty points in the text entered: 8.75";
        assertEquals(expected,testcase);
    }
}

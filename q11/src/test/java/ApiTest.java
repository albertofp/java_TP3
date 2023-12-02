import br.edu.infnet.Main;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ApiTest {

    @Test
    public void testPostRequest() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        Main.main(new String[]{});
        System.setOut(System.out);

        String expected = "{  \"{\\\"userId\\\":1,\\\"id\\\":1,\\\"title\\\":\\\"delectus aut autem\\\",\\\"completed\\\":false}\": \"\",  \"id\": 101}";

        String got = outputStream.toString().trim();
        assertEquals(expected, got);
    }
}

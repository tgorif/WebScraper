import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WriterTest {

    @BeforeEach
    void setUp(

    ) {
    }

    @Test
    void testFfile() {
        Writer writer = new Writer(new ArrayList<>(),"","","");
        assertNotNull(writer.f);
    }
}
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.assertTrue;

public class SerumClientTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SerumClientTest.class);

    @Test
    public void testHelloWorld() {
        LOGGER.info("Hello Serum.");
        assertTrue(true);
    }
}

import com.example.springbootservices.service.ServiceClass;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@Execution(ExecutionMode.CONCURRENT)
public class NestedTest {
    @Autowired
    private ServiceClass serviceClass;
    @Nested
    class InnerClass {
        @Test
        void test1() {
            assertEquals(2 + 2, 4);
        }

        @Test
        void test2() {
            assertEquals(5 - 3, 2);
        }
    }

    @Test
    void outerTest() {
        assertThrows(RuntimeException.class, () -> {
            serviceClass.processMessage("Hi");
    });
    }
}
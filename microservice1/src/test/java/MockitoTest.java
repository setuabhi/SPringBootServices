import com.example.springbootservices.service.ServiceClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MockitoTest {
    @Mock
    private ServiceClass serviceClass;
    @Test
    void testGetEmployeeName() {
        // Define behavior for the mocked method
        when(serviceClass.processMessage("Test")).thenReturn("Mocked Output");

        // Assert the mocked behavior
        assertEquals("Mocked Output", serviceClass.processMessage("Test"));

        // Verify that the method was called
        verify(serviceClass).processMessage("Test");
    }
}

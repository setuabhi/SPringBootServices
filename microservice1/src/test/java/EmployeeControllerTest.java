import com.example.springbootservices.Main;
import com.example.springbootservices.repository.Employee;
import com.example.springbootservices.service.ServiceClass;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ServiceClass serviceClass;



    @Test
    public void testCreateEmployeeWithValidData() throws Exception {
        // Create a valid employee JS  hhON object
        Employee employee = new Employee();
        employee.setName("John Doe");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/saveEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isOk())  // Expecting HTTP 200 OK
                .andExpect(jsonPath("$.name").value("John Doe"));  // Verify the returned employee name
    }

    @Test
    public void exception()
    {
        Exception exception = assertThrows(RuntimeException.class, () -> {
            serviceClass.processMessage("Hi");
        });
        assertEquals("Test", exception.getMessage());
    }

    @Test
    public void testCreateEmployeeWithEmptyName() throws Exception {
        // Create an employee JSON object with an empty name
        Employee employee = new Employee();
        employee.setName("");

        mockMvc.perform(MockMvcRequestBuilders.post("/api/saveEmployee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employee)))
                .andExpect(status().isBadRequest())  // Expecting HTTP 400 Bad Request
                .andExpect(jsonPath("$").value("Employee name cannot be empty"));  // Verify error message
    }
}

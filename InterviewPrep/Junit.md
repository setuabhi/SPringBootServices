**Junit tutorial**
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <version>2.7.18</version>
</dependency>

Life Cycle Annotations:

    @BeforeAll: Executes once before all tests in a class.
    @BeforeEach: Runs before each test case.
    @Test: Marks a method as a test case.
    @Disabled: Skips the test
    @AfterEach: Runs after each test case.
    @AfterAll: Executes once after all tests in a class.

Assert Statements:

    assertEquals(expected, actual)
    assertTrue(condition)
    assertFalse(condition)
    assertNull(object)
    Exception exception = assertThrows(ArithmeticException.class, () -> calculator.divide(6, 0));
                          assertEquals("Cannot divide by zero", exception.getMessage());
    assertAll("Grouped Assertions",
                () -> assertEquals(2 + 2, 4, "2 + 2 should equal 4"),
                () -> assertTrue("JUnit".startsWith("J"), "JUnit should start with 'J'")
                );

Benefits of Junit5 over 4:

    1. Nested (For better grouping)
    2. assertAll and assertThrows
    3. lambda: assertEquals(4, () -> 2 + 2);
    4. Parallel test execution: @Execution(ExecutionMode.CONCURRENT)
    5. Dynamic Test cases using: @TestFactory

Test Post mapping:

    1. Annotations on Test class: @SpringBootTest(classes = Main.class) and @AutoConfigureMockMvc
    2. @Autowired
        private MockMvc mockMvc;
    3. mockMvc.perform(MockMvcRequestBuilders.post("/api/saveEmployee")
        .contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(employee)))
        .andExpect(status().isOk())  // Expecting HTTP 200 OK
        .andExpect(jsonPath("$.name").value("John Doe")); since output is json and name is John Doe

@SpringBootTest(classes = Main.class)

    It ensures that the entire Spring application context is started during the test, providing a real-world environment to test your application.
    We can't test MockMvc without it

Mockito:

    1.  <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId> //It has mockito  dependency
            <scope>test</scope>
        </dependency>
    2.  @ExtendWith(MockitoExtension.class) // Enable Mockito with JUnit 5
        class EmployeeTest {
            @Mock
            private Employee employee; // Mock the Employee class
            @Test
            void testGetEmployeeName() {
                when(employee.getEmployeeName()).thenReturn("Mocked Name"); //Mocked getEmployeeName method
                assertEquals("Mocked Name", employee.getEmployeeName());
                verify(employee).getEmployeeName(); // Verify that the method was called
            }
        }
    3. Mock static method: <artifactId>mockito-inline</artifactId> needed
            MockedStatic<Employee> mockedStatic = mockStatic(Employee.class)) {
            // Define behavior of the static method
            mockedStatic.when(Employee::getEmployeeName()).thenReturn("Mocked Name");
            assertEquals("Mocked Name", employee.getEmployeeName());

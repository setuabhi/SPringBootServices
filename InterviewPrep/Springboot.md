Questions:

      1. Explain dispatcher servlet flow and MVC?
      2. Explain DI with constructor and field injection?
      3. @Configuration (bean), @EnableAutoConfiguration (jackson mapper), and @ComponentScan
      4. What's AOP? and which annotation used on class and method level
      5. Spring boot run method?




Answers:

1. Dispatcher servlet: It intercepts all HTTP requests and routes them
   to the appropriate controllers. then it manages the flow of data from the controller to the view and back as response

         @RestController
         Class Greet{
         @GetMapping("/hello")
         public String hello() {
         return "Hello Abhinav";
         } }

         Client sends HTTP request
         DispatcherServlet receives request
         DispatcherServlet looks for matching controller
         Greet controller method is executed
         Return value is converted to JSON/String using HttpMessageConverter
         Response sent back to client
   
         Here Model="Hello Abhinav", View=HTTP response body (plain text) and Controller = Your Greet class

2. Dependency Injection (DI) in Spring Boot is a design pattern that allows objects to be injected into other objects rather than being created manually.

              1️⃣ Constructor Injection (Recommended)
              Spring injects dependencies via the constructor. This is the most preferred approach as it ensures immutability and testability.
              Example:
              @Component
              public class Car {
              private final Engine engine;
                  @Autowired  // Optional in newer Spring versions
                  public Car(Engine engine) {
                      this.engine = engine;
                  }
              }


              2️⃣ Field Injection (Not Recommended)
              Spring injects dependencies directly into fields using @Autowired.
              @Component
              public class Car {
              @Autowired
              private Engine engine;
              public void start() {
                 engine.run();
                 }
              }
              🔴 Why is field injection not recommended?
              Harder to test (cannot create an instance manually for unit testing):
                  @Test
                  void testStart() {
                  Car car = new Car(); // ❌ Engine is NOT injected as it's a interface
                  car.start(); // NullPointerException (engine is null)

                  Car car = new Car(new DieselEngine();
                  car.start(); // works fine
                  }
3. @Configuration, @EnableAutoConfiguration, and @ComponentScan


     Configuration means This class creates beans (An object that is created, managed, and controlled by Spring container)

     EnableAutoConfiguration means spring boot looks for dependencies in pom, application.properties, Existing beans and configure them as per the need
     For Ex: if you add <artifactId>spring-boot-starter-web</artifactId>, spring boot configure DispatcherServlet, tomcat, MVC configuration, Jackson mapper(Json to object and Object to Json)

     Componenetsacn means Scan current package and sub-packages for:
      @Component
      @Service
      @Repository
      @Controller
      @RestController
4. AOP

   
      AOP (Aspect-Oriented Programming) is a way to separate common logic (like logging, security, transactions) from your main business logic.
      @Aspect and @Component annotation is used

       @Before("execution(* com.example.service.*.*(..))") //Before each method of com.example.service
       public void logBefore() {
           System.out.println("Method started");
       }
      @After	After method finishes
      @AfterReturning	After successful return
      @AfterThrowing	If exception occurs
      @Around	Before + After (Full control) ⭐ Most powerful
5. Run method


      SpringApplication.run(DemoApplication.class, args);
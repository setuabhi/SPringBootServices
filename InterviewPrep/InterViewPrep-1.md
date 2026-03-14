Questions:

      1. Explain dispatcher servlet flow and MVC?
      2. Explain DI with constructor and field injection, which one is recommended? In field injection how to make spring Inject it?
      3. @Configuration (what's bean), @EnableAutoConfiguration (explain spring-boot-starter-web), and @ComponentScan
      4. What's AOP? and which annotation used on class and method level
      5. Spring boot run method?
      6. Explain CommandLineRunner and ApplicationRunner, explain their run methods
      7. @ConditionalOnProperty and @Lazy 




Answers:

1.     Dispatcher servlet: It gets all HTTP requests and routes them
       to the appropriate controllers, then it manages the flow of data from the controller to the view and back as response

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

2.      Dependency Injection (DI): Injection of an object Class A in Class B without manually creating

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
              Harder to test (cannot create an instance manually for unit testing) and we can't make it Immutable:
                  @Test
                  void testStart() {
                  Car car = new Car(); // ❌ Engine is NOT injected
                  car.start(); // NullPointerException (engine is null)

                  Engine mockedEngine= new Engine();
                  Car car = new Car(mockedEngine);
                  car.start(); // works fine
                  }
       In field injection if you write @SpringBootTest, Spring will create the full application context, and the dependency will be injected.
3.     Configuration means This class creates beans (An object that is created, managed, and controlled by Spring container)

       EnableAutoConfiguration means spring boot looks for dependencies in pom, application.properties, Existing beans and configure them as per the need
       For Ex: if you add <artifactId>spring-boot-starter-web</artifactId>, spring boot configure DispatcherServlet, tomcat, Jackson mapper(Json to object and Object to Json)

       Componenetscan means Scan current package and sub-packages for:
       @Component
       @Configurations
       @Service
       @Repository
       @Controller

4.     AOP (Aspect-Oriented Programming) is a way to separate common logic (like logging, security, transactions) from your main business logic.

       @Aspect and @Component annotation is used

       @Before("execution(* com.example.service.*.*(..))") //Before each method of com.example.service
       public void logBefore() {
           System.out.println("Method started");
       }
       @After	After method finishes
       @AfterReturning	After successful return
       @AfterThrowing	If exception occurs
       @Around	Before + After (Full control) ⭐ Most powerful

5.     Run method:
         1. Creates application context to manage beans defined under Component, Configurations, Service, Repository and Controller
         2. Scans Componenet, Configurations, Service, Repository and Controller in pakcage and sub packages
         3. Starts Tomcat server, DispatcherServlet, Jackson JSON converter
         4. Runs CommandLineRunner and ApplicationRunner
6.     Both CommandLineRunner and ApplicationRunner are used to run some code immediately after the Spring Boot application starts, example warm up cache

       @Component is used and class will implement CommandLineRunner and ApplicationRunner

       CommandLineRunner: java -jar app.jar Abhinav 30
          @Override
          public void run(String... args) throws Exception { // args[0] will have Abhinav and args[1] will have 30
          System.out.println("Application started!");
          }

       ApplicationRunner: java -jar app.jar --name=Abhinav --age=30
           @Override
           public void run(ApplicationArguments args) throws Exception { // args.getOptionValues("name") will have abhinav and args.getOptionValues("age") will have 30
           System.out.println("Application started!");
           }
7.     @ConditionalOnProperty(name="abc.def" havingValue="true") this means myFeatureService bean will be create only if abc.def=true present in application.properties or we pass --abc.def=true as prgram argument
       @Bean
       public MyFeatureService myFeatureService() {
        return new MyFeatureService();
       }

       While @Lazy means  myFeatureService bean will be created only if it is first used, not during startup
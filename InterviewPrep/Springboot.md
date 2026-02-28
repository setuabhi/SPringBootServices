Questions:

      1. Explain dispacther servlet flow?
      2. Explain DI with constructor and field injection?




Answers:

1. Dispatcher servlet: It's  the heart of the Spring MVC framework. It intercepts all HTTP requests and routes them
   to the appropriate controllers. then it manages the flow of data from the controller to the view and back as response

        1. A client sends an HTTP request.
        2. DispatcherServlet receives the request and consults HandlerMapping to find a suitable controller.
        3. The controller processes the request and returns a ModelAndView.
        4. DispatcherServlet sends the model to the ViewResolver.
        5. The view is rendered and returned as a response.
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
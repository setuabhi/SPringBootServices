1. Load balancer we can achieve via RestTemplate, Feign Client and Nimbus
2. Spring Boot (with embedded Tomcat) uses a thread pool to handle multiple HTTP requests concurrently, which made our endpoints async by default
3. By default @GetMapping("/getCurrentTime") is async using tomcat thread, if you want to make it sync use synchronized keyword
4. If want to make any method Async (means that method will be called in different thread, then use @Async annotation)
5. Design Patters:


    API Gateway Pattern – A single entry point for clients, handling authentication, routing, and aggregation.

    Service Registry and Discovery – Keeps track of service instances dynamically (e.g., Eureka, Consul).
    
    Circuit Breaker Pattern – Prevents cascading failures when a service is down (e.g., Netflix Hystrix).

    Event-Driven Architecture – Services communicate asynchronously using events (Kafka).
    
    Saga Pattern – Manages distributed transactions across microservices
     a) Since traditional database transactions (ACID) don't work across multiple microservices, the Saga Pattern ensures data consistency using a sequence of compensating transactions.
     b) Types of Saga Implementations:
            Choreography-Based Saga – Each service listens for events and triggers the next step, using kafka
            Orchestration-Based Saga – A central orchestrator manages the transaction workflow, call next microservice sequently

    CQRS (Command Query Responsibility Segregation) – Separates read and write microservices to optimize performance.
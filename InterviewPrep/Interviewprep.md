Content:

    1. WebClientExample where we call endpoints parallely
    2. Microservices Design patterns (ChoreographyBasedSaga / OrchestrationBasedSaga and CQRS)
    3. Ehcache example, manual cache handle and evict


Feature          RestTemplate + ExecutorService	        WebClient (Reactive)
Parallelism	     ✅ Yes	                                ✅ Yes
Thread-blocking	 ❌ Yes (each task blocks a thread)  	✅ No (non-blocking, event loop)
Thread usage	 High – one thread per request	        Low – shared event loop
Scalability	     Poor beyond ~100s of concurrent calls	Excellent – handles 1000s+ efficiently
Resource usage	 Heavy (CPU, memory)	                Light
Streaming	     ❌ No	                                ✅ Yes (e.g., Server-Sent Events, use (.subscribe)


Circuit Breaker Pattern – Prevents cascading failures when a service is down (e.g., resilience4j).

Event-Driven Architecture – Services communicate asynchronously using events (Kafka).

Saga Pattern – Manages distributed transactions across microservices
a) Since traditional database transactions (ACID) don't work across multiple microservices, the Saga Pattern ensures data consistency using a sequence of compensating transactions.
b) Types of Saga Implementations:
Choreography-Based Saga – Each service listens for events and triggers the next step, using kafka
Orchestration-Based Saga – A central orchestrator manages the transaction workflow, call next microservice sequently

CQRS (Command Query Responsibility Segregation) – Separates read and write microservices to optimize performance.
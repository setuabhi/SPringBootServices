Questions:

    1. Difference b/w WebClient and Executor Service
    2. Which method being used in Webclient for immediate response and streaming
    3. Return type of webClient.get()
    4. How to create Webclient and Executor service object
    5. What input invokeAll expects and what's it's return type
    6. Where to define CacheName, and tell all three Caching annotations.
    7. What we need to Autowire for manually handeling Caching, how to get and put from cache
    8. What's the key for @Cachable and @CacheEvict

Answers:

1.      Feature          RestTemplate + ExecutorService	        WebClient (Reactive)
        Parallelism	     ✅ Yes	                                ✅ Yes
        Thread-blocking	 ❌ Yes (each task blocks a thread)  	✅ No (non-blocking, event loop)
        Thread usage	 High – one thread per request	        Low – shared event loop
        Scalability	     Poor beyond ~100s of concurrent calls	Excellent – handles 1000s+ efficiently
        Resource usage	 Heavy (CPU, memory)	                Light
        Streaming	     ❌ No	                                ✅ Yes (e.g., Server-Sent Events, use (.subscribe)

2.      Block for immediate repsonse, and subcribe for stream
3.      Mono
4.      WebClient webClient = WebClient.create(); ExecutorService executor = Executors.newFixedThreadPool(3);
5.      Invoke all expects List of Callable and returns List<Future<String>>
6.      in ehCache.xml, @EnableCaching, @Cachable("CacheName") @CacheEvict("CacheName")
7.      CacheManager, cm.get(key,value), cm.put(key,value)
8.      Hashcode of parameter
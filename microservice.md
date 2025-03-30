1. Load balancer we can achieve via RestTemplate, Feign Client and Nimbus
2. Spring Boot (with embedded Tomcat) uses a thread pool to handle multiple HTTP requests concurrently, which made our endpoints async by default
3. By default @GetMapping("/getCurrentTime") is async using tomcat thread, if you want to make it sync use synchronized keyword
4. If want to make any method Async (means that method will be called in different thread, then use @Async annotation)
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Java 11 introduced HttpClient, which supports asynchronous requests using CompletableFuture, we can acheive this using mutilthreading too
 */
public class WebClientExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        WebClient webClient = WebClient.create();
        Mono<String> m1 = webClient.get().uri("https://jsonplaceholder.typicode.com/posts/1").retrieve().bodyToMono(String.class);
        Mono<String> m2 = webClient.get().uri("https://jsonplaceholder.typicode.com/posts/2").retrieve().bodyToMono(String.class);
        Mono<String> m3 = webClient.get().uri("https://jsonplaceholder.typicode.com/posts/3").retrieve().bodyToMono(String.class);

        Mono<Map<String, String>> resultMono = Mono.zip(m1, m2, m3)
                .map(tuple -> {
                    Map<String, String> map = new HashMap<>();
                    map.put("post1", tuple.getT1());
                    map.put("post2", tuple.getT2());
                    map.put("post3", tuple.getT3());
                    return map;
                });
        Map<String, String> resultMap = resultMono.block(); // means you're done with the outputs
        System.out.println("Done");

//Stream example:
        Mono<String> responseMono = webClient.get()
                .uri("https://jsonplaceholder.typicode.com/posts/1")
                .retrieve()
                .bodyToMono(String.class);

        responseMono.subscribe(
                response -> System.out.println("Received response: " + response),    // onNext
                error -> System.err.println("Error: " + error),                     // onError
                () -> System.out.println("Request complete!")                      // onComplete
        );

//We can achieve same using  executor framework with 3 threads see advantages and disadvanatages in readme
        RestTemplate rs = new RestTemplate();
        ExecutorService executor = Executors.newFixedThreadPool(3);
        Callable<String> call1 = () -> rs.getForObject("https://jsonplaceholder.typicode.com/posts/1",String.class);
        Callable<String> call2 = () -> rs.getForObject("https://jsonplaceholder.typicode.com/posts/2",String.class);
        Callable<String> call3 = () -> rs.getForObject("https://jsonplaceholder.typicode.com/posts/3",String.class);

        List<Future<String>> results = executor.invokeAll(List.of(call1, call2, call3));

        Map<String, String> result = new HashMap<>();
        result.put("service1", results.get(0).get());
        result.put("service2", results.get(1).get());
        result.put("service3", results.get(2).get());

        executor.shutdown();


    }
}

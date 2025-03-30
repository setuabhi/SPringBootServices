import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.*;

/**
 * Java 11 introduced HttpClient, which supports asynchronous requests using CompletableFuture, we can acheive this using mutilthreading too
 */
public class HttpClientExample {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        // Define the requests
        HttpRequest request1 = HttpRequest.newBuilder().uri(URI.create("https://jsonplaceholder.typicode.com/posts/1")).build();
        HttpRequest request2 = HttpRequest.newBuilder().uri(URI.create("https://jsonplaceholder.typicode.com/posts/2")).build();
        HttpRequest request3 = HttpRequest.newBuilder().uri(URI.create("https://jsonplaceholder.typicode.com/posts/3")).build();

        // Send async requests
        CompletableFuture<HttpResponse<String>> response1 = client.sendAsync(request1, HttpResponse.BodyHandlers.ofString());
        CompletableFuture<HttpResponse<String>> response2 = client.sendAsync(request2, HttpResponse.BodyHandlers.ofString());
        CompletableFuture<HttpResponse<String>> response3 = client.sendAsync(request3, HttpResponse.BodyHandlers.ofString());

        //CompletableFuture.allOf(response1, response2, response3) creates a new CompletableFuture<Void> that completes only when all the given futures (response1, response2, response3) have completed.
        //It does not return results, but just signals that all tasks are done.
        //This helps us synchronize the execution of multiple asynchronous calls without blocking the main thread.
        CompletableFuture<Void> allResponses = CompletableFuture.allOf(response1, response2, response3);

        // Process responses when all are done
        allResponses.thenRun(() -> {
            try {
                System.out.println("Response 1: " + response1.get().body());
                System.out.println("Response 2: " + response2.get().body());
                System.out.println("Response 3: " + response3.get().body());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).join(); // Wait for completion

        System.out.println("All requests completed.");

//No meed of above code, just use executer framework with 3 threads.
        RestTemplate rs = new RestTemplate();
        ExecutorService es = Executors.newFixedThreadPool(3);
        Callable<String> call1 = () -> rs.getForObject("https://jsonplaceholder.typicode.com/posts/1",String.class);
        Callable<String> call2 = () -> rs.getForObject("https://jsonplaceholder.typicode.com/posts/2",String.class);
        Callable<String> call3 = () -> rs.getForObject("https://jsonplaceholder.typicode.com/posts/3",String.class);

        Future<String> f1 = es.submit(call1);
        Future<String> f2 = es.submit(call2);
        Future<String> f3 = es.submit(call3);

        System.out.println(f1.get());
        System.out.println(f2.get());
        System.out.println(f3.get());



    }
}

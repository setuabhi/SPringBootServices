import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

/**
 * Java 11 introduced HttpClient, which supports asynchronous requests using CompletableFuture, we can acheive this using mutilthreading too
 */
@SpringBootApplication
public class HttpClientExample {
    public static void main(String[] args) {
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
    }
}

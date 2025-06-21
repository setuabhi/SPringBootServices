import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class OrchestrationBasedSaga {

    // centralOrchestrator microservice
    /*
    @PostMapping("/PlaceOrderOrchestr")
    public void processOrder(@RequestBody Order order) {
        Boolean status = callOrderProcessMicroserviceUsingFeignClient(orderId);
        if (status) {
            Boolean status2 = callPaymentProcessMicroserviceUsingFeignClient(orderId);
        } else {
            callFailedPaymentProcessMicroserviceUsingFeignClient(orderId);
            callFailedOrderProcessMicroserviceUsingFeignClient(orderId);
        }
        if (status2) {
            callUpdateInventoryMicroserviceUsingFeignClient(orderId);
        } else {
            callFailedUpdateInventoryMicroserviceUsingFeignClient(orderId);
            callFailedPaymentProcessMicroserviceUsingFeignClient(orderId);
            callFailedOrderProcessMicroserviceUsingFeignClient(orderId);
        }
    }*/
}

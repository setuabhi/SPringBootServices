import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class ChoreographyBasedSaga {
/*
    // Microservice 1
    @PostMapping("/PlaceOrder")
    public void createOrder(@RequestBody Order order) {
        Boolean status = orderRepository.save(order);
        if (status) {
            kafkaTemplate.send("order_success", order); //sends order id to order_created
        } else {
            orderRepository.cancel(order);
        }


        @KafkaListener(topics = "order_failed")
        {
            orderRepository.cancel(order);
        }
    }

    // Microservice 2
    @KafkaListener(topics = "order_success")
    public void processPayment(Order order) { //reads order id from order_created and checks it's status after payment
        boolean success = paymentGateway.charge(order);
        if (success) {
            kafkaTemplate.send("payment_success", orderId); //sends order id to payment_success
        } else {
            paymentGateway.rollback(order);
            kafkaTemplate.send("order_failed", orderId);  //sends order id to order_failed to rollback the  txn / DB updates done in createOrder to maintain Atomicity
        }
        @KafkaListener(topics = "payment_failed")
        {
            paymentGateway.rollback(order);
        }
    }

    // Microservice 3
    @KafkaListener(topics = "payment_success")
    public void updateInventory(String orderId) { //reads orderId from payment_success and update it's inventory
        boolean success = inventoryRepository.updateStock(orderId);
        if (false) {
            kafkaTemplate.send("order_failed", orderId); //sends order id to order_failed to rollback the  txn / DB updates done in createOrder to maintain Atomicity
            kafkaTemplate.send("payment_failed", orderId); //sends order id to payment_failed to rollback the  txn / DB updates done in processPayment to maintain Atomicity
        }
    }

*/
}

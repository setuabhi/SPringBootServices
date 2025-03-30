import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class ChoreographyBasedSaga {

    // Microservice 1
  /*  @PostMapping("/PlaceOrder")
    public void createOrder(@RequestBody Order order) {
        orderRepository.save(order);
        kafkaTemplate.send("order_created", order.getId()); //sends order id to order_created
    }

    // Microservice 2
    @KafkaListener(topics = "order_created")
    public void processPayment(String orderId) { //reads order id from order_created and checks it's status after payment
        boolean success = paymentGateway.charge(orderId);
        if (success) {
            kafkaTemplate.send("payment_success", orderId); //sends order id to payment_success
        } else {
            kafkaTemplate.send("payment_failed", orderId);  //sends order id to payment_failed
        }
    }

    // Microservice 3
    @KafkaListener(topics = "payment_success")
    public void updateInventory(String orderId) { //reads orderId from payment_success and update it's inventory
        inventoryRepository.updateStock(orderId);
        kafkaTemplate.send("inventory_updated", orderId); //sends order id to inventory_updated
    }

    // Microservice 4
    @KafkaListener(topics = "payment_failed")
    public void cancelOrder(String orderId) { //reads orderId from payment_failed and cancel order
        orderRepository.cancelOrder(orderId);
        kafkaTemplate.send("order_cancelled", orderId); //sends order id to order_cancelled
    }
*/

}

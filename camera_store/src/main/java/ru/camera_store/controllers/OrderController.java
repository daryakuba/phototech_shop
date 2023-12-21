package ru.camera_store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.camera_store.models.Order;
import ru.camera_store.services.OrderService;
import java.util.List;

@RestController
public class OrderController {
    @Autowired
    private OrderService service;

    @PostMapping("/orders")
    public void order(@RequestBody Order order){
        service.addOrder(order);
    }

    @GetMapping("/orders/updStatus/{order_id}/{newStatus}")
    public void updStatus(@PathVariable long order_id, @PathVariable String newStatus){
        service.updateStatus(order_id, newStatus);
    }

    @GetMapping("/orders")
    public @ResponseBody List<Order> getAll(){
        return service.getOrders();
    }

    @GetMapping("/orders/getActive/{user_id}")
    public @ResponseBody List<Order> getActive(@PathVariable long user_id){return service.getUsersActOrders(user_id);}

    @GetMapping("/orders/{id}")
    public @ResponseBody Order get(@PathVariable long id){
        return service.getOrder(id);
    }

    @DeleteMapping("/orders/{id}")
    public void delete(@PathVariable long id){
        service.deleteOrder(id);
    }

    @GetMapping("/user/order/status/{id}")
    public String getOrderStatus(@PathVariable Long id){
        return service.getOrder(id).getStatus();
    }
}
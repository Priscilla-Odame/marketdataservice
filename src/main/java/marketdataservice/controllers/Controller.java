package marketdataservice.controllers;

import marketdataservice.dto.OrderDto;
import marketdataservice.services.MarketDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {
//    private final RestTemplate restTemplate;
//
//    public Controller(RestTemplate restTemplate){
//        this.restTemplate=restTemplate;
//    }

//    @PostMapping
//    public ResponseEntity<String> createMessage(@RequestBody String message){
//        this.publisher.publish(message);
//
//        return ResponseEntity.ok(message);
//    }

//    @PostMapping(value="/subscribe")
//    public ResponseEntity<OrderDto> subscribe (@RequestBody String rbody) {
//        String EXCHANGE_URL = "https://exchange.matraining.com";
//
//
//    }

    @Autowired
    private RedisTemplate<String, OrderDto> redisTemplate;

    @Autowired
    private ChannelTopic exchange1ChannelTopic;

    @Autowired
    private ChannelTopic exchange2ChannelTopic;

    @Autowired
    private MarketDataService marketDataService;

    @GetMapping("/getExchange1MarketData")
    public ResponseEntity<List<OrderDto>> getExchange1MarketData(){
        return new ResponseEntity<>(marketDataService.getExchange1MarketData(), HttpStatus.OK);
    }

    @GetMapping("/getExchange2MarketData")
    public ResponseEntity<List<OrderDto>> getExchange2MarketData(){
        return new ResponseEntity<>(marketDataService.getExchange2MarketData(), HttpStatus.OK);
    }

    @PostMapping("/exchange1")
    public String publishExhange1(@RequestBody String message) {
        String EXCHANGE_URL = "https://exchange.matraining.com/md/subscription";
        redisTemplate.convertAndSend(exchange1ChannelTopic.getTopic(), message);
        return "Successfullly Published";
    }

    @PostMapping("/exchange2")
    public String publishExchange2(@RequestBody String message) {
        String EXCHANGE_URL = "https://exchange2.matraining.com/md/subscription";
        redisTemplate.convertAndSend(exchange2ChannelTopic.getTopic(), message);
        return "Successfullly Published";
    }
}

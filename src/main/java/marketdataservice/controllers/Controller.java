package marketdataservice.controllers;

import marketdataservice.dto.MarketDto;
import marketdataservice.dto.OrderDto;
import marketdataservice.services.MarketDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    private RedisTemplate<String, ArrayList<MarketDto>> redisTemplate;

    @Autowired
    private ChannelTopic exchange1ChannelTopic;

    @Autowired
    private ChannelTopic exchange2ChannelTopic;

    @Autowired
    private MarketDataService marketDataService;

    @GetMapping("/getExchange1MarketData")
    public ResponseEntity<?> getExchange1MarketData(){
        return new ResponseEntity<>(marketDataService.getExchange1MarketData(), HttpStatus.OK);
    }

    @GetMapping("/getExchange2MarketData")
    public ResponseEntity<?> getExchange2MarketData(){
        return new ResponseEntity<>(marketDataService.getExchange2MarketData(), HttpStatus.OK);
    }

}

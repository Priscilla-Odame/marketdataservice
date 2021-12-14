package marketdataservice.services;

import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RestController
public class Publisher {

    @Autowired
    private RedisTemplate<String, ArrayList<MarketDto>> redisTemplate;

    @Autowired
    private ChannelTopic exchange1ChannelTopic;

    @Autowired
    private ChannelTopic exchange2ChannelTopic;

    @PostMapping("/exchange1")
    public String publishExchange1(@RequestBody ArrayList<MarketDto> marketDto) {
        log.info("Message recieved: {}" ,marketDto);
        redisTemplate.convertAndSend(exchange1ChannelTopic.getTopic(), marketDto);
        return "Successfullly Published";
    }

    @PostMapping("/exchange2")
    public String publishExchange2(@RequestBody List<MarketDto> marketDto) {
        log.info("Message recieved: {}" ,marketDto);
        redisTemplate.convertAndSend(exchange2ChannelTopic.getTopic(), marketDto.toString());
        return "Successfullly Published";
    }
}

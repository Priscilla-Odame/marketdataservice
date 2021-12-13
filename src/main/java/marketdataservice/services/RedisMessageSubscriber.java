package marketdataservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import marketdataservice.dto.ExchangeData;
import marketdataservice.dto.MarketDto;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class RedisMessageSubscriber implements MessageListener {

    ObjectMapper objectMapper = new ObjectMapper();

    public static List<String> messageList = new ArrayList<String>();

    public void onMessage(Message message, byte[] pattern) {
        try {
            MarketDto[] marketDtos = objectMapper.readValue(
                    new String(message.getBody()),
                    MarketDto[].class
            );
            ExchangeData.exchangeData = new ArrayList<>(Arrays.asList(marketDtos));
            log.info("Data published from Smart Stake Market data: {}", ExchangeData.exchangeData);
        } catch (IOException e){
            log.error("Could not publish", e);
        }
//        System.out.println( );
//        messageList.add(new String(message.getBody()));
//        System.out.println("Message received: " + message.toString());
    }
}

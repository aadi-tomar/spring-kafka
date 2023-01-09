package com.aadi.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {

    private static final Logger logger = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);

    @KafkaListener(topics = "wikimedia_recentChange", groupId = "myGroup")
    public void consume(String eventMessage){
        logger.info(String.format("eventMessage received -> %s ", eventMessage));

    }
}

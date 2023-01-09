package com.aadi.springboot;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikiMediaProducer {

    private static final Logger logger = LoggerFactory.getLogger(WikiMediaProducer.class);
    private KafkaTemplate<String, String> kafkaTemplate;
    private static final String topic = "wikimedia_recentChange";
    private EventHandler eventHandler;
    private EventSource eventSource;
    private static final String url = "https://stream.wikimedia.org/v2/stream/recentchange";

    public WikiMediaProducer(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendMessage() throws InterruptedException {
        eventHandler = new WikimediaChangesHandler(kafkaTemplate, topic);
        EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
        eventSource = builder.build();
        eventSource.start();
        TimeUnit.MINUTES.sleep(10);

    }
}

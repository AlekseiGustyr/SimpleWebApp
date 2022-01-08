package com.mastery.java.task.server;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class ActiveMqServer {

    @JmsListener(destination = "my_topic")
    public void receiveTopic(String message) {
        System.out.println ("Topic ============= Topic");
        System.out.println(message);
    }

    @JmsListener(destination = "my_queue")
    public void receiveQueue(String message) {
        System.out.println ("Queue ============= Queue");
        System.out.println(message);
    }
}

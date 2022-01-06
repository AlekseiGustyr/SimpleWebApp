package com.mastery.java.task.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.*;



@RestController
@RequestMapping("/active")
public class ActiveMQRestController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @RequestMapping("/send_topic")
    public void sendTopic(){
        jmsTemplate.send("my_topic", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage();
                textMessage.setText("send topic_data");
                return textMessage;
            }
        });
    }
    @RequestMapping("/send_queue/{message}")
    public void sendQueue(@PathVariable(value = "message") String message){
        jmsTemplate.send("my_queue", new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                TextMessage textMessage = session.createTextMessage();
                textMessage.setText(message);
                return textMessage;
            }
        });
    }

    @GetMapping("/get_message_from_queue")
    @JmsListener(destination = "my_queue")
    public void getMessageFromQueue(String message){
        System.out.println ("queue ============= queue");
        System.out.println(message);
    }

}

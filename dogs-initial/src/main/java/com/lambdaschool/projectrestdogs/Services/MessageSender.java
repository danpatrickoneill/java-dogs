package com.lambdaschool.projectrestdogs.Services;

import com.lambdaschool.projectrestdogs.ProjectrestdogsApplication;
import com.lambdaschool.projectrestdogs.model.Dog;
import com.lambdaschool.projectrestdogs.model.MessageDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;


@Service
public class MessageSender {
    private RabbitTemplate rt;
    private static final Logger logger = LoggerFactory.getLogger(MessageSender.class);

    public MessageSender(RabbitTemplate rt) {
        this.rt = rt;
    }

    public void sendMessage(String queue, MessageDetail message) {
        logger.info("Sending message...");
        rt.convertAndSend(queue, message);

    }
}


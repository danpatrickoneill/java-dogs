package com.lambdaschool.projectrestdogs;

import com.lambdaschool.projectrestdogs.model.MessageDetail;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;

@EnableWebMvc
@EnableScheduling
@SpringBootApplication
public class ProjectrestdogsApplication
{

    public static final String EXCHANGE_NAME = "LambdaServer";
    public static final String QUEUE_NAME_DOGS_ACCESSED = "DogsAccessedQueue";
    public static final String QUEUE_NAME_BREEDS_ACCESSED = "BreedsAccessedQueue";
    public static final String QUEUE_NAME_DOG_CREATED = "DogCreatedQueue";

    public static DogList ourDogList;
    public static ArrayList<MessageDetail> ourMessageList = new ArrayList<MessageDetail>();

    public static void main(String[] args) {
        ourDogList = new DogList();
        ApplicationContext ctx = SpringApplication.run(ProjectrestdogsApplication.class, args);

        DispatcherServlet dispatcherServlet = (DispatcherServlet) ctx.getBean("dispatcherServlet");
        dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
    }

    @Bean
    public TopicExchange appExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue appQueueDogs() {
        return new Queue(QUEUE_NAME_DOGS_ACCESSED);
    }

    @Bean
    public Binding declareBindingDogs() {
        return BindingBuilder.bind(appQueueDogs()).to(appExchange()).with(QUEUE_NAME_DOGS_ACCESSED);
    }

    @Bean
    public Queue appQueueBreeds() {
        return new Queue(QUEUE_NAME_BREEDS_ACCESSED);
    }

    @Bean
    public Binding declareBindingBreeds() {
        return BindingBuilder.bind(appQueueBreeds()).to(appExchange()).with(QUEUE_NAME_BREEDS_ACCESSED);
    }

    @Bean
    public Queue appQueueCreateDog() {
        return new Queue(QUEUE_NAME_DOG_CREATED);
    }

    @Bean
    public Binding declareBindingCreateDog() {
        return BindingBuilder.bind(appQueueCreateDog()).to(appExchange()).with(QUEUE_NAME_DOG_CREATED);
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}


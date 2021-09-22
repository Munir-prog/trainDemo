package com.mprog;

import com.mprog.service.ConversationService;
import com.mprog.service.ConversationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@Slf4j
@ComponentScan
public class App {

    public static void main(String[] args) {
        log.info("Program started");
        var context = new AnnotationConfigApplicationContext(App.class);
        var conversationService = context.getBean(ConversationService.class);
        conversationService.doConversation();
        log.info("Program finished");
    }
}

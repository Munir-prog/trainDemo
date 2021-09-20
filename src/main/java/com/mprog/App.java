package com.mprog;

import com.mprog.config.ConnectionManager;
import com.mprog.dao.TicketDao;
import com.mprog.entity.Ticket;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ComponentScan
public class App {


    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(App.class);
        var ticketDao = context.getBean(TicketDao.class);
        ticketDao.findAll().forEach(System.out::println);
    }



}

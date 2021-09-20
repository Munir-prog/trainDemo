package com.mprog;

import com.mprog.config.ConnectionManager;
import com.mprog.entity.Ticket;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@ComponentScan
public class App {


    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(App.class);
        var connectionManager = context.getBean(ConnectionManager.class);
        log.info("Done !!!");

        try (var connection = connectionManager.get();
            var preparedStatement = connection.prepareStatement("select * from ticket")){
            var resultSet = preparedStatement.executeQuery();
            List<Ticket> tickets = new ArrayList<>();
            while (resultSet.next()){
                tickets.add(buildTicket(resultSet));
            }
            System.out.println(tickets);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @SneakyThrows
    private static Ticket buildTicket(ResultSet resultSet) {
        return Ticket.builder()
                .id(resultSet.getInt("id"))
                .passengerNo(resultSet.getString("passenger_no"))
                .passengerName(resultSet.getString("passenger_name"))
                .passengerLastName(resultSet.getString("passenger_last_name"))
                .routeId(resultSet.getInt("route_id"))
                .railwayCarNo(resultSet.getInt("railway_car_no"))
                .seatNo(resultSet.getString("seat_no"))
                .cost(resultSet.getInt("cost"))
                .build();
    }

}

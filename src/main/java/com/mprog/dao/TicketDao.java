package com.mprog.dao;

import com.mprog.App;
import com.mprog.config.ConnectionManager;
import com.mprog.entity.Ticket;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class TicketDao implements Dao<Ticket> {

    private final ConnectionManager connectionManager;

    @Override
    public List<Ticket> findAll() {

        List<Ticket> tickets = new ArrayList<>();

        try (var connection = connectionManager.get();
             var preparedStatement = connection.prepareStatement("select * from ticket")){
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                tickets.add(buildTicket(resultSet));
            }
            System.out.println(tickets);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return tickets;
    }

    @Override
    public Ticket findById() {
        return null;
    }

    @SneakyThrows
    private Ticket buildTicket(ResultSet resultSet) {
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

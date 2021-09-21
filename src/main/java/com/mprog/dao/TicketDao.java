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
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TicketDao implements Dao<Ticket> {

    private final ConnectionManager connectionManager;

    @Override
    @SneakyThrows
    public List<Ticket> findAll() {

        List<Ticket> tickets = new ArrayList<>();

        try (var connection = connectionManager.get();
             var preparedStatement = connection.prepareStatement("select * from ticket")) {
            var resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tickets.add(buildTicket(resultSet));
            }
        }

        return tickets;
    }

    @Override
    @SneakyThrows
    public Optional<Ticket> findById(int ticketId) {
        Ticket ticket = null;

        try (var connection = connectionManager.get();
             var preparedStatement = connection.prepareStatement("select * from ticket where id = ?")){
            preparedStatement.setObject(1, ticketId);
            var resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                ticket = buildTicket(resultSet);
            }
        }

        return Optional.ofNullable(ticket);
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

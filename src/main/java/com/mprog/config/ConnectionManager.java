package com.mprog.config;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;

@Component
@Getter
@Setter
//@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ConnectionManager {

//    private static final ConnectionManager INSTANCE = new ConnectionManager();

    @Value("${db.url}")
    private String dbUrl;

    @Value("${db.user}")
    private String dbUser;

    @Value("${db.password}")
    private String dbPassword;

    static {
        loadDriver();
    }

    @SneakyThrows
    private static void loadDriver() {
        Class.forName("org.postgresql.Driver");
    }

    @SneakyThrows
    public Connection get() {
        return DriverManager
                .getConnection(dbUrl, dbUser, dbPassword);
    }

//    public static ConnectionManager getINSTANCE() {
//        return INSTANCE;
//    }
}

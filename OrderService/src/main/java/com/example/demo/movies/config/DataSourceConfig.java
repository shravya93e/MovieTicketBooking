package com.example.demo.movies.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
 
import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.SQLInvalidAuthorizationSpecException;
 
@Configuration
public class DataSourceConfig {
 
    @Value("${spring.datasource.url}")
    private String url;
 
    @Value("${spring.datasource.username}")
    private String username;
 
    @Value("${spring.datasource.password}")
    private String password;
 
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        try {
            dataSource.getConnection();
        } catch (SQLSyntaxErrorException e) {
            // Handle SQL syntax errors
            System.err.println("SQL Syntax Error: " + e.getMessage());
        } catch (SQLInvalidAuthorizationSpecException e) {
            // Handle invalid authorization specification errors
            System.err.println("Invalid Authorization: " + e.getMessage());
        } catch (SQLException e) {
            // Handle all other SQL exceptions
            System.err.println("SQL Exception: " + e.getMessage());
        } catch (Exception e) {
            // Handle any other exceptions
            System.err.println("Unknown Error: " + e.getMessage());
        }
        return dataSource;
    }
}
package com.spnsolo.dao;

import com.spnsolo.Main;
import com.spnsolo.data.Location;
import com.spnsolo.data.Problem;
import com.spnsolo.data.Route;
import com.spnsolo.data.Solution;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MainDAO {
    private Connection connection;

    private static final String GET_LOCATION = "SELECT * FROM locations WHERE id LIKE ?";
    private static final String GET_UNSOLVED_PROBLEM = "SELECT * FROM problems a LEFT JOIN solutions b " +
            "ON a.id = b.problem_id WHERE b.problem_id IS NULL";
    private static final String GET_ALL_LOCATIONS = "SELECT * FROM locations";
    private static final String GET_ALL_ROUTES = "SELECT * FROM routes";

    private static final String CREATE_SOLUTION = "INSERT INTO solutions (problem_id,cost) values(?,?)";

    public MainDAO(){
        Properties properties = loadProperties();
        String url = properties.getProperty("url");
        String user = properties.getProperty("username");
        String pass = properties.getProperty("password");
        try{
            connection = DriverManager.getConnection(url,user,pass);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Problem> getUnsolvedProblems() throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(GET_UNSOLVED_PROBLEM)){
            List<Problem> problems = new ArrayList<>();
            Problem problem;
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                problem = new Problem();
                problem.setId(resultSet.getInt("id"));
                problem.setFrom_id(resultSet.getInt("from_id"));
                problem.setTo_id(resultSet.getInt("to_id"));
                problems.add(problem);
            }
            return problems;
        } catch (SQLException e) {
            throw new SQLException();
        }
    }

    public Location getLocationById(Integer id) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(GET_LOCATION)){
            Location location = new Location();
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                location.setId(resultSet.getInt("id"));
                location.setName(resultSet.getString("name"));
            }
            return location;
        }catch (SQLException e){
            throw new SQLException();
        }
    }

    public List<Location> readAllLocations() throws SQLException {
        try(Statement statement = connection.createStatement()){
            List<Location> locations = new ArrayList<>();
            Location location;
            ResultSet resultSet = statement.executeQuery(GET_ALL_LOCATIONS);
            while (resultSet.next()){
                location = new Location();
                location.setId(resultSet.getInt("id"));
                location.setName(resultSet.getString("name"));

                locations.add(location);
            }
            return locations;
        }catch (SQLException e){
            throw new SQLException();
        }
    }

    public List<Route> readAllRouts() throws SQLException {
        try(Statement statement = connection.createStatement()){
            List<Route> routes = new ArrayList<>();
            Route route;
            ResultSet resultSet = statement.executeQuery(GET_ALL_ROUTES);
            while (resultSet.next()){
                route = new Route();
                route.setId(resultSet.getInt("id"));
                route.setFrom_id(resultSet.getInt("from_id"));
                route.setTo_id(resultSet.getInt("to_id"));
                route.setCost(resultSet.getInt("cost"));

                routes.add(route);
            }
            return routes;
        }catch (SQLException e){
            throw new SQLException();
        }
    }

    public Integer createSolution(Solution solution) throws SQLException {
        try(PreparedStatement statement = connection.prepareStatement(CREATE_SOLUTION)){
            statement.setInt(1,solution.getProblem_id());
            statement.setInt(2,solution.getCost());
            return statement.executeUpdate();
        }catch (SQLException e){
            throw new SQLException();
        }
    }
    private static Properties loadProperties() {

        Properties properties = new Properties();

        try(InputStream input = Main.class.getResourceAsStream("/application.properties")) {
            properties.load(input);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return properties;
    }


}

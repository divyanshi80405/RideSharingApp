package src;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RideBookingSystem {

    Connection connection;
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    public RideBookingSystem() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/postgres",
                    "postgres",
                    "Alexps@0781"
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void register(int userId, String name, String email, String password) {
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT 1 FROM users WHERE user_id = ? OR email = ?"
            );
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, email);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("User already exists");
                return;
            }

            preparedStatement = connection.prepareStatement(
                    "INSERT INTO users VALUES (?, ?, ?, ?)"
            );
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, password);

            preparedStatement.executeUpdate();
            System.out.println("User registered");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean login(int userId, String password) {
        try {
            preparedStatement = connection.prepareStatement(
                    "SELECT * FROM users WHERE user_id = ? AND password = ?"
            );
            preparedStatement.setInt(1, userId);
            preparedStatement.setString(2, password);

            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                System.out.println("Login successful");
                return true;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void createRide(String destination,
                           String source,
                           int totalSeats,
                           int availableSeats,
                           double fare,
                           int userId) {
        try {
            preparedStatement = connection.prepareStatement(
                    "INSERT INTO rides (destination, source, total_seats, available_seats, fare, user_id) VALUES (?, ?, ?, ?, ?, ?)"
            );
            preparedStatement.setString(1, destination);
            preparedStatement.setString(2, source);
            preparedStatement.setInt(3, totalSeats);
            preparedStatement.setInt(4, availableSeats);
            preparedStatement.setDouble(5, fare);
            preparedStatement.setInt(6, userId);

            preparedStatement.executeUpdate();
            System.out.println("Ride created");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Ride> showAllRides() {
        List<Ride> rides = new ArrayList<>();

        try {
            preparedStatement = connection.prepareStatement("SELECT * FROM rides");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Ride ride = new Ride(
                        resultSet.getInt("ride_id"),
                        resultSet.getString("source"),
                        resultSet.getString("destination"),
                        resultSet.getInt("total_seats"),
                        resultSet.getInt("available_seats"),
                        resultSet.getDouble("fare"),
                        resultSet.getInt("user_id")
                );
                rides.add(ride);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return rides;
    }

    public void deleteRide(int rideId) {
        try {
            preparedStatement = connection.prepareStatement(
                    "DELETE FROM rides WHERE ride_id = ?"
            );
            preparedStatement.setInt(1, rideId);

            int row = preparedStatement.executeUpdate();
            System.out.println(row > 0 ? "Ride deleted" : "Ride not found");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
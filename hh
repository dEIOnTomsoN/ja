import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AddGameData {

    public static void main(String[] args) {
        try {
            // JDBC connection parameters
            String url = "jdbc:mysql://localhost:3306/your_database_name";
            String user = "your_username";
            String password = "your_password";

            // Establishing connection
            Connection connection = DriverManager.getConnection(url, user, password);

            // Taking input from the user
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter game name:");
            String gameName = scanner.nextLine();

            System.out.println("Enter price:");
            double price = scanner.nextDouble();

            System.out.println("Enter discount percentage:");
            double discountPercentage = scanner.nextDouble();

            // Calculating price after discount
            double priceAfterDiscount = price - (price * (discountPercentage / 100));

            // Inserting data into the database
            String insertQuery = "INSERT INTO games (game_name, price, discount_percentage, price_after_discount) VALUES (?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
                preparedStatement.setString(1, gameName);
                preparedStatement.setDouble(2, price);
                preparedStatement.setDouble(3, discountPercentage);
                preparedStatement.setDouble(4, priceAfterDiscount);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Data added successfully!");
                } else {
                    System.out.println("Failed to add data.");
                }
            }

            // Closing resources
            scanner.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

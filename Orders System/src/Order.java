import java.sql.*;

/**
 * Created by Ignas on 2016-05-17.
 */
public class Order extends BaseFunctions{

    public Order(Connection con) {
        super(con);
    }

    public void displayOrders() {
        System.out.println("\nNuomos:");
        try {
            Statement select = connection.createStatement();
            ResultSet result = select.executeQuery("SELECT * FROM igja2281.klientunuomos ORDER BY paimta");
            displayResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void returnCar(String carId) {
        try {
            String sql = "UPDATE igja2281.nuomos SET \"grazinta\" = CURRENT_DATE WHERE \"automobilis\" = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            //statement.setDate(1, Date.valueOf(LocalDate.now()));
            statement.setString(1, carId);
            statement.execute();
            System.out.println("Done");
        } catch (SQLException e) {
            System.out.println("UPDATE KLAIDA: " + e.getMessage());
        }
    }

    public void createOrder(String carId, long id) {
        //INSERT INTO Nuomos VALUES(default, '37508097779', 'HUB007', '2016-05-19');
        String sql = "INSERT INTO igja2281.nuomos VALUES (default , ?, ?, CURRENT_DATE )";
        setAutoCommit(false);
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(id));
            statement.setString(2, carId);
            //statement.setDate(3, Date.valueOf(LocalDate.now()));
            statement.execute();
            commit();
            System.out.println("Success");
        } catch (SQLException e) {
            if (connection != null) {
                rollback();
            }
            System.out.println("ĮDĖJIMO KLAIDA: " + e.getMessage());
        } finally {
            setAutoCommit(true);
        }
    }
}

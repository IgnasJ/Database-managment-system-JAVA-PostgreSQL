import java.sql.*;

/**
 * Created by Ignas on 2016-05-17.
 */
public class Car  extends BaseFunctions{

    public Car(Connection con) {
        super(con);
    }

    public void displayCars() {
        System.out.println("\nAutomobiliai:");
        try {
            Statement select = connection.createStatement();
            ResultSet result = select.executeQuery("SELECT * FROM igja2281.autoinfo ORDER  BY valstybinis_numeris");
            displayResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCar(int id, String carId, int price) {
        //INSERT INTO Automobiliai VALUES('ABC123', 1, 140);
        String sql = "INSERT INTO igja2281.automobiliai VALUES (?, ?, ?)";
        setAutoCommit(false);
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, carId);
            statement.setInt(2, id);
            statement.setInt(3, price);
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

    public void updatePrice(String carId, int price) {
        try {
            String sql = "UPDATE igja2281.automobiliai SET \"kaina\" = ? WHERE \"valstybinis_numeris\" = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(2, carId);
            statement.setInt(1, price);
            statement.execute();
            System.out.println("Done");
        } catch (SQLException e) {
            System.out.println("UPDATE KLAIDA: " + e.getMessage());
        }
    }

    public void remove(String carId) {
        try {
            String sql = "DELETE FROM igja2281.automobiliai WHERE \"valstybinis_numeris\" = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, carId);
            statement.execute();
        } catch (SQLException e) {
            System.out.println("REMOVE KLAIDA: " + e);
        }
    }

    public void showAvailableCars() {
        System.out.println("\nAutomobiliai:");
        try {
            Statement select = connection.createStatement();
            ResultSet result = select.executeQuery("SELECT * FROM igja2281.autoinfo WHERE valstybinis_numeris NOT IN ( SELECT automobilis from igja2281.nuomos where grazinta is NULL);");
            displayResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

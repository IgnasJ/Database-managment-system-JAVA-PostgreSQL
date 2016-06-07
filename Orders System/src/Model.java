import java.sql.*;

/**
 * Created by Ignas on 2016-05-17.
 */
public class Model extends BaseFunctions{

    public Model(Connection con) {
        super(con);
    }

    public void displayModels() {
        System.out.println("\nModeliai:");
        try {
            Statement select = connection.createStatement();
            ResultSet result = select.executeQuery("SELECT * FROM igja2281.modeliai");
            displayResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addModel(String brand, String model, int year){
        String sql = "INSERT INTO igja2281.Modeliai VALUES (default, ?, ?, ?)";
        setAutoCommit(false);
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, brand);
            statement.setString(2, model);
            statement.setInt(3, year);
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

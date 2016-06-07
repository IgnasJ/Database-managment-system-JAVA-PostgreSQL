import java.sql.*;

/**
 * Created by Ignas on 2016-05-17.
 */
public class Client extends BaseFunctions{

    public Client(Connection con) {
        super(con);
    }

    protected void displayClients() {
        System.out.println("\nKlientai:");
        try {
            Statement select = connection.createStatement();
            ResultSet result = select.executeQuery("SELECT * FROM igja2281.klientai");
            displayResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addClient(long id, String name, String surname, String phone, String address) {
        String sql = "INSERT INTO igja2281.klientai VALUES (?, ?, ?, ?, ?)";
        setAutoCommit(false);
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(id));
            statement.setString(2, name);
            statement.setString(3, surname);
            statement.setString(4, phone);
            statement.setString(5, address);
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

    public void updateAddress(long id, String address) {
        try {
            String sql = "UPDATE igja2281.klientai SET \"adresas\" = ? WHERE ak = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, address);
            statement.setString(2, String.valueOf(id));
            statement.execute();
            System.out.println("Done");
        } catch (SQLException e) {
            System.out.println("UPDATE KLAIDA: " + e);

        }
    }

    public void updatePhoneNumber(long id, String phone) {
        try {
            String sql = "UPDATE igja2281.klientai SET \"telefono_numeris\" = ? WHERE \"ak\" = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, phone);
            statement.setString(2, String.valueOf(id));
            statement.execute();
            System.out.println("Done");
        } catch (SQLException e) {
            System.out.println("UPDATE KLAIDA: " + e.getMessage());
        }
    }

    public void findClientById(long id) {
        try {
            String sql = "SELECT * FROM igja2281.klientai WHERE \"ak\" = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, String.valueOf(id));
            ResultSet result = statement.executeQuery();
            displayResultSet(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeClient(long id) {
        setAutoCommit(false);
        try {
            String sql = "DELETE FROM igja2281.klientai WHERE \"ak\" = ?";
            String sql2 = "DELETE FROM igja2281.nuomos WHERE \"klientas\" = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            PreparedStatement statement2 = connection.prepareStatement(sql2);
            statement.setString(1, String.valueOf(id));
            statement2.setString(1, String.valueOf(id));
            statement2.execute();
            statement.execute();
            System.out.println("Done");
        } catch (SQLException e) {
            if (connection != null) {
                rollback();
            }
            System.out.println("REMOVE KLAIDA: " + e.getMessage());
        } finally {
            setAutoCommit(true);
        }
    }
}

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

/**
 * Created by Ignas on 2016-05-19.
 */
public class BaseFunctions {
    protected Connection connection;

    public BaseFunctions(Connection con) {
        this.connection = con;
    }

    protected void displayResultSet (ResultSet rs) throws SQLException {
        if (rs != null) {
            ResultSetMetaData md = rs.getMetaData ( );
            int ncols = md.getColumnCount ( );
            int nrows = 0;
            int[ ] width = new int[ncols + 1];       // masyvas plociams stulpeliu kaupti
            StringBuilder b = new StringBuilder ( ); // bufferi'is saugantis bar line'a

            // apskaiciuojami plociai stulpeliu
            for (int i = 1; i <= ncols; i++)
            {
                width[i] = md.getColumnDisplaySize (i);
                if (width[i] < md.getColumnName (i).length ( ))
                    width[i] = md.getColumnName (i).length ( );
                // isNullable( ) grazina 1/0, o ne true/false
                if (width[i] < 4 && md.isNullable (i) != 0)
                    width[i] = 4;
            }

            // konstruojam +---+---... linija
            b.append ("+");
            for (int i = 1; i <= ncols; i++)
            {
                for (int j = 0; j < width[i]; j++)
                    b.append ("-");
                b.append ("+");
            }

            // spausdinam bar line'a, stulpelio header'i, bar line'a
            System.out.println (b.toString ( ));
            System.out.print ("|");
            for (int i = 1; i <= ncols; i++)
            {
                System.out.print (md.getColumnName (i));
                for (int j = md.getColumnName (i).length ( ); j < width[i]; j++)
                    System.out.print (" ");
                System.out.print ("|");
            }
            System.out.println ( );
            System.out.println (b.toString ( ));

            // spausdinam result set'o turini

            while (rs.next())
            {
                ++nrows;
                System.out.print ("|");
                for (int i = 1; i <= ncols; i++)
                {
                    String s = rs.getString(i);
                    if (rs.wasNull())
                        s = "Nera";
                    System.out.print (s);
                    for (int j = s.length(); j < width[i]; j++)
                        System.out.print(" ");
                    System.out.print("|");
                }
                System.out.println();
            }

            // spausdinam bar line'a, ir eiluciu skaiciu
            System.out.println (b.toString ( ));
            System.out.println (nrows + " irasai");
        } else {
            throw new SQLException("Tokiu duomenu nera!");
        }
    }


    public void setAutoCommit(boolean value)
    {
        try
        {
            connection.setAutoCommit(value);
        }
        catch (SQLException e)
        {
            System.out.println("TRANSAKCIJOS KLAIDA: " + e);
        }
    }

    public void commit()    {
        try {
            connection.commit();
        }
        catch (SQLException e)        {
            System.out.println("TRANSAKCIJOS KLAIDA: " + e);
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        }
        catch (SQLException e){
            System.out.println("TRANSAKCIJOS KLAIDA: " + e);
        }
    }
}

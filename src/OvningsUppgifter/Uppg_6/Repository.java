package OvningsUppgifter.Uppg_6;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class Repository {

    private Properties p = new Properties();

    public Repository() {
        try {
            p.load(new FileInputStream("src/OvningsUppgifter/Propertiesfil/settings.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String läggTillTomte(String tomteNamn, String chefsNamn, int roll, int säkklass) {
        boolean wasUpdated = false;
        String returnmessage ="";

        try (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             CallableStatement stmt = c.prepareCall("call läggTillTomte(?,?,?,?,?)");

        ) {
            stmt.setString(1, tomteNamn);
            stmt.setString(2, chefsNamn);
            stmt.setInt(3, roll);
            stmt.setInt(4, säkklass);
            stmt.registerOutParameter(5, Types.BOOLEAN);
            stmt.executeQuery();
            wasUpdated = stmt.getBoolean(5);

            if (wasUpdated == true) {
                returnmessage = "Du har lagt till en ny Tomte: " + tomteNamn;
            }

        } catch (SQLException e) {
            returnmessage = e.getMessage();
        }

        return returnmessage;
    }
}
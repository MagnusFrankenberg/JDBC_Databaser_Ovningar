package OvningsUppgifter.Uppg_5;

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

    public String läggInPresent(String present, int tillverkare) {
        boolean wasUpdated = false;
        String returnmessage ="";

        try (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             CallableStatement stmt = c.prepareCall("call läggTillPresent(?,?,?)");

        ) {
            stmt.setString(1, present);
            stmt.setInt(2, tillverkare);
            stmt.registerOutParameter(3, Types.BOOLEAN);
            stmt.executeQuery();
            wasUpdated = stmt.getBoolean(3);

            if (wasUpdated == true) {
                returnmessage = "Du har lagt till en ny present: " + present;
            }

        } catch (SQLException e) {
            returnmessage = e.getMessage();
        }

        return returnmessage;
    }
}
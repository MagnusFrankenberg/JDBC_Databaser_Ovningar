package OvningsUppgifter.Uppg_2b;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class Repository {

    private Connection c;
    private Properties p = new Properties();

    public Repository(){
        try{
            p.load(new FileInputStream("src/OvningsUppgifter/Propertiesfil/settings.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getBarnRapportText(String barnNamn) {

        try (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             PreparedStatement stmt = c.prepareStatement("select " +
                     "rapport.rapporttext as rapporttext from barn " +
                     "inner join rapport on rapport.barnid=barn.id " +
                     "and barn.namn = ?");
        ) {

            stmt.setString(1, barnNamn);
            ResultSet rs = stmt.executeQuery();
            String rapportText = null;
            while (rs.next()) {
                rapportText = rs.getString("rapporttext");
            }

            return rapportText;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

}

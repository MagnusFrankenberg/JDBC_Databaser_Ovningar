package OvningsUppgifter.Uppg_3c;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class Repository {

    private Properties p = new Properties();

    public Repository(){
        try{
            p.load(new FileInputStream("src/OvningsUppgifter/Propertiesfil/settings.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String läggInPresent(String present, int tillverkare){
        int rowsUpdated=0;

        try (Connection c = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             PreparedStatement stmt1 = c.prepareStatement("insert into Present(namn,tillverkningstomteID) values(?,?)");

        ) {
            stmt1.setString(1, present);
            stmt1.setInt(2, tillverkare);
            rowsUpdated = stmt1.executeUpdate();

            if(rowsUpdated==1){
                return "Du har lagt till en ny present: "+present;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        if(rowsUpdated==1){
            return "Du har lagt till en ny present: "+present;
        }else{
            return "Något gick fel, presenten har inte lagts till";
        }
    }
}

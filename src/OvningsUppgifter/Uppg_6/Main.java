package OvningsUppgifter.Uppg_6;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {

        Repository r = new Repository();
        Scanner sc = new Scanner(System.in);
        String tomteNamn;
        String chefsNamn;
        int roll;
        int säkerhetsklass;

        while(true){

            System.out.println("Ange namn på ny Tomte: ");
            tomteNamn = sc.nextLine();

            System.out.println("Ange namn på nya tomtens Chef: ");
            chefsNamn = sc.nextLine();

            System.out.println("Ange kod för roll (1=Chef, 2=Underrättelse, 3=Tillverkning): ");
            roll = sc.nextInt();
            sc.nextLine();

            System.out.println("För Underrättelsetomte, Ange kod för säkerhetsklass (1,2 el. 3): ");
            säkerhetsklass = sc.nextInt();
            sc.nextLine();

            String result = r.läggTillTomte(tomteNamn,chefsNamn,roll,säkerhetsklass);

            System.out.println(result);
        }
    }
}

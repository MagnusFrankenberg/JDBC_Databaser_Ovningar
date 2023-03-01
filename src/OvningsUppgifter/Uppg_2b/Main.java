package OvningsUppgifter.Uppg_2b;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Repository r = new Repository();
        Scanner sc = new Scanner(System.in);

        while(true){

            System.out.println("Ange namn på Barn som du vill ha rapport på: ");
            String barnnamn = sc.nextLine();
            System.out.println(r.getBarnRapportText(barnnamn)+"\n");

        }
    }
}

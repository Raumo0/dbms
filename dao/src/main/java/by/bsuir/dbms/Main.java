package by.bsuir.dbms;

import by.bsuir.dbms.tools.FileWorker;
import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static FileWorker worker;

    public static void main(String[] args) throws IOException {
        String fimename = "myfile.csv";
        worker = new FileWorker(fimename);

        worker.write("1997,Ford,E350,\"ac, abs, moon\",3000.00\n" +
                "1999,Chevy,\"Venture \"\"Extended Edition\"\"\",\"\",4900.00\n" +
                "1996,Jeep,Grand Cherokee,\"MUST SELL! air, moon roof, loaded\",4799.00");

//        System.out.println(worker.goTo(100));
//        System.out.println("*******");
//        worker.writeFrom("*******", 100);

//        String str = worker.read();
//        System.out.println(str);
//        System.out.println("*******");

//        System.out.println(worker.readFrom(105));


        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(fimename));
            String[] line;
            while ((line = reader.readNext()) != null) {
                for (String s : line){
                    System.out.print(s + "\t\t");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

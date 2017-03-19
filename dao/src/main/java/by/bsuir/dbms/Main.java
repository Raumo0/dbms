package by.bsuir.dbms;

import by.bsuir.dbms.constants.DAOConstants;
import by.bsuir.dbms.dao.Impl.TableImpl;
import by.bsuir.dbms.exceptions.DAOException;
import by.bsuir.dbms.tools.FileWorker;
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static FileWorker worker;

    public static void main(String[] args) throws IOException {
        String fimename = "yourfile.csv";
//        worker = new FileWorker(fimename);
//
//        worker.write("1997,Ford,E350,\"ac, abs, moon\",3000.00\n" +
//                "1999,Chevy,\"Venture \"\"Extended Edition\"\"\",\"\",4900.00\n" +
//                "1996,Jeep,Grand Cherokee,\"MUST SELL! air, moon roof, loaded\",4799.00");

//        System.out.println(worker.goTo(100));
//        System.out.println("*******");
//        worker.writeFrom("*******", 100);

//        String str = worker.read();
//        System.out.println(str);
//        System.out.println("*******");

//        System.out.println(worker.readFrom(105));


//        CSVReader reader = null;
//        try {
//            reader = new CSVReader(new FileReader(fimename));
//            String[] line;
//            while ((line = reader.readNext()) != null) {
//                for (String s : line){
//                    System.out.print(s + "\t\t");
//                }
//                System.out.println();
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        String[] header = "first#second#third".split("#");
        TableImpl table = TableImpl.getInstance();
        List<String[]> a = new ArrayList<String[]>();
        a.add(header);
        a.add(header);
        a.add(header);
        a.add(header);
        a.add(header);
        try {
            table.create(DAOConstants.PATH_DB_TEST + "yourfile.csv", header, a);
        } catch (DAOException e) {
            e.printStackTrace();
        }
//        table.delete(DAOConstants.PATH_DB_TEST + "yourfile.csv");
//

//        CSVParser parser = new CSVParser();
//        String[] s = parser.parseLineMulti("1997,Ford,E350,\"ac, abs, moon\",3000.00\n" +
//                "1999,Chevy,\"Venture \"\"Extended Edition\"\"\",\"\",4900.00\n" +
//                "1996,Jeep,Grand Cherokee,\"MUST SELL! air, moon roof, loaded\",4799.00");
//        for (String a : s) {
//            System.out.println(a);
//        }
    }
}

package by.bsuir.dbms.tools;

import by.bsuir.dbms.exceptions.FileException;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileWorker {
    private String path;
    private RandomAccessFile file;

    public FileWorker(String path) {
        this.path = path;
    }

    public long goTo(int num) throws IOException {
        file = new RandomAccessFile(path, "r");
        file.seek(num);
        long pointer = file.getFilePointer();
        file.close();

        return pointer;
    }

    public String read() throws IOException {
        file = new RandomAccessFile(path, "r");
        String res = "";
        int b = file.read();
        while(b != -1){
            res = res + (char)b;
            b = file.read();
        }
        file.close();

        return res;
    }

    public String readFrom(int numberSymbol) throws IOException {
        file = new RandomAccessFile(path, "r");
        String res = "";

        file.seek(numberSymbol);
        int b = file.read();

        while(b != -1){
            res = res + (char)b;

            b = file.read();
        }
        file.close();

        return res;
    }

    public List<String[]> readCSV() throws FileException {
        CSVReader reader ;
        List<String[]> result = new ArrayList<String[]>();
        try {
            reader = new CSVReader(new FileReader(path));
            String[] line;
            while ((line = reader.readNext()) != null) {
                result.add(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException(e);
        }
        return result;
    }

    public String[] readHeaderCSV() throws FileException {
        CSVReader reader ;
        String[] result;
        try {
            reader = new CSVReader(new FileReader(path));
            result = reader.readNext();
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException(e);
        }
        return result;
    }

    public void write(String st) throws IOException {
        file = new RandomAccessFile(path, "rw");
        file.write(st.getBytes());
        file.close();
    }

    public void writeCSV(List<String[]> rows) throws FileException {
        CSVWriter writer;
        try {
            writer = new CSVWriter(new FileWriter(path));
            writer.writeAll(rows);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException(e);
        }
    }


    public void writeAppendCSV(String[] rows) {
        List<String[]> lines = new ArrayList<>();
        lines.add(rows);
        this.writeAppendCSV(lines);
    }

    public void writeAppendCSV(List<String[]> rows) {
        CSVWriter writer;
        try {
            writer = new CSVWriter(new FileWriter(path, true));
            if (rows != null)
                writer.writeAll(rows);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void create(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
    }

    public void delete(String path) {
        File file = new File(path);
        file.delete();
    }

    public void makeDirectory(String path) {
        File file = new File(path);
        if (!file.isDirectory()) {
            file.mkdirs();
        }
    }

    public void removeDirectory(String path) {
        File file = new File(path);
        if (!file.isDirectory()) {
            file.delete();
        }
    }

    public boolean fileExists(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            return false;
        }
        return file.exists();
    }
}
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

    public FileWorker(String path, String mode) throws FileException {
        this.path = path;
        try {
            file = new RandomAccessFile(path, mode);//"rw"
        } catch (FileNotFoundException e) {
            throw new FileException(e);
        }
    }

    public long goTo(int num) throws FileException {
        long pointer;
        try {
            file.seek(num);
            pointer = file.getFilePointer();
        } catch (IOException e) {
            throw new FileException(e);
        }
        return pointer;
    }

    public String read() throws FileException {
        String res = "";
        int b;
        try {
            b = file.read();
            while(b != -1){
                res = res + (char)b;
                b = file.read();
            }
        } catch (IOException e) {
            throw new FileException(e);
        }
        return res;
    }

    public String readFrom(int numberSymbol) throws FileException {
        String res = "";
        try {
            file.seek(numberSymbol);
            int b = file.read();

            while (b != -1) {
                res = res + (char) b;

                b = file.read();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

    public List<String[]> readCSV() throws FileException {
        CSVReader reader = null;
        List<String[]> result = new ArrayList<>();
        try {
            reader = new CSVReader(new FileReader(path));
            String[] line;
            while ((line = reader.readNext()) != null) {
                result.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException(e);
        }
        finally {
            try {
                reader.close();
            } catch (IOException | NullPointerException e) {
                throw new FileException(e);
            }
        }
        return result;
    }

    public String[] readHeaderCSV() throws FileException {
        CSVReader reader = null;
        String[] result;
        try {
            reader = new CSVReader(new FileReader(path));
            result = reader.readNext();
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException(e);
        }
        finally {
            try {
                reader.close();
            } catch (IOException | NullPointerException e) {
                throw new FileException(e);
            }
        }
        return result;
    }

    public void write(String string) throws FileException {
        try {
            file.write(string.getBytes());
        } catch (IOException e) {
            throw new FileException(e);
        }
    }

    public void writeCSV(List<String[]> rows) throws FileException {
        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(path));
            writer.writeAll(rows);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException(e);
        }
        finally {
            try {
                writer.close();
            } catch (IOException | NullPointerException e) {
                throw new FileException(e);
            }
        }
    }


    public void writeAppendCSV(String[] rows) throws FileException {
        List<String[]> lines = new ArrayList<>();
        lines.add(rows);
        this.writeAppendCSV(lines);
    }

    public void writeAppendCSV(List<String[]> rows) throws FileException {
        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(path, true));
            if (rows != null)
                writer.writeAll(rows);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileException(e);
        }
        finally {
            try {
                writer.close();
            } catch (IOException | NullPointerException e) {
                throw new FileException(e);
            }
        }
    }

    public static boolean create(String filePath) throws FileException {
        File newFile = new File(filePath);
        if (!newFile.exists()) {
            newFile.getParentFile().mkdirs();
            try {
                return newFile.createNewFile();
            } catch (IOException e) {
                throw new FileException(e);
            }
        }
        return false;
    }

    public static boolean delete(String filePath) {
        File newFile = new File(filePath);
        return newFile.delete();
    }

    public static void makeDirectory(String filePath) {
        File newfile = new File(filePath);
        if (!newfile.isDirectory()) {
            newfile.mkdirs();
        }
    }

    public static void removeDirectory(String filePath) {
        File newFile = new File(filePath);
        if (!newFile.isDirectory()) {
            newFile.delete();
        }
    }

    public boolean exists(){
        return FileWorker.fileExists(path);
    }

    public static boolean fileExists(String path) {
        File file = new File(path);
        if (file.isDirectory()) {
            return false;
        }
        return file.exists();
    }

    public void close() throws FileException {
        try {
            file.close();
        } catch (IOException e) {
            throw new FileException(e);
        }
    }
}
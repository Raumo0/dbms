package by.bsuir.dbms.tools;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

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

    public void write(String st) throws IOException {
        file = new RandomAccessFile(path, "rw");
        file.write(st.getBytes());
        file.close();
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
}
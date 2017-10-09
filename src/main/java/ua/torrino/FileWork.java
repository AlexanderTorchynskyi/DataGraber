package ua.torrino;

import java.io.FileNotFoundException;
import java.io.IOException;

abstract class FileWork {

    abstract public boolean writeInFile() throws IOException, NoSuchFieldException, IllegalAccessException;
    abstract public boolean readFromFile() throws IOException;

}

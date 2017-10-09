package ua.torrino;

import java.io.FileNotFoundException;
import java.io.IOException;


abstract class Excel extends FileWork {
    /*
     * Basic methods to work with excel. I will spread it step by step.
     */
    public abstract boolean writeInFile() throws  IOException, NoSuchFieldException, IllegalAccessException;

    public abstract boolean readFromFile() throws IOException;

}

package it.edu.iisvoltapescara.libreria.file;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ManagedFile {
    private final File file;

    public ManagedFile(File file) {
        this.file = file;
        createFileIfNonExistent();
    }

    public void append(String text) {
        createFileIfNonExistent();

        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write(text + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void write(String text) {
        createFileIfNonExistent();

        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> readLines() {
        createFileIfNonExistent();

        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            return bufferedReader.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }

    private void createFileIfNonExistent() {
        createDirIfNotExists();
        if (file.exists()) return;

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createDirIfNotExists() {
        if (file.getParentFile() == null) return;
        if (!file.getParentFile().exists()) file.getParentFile().mkdirs();
    }

    public void removeIf(Predicate<String> predicate) {
        ArrayList<String> lines = new ArrayList<>(readLines());
        lines.removeIf(predicate);

        write(String.join("\n", lines));
    }
}
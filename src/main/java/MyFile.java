import org.apache.commons.io.FileUtils;

import java.io.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MyFile {
    private List<File> oldFiles = new ArrayList<File>();
    private File newFile;
    private int number = 1;

    public MyFile(File newFile, File directory) throws IOException {
        this.newFile = newFile;
        addFilesToList(newFile, directory);

    }

    public MyFile(File newFile) throws IOException {
        this.newFile = newFile;
        readFiles(newFile);
    }


    public void addFilesToList(File newFile, File directory) throws IOException {


        File fileForCopy;
        PrintWriter writer;


        if (oldFiles.size() == 0) {
            oldFiles.add(newFile);
            fileForCopy = new File(directory.getAbsolutePath() + "/1.0 " + newFile.getName());
            writer = new PrintWriter(fileForCopy.getAbsolutePath(), "UTF-8");
            FileUtils.copyFile(newFile, fileForCopy);
            System.out.println("You have added a file to the repository!");
            number++;

        } else if (!FileUtils.contentEquals(oldFiles.get(oldFiles.size() - 1), newFile)) {


            fileForCopy = new File(directory.getAbsolutePath() + "/" + number + ".0 " + newFile.getName());

            writer = new PrintWriter(fileForCopy.getAbsolutePath(), "UTF-8");
            FileUtils.copyFile(newFile, fileForCopy);
            oldFiles.add(newFile);
            System.out.println("You have added a file to the repository!");
            number++;

        }else
        {
            System.out.println("file with such content in the repository");
        }

    }


    public void readFiles(File newFile) throws IOException {
        boolean flag = true;
        for(File fl:oldFiles)
        {
            if(fl.getName().equals(newFile.getName()))
            {
                flag=false;
            }
        }
        if(flag) {
            oldFiles.add(newFile);
            number++;
        }

    }

    public List<File> getNewFile() {

        return oldFiles;
    }


    public void compareFiles() throws IOException {

        BufferedReader reader1 = new BufferedReader(new FileReader(oldFiles.get(oldFiles.size() - 2)));
        BufferedReader reader2 = new BufferedReader(new FileReader(oldFiles.get(oldFiles.size() - 1)));



        HashSet<String> file1 = new HashSet<>();

        String s;
        int num = 0;
        while ((s = reader1.readLine()) != null) {
            file1.add(s);

        }

        while ((s = reader2.readLine()) != null) {
            num++;
            if (!file1.contains(s)) {
                System.out.println("line " + num + " is different " + s);

            }
        }




    }

    public String getNameOfMyFile() {
        return newFile.getName();
    }


}


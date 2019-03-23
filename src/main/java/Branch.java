import java.io.File;
import java.io.IOException;;
import java.util.*;


public class Branch {

    private Set<MyFile> files = new HashSet<>();
    private HashSet<Branch> mySet = new HashSet<>();
    private File directory;


    public Branch(File directory) {
        this.directory = directory;
    }


    public void commitFile(File file1) throws IOException {
        readFile();
        System.out.println(getFiles());

        boolean flag = false;
        for (MyFile files1 : files) {
            for (File f : files1.getNewFile()) {
                if (f.getName().substring(4).equals(file1.getName())) {
                    flag = true;

                }

            }

            if (flag) {
                files1.addFilesToList(file1, directory);

                return;
            }

        }
        if (!flag) {
            files.add(new MyFile(file1, directory));

        }


    }


    public void readFile() throws IOException {
        File file = new File(directory.getAbsolutePath());
        File[] readFiles = directory.listFiles();


        Arrays.sort(readFiles, new Comparator<File>(){
            public int compare(File f1, File f2)
            {
                return Long.valueOf(f1.lastModified()).compareTo(f2.lastModified());
            } });


        for (File f : readFiles) {
            if (!f.isDirectory()) {
                addAllFiles(f);
            }
        }


    }


    private void addAllFiles(File file1) throws IOException {

        boolean flag = false;

        for (MyFile files1 : files) {

            for (File f : files1.getNewFile()) {

                if (f.getName().substring(4).equals(file1.getName().substring(4))) {
                    flag = true;

                }
            }

            if (flag) {
                files1.readFiles(file1);
                return;
            }

        }
        if (!flag) {
            files.add(new MyFile(file1));
        }

    }


    public String getNameOfBranch() {
        return directory.getName();
    }




    public Branch createDir(String nameOfBranch1) {

        File file1 = new File(directory.getAbsolutePath() + "/" + nameOfBranch1);
        file1.mkdirs();
        Branch br = new Branch(file1);
        mySet.add(br);
        return br;
    }

    public HashSet<Branch> getMySet() {
        return mySet;
    }


    public void compareFiles(String pathToFile) throws IOException {

        readFile();
        for (MyFile file : files) {
            if (file.getNameOfMyFile().substring(4).equals(pathToFile)) {
                file.compareFiles();
            }

        }
    }


    public Set<MyFile> getFiles() {
        return files;
    }
}

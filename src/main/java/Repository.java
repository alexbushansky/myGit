import java.io.File;
import java.io.IOException;

public class Repository {


    private Branch branch;
    private Branch temp = new Branch(new File("1232"));





    public Repository(Branch branch) {
        this.branch=branch;
    }

    public void commit(String pathToFile) throws IOException {
        branch.commitFile(new File(pathToFile));
    }

    public void checkOut(String nameOfBranch)
    {
        boolean flag = true;
        if (nameOfBranch.equals(temp.getNameOfBranch()))
        {
            branch=temp;
            System.out.println("You are in branch " + branch.getNameOfBranch() + "!");
            return;

        }else if(!nameOfBranch.equals(temp.getNameOfBranch())) {


            if (!branch.getNameOfBranch().equals(nameOfBranch)) {
                if (branch.getMySet().size() == 0) {
                    flag = false;

                }

                for (Branch br : branch.getMySet()) {
                    if (!br.getNameOfBranch().equals(nameOfBranch)) {
                        flag = false;
                    }
                }

            }

            if (!flag) {
                temp = branch;
                branch = branch.createDir(nameOfBranch);

                System.out.println("You are in branch " + branch.getNameOfBranch() + "!");

            }
        }

    }


    public Branch getBranch() {
        return branch;
    }
}

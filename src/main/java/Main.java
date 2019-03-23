import java.io.File;
import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {

        File f = new File("master");

        Branch br = new Branch(f);
        Repository repository = new Repository(br);

        System.out.println("1. innit " +
                "\n2. commit" +
                "\n3. checkout" +
                "\n4. compare" +
                "\n5. exit" +
                "\n Enter number! ");


        int typing;
        Scanner sc = new Scanner(System.in);
        loop:
        while (sc.hasNextInt()) {
            typing = sc.nextInt();

            switch (typing) {
                case 1:
                    if(f.mkdirs()) {
                        f.mkdirs();
                    }
                    else
                        System.out.println("already added");
                    break ;
                case 2:
                    System.out.println("Enter path to file!");
                    Scanner sc3 = new Scanner(System.in);
                    repository.commit(sc3.nextLine());
                    break;
                case 3:
                    System.out.println("Enter name of branch");
                    Scanner sc1 = new Scanner(System.in);
                    repository.checkOut(sc1.nextLine());
                    break;
                case 4:
                    System.out.println("Enter name of file in Branch for compare");
                    Scanner sc2 = new Scanner(System.in);
                    repository.getBranch().compareFiles(sc2.nextLine());
                    break;
                case 5:
                    break loop;


            }
            System.out.println("1. innit " +
                    "\n2. commit" +
                    "\n3. checkout" +
                    "\n4. compare" +
                    "\n5. exit" +
                    "\n Enter number! ");

        }


    }
}


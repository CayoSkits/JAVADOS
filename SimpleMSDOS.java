import java.io.File;
import java.util.Scanner;

public class SimpleMSDOS {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String currentDirectory = System.getProperty("user.dir");

        while (true) {
            System.out.print(currentDirectory + "> ");
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("exit")) {
                break;
            } else if (command.equalsIgnoreCase("dir")) {
                listDirectory(currentDirectory);
            } else if (command.startsWith("cd ")) {
                String newPath = command.substring(3).trim();
                File newDir = new File(currentDirectory, newPath);
                if (newDir.isDirectory()) {
                    currentDirectory = newDir.getAbsolutePath();
                } else {
                    System.out.println("The system cannot find the path specified.");
                }
            } else {
                System.out.println("Invalid command");
            }
        }

        scanner.close();
    }

    private static void listDirectory(String path) {
        File dir = new File(path);
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    System.out.println("<DIR> " + file.getName());
                } else {
                    System.out.println("      " + file.getName());
                }
            }
        } else {
            System.out.println("The directory is empty or cannot be read.");
        }
    }
}

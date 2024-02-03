import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class StudentDataEntry {
    public static void main(String[] args) {
        List<Student> studentList = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Enter student data (or type 'exit' to finish):");
            System.out.print("Name: ");
            String name = scanner.nextLine();

            if (name.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.print("Address: ");
            String address = scanner.nextLine();

            double GPA;
            while (true) {
                try {
                    System.out.print("GPA: ");
                    GPA = Double.parseDouble(scanner.nextLine());
                    // Validate GPA
                    if (GPA < 0 || GPA > 4.0) {
                        throw new NumberFormatException();
                    }
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid GPA. Please enter a numeric value between 0 and 4.0.");
                }
            }

            studentList.add(new Student(name, address, GPA));
        }

        Collections.sort(studentList);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("student_data.txt"))) {
            for (Student student : studentList) {
                writer.write("Name: " + student.getName() + "\n");
                writer.write("Address: " + student.getAddress() + "\n");
                writer.write("GPA: " + student.getGPA() + "\n\n");
            }
            System.out.println("Data written to student_data.txt.");
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
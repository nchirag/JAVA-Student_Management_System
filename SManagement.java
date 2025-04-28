import java.util.*;

class Teacher {
    private String name;
    private String username;
    private String password;
    private boolean isLoggedIn;

    public Teacher(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.isLoggedIn = false;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
        System.out.println("Username updated successfully");
    }

    public void setPassword(String password) {
        this.password = password;
        System.out.println("Password updated successfully");
    }

    public void setLoggedIn(boolean isLoggedIn) {
        this.isLoggedIn = isLoggedIn;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    public void logout() {
        this.isLoggedIn = false;
        System.out.println("Logged out successfully");
    }
}

class Student {
    private String name;
    private int regNo;
    private int age;
    private double marks;

    public Student(String name, int regNo, int age, double marks) {
        this.name = name;
        this.regNo = regNo;
        this.age = age;
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public int getRegNo() {
        return regNo;
    }

    public int getAge() {
        return age;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
        System.out.println("Marks updated successfully");
    }

    public void displayDetails() {
        System.out.println("Name: " + name + ", Reg No: " + regNo + ", Age: " + age + ", Marks: " + marks);
    }
}

public class SManagement {
    private static final Scanner sc = new Scanner(System.in);
    private static List<Student> students = new ArrayList<>();
    private static List<Teacher> teachers = new ArrayList<>();
    private static Teacher currentTeacher = null;

    public static void register() {
        System.out.println("Enter the name of the teacher - ");
        String name = sc.nextLine();
        System.out.println("Enter the username - ");
        String username = sc.nextLine();
        System.out.println("Enter the password - ");
        String password = sc.nextLine();
        teachers.add(new Teacher(name, username, password));
        System.out.println("Teacher registered successfully");
    }

    public static void login() {
        System.out.println("Enter the username - ");
        String username = sc.nextLine();
        System.out.println("Enter password - ");
        String password = sc.nextLine();
        for (Teacher teacher : teachers) {
            if (teacher.login(username, password)) {
                System.out.println("Login successful");
                teacher.setLoggedIn(true);
                currentTeacher = teacher;
                return;
            }
        }
        System.out.println("Invalid username or password");
    }

    public static void addStudent() {
        if (currentTeacher == null || !currentTeacher.isLoggedIn()) {
            System.out.println("Please login first");
            return;
        }
        
        System.out.println("Enter the name of the student - ");
        String name = sc.nextLine();
        System.out.println("Enter the registration number - ");
        int regNo = sc.nextInt();
        System.out.println("Enter the age - ");
        int age = sc.nextInt();
        System.out.println("Enter the marks - ");
        double marks = sc.nextDouble();
        students.add(new Student(name, regNo, age, marks));
        System.out.println("Student added successfully");
    }

    public static void viewStudent() {
        if (currentTeacher == null || !currentTeacher.isLoggedIn()) {
            System.out.println("Please login first");
            return;
        }

        System.out.println("Enter the registration number - ");
        int regNo = sc.nextInt();

        boolean found = false;
        for (Student student : students) {
            if (student.getRegNo() == regNo) {
                student.displayDetails();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found");
        }
    }

    public static void updateMarks() {
        if (currentTeacher == null || !currentTeacher.isLoggedIn()) {
            System.out.println("Please login first");
            return;
        }
        System.out.println("Enter the registration number - ");
        int regNo = sc.nextInt();
        boolean found = false;
        for (Student student : students) {
            if (student.getRegNo() == regNo) {
                System.out.println("Enter new marks - ");
                double marks = sc.nextDouble();
                student.setMarks(marks);
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student not found");
        }
    }

    public static void deleteStudent() {
        if (currentTeacher == null || !currentTeacher.isLoggedIn()) {
            System.out.println("Please login first");
            return;
        }
        System.out.println("Enter the registration number - ");
        int regNo = sc.nextInt();
        boolean found = false;
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            if (student.getRegNo() == regNo) {
                iterator.remove();
                System.out.println("Student deleted successfully");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student not found");
        }
    }

    public static void logout() {
        if (currentTeacher != null) {
            currentTeacher.logout();
            currentTeacher = null;
        } else {
            System.out.println("No teacher is currently logged in");
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Student Management System ===");
            System.out.println("1. Login as Teacher");
            System.out.println("2. Register New Teacher");
            System.out.println("3. Add Student");
            System.out.println("4. View Student");
            System.out.println("5. Update Marks");
            System.out.println("6. Delete Student");
            System.out.println("7. Logout");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Clear buffer
            
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    addStudent();
                    break;
                case 4:
                    viewStudent();
                    break;
                case 5:
                    updateMarks();
                    break;
                case 6:
                    deleteStudent();
                    break;
                case 7:
                    logout();
                    break;
                case 8:
                    System.out.println("Exiting the program");
                    sc.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}

import java.util.*;

class Course {
    String code, title, description, schedule;
    int capacity;
    int enrolled;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
        this.enrolled = 0;
    }

    public boolean isAvailable() {
        return enrolled < capacity;
    }

    public void enroll() {
        enrolled++;
    }

    public void drop() {
        if (enrolled > 0) enrolled--;
    }

    public void display() {
        System.out.println(code + " | " + title + " | " + schedule + " | Capacity: " + enrolled + "/" + capacity);
        System.out.println("  " + description);
    }
}

class Student {
    String id, name;
    List<Course> registeredCourses = new ArrayList<>();

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void registerCourse(Course c) {
        if (registeredCourses.contains(c)) {
            System.out.println(" Already registered for this course.");
            return;
        }
        if (c.isAvailable()) {
            registeredCourses.add(c);
            c.enroll();
            System.out.println("Successfully registered!");
        } else {
            System.out.println(" Course is full.");
        }
    }

    public void dropCourse(Course c) {
        if (registeredCourses.remove(c)) {
            c.drop();
            System.out.println("Course dropped.");
        } else {
            System.out.println(" Not registered for this course.");
        }
    }

    public void showRegisteredCourses() {
        if (registeredCourses.isEmpty()) {
            System.out.println("No courses registered.");
        } else {
            System.out.println(" Registered Courses:");
            for (Course c : registeredCourses) {
                System.out.println("- " + c.code + ": " + c.title);
            }
        }
    }
}

public class StudentSystem {
    static Scanner scanner = new Scanner(System.in);
    static List<Course> courseList = new ArrayList<>();
    static Student student;

    public static void main(String[] args) {
        loadCourses();
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        student = new Student(id, name);

        int choice;
        do {
            System.out.println("\n=== Student Course Registration ===");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    listCourses();
                    break;
                case 2:
                    registerCourse();
                    break;
                case 3:
                    dropCourse();
                    break;
                case 4:
                    student.showRegisteredCourses();
                    break;
                case 5:
                    System.out.println(" Exiting...");
                    break;
                default:
                    System.out.println(" Invalid option.");
            }
        } while (choice != 5);
    }

    public static void loadCourses() {
        courseList.add(new Course("CS101", "Intro to Programming", "Basics of Java", 2, "Mon-Wed 10AM"));
        courseList.add(new Course("CS102", "Data Structures", "Arrays, Lists, Trees", 3, "Tue-Thu 11AM"));
        courseList.add(new Course("CS103", "Web Dev", "HTML, CSS, JS", 2, "Mon-Fri 9AM"));
    }

    public static void listCourses() {
        System.out.println("\n Available Courses:");
        for (Course c : courseList) {
            c.display();
        }
    }

    public static void registerCourse() {
        System.out.print("Enter course code to register: ");
        String code = scanner.nextLine();
        for (Course c : courseList) {
            if (c.code.equalsIgnoreCase(code)) {
                student.registerCourse(c);
                return;
            }
        }
        System.out.println("Course not found.");
    }

    public static void dropCourse() {
        System.out.print("Enter course code to drop: ");
        String code = scanner.nextLine();
        for (Course c : courseList) {
            if (c.code.equalsIgnoreCase(code)) {
                student.dropCourse(c);
                return;
            }
        }
        System.out.println(" Course not found.");
    }
}

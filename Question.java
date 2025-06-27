
import java.util.*;

class Question {
    String question;
    String[] options;
    int correctIndex;

    public Question(String question, String[] options, int correctIndex) {
        this.question = question;
        this.options = options;
        this.correctIndex = correctIndex;
    }
}

public class Main {
    static List<Question> questions = new ArrayList<>();
    static int score = 0;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        loadQuestions();
        System.out.println(" Welcome to the Quiz!");
        System.out.println(" You have 10 seconds per question.\n");

        for (int i = 0; i < questions.size(); i++) {
            askQuestion(questions.get(i), i + 1);
        }

        System.out.println("\n===  Quiz Finished! ===");
        System.out.println(" Final Score: " + score + " out of " + questions.size());
    }

    public static void loadQuestions() {
        questions.add(new Question(
            "What is the capital of Japan?",
            new String[]{"Seoul", "Tokyo", "Beijing", "Bangkok"}, 1));

        questions.add(new Question(
            "Which language runs in a web browser?",
            new String[]{"C", "Python", "JavaScript", "Java"}, 2));

        questions.add(new Question(
            "What is the square root of 64?",
            new String[]{"6", "8", "7", "9"}, 1));
    }

    public static void askQuestion(Question q, int number) {
        System.out.println("Q" + number + ": " + q.question);
        for (int i = 0; i < q.options.length; i++) {
            System.out.println((i + 1) + ". " + q.options[i]);
        }

        Timer timer = new Timer();
        final boolean[] answered = {false};

        timer.schedule(new TimerTask() {
            public void run() {
                if (!answered[0]) {
                    System.out.println("\n Time's up!");
                    System.exit(0); 
                }
            }
        }, 10000); 

        try {
            System.out.print("Your answer (1-4): ");
            int answer = scanner.nextInt();
            answered[0] = true;
            timer.cancel();

            if (answer - 1 == q.correctIndex) {
                System.out.println(" Correct!\n");
                score++;
            } else {
                System.out.println(" Incorrect! Correct answer: " + q.options[q.correctIndex] + "\n");
            }
        } catch (Exception e) {
            timer.cancel();
            System.out.println(" Invalid input or timeout.");
        }
    }
}

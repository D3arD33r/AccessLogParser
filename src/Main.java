import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        System.out.println("Введите первое число:");
        int numberFirst = new Scanner(System.in).nextInt();

        System.out.println("Введите второе число:");
        int numberSecond = new Scanner(System.in).nextInt();

        int sum = numberFirst + numberSecond;
        int subtraction = numberFirst - numberSecond;
        int multiplication = numberFirst * numberSecond;
        double division =(double) numberFirst / numberSecond;

        System.out.println("Сумма: " + sum);
        System.out.println("Разность: " + subtraction);
        System.out.println("Произведение: " + multiplication);
        System.out.println("Частное: " + division);

    }
}

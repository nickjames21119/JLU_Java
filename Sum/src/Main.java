import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        int sum = 0;
        int number = 0;
        while(number!=999){

            Scanner scanner = new Scanner(System.in);
            number = scanner.nextInt();
            sum+=number;
        }
        System.out.println(sum);
    }
}
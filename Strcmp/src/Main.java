import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan1 = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        String str1 = scan1.next();
        String str2 = scan2.next();
        System.out.println(Solution.isMatch(str1, str2));
    }
}
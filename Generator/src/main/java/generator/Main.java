package generator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();
        Generator generator = new Generator();
        generator.generate(number);
        ConnectionPool.getInstance().destroyConnections();
    }
}
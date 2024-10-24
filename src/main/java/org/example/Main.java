package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.exceptions.InsufficientFundsException;
import org.example.exceptions.NegativeAmountException;
import org.example.operation.DepositOperation;
import org.example.operation.WithdrawOperation;
import org.example.users.Corporate;
import org.example.users.Individual;

import java.util.Scanner;

public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class);
    private static final String HELLO = """
            Здравствуйте! Выберите тип регистрации:
            1. Регистрация физического лица
            2. Регистрация юридического лица
            """;
    private static final String MENU = """
                Выберите действие:
                1. Пополнить счёт
                2. Снять деньги
                3. Просмотреть счёт
                4. История транзакций
                5. Выйти
                """;

    public static void main(String[] args) {
        double amount;
        Account account = null;
        Scanner scanner = new Scanner(System.in);

        System.out.println(HELLO);

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                account = new Account(registrationIndividual());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("\nУСПЕШНО!\n");
                break;
            case 2:
                account = new Account(registrationCorporate());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("\nУСПЕШНО!\n");
                break;
            default:
                System.out.println("Ошибка!");
                LOGGER.warn("Была введена неизвестная команда {}", scanner);
                break;
        }

        LOGGER.info("Успешная регистрация аккаунта {}", account);

        while (true) {
            System.out.println(MENU);

            switch (scanner.nextInt()) {
                case 1:
                    System.out.print("Введите сумму пополнения: ");
                    amount = scanner.nextDouble();
                    DepositOperation deposit = new DepositOperation(amount);
                    try {
                        deposit.execute(account);
                        System.out.println("Успешно!\n");
                        LOGGER.info("Пополнение счёте на сумму: {}", amount);
                    } catch (NegativeAmountException e) {
                        LOGGER.error("Ошибка! {} - {}", e.getMessage(), amount);
                        System.out.println(e.getMessage() + "\n");
                    }
                    break;

                case 2:
                    System.out.print("Введите сумму списания: ");
                    amount = scanner.nextDouble();
                    WithdrawOperation withdraw = new WithdrawOperation(amount);
                    try {
                        withdraw.execute(account);
                        LOGGER.info("Списание на сумму {} прошло успешно", amount);
                        System.out.println("Успешно!\n");
                    } catch (InsufficientFundsException e) {
                        LOGGER.error("Ошибка!{} - {}", e.getMessage(), amount);
                        System.out.println(e.getMessage() + "\n");
                    }
                    break;

                case 3:
                    System.out.println(account);
                    LOGGER.info("Просмотр банковского счёта");
                    break;

                case 4:
                    int count = 1;
                    if (account.getTransactionHistory().getTransactions() == null) {
                        System.out.println("История транзакций пуста.\n");
                        break;
                    }
                    for (Transaction transaction : account.getTransactionHistory().getTransactions()) {
                        System.out.println(count++ + ". Тип операции - " + transaction.getType() + ", сумма - " + transaction.getAmount());
                    }
                    LOGGER.info("Просмотр истории транзакций");
                    break;

                case 5:
                    System.exit(1);
                    break;

                default:
                    System.out.println("Неизвестная команда :(");
                    LOGGER.warn("Была введена неизвестная команда {}", scanner);
                    break;
            }
        }
    }

    public static Individual registrationIndividual() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите имя: ");
        String firstName = scanner.nextLine().trim();
        System.out.print("Введите фамилию: ");
        String lastName = scanner.nextLine().trim();
        System.out.print("Введите номер телефона: ");
        String phone = scanner.nextLine().trim();
        System.out.print("Введите электронную почту: ");
        String email = scanner.nextLine().trim();
        System.out.print("Введите ИНН: ");
        String ITN = scanner.nextLine().trim();

        return new Individual(firstName, lastName, phone, email, ITN);
    }

    public static Corporate registrationCorporate() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите название компании: ");
        String companyName = scanner.nextLine().trim();
        System.out.print("Введите ФИО директора: ");
        String director = scanner.nextLine().trim();
        System.out.print("Введите номер телефона компании: ");
        String phone = scanner.nextLine().trim();
        System.out.print("Введите корпоративную почту: ");
        String email = scanner.nextLine().trim();
        System.out.print("Введите ИНН: ");
        String TXN = scanner.nextLine().trim();

        return new Corporate(companyName, director, phone, email, TXN);
    }
}
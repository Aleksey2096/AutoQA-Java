package pl.traineeship.autoqa.lesson3;

/*
Создать массив из 5 сотрудников.

Пример:

// Вначале объявляем массив объектов

Person[] persArray = new Person[5];

// потом для каждой ячейки массива задаем объект

persArray[0] = new Person("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 30);

persArray[1] = new Person(...);

...

persArray[4] = new Person(...);

С помощью цикла вывести информацию только о сотрудниках старше 40 лет.
 */
public final class App {
    private static final int EMPLOYEES_COUNT = 5;
    private static final int AGE_LIMIT = 40;

    public static void main(String[] args) {
        Employee[] employees = new Employee[EMPLOYEES_COUNT];

        employees[0] = new Employee("Robert Johnson", "Designer", "robert_johnson@gmail.com",
                "+16059714423", 45000, 29);
        employees[1] = new Employee("Elizabeth Miller", "Producer", "elizabeth_miller@gmail.com",
                "+12794992386", 230000, 57);
        employees[2] = new Employee("Charles Smith", "Technician", "charles_smith@gmail.com",
                "+17252392778", 169000, 45);
        employees[3] = new Employee("Mary Brown", "Developer", "mary_brown@gmail.com",
                "+15159937948", 140000, 36);
        employees[4] = new Employee("George Martin", "Architect", "george_martin@gmail.com",
                "+16059713700", 299000, 62);

        for (Employee employee : employees) {
            if (employee.getAge() > AGE_LIMIT) {
                System.out.println(employee);
            }
        }
    }
}

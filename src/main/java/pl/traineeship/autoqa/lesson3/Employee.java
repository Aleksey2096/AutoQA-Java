package pl.traineeship.autoqa.lesson3;

/*
Создать класс "Сотрудник" с полями: ФИО, должность, email, телефон, зарплата, возраст.

Конструктор класса должен заполнять эти поля при создании объекта.

Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль.
 */
public final class Employee {
    private final String fullName;
    private final String jobTitle;
    private final String email;
    private final String phoneNumber;
    private final Integer salary;
    private final Integer age;

    public Employee(final String newFullName, final String newJobTitle, final String newEmail,
                    final String newPhoneNumber,
                    final Integer newSalary, final Integer newAge) {
        fullName = newFullName;
        jobTitle = newJobTitle;
        email = newEmail;
        phoneNumber = newPhoneNumber;
        salary = newSalary;
        age = newAge;
    }

    public Integer getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "fullName='" + fullName + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", salary=" + salary +
                ", age=" + age +
                '}';
    }
}

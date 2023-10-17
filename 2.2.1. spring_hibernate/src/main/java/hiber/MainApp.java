package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("Николай", "Иванов", "Nikolay@mail.ru");
        User user2 = new User("Андрей", "Кузнецов", "Andrey@mail.ru");
        User user3 = new User("Василий", "Сидоров", "Vasily@mail.ru");

        Car car1 = new Car("Honda", 3);
        Car car2 = new Car("Renault", 5);
        Car car3 = new Car("Volvo", 7);

        userService.add(user1, car1);
        userService.add(user2,car2);
        userService.add(user3,car3);


                List<User> userWithMachine = userService.getUser("honda", 3);
        for (User userWithCar : userWithMachine) {
            System.out.println("Id = " + userWithCar.getId());
            System.out.println("First Name = " + userWithCar.getFirstName());
            System.out.println("Last Name = " + userWithCar.getLastName());
            System.out.println("Email = " + userWithCar.getEmail());
            System.out.println();
        }

                List<User> allUsers = userService.listUsers();
        for (User user : allUsers) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println();
        }

        context.close();
    }
}

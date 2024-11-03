package api.user;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class User {
    private final String email;
    private final String password;
    private final String name;

    private static Faker faker = new Faker();

    public static User createUser() {
        return new User(faker.internet().emailAddress(),
                faker.internet().password(), faker.name().firstName());
    }

    public static User createUserWithInvalidPassword() {
        String email = faker.internet().emailAddress();
        String password = faker.internet().password(1, 5);
        String name = faker.name().firstName();
        return new User(email, password, name);
    }
}

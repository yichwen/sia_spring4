package spittr.data;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import spittr.Spitter;

import java.util.Arrays;
import java.util.List;

@Repository
public class SpitterRepositoryImpl implements SpitterRepository {

    private List<Spitter> spitters = Arrays.asList(
            new Spitter(0L,
                    "user999", new BCryptPasswordEncoder().encode("password"),
                    "USER", "999",
                    "user999@mail.com"),
            new Spitter(1L,
                    "admin01", new BCryptPasswordEncoder().encode("password"),
                    "ADMIN", "01",
                    "admin01@mail.com")
    );

    @Override
    public Spitter save(Spitter spitter) {
        long index = this.spitters.size();
        Spitter newSpitter = new Spitter(index,
                spitter.getUsername(), spitter.getPassword(),
                spitter.getFirstName(), spitter.getLastName(),
                spitter.getEmail()
        );
        spitters.add(newSpitter);
        return newSpitter;
    }

    @Override
    public Spitter findByUsername(String username) {
        return spitters.stream().filter(s -> s.getUsername().equalsIgnoreCase(username)).findFirst().orElse(null);
    }

}

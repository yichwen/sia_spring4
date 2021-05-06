package spittr.data;

import org.springframework.stereotype.Repository;
import spittr.Spitter;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SpitterRepositoryImpl implements SpitterRepository {

    private List<Spitter> spitters = new ArrayList<>();

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

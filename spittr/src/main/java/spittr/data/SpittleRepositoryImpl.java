package spittr.data;

import org.springframework.stereotype.Repository;
import spittr.Spittle;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class SpittleRepositoryImpl implements SpittleRepository {

    private List<Spittle> spittles = createSpittleList(20);

    @Override
    public void save(Spittle spittle) {
        this.spittles.add(spittle);
    }

    @Override
    public List<Spittle> findSpittles(long max, int count) {
        return spittles;
    }

    @Override
    public Spittle findOne(long id) {
//        return spittles.stream().filter(s -> s.getId() == id).findFirst().orElse(new Spittle("Unknown spittle", new Date()));
        return spittles.stream().filter(s -> s.getId() == id).findFirst().orElse(null);
    }

    private List<Spittle> createSpittleList(int count) {
        List<Spittle> spittles = new ArrayList<>();
        for (int i=0; i < count; i++) {
            spittles.add(new Spittle((long) i, "Spittle " + i, new Date(), 0.0, 0.0));
        }
        return spittles;
    }

}

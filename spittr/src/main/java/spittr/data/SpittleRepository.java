package spittr.data;

import spittr.Spittle;
import java.util.List;

public interface SpittleRepository {
    void save(Spittle spittle);
    List<Spittle> findSpittles(long max, int count);
    Spittle findOne(long spittleId);
}

package Interfaces;

import java.util.List;

public interface Repository<T>
{
    void add(T t);
    void remove(T t);
    void update(T t,T tt);
    List<T> query(Specification<T> specification);
}

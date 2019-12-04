package Interfaces;

public interface Observer<T>
{
    void onAdd(T t);
    void onDelete(T t);
    void onUpdate(T t,T tt);
}

package repository;

public interface GeneralRepository<T, U> {
    public void create(T t);
    public void update(T t);
    public void delete(U u);
}
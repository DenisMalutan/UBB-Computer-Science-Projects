package repository;
import model.Vehicle;
import exception.RepositoryException;

public interface IRepository {
    void add(Vehicle v) throws RepositoryException;
    void remove(Vehicle v) throws RepositoryException;
    Vehicle[] getAll();
}
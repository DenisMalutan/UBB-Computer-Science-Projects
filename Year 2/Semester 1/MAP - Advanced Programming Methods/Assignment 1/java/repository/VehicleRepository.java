package repository;
import model.Vehicle;
import exception.RepositoryException;

public class VehicleRepository implements IRepository {
    private Vehicle[] vehicles;
    private int size;

    public VehicleRepository(int capacity) {
        this.vehicles = new Vehicle[capacity];
        this.size = 0;
    }

    @Override
    public void add(Vehicle v) throws RepositoryException {
        if (size == vehicles.length) {
            throw new RepositoryException("Repository is full! Cannot add more vehicles.");
        }
        vehicles[size++] = v;
    }

    @Override
    public void remove(Vehicle v) throws RepositoryException {
        int indexToRemove = -1;
        for (int i = 0; i < size; i++) {
            if (vehicles[i].equals(v)) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove == -1) {
            throw new RepositoryException("Vehicle not found in repository!");
        }

        // Shift elements to the left to fill the gap
        for (int i = indexToRemove; i < size - 1; i++) {
            vehicles[i] = vehicles[i + 1];
        }
        vehicles[size - 1] = null;
        size--;
    }

    @Override
    public Vehicle[] getAll() {
        Vehicle[] currentVehicles = new Vehicle[size];
        System.arraycopy(vehicles, 0, currentVehicles, 0, size);
        return currentVehicles;
    }
}
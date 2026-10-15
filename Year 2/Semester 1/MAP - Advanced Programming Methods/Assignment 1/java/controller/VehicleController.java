package controller;
import model.Vehicle;
import repository.IRepository;
import exception.RepositoryException;

public class VehicleController {
    private IRepository repository;

    public VehicleController(IRepository repository) {
        this.repository = repository;
    }

    public void addVehicle(Vehicle v) throws RepositoryException {
        repository.add(v);
    }

    public void removeVehicle(Vehicle v) throws RepositoryException {
        repository.remove(v);
    }

    public Vehicle[] getVehiclesByColor(String targetColor) {
        Vehicle[] allVehicles = repository.getAll();
        int count = 0;

        // First pass to count how many match the color
        for (Vehicle v : allVehicles) {
            if (v.getColor().equalsIgnoreCase(targetColor)) {
                count++;
            }
        }

        // Second pass to populate the filtered array
        Vehicle[] filteredVehicles = new Vehicle[count];
        int index = 0;
        for (Vehicle v : allVehicles) {
            if (v.getColor().equalsIgnoreCase(targetColor)) {
                filteredVehicles[index++] = v;
            }
        }

        return filteredVehicles;
    }
}
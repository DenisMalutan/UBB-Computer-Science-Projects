package view;

import model.Car;
import model.Motorcycle;
import model.Bicycle;
import model.Vehicle;
import repository.IRepository;
import repository.VehicleRepository;
import controller.VehicleController;
import exception.RepositoryException;

public class Main {
    public static void main(String[] args) {
        // Initialize the repository with a fixed size of 10
        IRepository repository = new VehicleRepository(10);
        VehicleController controller = new VehicleController(repository);

        try {
            // Adding vehicles to the repository
            Vehicle car1 = new Car("Red");
            Vehicle car2 = new Car("Blue");
            Vehicle moto1 = new Motorcycle("Red");
            Vehicle bike1 = new Bicycle("Green");
            Vehicle bike2 = new Bicycle("Red");

            controller.addVehicle(car1);
            controller.addVehicle(car2);
            controller.addVehicle(moto1);
            controller.addVehicle(bike1);
            controller.addVehicle(bike2);

            // Display all RED vehicles
            System.out.println("--- All Red Vehicles ---");
            Vehicle[] redVehicles = controller.getVehiclesByColor("Red");
            for (Vehicle v : redVehicles) {
                System.out.println(v.toString());
            }

            // Demonstrate removal and exception handling
            System.out.println("\n--- Testing Exception Handling ---");
            controller.removeVehicle(car1); // Successfully removes Red Car
            System.out.println("Red Car removed successfully.");

            Vehicle car3 = new Car("Yellow");
            controller.removeVehicle(car3); // Will throw an exception because it was never added

        } catch (RepositoryException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
    }
}
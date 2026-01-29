package src;
public class Main {

    public static void main(String[] args) {

        RideBookingSystem system = new RideBookingSystem();

        // Register user
        system.register(1, "Alex Brown", "alex@gmail.com", "1234");

        // Login
        boolean status = system.login(1, "1234");
        System.out.println("Login status: " + status);

        // Create Ride
        system.createRide("Delhi", "Agra", 4, 4, 500, 1);
    }
}
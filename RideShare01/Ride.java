package src;
public class Ride {

    int rideId;
    String source;
    String destination;
    int totalSeats;
    int availableSeats;
    double fare;
    int userId;

    public Ride(int rideId, String source, String destination,
                int totalSeats, int availableSeats, double fare, int userId) {
        this.rideId = rideId;
        this.source = source;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.fare = fare;
        this.userId = userId;
    }

    public String toString() {
        return rideId + " " + source + " -> " + destination + " " + fare;
    }
}

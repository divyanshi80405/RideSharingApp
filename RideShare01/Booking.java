package src;
public class Booking {

    int bookingId;
    int rideId;
    int userId;
    int seats;
    double totalFare;

    public Booking(int bookingId, int rideId, int userId, int seats, double totalFare) {
        this.bookingId = bookingId;
        this.rideId = rideId;
        this.userId = userId;
        this.seats = seats;
        this.totalFare = totalFare;
    }
}

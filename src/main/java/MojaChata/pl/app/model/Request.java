package MojaChata.pl.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "requests")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "requestSeq")
    @SequenceGenerator(name = "requestSeq", sequenceName = "REQUESTS_SEQ")
    @Column(name = "request_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "cottage_id", nullable = false)
    @NotNull(message = "Cottage is mandatory")
    private Cottage cottage;

    private long customerId;

    @Temporal(TemporalType.DATE)
    @Column(name = "checkin_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Check-in date is required.")
    private LocalDate checkInDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "checkout_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Check-out date is required.")
    private LocalDate checkOutDate;

    @Column(name = "total_price")
    private float totalPrice;

    public Request() {}

    public Request(Cottage cottage, long customerId) {
        this.cottage = cottage;
        this.customerId = customerId;
        this.checkInDate = LocalDate.now();
        this.checkOutDate = LocalDate.now();
        this.totalPrice = 0;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public Cottage getCottage() {
        return cottage;
    }
    public void setCottage(Cottage cottage) {
        this.cottage = cottage;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }
    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }
    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public float getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}

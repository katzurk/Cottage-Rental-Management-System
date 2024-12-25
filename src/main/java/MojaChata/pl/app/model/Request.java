package MojaChata.pl.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.sql.Date;

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
    private Date checkin_date;
    @Temporal(TemporalType.DATE)
    private java.sql.Date checkout_date;
    // TODO: total price = (checkout_date - checkin_date) * cottage.minPricePerDay
    private float total_price;

    public Request() {}

    public Request(Cottage cottage, long customerId) {
        this.cottage = cottage;
        this.customerId = customerId;
        this.checkin_date = new java.sql.Date(System.currentTimeMillis());
        this.checkout_date = new java.sql.Date(System.currentTimeMillis());
        this.total_price = 0;
    }

    public Request(Cottage cottage, long customerId, Date checkin_date, java.sql.Date checkout_date, float total_price) {
        // to do
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
}

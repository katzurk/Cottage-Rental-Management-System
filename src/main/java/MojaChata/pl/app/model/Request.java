package MojaChata.pl.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity(name = "requests")
public class Request {

    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "request_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "cottage_id", nullable = false)
    @NotNull(message = "Cottage is mandatory")
    private Cottage cottage;

    private long customerId;

    // private date checkin_date;
    // private date checkout_date;
    // private float total_price;


    public Request() {}

    public Request(Cottage cottage, long customerId) {
        this.cottage = cottage;
        this.customerId = customerId;
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

    public void setCustomerId(long submitterId) {
        this.customerId = submitterId;
    }

    public Cottage getCottage() {
        return cottage;
    }

    public void setCottage(Cottage cottage) {
        this.cottage = cottage;
    }
}

package MojaChata.pl.app.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "cottage_id", nullable = false)
    @NotNull(message = "Cottage is mandatory")
    private Cottage cottage;

    private long submitterId;

    private long recipientId;

    public Request() {}
    public Request(Cottage cottage, long submitterId, long recipientId) {
        this.cottage = cottage;
        this.submitterId = submitterId;
        this.recipientId = recipientId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSubmitterId() {
        return submitterId;
    }

    public void setSubmitterId(long submitterId) {
        this.submitterId = submitterId;
    }

    public long getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(long recipientId) {
        this.recipientId = recipientId;
    }

    public Cottage getCottage() {
        return cottage;
    }

    public void setCottage(Cottage cottage) {
        this.cottage = cottage;
    }
}

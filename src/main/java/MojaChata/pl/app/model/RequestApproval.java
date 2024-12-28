package MojaChata.pl.app.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity(name = "request_approvals")
public class RequestApproval {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "requestApprovalSeq")
    @SequenceGenerator(name = "requestApprovalSeq", sequenceName = "REQUEST_APPROVALS_SEQ")
    @Column(name = "request_approval_id")
    private long id;

    @Column(nullable = false)
    private LocalDate dateCreated;

    @Column(nullable = false)
    private boolean isApproved;

    @OneToOne
    @JoinColumn(name = "REQUEST_ID", nullable = false)
    private Request request;

    public LocalDate getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public boolean isApproved() {
        return isApproved;
    }
    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    public Request getRequest() {
        return request;
    }
    public void setRequest(Request request) {
        this.request = request;
    }
}
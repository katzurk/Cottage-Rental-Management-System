package MojaChata.pl.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity (name= "cottages_restrictions")
public class CottagesRestrictions {
    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "restriction_id")
    private Restriction restrictionId;

    @ManyToOne
    @JoinColumn(name = "cottage_id")
    private Cottage cottageId;

    public Restriction getRestrictionId() {
        return this.restrictionId;
    }

    public Cottage getCottageId() {
        return this.cottageId;
    }

    public void setRestrictionId(Restriction restrictionId) {
        this.restrictionId = restrictionId;
    }

    public void setCottageId(Cottage cottageId) {
        this.cottageId = cottageId;
    }
}

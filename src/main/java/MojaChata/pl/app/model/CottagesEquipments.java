package MojaChata.pl.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity (name= "cottages_equipments")
public class CottagesEquipments {
    @Id
    private long id;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipmentId;

    @ManyToOne
    @JoinColumn(name = "cottage_id")
    private Cottage cottageId;

    public Equipment getEquipmentId() {
        return this.equipmentId;
    }

    public Cottage getCottageId() {
        return this.cottageId;
    }

    public void setEquipmentId(Equipment equipmentId) {
        this.equipmentId = equipmentId;
    }

    public void setCottageId(Cottage cottageId) {
        this.cottageId = cottageId;
    }
}

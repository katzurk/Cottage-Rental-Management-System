package MojaChata.pl.app.model;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity(name = "cottages")
public class Cottage {

    @Id
    @Column(name = "cottage_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cottageSeq")
    @SequenceGenerator(name = "cottageSeq", sequenceName = "COTTAGES_SEQ")
    private long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @OneToOne
    @JoinColumn(name = "address_id")
    @NotNull(message = "Address is mandatory")
    @Cascade(CascadeType.ALL)
    private Address address;

    @Positive(message = "Size is mandatory")
    private int size_m2;

    @Positive(message = "RoomsNumber is mandatory")
    private int roomsNumber;

    @Positive(message = "BathroomsNumber is mandatory")
    private int bathroomsNumber;

    @Positive(message = "MaxPeopleNum is mandatory and needs to be gt 0")
    private int maxPeopleNum;

    @Positive(message = "Price is mandatory")
    @Column(name = "min_price_per_day")
    private BigDecimal minPricePerDay;

    @ManyToOne()
    @JoinColumn(name = "owner_id")
    // @NotNull(message = "Owner is mandatory")
    private User owner;

    @ManyToMany()
    @JoinTable(name = "cottages_equipments",
        joinColumns = { @JoinColumn(name = "cottage_id") },
        inverseJoinColumns = { @JoinColumn(name = "equipment_id") })
    private List<Equipment> equipments;

    @ManyToMany()
    @JoinTable(name = "cottages_facilities",
        joinColumns = { @JoinColumn(name = "cottage_id") },
        inverseJoinColumns = { @JoinColumn(name = "facility_id") })
    private List<Facility> facilities;

    @ManyToMany()
    @JoinTable(name = "cottages_restrictions",
        joinColumns = { @JoinColumn(name = "cottage_id") },
        inverseJoinColumns = { @JoinColumn(name = "restriction_id") })
    private List<Restriction> restrictions;

    // standard constructors / setters / getters / toString
    Cottage() {}

    Cottage(String name, Address address, int size_m2, int roomsNumber, int bathroomsNumber, int maxPeopleNum, BigDecimal price, User owner) {

      this.name = name;
      this.address = address;
      this.size_m2 = size_m2;
      this.roomsNumber = roomsNumber;
      this.bathroomsNumber = bathroomsNumber;
      this.maxPeopleNum = maxPeopleNum;
      this.minPricePerDay = price;
      this.owner = owner;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Address getAddress() {
        return this.address;
    }

    public int getSize_m2() {
        return this.size_m2;
    }

    public int getRoomsNumber() {
        return this.roomsNumber;
    }

    public int getBathroomsNumber() {
        return this.bathroomsNumber;
    }

    public int getMaxPeopleNum() {
        return this.maxPeopleNum;
    }

    public BigDecimal getMinPricePerDay() {
        return this.minPricePerDay;
    }

    public User getOwner(){
        return this.owner;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public List<Facility> getFacilities() {
        return facilities;
    }

    public List<Restriction> getRestrictions() {
        return restrictions;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setSize_m2(int size) {
        this.size_m2 = size;
    }

    public void setRoomsNumber(int roomsNumber) {
        this.roomsNumber = roomsNumber;
    }

    public void setBathroomsNumber(int bathroomsNumber) {
        this.bathroomsNumber = bathroomsNumber;
    }

    public void setMaxPeopleNum(int maxPeopleNum) {
        this.maxPeopleNum = maxPeopleNum;
    }

    public void setMinPricePerDay(BigDecimal price) {
        this.minPricePerDay = price;
    }

    public void setOwner(User newOwner) {
        this.owner = newOwner;
    }

    @Override
    public String toString() {
        return "Cottage{" + "id=" + this.id + ", name='" + this.name + '\'' + ", size=" + this.size_m2 + ", roomsNumber=" + this.roomsNumber
                + ", bathroomsNumber=" + this.bathroomsNumber + ", maxPeopleNum=" + this.maxPeopleNum + ", price=" + this.minPricePerDay
                + ", owner: " + this.owner + ", address: " + this.address + "}";
    }

}

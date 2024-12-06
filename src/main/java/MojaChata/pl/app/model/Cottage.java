package MojaChata.pl.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

@Entity
public class Cottage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @Positive(message = "Size is mandatory")
    private int size;

    @Positive(message = "RoomsNumber is mandatory")
    private int roomsNumber;

    @Positive(message = "BathroomsNumber is mandatory")
    private int bathroomsNumber;

    @Positive(message = "MaxPeopleNum is mandatory and needs to be gt 0")
    private int maxPeopleNum;

    @Positive(message = "Price is mandatory")
    private int price;

    private long ownerId;

    // standard constructors / setters / getters / toString
    Cottage() {}

    Cottage(String name, String address, int size, int roomsNumber, int bathroomsNumber, int maxPeopleNum, int price, int ownerId ) {

      this.name = name;
      this.address = address;
      this.size = size;
      this.roomsNumber = roomsNumber;
      this.bathroomsNumber = bathroomsNumber;
      this.maxPeopleNum = maxPeopleNum;
      this.price = price;
      this.ownerId = ownerId;
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    public int getSize() {
        return this.size;
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

    public int getPrice() {
        return this.price;
    }

    public long getOwnerId(){
        return this.ownerId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setSize(int size) {
        this.size = size;
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

    public void setPrice(int price) {
        this.price = price;
    }

    public void setOwnerId(long id) {
        this.ownerId = id;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + this.id + ", name='" + this.name + '\'' + ", address='" + this.address + '\'' + ", size=" + this.size + ", roomsNumber=" + this.roomsNumber
                + ", bathroomsNumber=" + this.bathroomsNumber + ", maxPeopleNum=" + this.maxPeopleNum + ", price=" + this.price + '}';
    }

}

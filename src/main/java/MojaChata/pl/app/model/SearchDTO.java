package MojaChata.pl.app.model;

public class SearchDTO {
    private String addressCountry;
    private String addressCity;
    private String addressStreet;
    private Integer minPrice;
    private Integer maxPrice;
    private Integer minSize;
    private Integer maxSize;
    private Long ownerId;
    SearchDTO() {};

    public String getAddressCity() {return addressCity;}
    public void setAddressCity(String addressCity) {this.addressCity = addressCity;}

    public String getAddressCountry() {return addressCountry;}
    public void setAddressCountry(String addressCountry) {this.addressCountry = addressCountry;}

    public String getAddressStreet() {
        return addressStreet;
    }
    public void setAddressStreet(String address) {
        this.addressStreet = address;
    }

    public Integer getMinPrice() {
        return minPrice;
    }
    public void setMinPrice(Integer minPrice) {
        this.minPrice = minPrice;
    }

    public Integer getMaxPrice() {
        return maxPrice;
    }
    public void setMaxPrice(Integer maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Long getOwnerId() { return this.ownerId; }
    public void setOwnerId(Long ownerId) { this.ownerId = ownerId; }

    public Integer getMinSize() {
        return minSize;
    }
    public void setMinSize(Integer minSize) {
        this.minSize = minSize;
    }

    public Integer getMaxSize() {
        return maxSize;
    }
    public void setMaxSize(Integer maxSize) {
        this.maxSize = maxSize;
    }

}

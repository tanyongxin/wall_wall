package wall.pojo;

import java.math.BigDecimal;

public class School {

    private Integer id;

    private String schoolName;

    private String address;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private Boolean is_valid;

    public Boolean getIs_valid() {
        return is_valid;
    }

    public School setIs_valid(Boolean is_valid) {
        this.is_valid = is_valid;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName == null ? null : schoolName.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }


}

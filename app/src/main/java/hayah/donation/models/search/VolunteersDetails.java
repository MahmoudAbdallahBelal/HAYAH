package hayah.donation.models.search;

import com.google.gson.annotations.SerializedName;

/**
 * Created by belal on 8/18/18.
 */

public class VolunteersDetails {

    @SerializedName("name")
    private String name ;


    @SerializedName("phone")
    private String phone ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @SerializedName("phone2")

    private String phone2 ;


    @SerializedName("address")
    private String address ;


    @SerializedName("address2")
    private String address2 ;




}

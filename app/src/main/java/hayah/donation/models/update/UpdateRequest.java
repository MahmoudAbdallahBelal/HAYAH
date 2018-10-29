package hayah.donation.models.update;

import com.google.gson.annotations.SerializedName;

public class UpdateRequest {

    @SerializedName("name")
    private  String name ;

    @SerializedName("email")
    private  String email ;


    @SerializedName("phone")
    private  String phone ;

    @SerializedName("country_id")
    private  String country_id ;

    @SerializedName("state_id")
    private  String state_id ;

    @SerializedName("city_id")
    private  String city_id ;


    @SerializedName("phone2")
    private  String phone2 ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry_id() {
        return country_id;
    }

    public void setCountry_id(String country_id) {
        this.country_id = country_id;
    }

    public String getState_id() {
        return state_id;
    }

    public void setState_id(String state_id) {
        this.state_id = state_id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }

    public String getAvailable() {
        return available;
    }

    public void setAvailable(String available) {
        this.available = available;
    }

    @SerializedName("address")
    private  String address ;

    @SerializedName("age")
    private  String age ;

    @SerializedName("blood_type")
    private  String blood_type ;

    @SerializedName("available")
    private  String available ;
}

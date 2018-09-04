package hayah.hayah.models.register;

import com.google.gson.annotations.SerializedName;

/**
 * Created by belal on 8/18/18.
 */

public class RegisterRequest {

    @SerializedName("name")
    private  String name ;

    @SerializedName("age")
    private  String age ;


    @SerializedName("address")
    private  String address ;

    @SerializedName("phone")
    private  String phone ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getPhone2() {
        return phone2;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    @SerializedName("blood_type")
    private  String blood_type ;

    @SerializedName("address2")
    private  String address2 ;

    @SerializedName("phone2")
    private  String phone2 ;





}

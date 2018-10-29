package hayah.donation.models.search;

import com.google.gson.annotations.SerializedName;

/**
 * Created by belal on 8/18/18.
 */

public class SearchRequest {


    @SerializedName("country_id")
    private  String country_id ;

    @SerializedName("state_id")
    private  String state_id ;

    @SerializedName("city_id")
    private  String city_id ;

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

    @SerializedName("blood_type")

    private  String blood_type ;




    public String getBlood_type() {
        return blood_type;
    }

    public void setBlood_type(String blood_type) {
        this.blood_type = blood_type;
    }
}

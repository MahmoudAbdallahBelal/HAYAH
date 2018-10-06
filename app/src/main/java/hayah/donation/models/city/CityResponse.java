package hayah.donation.models.city;

import com.google.gson.annotations.SerializedName;

/**
 * Created by belal on 9/29/18.
 */

public class CityResponse {

    @SerializedName("id")
    private  String id ;

    @SerializedName("name_en")
    private  String name_en ;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName_en() {
        return name_en;
    }

    public void setName_en(String name_en) {
        this.name_en = name_en;
    }

    public String getName_ar() {
        return name_ar;
    }

    public void setName_ar(String name_ar) {
        this.name_ar = name_ar;
    }

    @SerializedName("name_ar")

    private  String name_ar ;
}

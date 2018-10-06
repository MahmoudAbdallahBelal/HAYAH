package hayah.donation.models.search;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by belal on 8/18/18.
 */

public class DataObjectDetails {

    @SerializedName("volunteers")
    private List<VolunteersDetails> volunteers;

    public List<VolunteersDetails> getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(List<VolunteersDetails> volunteers) {
        this.volunteers = volunteers;
    }
}

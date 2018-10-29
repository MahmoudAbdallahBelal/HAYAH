package hayah.donation.models.update;

import com.google.gson.annotations.SerializedName;

public class UpdateDataObject {

    @SerializedName("user")
    private boolean user;

    public boolean isUser() {
        return user;
    }

    public void setUser(boolean user) {
        this.user = user;
    }
}

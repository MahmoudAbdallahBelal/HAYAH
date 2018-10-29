package hayah.donation.models.update;

import com.google.gson.annotations.SerializedName;

public class UpdateResponse {

    @SerializedName("status")
    private boolean status ;

    @SerializedName("message")
    private  String message;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UpdateDataObject getData() {
        return data;
    }

    public void setData(UpdateDataObject data) {
        this.data = data;
    }

    @SerializedName("data")
    private  UpdateDataObject data;
}

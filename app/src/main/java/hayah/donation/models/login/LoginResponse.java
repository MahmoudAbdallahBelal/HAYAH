package hayah.donation.models.login;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {


    @SerializedName("error")
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @SerializedName("status")
    private String status;

    @SerializedName("message")
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserObject getData() {
        return data;
    }

    public void setData(UserObject data) {
        this.data = data;
    }

    public String getMessage() {
        return message;

    }

    public void setMessage(String message) {
        this.message = message;
    }


    @SerializedName("data")
    private UserObject data;

}

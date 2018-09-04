package hayah.hayah.models.register;

import com.google.gson.annotations.SerializedName;

/**
 * Created by belal on 8/18/18.
 */

public class RegisterResponse {

    @SerializedName("message")
    private  String message ;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



}

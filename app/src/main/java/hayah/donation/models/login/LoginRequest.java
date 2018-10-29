package hayah.donation.models.login;

import com.google.gson.annotations.SerializedName;

public class LoginRequest {
    @SerializedName("user")
    private String user;

    @SerializedName("password")
    private String password;

    public String getEmail() {
        return user;
    }

    public void setEmail(String email) {
        this.user = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

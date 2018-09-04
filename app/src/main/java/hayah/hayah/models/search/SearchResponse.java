package hayah.hayah.models.search;

import com.google.gson.annotations.SerializedName;

/**
 * Created by belal on 8/18/18.
 */

public class SearchResponse {

    public DataObjectDetails getData() {
        return data;
    }

    public void setData(DataObjectDetails data) {
        this.data = data;
    }

    @SerializedName("data")

    private  DataObjectDetails data ;
}

package ilightning.retrofithtml.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Hoàng on 6/17/2016.
 */
public class StatusPost {
    @SerializedName("status")
    @Expose
    private String status;

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
    }
}

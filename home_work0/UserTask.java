package home_work0;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserTask {

    @SerializedName("userId")
    private int userId;

    @SerializedName("id")
    private int id;

    @SerializedName("title")
    private String title;

    @SerializedName("completed")
    private boolean completed;

}

package home_work0;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class UserComment {

    @SerializedName("postId")
    private int postId;

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("body")
    private String body;

}

package home_work0;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Company {

    @SerializedName("name")
    private String nameCompany;

    @SerializedName("catchphrase")
    private String catchPhrase;

    @SerializedName("bbs")
    private String bbs;

}

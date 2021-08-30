package home_work0;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("username")
    private String userName;

    @SerializedName("email")
    private String email;

    @SerializedName("address")
    private Address address;

    @SerializedName("phone")
    private String phoneNumber;

    @SerializedName("website")
    private String website;

    @SerializedName("company")
    private Company company;

}

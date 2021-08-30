package home_work0;

import com.google.gson.annotations.SerializedName;
import lombok.*;

@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Address {

    @SerializedName("street")
    private String street;

    @SerializedName("suite")
    private String apartment;

    @SerializedName("city")
    private String city;

    @SerializedName("zipcode")
    private String zipcode;

    @SerializedName("geo")
    private Geo geo;

}
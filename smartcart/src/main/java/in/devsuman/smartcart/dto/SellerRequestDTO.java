package in.devsuman.smartcart.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SellerRequestDTO {
    private Long id;
    private String name;
    private String companyName;
    private String phoneNumber;
    private String password;
    private String email;
    private String streetAddress;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}

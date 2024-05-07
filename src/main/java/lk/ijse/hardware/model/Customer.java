package lk.ijse.hardware.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Customer {
    private String c_id;
    private String name;
    private String address;
    private int tel;
    private String email;

}

package lk.ijse.hardware.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Supplier {
    private String s_id;
    private String name;
    private String company;
    private int tel;
    private String email;

}

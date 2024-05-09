package lk.ijse.hardware.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Order {
    private String o_id;
    private String name;
    private int qty_on_hand;
    private double price;
    private String c_id;
    private String s_id;

}

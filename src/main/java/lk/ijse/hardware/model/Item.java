package lk.ijse.hardware.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Item {
    private String i_id;
    private String description;
    private double unit_price;
    private int qty_on_hand;

}

package lk.ijse.hardware.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class Order_Detail {
    private int qty_on_hand;
    private int sold_item_count;
    private Date date;
    private String o_id;
    private String i_id;

}

package lk.ijse.hardware.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order_Detail {
    private String o_id;
    private String i_id;
    private int qty;
    private double unit_price;

}

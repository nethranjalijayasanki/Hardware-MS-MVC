package lk.ijse.hardware.model.tm;

import com.jfoenix.controls.JFXButton;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class ItemTm {
    private String i_id;
    private String description;
    private double unitPrice;
    private int qtyOnHand;
    private String s_id;

}

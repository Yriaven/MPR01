package Klasy;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by pbetkows on 16.09.2017.
 */
public class OITM {

    public SimpleStringProperty ItemName = new SimpleStringProperty();
    public SimpleStringProperty ItemCode = new SimpleStringProperty();
    public SimpleIntegerProperty OnHand = new SimpleIntegerProperty();

    public String getItemName() {
        return ItemName.get();
    }


    public String getItemCode() {
        return ItemCode.get();
    }



    public Integer getOnHand() {
        return OnHand.get();
    }


}

package businessobjects;

import lombok.Data;
import utility.Services.PropertyReader;

import java.util.Objects;

/**
 * Created by user on 04.06.17.
 */
@Data
public class Item {

    private String name;
    private String price;
    private String type;

    public Item(String fileLocation) {
        PropertyReader propertyReader = new PropertyReader(fileLocation);
        this.name = propertyReader.getValue("name");
        this.price = propertyReader.getValue("price");
        this.type = propertyReader.getValue("type");

    }

    public Item() {
    }

}

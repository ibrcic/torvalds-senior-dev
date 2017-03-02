import java.awt.Image;
import java.sql.Date;

import javax.ws.rs.Produces;

@Produces("application/json")
public class Item extends ItemType{
   public Item(int itemTypeId, String itemTypeName, Image image, String manufacturer, String model) {
      super(itemTypeId, itemTypeName, image, manufacturer, model);
      // TODO Auto-generated constructor stub
   }
   int itemId;
   String serialNumber;
   int typeId;
   String department;
   Date aquireDate;
   int yellowTag;
   String procurementOrder;
   double cost;
   String assetTag;

}

/**
 * 
 */
package service;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import antlr.Token;
import pojos.Item;
import dataAccess.ConnectMaria;
/**
 * @author Noah
 *
 */

public class LabAssistantQueries {
	@GET
	@Path("/returnItem/ID/{itemId: [0-9]*}/{token}")
	@Produces("application/json")
	public Item getItem(@PathParam("itemID") int itemID, @PathParam("token") Token token){
		
		Item fetchedItem = new Item(itemID);
		try {
			fetchedItem.fetch(new ConnectMaria(token).getConn());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			// TODO Generate a Error object to return
		}
		return fetchedItem;
	}
	
	@GET
	@Path("/returnItem/SerialNumber/{serialNumber: [^/]+?/{token}")
	@Produces("application/json")
	public String getItem(@PathParam("serialNumber") String serialNumber, @PathParam("token") Token token) {
		return null;
	}
	
	@GET
	@Path("/returnItem")
	@Produces("application/json")
	public String returnItem() {
		return null;
	}
	
	@GET
	@Path("/returnItem")
	@Produces("application/json")
	public String loanItem() {
		return null;
	}
	
	@GET
	@Path("/Inventory")
	@Produces("application/json")
	public String getAvailableInventory() {
		return null;
	}
	
	
	
}

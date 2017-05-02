package com.ist.services.rest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ist.services.rest.pojo.Item;

/**
 * @author Hassan Jegan Ndow
 *
 */

@Path("items")
public class ItemService {

	ItemDao itemDao = new ItemDao();

	// MariaDB dev user access
	String username = "devJegan";
	String password = ")I(Like4Pies^";

	// Produces a list of all items
	@Path("data.json")
	@GET
	@Produces("application/json")
	public Response getAllItems() throws JSONException, SQLException {

		List<Item> itemList = itemDao.getAllItems(username, password);

		JSONArray jArray = new JSONArray();
		try {

			for (Item item : itemList) {
				JSONObject itemJSON = new JSONObject();
				itemJSON.put("idItem", item.getIdItem());
				itemJSON.put("barcode", item.getBarcode());
				itemJSON.put("itemTypeId", item.getItemTypeId());
				itemJSON.put("itemTypeName", item.getItemTypeName());
				itemJSON.put("manufacturer", item.getManufacturer());
				itemJSON.put("model", item.getModel());
				itemJSON.put("serialNumber", item.getSerialNumber());
				itemJSON.put("procurementOrder", item.getProcurementOrder());
				itemJSON.put("department", item.getDepartment());
				itemJSON.put("aquireDate", item.getAquireDate());
				itemJSON.put("yellowTag", item.getYellowTag());
				itemJSON.put("cost", item.getCost());
				itemJSON.put("assetTag", item.getAssetTag());
				itemJSON.put("damageId", item.getDamageId());
				itemJSON.put("damageName", item.getDamageName());
				itemJSON.put("damageDescription", item.getDamageDescription());
				itemJSON.put("warrentyId", item.getWarrentyId());
				itemJSON.put("warrentyName", item.getWarrentyName());
				itemJSON.put("warrentyCompany", item.getWarrentyCompany());
				itemJSON.put("warrentyDescription", item.getWarrentyDescription());
				itemJSON.put("endDate", item.getEndDate());
				itemJSON.put("severity", item.getSeverity());
				jArray.put(itemJSON);
			}
			// jObject.put("ItemList", jArray);
		} catch (JSONException jse) {
			System.out.println(jse.getMessage());
		}

		String result = jArray.toString();

		String resultFormatted = result.replaceAll("\\\\", "");
		String resultFormatted2 = resultFormatted.replaceAll("\"\\[\"", "\\[");
		String resultFormatted3 = resultFormatted2.replaceAll("\"\\]\"", "\\]");
		String resultFormatted4 = resultFormatted3.replaceAll("\\}\",\"\\{", "\\},\\{");
		String resultFormatted5 = resultFormatted4.replaceAll("\"\\{", "\\{");
		String resultFormatted6 = resultFormatted5.replaceAll("\"\\]", "\\]");

		// System.out.println(resultFormatted5);

		return Response.status(200).entity(resultFormatted6).header("Access-Control-Allow-Origin", "*").build();
	}

	// Produces JSON of a specific item by its id
	@Path("/{itemId}/data.json")
	@GET
	@Produces("application/json")
	public Response getItemById(@PathParam("itemId") long itemId) throws JSONException, SQLException {

		Item item = itemDao.getItem(itemId, username, password);

		JSONObject itemJSON = new JSONObject();
		if (!item.equals(null)) {
			itemJSON.put("idItem", item.getIdItem());
			itemJSON.put("barcode", item.getBarcode());
			itemJSON.put("itemTypeId", item.getItemTypeId());
			itemJSON.put("itemTypeName", item.getItemTypeName());
			itemJSON.put("manufacturer", item.getManufacturer());
			itemJSON.put("model", item.getModel());
			itemJSON.put("serialNumber", item.getSerialNumber());
			itemJSON.put("procurementOrder", item.getProcurementOrder());
			itemJSON.put("department", item.getDepartment());
			itemJSON.put("aquireDate", item.getAquireDate());
			itemJSON.put("yellowTag", item.getYellowTag());
			itemJSON.put("cost", item.getCost());
			itemJSON.put("assetTag", item.getAssetTag());
			itemJSON.put("damageId", item.getDamageId());
			itemJSON.put("damageName", item.getDamageName());
			itemJSON.put("damageDescription", item.getDamageDescription());
			itemJSON.put("warrentyId", item.getWarrentyId());
			itemJSON.put("warrentyName", item.getWarrentyName());
			itemJSON.put("warrentyCompany", item.getWarrentyCompany());
			itemJSON.put("warrentyDescription", item.getWarrentyDescription());
			itemJSON.put("endDate", item.getEndDate());
			itemJSON.put("severity", item.getSeverity());
		}

		String result = itemJSON.toString();
		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();

	}

	// Produces JSON of a specific item by its serial number
	@Path("/serial/{serialNumber}/data.json")
	@GET
	@Produces("application/json")
	public Response getItemBySerial(@PathParam("serialNumber") String serialNumber) throws JSONException, SQLException {

		Item item = itemDao.getItemBySerial(serialNumber, username, password);

		JSONObject itemJSON = new JSONObject();
		if (!item.equals(null)) {
			itemJSON.put("idItem", item.getIdItem());
			itemJSON.put("barcode", item.getBarcode());
			itemJSON.put("itemTypeId", item.getItemTypeId());
			itemJSON.put("itemTypeName", item.getItemTypeName());
			itemJSON.put("manufacturer", item.getManufacturer());
			itemJSON.put("model", item.getModel());
			itemJSON.put("serialNumber", item.getSerialNumber());
			itemJSON.put("procurementOrder", item.getProcurementOrder());
			itemJSON.put("department", item.getDepartment());
			itemJSON.put("aquireDate", item.getAquireDate());
			itemJSON.put("yellowTag", item.getYellowTag());
			itemJSON.put("cost", item.getCost());
			itemJSON.put("assetTag", item.getAssetTag());
			itemJSON.put("damageId", item.getDamageId());
			itemJSON.put("damageName", item.getDamageName());
			itemJSON.put("damageDescription", item.getDamageDescription());
			itemJSON.put("warrentyId", item.getWarrentyId());
			itemJSON.put("warrentyName", item.getWarrentyName());
			itemJSON.put("warrentyCompany", item.getWarrentyCompany());
			itemJSON.put("warrentyDescription", item.getWarrentyDescription());
			itemJSON.put("endDate", item.getEndDate());
			itemJSON.put("severity", item.getSeverity());
		}

		String result = itemJSON.toString();
		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();

	}

	// Produces JSON of a specific item by its barcode
	@Path("/barcode/{barcode}/data.json")
	@GET
	@Produces("application/json")
	public Response getItemByBarcode(@PathParam("barcode") String barcode) throws JSONException, SQLException {

		Item item = itemDao.getItemByBarcode(barcode, username, password);

		JSONObject itemJSON = new JSONObject();
		if (!item.equals(null)) {
			itemJSON.put("idItem", item.getIdItem());
			itemJSON.put("barcode", item.getBarcode());
			itemJSON.put("itemTypeId", item.getItemTypeId());
			itemJSON.put("itemTypeName", item.getItemTypeName());
			itemJSON.put("manufacturer", item.getManufacturer());
			itemJSON.put("model", item.getModel());
			itemJSON.put("serialNumber", item.getSerialNumber());
			itemJSON.put("procurementOrder", item.getProcurementOrder());
			itemJSON.put("department", item.getDepartment());
			itemJSON.put("aquireDate", item.getAquireDate());
			itemJSON.put("yellowTag", item.getYellowTag());
			itemJSON.put("cost", item.getCost());
			itemJSON.put("assetTag", item.getAssetTag());
			itemJSON.put("damageId", item.getDamageId());
			itemJSON.put("damageName", item.getDamageName());
			itemJSON.put("damageDescription", item.getDamageDescription());
			itemJSON.put("warrentyId", item.getWarrentyId());
			itemJSON.put("warrentyName", item.getWarrentyName());
			itemJSON.put("warrentyCompany", item.getWarrentyCompany());
			itemJSON.put("warrentyDescription", item.getWarrentyDescription());
			itemJSON.put("endDate", item.getEndDate());
			itemJSON.put("severity", item.getSeverity());
		}

		String result = itemJSON.toString();
		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();

	}

	// Produces JSON of a specific item type by its id
	@Path("types/{itemTypeId}/data.json")
	@GET
	@Produces("application/json")
	public Response getItemTypeById(@PathParam("itemTypeId") long itemTypeId) throws JSONException, SQLException {

		Item item = itemDao.getItemTypeById(itemTypeId, username, password);

		JSONObject itemJSON = new JSONObject();
		if (!item.equals(null)) {
			itemJSON.put("itemTypeId", item.getItemTypeId());
			itemJSON.put("itemTypeName", item.getItemTypeName());
			itemJSON.put("image", item.getImage());
			itemJSON.put("manufacturer", item.getManufacturer());
			itemJSON.put("model", item.getModel());
		}

		String result = itemJSON.toString();
		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();

	}

	// Produces JSON of a specific damage by its id
	@Path("damages/{damageId}/data.json")
	@GET
	@Produces("application/json")
	public Response getDamageById(@PathParam("damageId") long damageId) throws JSONException, SQLException {

		Item item = itemDao.getDamageById(damageId, username, password);

		JSONObject itemJSON = new JSONObject();
		if (!item.equals(null)) {
			itemJSON.put("damageId", item.getDamageId());
			itemJSON.put("damageName", item.getDamageName());
			itemJSON.put("damageDescription", item.getDamageDescription());
			itemJSON.put("severity", item.getSeverity());
		}

		String result = itemJSON.toString();
		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();

	}

	// Produces JSON of a specific warranty by its id
	@Path("warranties/{warrantyId}/data.json")
	@GET
	@Produces("application/json")
	public Response getWarrantyById(@PathParam("warrantyId") long warrantyId) throws JSONException, SQLException {

		Item item = itemDao.getWarrantyById(warrantyId, username, password);

		JSONObject itemJSON = new JSONObject();
		if (!item.equals(null)) {
			itemJSON.put("warrentyId", item.getWarrentyId());
			itemJSON.put("warrentyName", item.getWarrentyName());
			itemJSON.put("warrentyCompany", item.getWarrentyCompany());
			itemJSON.put("warrentyDescription", item.getWarrentyDescription());
			itemJSON.put("endDate", item.getEndDate());
		}

		String result = itemJSON.toString();
		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();

	}

	// Adds an item to the database
	@POST
	@Path("/add")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addItem(Item item) throws SQLException {
		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			if (itemDao.addItem(item, username, password) == 1) {
				jsonObject.put("status", "item added");
			} else {
				jsonObject.put("status", "query could not be made");
			}
			result = jsonObject.toString();

		}

		catch (Exception e) {
			String resultError = e.getMessage();
			jsonObject.put("status", resultError);
			result = jsonObject.toString();
			return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
		}

		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
	}

	// Adds an item type to the database
	@POST
	@Path("type/add")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addItemType(Item item) throws SQLException {
		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			if (itemDao.addItemType(item, username, password) == 1) {
				jsonObject.put("status", "item type added");
			}

			else {
				jsonObject.put("status", "query could not be made");
			}
			result = jsonObject.toString();

		}

		catch (Exception e) {
			String resultError = e.getMessage();
			jsonObject.put("status", resultError);
			result = jsonObject.toString();
			return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
		}

		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
	}

	// Adds damage to the database
	@POST
	@Path("damage/add")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addDamage(Item item) throws SQLException {
		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			if (itemDao.addDamage(item, username, password) == 1) {
				jsonObject.put("status", "damage added");
			} else {
				jsonObject.put("status", "query could not be made");
			}
			result = jsonObject.toString();

		}

		catch (Exception e) {
			String resultError = e.getMessage();
			jsonObject.put("status", resultError);
			result = jsonObject.toString();
			return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
		}

		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
	}

	// Adds warranty to the database
	@POST
	@Path("warranty/add")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addWarranty(Item item) throws SQLException {
		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			if (itemDao.addWarranty(item, username, password) == 1) {
				jsonObject.put("status", "warranty added");
			} else {
				jsonObject.put("status", "query could not be made");
			}
			result = jsonObject.toString();

		}

		catch (Exception e) {
			String resultError = e.getMessage();
			jsonObject.put("status", resultError);
			result = jsonObject.toString();
			return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
		}

		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
	}

	// Links warranty to item in the database
	@POST
	@Path("warranty/attach")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response attachWarranty(Item item) throws SQLException {
		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			if (itemDao.attachWarranty(item, username, password) == 1) {
				jsonObject.put("status", "item now has warranty");
			}

			else {
				jsonObject.put("status", "query could not be made");
			}
			result = jsonObject.toString();

		}

		catch (Exception e) {
			String resultError = e.getMessage();
			jsonObject.put("status", resultError);
			result = jsonObject.toString();
			return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
		}

		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
	}

	// Links damage to item in the database
	@POST
	@Path("damage/attach")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response attachDamage(Item item) throws SQLException {
		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			if (itemDao.attachDamage(item, username, password) == 1) {
				jsonObject.put("status", "item now has damage");
			}

			else {
				jsonObject.put("status", "query could not be made");
			}
			result = jsonObject.toString();

		}

		catch (Exception e) {
			String resultError = e.getMessage();
			jsonObject.put("status", resultError);
			result = jsonObject.toString();
			return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
		}

		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
	}

	// Deletes item type in the database
	@DELETE
	@Path("type/delete")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delteItemType(Item item) throws SQLException {
		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			if (itemDao.deleteItemType(item, username, password) == 1) {
				jsonObject.put("status", "item type has been deleted");
			}

			else {
				jsonObject.put("status", "query could not be made");
			}
			result = jsonObject.toString();

		}

		catch (Exception e) {
			String resultError = e.getMessage();
			jsonObject.put("status", resultError);
			result = jsonObject.toString();
			return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
		}

		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
	}

	// Unlinks damage to item in the database
	@DELETE
	@Path("damage/detach")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response detachDamage(Item item) throws SQLException {
		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			if (itemDao.detachDamage(item, username, password) == 1) {
				jsonObject.put("status", "item no longer has damage");
			}

			else {
				jsonObject.put("status", "query could not be made");
			}
			result = jsonObject.toString();

		}

		catch (Exception e) {
			String resultError = e.getMessage();
			jsonObject.put("status", resultError);
			result = jsonObject.toString();
			return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
		}

		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
	}

	// Unlinks warranty to item in the database
	@DELETE
	@Path("warranty/detach")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response detachWarranty(Item item) throws SQLException {
		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			if (itemDao.detachWarranty(item, username, password) == 1) {
				jsonObject.put("status", "item no longer has warranty");
			}

			else {
				jsonObject.put("status", "query could not be made");
			}
			result = jsonObject.toString();

		}

		catch (Exception e) {
			String resultError = e.getMessage();
			jsonObject.put("status", resultError);
			result = jsonObject.toString();
			return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
		}

		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
	}

	// updates an Item in the database
	@PUT
	@Path("/update")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateItem(Item pItem) throws SQLException {

		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			itemDao.updateItem(pItem, username, password);
			jsonObject.put("status", "item updated");
			result = jsonObject.toString();

		}

		catch (Exception e) {
			String resultError = e.getMessage();
			jsonObject.put("status", resultError);
			result = jsonObject.toString();
			return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
		}

		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
	}

	// updates an Item Type in the database
	@PUT
	@Path("type/update")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateItemType(Item pItem) throws SQLException {

		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			itemDao.updateItemType(pItem, username, password);
			jsonObject.put("status", "item type updated");
			result = jsonObject.toString();

		}

		catch (Exception e) {
			String resultError = e.getMessage();
			jsonObject.put("status", resultError);
			result = jsonObject.toString();
			return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
		}

		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
	}

	// updates a Damage in the database
	@PUT
	@Path("damage/update")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateDamage(Item pItem) throws SQLException {

		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			itemDao.updateDamage(pItem, username, password);
			jsonObject.put("status", "damage updated");
			result = jsonObject.toString();

		}

		catch (Exception e) {
			String resultError = e.getMessage();
			jsonObject.put("status", resultError);
			result = jsonObject.toString();
			return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
		}

		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
	}

	// updates a Warranty in the database
	@PUT
	@Path("warranty/update")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateWarranty(Item pItem) throws SQLException {

		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			itemDao.updateWarranty(pItem, username, password);
			jsonObject.put("status", "warranty updated");
			result = jsonObject.toString();

		}

		catch (Exception e) {
			String resultError = e.getMessage();
			jsonObject.put("status", resultError);
			result = jsonObject.toString();
			return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
		}

		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
	}

	@PUT
	@Path("/retire/{username}-{password}/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response retireItem(Item itemTest, @PathParam("username") String username,
			@PathParam("password") String password) throws SQLException {

		String output = "Retired";
		itemDao.retireItem(itemTest, username, password);

		return Response.status(200).entity(output).header("Access-Control-Allow-Origin", "*").build();
	}

	// Checks to see if the web service can successfully connect to the database
	@Path("/test")
	@GET
	@Produces("application/json")
	public Response getResponse() throws JSONException, SQLException {
		String result = "nothing happened";
		Connection con = null;
		PreparedStatement pstmt = null;
		JSONObject jsonObject = new JSONObject();
		try {
			ConnectDb connectDb = new ConnectDb(username, password);
			con = connectDb.getConn();
			if (con != null) {
				jsonObject.put("status", "Connected to database");
				result = jsonObject.toString();

			} else {
				jsonObject.put("status", "Did not connect to database");
				result = jsonObject.toString();
			}

		} catch (SQLException e) {
			result = e.getMessage();
			System.out.println(result);
			return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
		} catch (Exception e) {
			result = e.getMessage();
			return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
		} finally {

			if (pstmt != null) {

				pstmt.close();

			}

			if (con != null) {
				con.close();
			}

		}

		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
	}

	// Produces a list of all damages
	@Path("damages/data.json")
	@GET
	@Produces("application/json")
	public Response getDamages() throws JSONException, SQLException {

		List<Item> itemList = itemDao.getDamages(username, password);

		JSONArray jArray = new JSONArray();
		try {

			for (Item item : itemList) {
				JSONObject itemJSON = new JSONObject();
				itemJSON.put("damageId", item.getDamageId());
				itemJSON.put("damageName", item.getDamageName());
				itemJSON.put("damageDescription", item.getDamageDescription());
				itemJSON.put("severity", item.getSeverity());
				jArray.put(itemJSON);
			}
			// jObject.put("ItemList", jArray);
		} catch (JSONException jse) {
			System.out.println(jse.getMessage());
		}

		String result = jArray.toString();

		String resultFormatted = result.replaceAll("\\\\", "");
		String resultFormatted2 = resultFormatted.replaceAll("\"\\[\"", "\\[");
		String resultFormatted3 = resultFormatted2.replaceAll("\"\\]\"", "\\]");
		String resultFormatted4 = resultFormatted3.replaceAll("\\}\",\"\\{", "\\},\\{");
		String resultFormatted5 = resultFormatted4.replaceAll("\"\\{", "\\{");
		String resultFormatted6 = resultFormatted5.replaceAll("\"\\]", "\\]");

		// System.out.println(resultFormatted5);

		return Response.status(200).entity(resultFormatted6).header("Access-Control-Allow-Origin", "*").build();
	}

	// Produces JSON of a list of damages belonging to a specific item
	@Path("damages/affectedItems/{itemId}/data.json")
	@GET
	@Produces("application/json")
	public Response getDamagesByItem(@PathParam("itemId") long itemId) throws JSONException, SQLException {

		List<Item> damagesList = itemDao.getDamagesByItem(itemId, username, password);

		JSONArray jArray = new JSONArray();
		try {

			for (Item damage : damagesList) {
				JSONObject userJSON = new JSONObject();
				userJSON.put("damageId", damage.getDamageId());
				jArray.put(userJSON);
			}
			// jObject.put("ItemList", jArray);
		} catch (JSONException jse) {
			System.out.println(jse.getMessage());
		}

		String result = jArray.toString();

		String resultFormatted = result.replaceAll("\\\\", "");
		String resultFormatted2 = resultFormatted.replaceAll("\"\\[\"", "\\[");
		String resultFormatted3 = resultFormatted2.replaceAll("\"\\]\"", "\\]");
		String resultFormatted4 = resultFormatted3.replaceAll("\\}\",\"\\{", "\\},\\{");
		String resultFormatted5 = resultFormatted4.replaceAll("\"\\{", "\\{");
		String resultFormatted6 = resultFormatted5.replaceAll("\"\\]", "\\]");

		// System.out.println(resultFormatted5);

		return Response.status(200).entity(resultFormatted6).header("Access-Control-Allow-Origin", "*").build();

	}

	// Produces JSON of a list of damages belonging to a specific item
	@Path("warranties/affectedItems/{itemId}/data.json")
	@GET
	@Produces("application/json")
	public Response getWarrantiesByItem(@PathParam("itemId") long itemId) throws JSONException, SQLException {

		List<Item> warrantiesList = itemDao.getWarrantiesByItem(itemId, username, password);

		JSONArray jArray = new JSONArray();
		try {

			for (Item warranty : warrantiesList) {
				JSONObject userJSON = new JSONObject();
				userJSON.put("warrantyId", warranty.getWarrentyId());
				jArray.put(userJSON);
			}
			// jObject.put("ItemList", jArray);
		} catch (JSONException jse) {
			System.out.println(jse.getMessage());
		}

		String result = jArray.toString();

		String resultFormatted = result.replaceAll("\\\\", "");
		String resultFormatted2 = resultFormatted.replaceAll("\"\\[\"", "\\[");
		String resultFormatted3 = resultFormatted2.replaceAll("\"\\]\"", "\\]");
		String resultFormatted4 = resultFormatted3.replaceAll("\\}\",\"\\{", "\\},\\{");
		String resultFormatted5 = resultFormatted4.replaceAll("\"\\{", "\\{");
		String resultFormatted6 = resultFormatted5.replaceAll("\"\\]", "\\]");

		// System.out.println(resultFormatted5);

		return Response.status(200).entity(resultFormatted6).header("Access-Control-Allow-Origin", "*").build();

	}

	// Produces a list of all items-damages
	@Path("damages/affectedItems/data.json")
	@GET
	@Produces("application/json")
	public Response getDamagedItems() throws JSONException, SQLException {

		List<Item> itemList = itemDao.getItemsDamages(username, password);

		JSONArray jArray = new JSONArray();
		try {

			for (Item item : itemList) {
				JSONObject itemJSON = new JSONObject();
				itemJSON.put("idItem", item.getIdItem());
				itemJSON.put("damageId", item.getDamageId());
				jArray.put(itemJSON);
			}
			// jObject.put("ItemList", jArray);
		} catch (JSONException jse) {
			System.out.println(jse.getMessage());
		}

		String result = jArray.toString();

		String resultFormatted = result.replaceAll("\\\\", "");
		String resultFormatted2 = resultFormatted.replaceAll("\"\\[\"", "\\[");
		String resultFormatted3 = resultFormatted2.replaceAll("\"\\]\"", "\\]");
		String resultFormatted4 = resultFormatted3.replaceAll("\\}\",\"\\{", "\\},\\{");
		String resultFormatted5 = resultFormatted4.replaceAll("\"\\{", "\\{");
		String resultFormatted6 = resultFormatted5.replaceAll("\"\\]", "\\]");

		// System.out.println(resultFormatted5);

		return Response.status(200).entity(resultFormatted6).header("Access-Control-Allow-Origin", "*").build();
	}

	// Produces a list of all items-damages
	@Path("warranties/affectedItems/data.json")
	@GET
	@Produces("application/json")
	public Response getWarrantiedItems() throws JSONException, SQLException {

		List<Item> itemList = itemDao.getItemsWarranties(username, password);

		JSONArray jArray = new JSONArray();
		try {

			for (Item item : itemList) {
				JSONObject itemJSON = new JSONObject();
				itemJSON.put("idItem", item.getIdItem());
				itemJSON.put("warrantyId", item.getWarrentyId());
				jArray.put(itemJSON);
			}
			// jObject.put("ItemList", jArray);
		} catch (JSONException jse) {
			System.out.println(jse.getMessage());
		}

		String result = jArray.toString();

		String resultFormatted = result.replaceAll("\\\\", "");
		String resultFormatted2 = resultFormatted.replaceAll("\"\\[\"", "\\[");
		String resultFormatted3 = resultFormatted2.replaceAll("\"\\]\"", "\\]");
		String resultFormatted4 = resultFormatted3.replaceAll("\\}\",\"\\{", "\\},\\{");
		String resultFormatted5 = resultFormatted4.replaceAll("\"\\{", "\\{");
		String resultFormatted6 = resultFormatted5.replaceAll("\"\\]", "\\]");

		// System.out.println(resultFormatted5);

		return Response.status(200).entity(resultFormatted6).header("Access-Control-Allow-Origin", "*").build();
	}

	// Produces a list of all warranties
	@Path("warranties/data.json")
	@GET
	@Produces("application/json")
	public Response getWarranties() throws JSONException, SQLException {

		List<Item> itemList = itemDao.getWarranties(username, password);

		JSONArray jArray = new JSONArray();
		try {

			for (Item item : itemList) {
				JSONObject itemJSON = new JSONObject();
				itemJSON.put("warrentyId", item.getWarrentyId());
				itemJSON.put("warrentyName", item.getWarrentyName());
				itemJSON.put("warrentyCompany", item.getWarrentyCompany());
				itemJSON.put("warrentyDescription", item.getWarrentyDescription());
				itemJSON.put("endDate", item.getEndDate());
				jArray.put(itemJSON);
			}
			// jObject.put("ItemList", jArray);
		} catch (JSONException jse) {
			System.out.println(jse.getMessage());
		}

		String result = jArray.toString();

		String resultFormatted = result.replaceAll("\\\\", "");
		String resultFormatted2 = resultFormatted.replaceAll("\"\\[\"", "\\[");
		String resultFormatted3 = resultFormatted2.replaceAll("\"\\]\"", "\\]");
		String resultFormatted4 = resultFormatted3.replaceAll("\\}\",\"\\{", "\\},\\{");
		String resultFormatted5 = resultFormatted4.replaceAll("\"\\{", "\\{");
		String resultFormatted6 = resultFormatted5.replaceAll("\"\\]", "\\]");

		// System.out.println(resultFormatted5);

		return Response.status(200).entity(resultFormatted6).header("Access-Control-Allow-Origin", "*").build();
	}

	// web doc
	// Produces a list of all item types
	@Path("types/data.json")
	@GET
	@Produces("application/json")
	public Response getItemTypes() throws JSONException, SQLException {

		List<Item> itemList = itemDao.getItemTypes(username, password);

		JSONArray jArray = new JSONArray();
		try {

			for (Item item : itemList) {
				JSONObject itemJSON = new JSONObject();
				itemJSON.put("itemTypeId", item.getItemTypeId());
				itemJSON.put("itemTypeName", item.getItemTypeName());
				itemJSON.put("image", item.getImage());
				itemJSON.put("manufacturer", item.getManufacturer());
				itemJSON.put("model", item.getModel());
				jArray.put(itemJSON);
			}
			// jObject.put("ItemList", jArray);
		} catch (JSONException jse) {
			System.out.println(jse.getMessage());
		}

		String result = jArray.toString();

		String resultFormatted = result.replaceAll("\\\\", "");
		String resultFormatted2 = resultFormatted.replaceAll("\"\\[\"", "\\[");
		String resultFormatted3 = resultFormatted2.replaceAll("\"\\]\"", "\\]");
		String resultFormatted4 = resultFormatted3.replaceAll("\\}\",\"\\{", "\\},\\{");
		String resultFormatted5 = resultFormatted4.replaceAll("\"\\{", "\\{");
		String resultFormatted6 = resultFormatted5.replaceAll("\"\\]", "\\]");

		// System.out.println(resultFormatted5);

		return Response.status(200).entity(resultFormatted6).header("Access-Control-Allow-Origin", "*").build();
	}

}

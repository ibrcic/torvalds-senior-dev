package com.ist.services.rest;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.Consumes;
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

	//left blank for security purposes
	String username = "";
	String password = "";

	// Produces a list of all items
	@Path("data.json")
	@GET
	@Produces("application/json")
	public Response getAllItems() throws JSONException, SQLException {

		List<Item> itemList = itemDao.getAllItems(username, password);

		JSONObject jObject = new JSONObject();
		JSONArray jArray = new JSONArray();
		try {

			for (Item item : itemList) {
				JSONObject itemJSON = new JSONObject();
				itemJSON.put("idItem", item.getIdItem());
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

		return Response.status(200).entity(resultFormatted6).build();
	}

	// Produces JSON of a specific item
	@Path("/{itemId}/data.json")
	@GET
	@Produces("application/json")
	public Response getItemById(@PathParam("itemId") long itemId) throws JSONException, SQLException {

		Item item = itemDao.getItem(itemId, username, password);

		JSONObject itemJSON = new JSONObject();
		if (!item.equals(null)) {
			itemJSON.put("idItem", item.getIdItem());
			itemJSON.put("itemTypeId", item.getItemTypeId());
			itemJSON.put("itemTypeName", item.getItemTypeName());
			itemJSON.put("manufacturer", item.getManufacturer());
			itemJSON.put("model", item.getModel());
			itemJSON.put("serialNumber", item.getSerialNumber());
			itemJSON.put("procurementOrder", item.getProcurementOrder());
			itemJSON.put("typeId", item.getTypeId());
			itemJSON.put("department", item.getDepartment());
			itemJSON.put("aquireDate", item.getAquireDate());
			itemJSON.put("yellowTag", item.getYellowTag());
			itemJSON.put("cost", item.getCost());
			itemJSON.put("assetTag", item.getAssetTag());
		}

		String result = itemJSON.toString();
		return Response.status(200).entity(result).build();

	}

	// TBD
	@Path("/serial/{serialNumber}/{username}-{password}/")
	@GET
	@Produces("application/json")
	public Response getItem(@PathParam("serialNumber") long serialNumber) throws JSONException, SQLException {

		Item item = itemDao.getItem(serialNumber, username, password);

		JSONObject itemJSON = new JSONObject();
		if (!item.equals(null)) {
			itemJSON.put("idItem", item.getIdItem());
			itemJSON.put("itemTypeId", item.getIdItem());
			itemJSON.put("itemTypeName", item.getItemTypeName());
			itemJSON.put("manufacturer", item.getManufacturer());
			itemJSON.put("model", item.getModel());
			itemJSON.put("serialNumber", item.getSerialNumber());
			itemJSON.put("typeId", item.getTypeId());
			itemJSON.put("department", item.getDepartment());
			itemJSON.put("aquireDate", item.getAquireDate());
			itemJSON.put("yellowTag", item.getYellowTag());
			itemJSON.put("procurementOrder", item.getProcurementOrder());
			itemJSON.put("cost", item.getCost());
			itemJSON.put("assetTag", item.getAssetTag());
		}

		String result = itemJSON.toString();
		return Response.status(200).entity(result).build();

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
			itemDao.addItem(item, username, password);
			jsonObject.put("status", "item added");
			result = jsonObject.toString();

		}

		catch (Exception e) {
			String resultError = e.getMessage();
			jsonObject.put("status", resultError);
			result = jsonObject.toString();
			return Response.status(200).entity(result).build();
		}

		return Response.status(200).entity(result).build();
	}

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
			return Response.status(200).entity(result).build();
		}

		return Response.status(200).entity(result).build();
	}

	@PUT
	@Path("/retire/{username}-{password}/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response retireItem(Item itemTest, @PathParam("username") String username,
			@PathParam("password") String password) throws SQLException {

		String output = "Retired";
		itemDao.retireItem(itemTest, username, password);

		return Response.status(200).entity(output).build();
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
			return Response.status(200).entity(result).build();
		} catch (Exception e) {
			result = e.getMessage();
			return Response.status(200).entity(result).build();
		} finally {

			if (pstmt != null) {

				pstmt.close();

			}

			if (con != null) {
				con.close();
			}

		}

		return Response.status(200).entity(result).build();
	}

}

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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.ist.services.rest.pojo.Item;
//import org.json.simple.JSONObject;

/**
 * @author Hassan Jegan Ndow
 *
 */

@Path("items")
public class ItemService {

	ItemDao itemDao = new ItemDao();

	// @Path("/{username}-{password}/")
	@GET
	@Produces("application/json")
	public Response getAllItems(@PathParam("username") String username, @PathParam("password") String password)
			throws JSONException, SQLException {
		username = "devJegan";
		password = ")I(Like4Pies^";
		List<Item> itemList = itemDao.getAllItems(username, password);

		JSONObject jObject = new JSONObject();
		try {
			JSONArray jArray = new JSONArray();
			for (Item item : itemList) {
				JSONObject itemJSON = new JSONObject();
				itemJSON.put("itemTypeId", item.getItemId());
				itemJSON.put("itemTypeName", item.getName());
				itemJSON.put("image", item.getImage());
				itemJSON.put("manufacturer", item.getManufacturer());
				itemJSON.put("model", item.getModel());
				itemJSON.put("serialNumber", item.getSerialNumber());
				itemJSON.put("typeId", item.getType());
				itemJSON.put("department", item.getDepartment());
				itemJSON.put("acquireDate", item.getAquireDate());
				itemJSON.put("yellowTag", item.getYellowTag());
				itemJSON.put("procOrder", item.getProcurementOrder());
				itemJSON.put("cost", item.getCost());
				itemJSON.put("assetTag", item.getAssetTag());
				jArray.put(itemJSON);
			}
			jObject.put("ItemList", jArray);
		} catch (JSONException jse) {
			System.out.println(jse.getMessage());
		}

		String result = jObject.toString();

		String resultFormatted = result.replaceAll("\\\\", "");
		String resultFormatted2 = resultFormatted.replaceAll("\"\\[\"", "\\[");
		String resultFormatted3 = resultFormatted2.replaceAll("\"\\]\"", "\\]");
		String resultFormatted4 = resultFormatted3.replaceAll("\\}\",\"\\{", "\\},\\{");

		System.out.println(resultFormatted4);

		return Response.status(200).entity(resultFormatted4).build();
	}

	@Path("/sample")
	@GET
	@Produces("application/json")
	public Response getSample() throws JSONException {

		String result = "";
		try {
			JSONObject jsonObject = new JSONObject();
			JSONArray jArray = new JSONArray();

			JSONObject itemJSON = new JSONObject();

			itemJSON.put("idItem", 237657);
			itemJSON.put("barcode", "null");
			itemJSON.put("serialNumber", "Z3ASEQ423");
			itemJSON.put("typeId", 1);
			itemJSON.put("department", "ISTE");
			itemJSON.put("acquireDate", "2016-11-11");
			itemJSON.put("yellowTag", 923756);
			itemJSON.put("procOrder", "procurement order info");
			itemJSON.put("cost", 599.99);
			itemJSON.put("assetTag", "asset tag info");

			itemJSON.put("itemTypeName", "iPhone");
			itemJSON.put("image", "null");
			itemJSON.put("manufacturer", "Apple");
			itemJSON.put("model", "6s");

			jArray.put(itemJSON);

			JSONObject itemJSON1 = new JSONObject();

			itemJSON1.put("idItem", 937657);
			itemJSON1.put("barcode", "null");
			itemJSON1.put("serialNumber", "D3ASFQ422");
			itemJSON1.put("typeId", 1);
			itemJSON1.put("department", "ISTE");
			itemJSON1.put("acquireDate", "2016-12-11");
			itemJSON1.put("yellowTag", 923756);
			itemJSON1.put("procOrder", "procurement order info");
			itemJSON1.put("cost", 599.99);
			itemJSON1.put("assetTag", "asset tag info");

			itemJSON1.put("itemTypeName", "iPhone");
			itemJSON1.put("image", "null");
			itemJSON1.put("manufacturer", "Apple");
			itemJSON1.put("model", "5s");

			jArray.put(itemJSON1);
			jsonObject.put("ItemList", jArray);

			result = jsonObject.toString();
		} catch (JSONException jse) {
			System.out.println(jse.getMessage());
		}

		String resultFormatted = result.replaceAll("\\\\", "");
		String resultFormatted2 = resultFormatted.replaceAll("\"\\[\"", "\\[");
		String resultFormatted3 = resultFormatted2.replaceAll("\"\\]\"", "\\]");
		String resultFormatted4 = resultFormatted3.replaceAll("\\}\",\"\\{", "\\},\\{");

		return Response.status(200).entity(resultFormatted4).build();

	}

	@Path("/test")
	@GET
	@Produces("application/json")
	public Response getResponse(@QueryParam("username") String username) throws JSONException, SQLException {
		String result = "nothing happened";
		Connection con = null;
		PreparedStatement pstmt = null;
		JSONObject jsonObject = new JSONObject();
		String password = "\")I(Like4Pies^\"";

		// String username = "devJegan";
		// String password = ")I(Like4Pies^";

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
			jsonObject.put("status", e.getMessage().toString());
			result = jsonObject.toString();
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

	@Path("/{itemId}/{username}-{password}/")
	@GET
	@Produces("application/json")
	public Response getItemById(@PathParam("itemId") long itemId, @PathParam("username") String username,
			@PathParam("password") String password) throws JSONException, SQLException {

		Item item = itemDao.getItem(itemId, username, password);

		JSONObject itemJSON = new JSONObject();
		if (!item.equals(null)) {
			// itemJSON.put("id", itemId);
			// itemJSON.put("name", item.getName());
			// itemJSON.put("barcode", item.getBarcode());
			// itemJSON.put("serialNumber", item.getSerialNumber());
			// itemJSON.put("description", item.getDescription());
			// itemJSON.put("image", item.getImage());
			// itemJSON.put("department", item.getDepartment());
			// itemJSON.put("acquireDate", item.getAcquireDate());
			// itemJSON.put("manufacturer", item.getManufacturer());
			// itemJSON.put("model", item.getModel());
			// itemJSON.put("yellowTag", item.getYellowTag());
			// itemJSON.put("procOrder", item.getProcOrder());
			// itemJSON.put("cost", item.getCost());
			// itemJSON.put("type", item.getType());
			// itemJSON.put("assetTag", item.getAssetTag());
			// itemJSON.put("location", item.getLocation());
			// itemJSON.put("waitList", item.getWaitList());
		}

		String result = itemJSON.toString();
		return Response.status(200).entity(result).build();

	}

	@Path("/serial/{serialNumber}/{username}-{password}/")
	@GET
	@Produces("application/json")
	public Response getItem(@PathParam("serialNumber") long serialNumber) throws JSONException {

		/*
		 * if(){ jsonObject.put("name", name); jsonObject.put("id", id);
		 * jsonObject.put("barcode", barcode); jsonObject.put("serialNumber",
		 * serialNumber); jsonObject.put("description", description);
		 * jsonObject.put("image", image); jsonObject.put("department",
		 * department); jsonObject.put("acquireDate", acquireDate);
		 * jsonObject.put("manufacturer", manufacturer); jsonObject.put("model",
		 * model); jsonObject.put("yellowTag", yellowTag);
		 * jsonObject.put("procOrder", procOrder); jsonObject.put("cost", cost);
		 * jsonObject.put("assetTag", assetTag); jsonObject.put("location",
		 * location); jsonObject.put("waitList", waitList);
		 * 
		 * String result = jsonObject.toString(); return
		 * Response.status(200).entity(result).build(); }
		 */

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("serialNumber", serialNumber);
		String result = jsonObject.toString();
		return Response.status(200).entity(result).build();

	}

	@POST
	@Path("/add/{username}-{password}/")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addItem(Item itemTest, @PathParam("username") String username,
			@PathParam("password") String password) throws SQLException {

		String output = "Added";

		itemDao.addItem(itemTest, username, password);

		return Response.status(200).entity(output).build();
	}

	@PUT
	@Path("/update/{username}-{password}/")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateItem(Item itemTest, @PathParam("username") String username,
			@PathParam("password") String password) throws SQLException {

		String output = "Updated";
		itemDao.updateItem(itemTest, username, password);

		return Response.status(200).entity(output).build();
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

}

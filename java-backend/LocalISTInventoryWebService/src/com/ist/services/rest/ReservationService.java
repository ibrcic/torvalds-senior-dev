package com.ist.services.rest;

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

import com.ist.services.rest.pojo.Reservation;

@Path("reservations")
public class ReservationService {

	ReservationDao reservationDao = new ReservationDao();

	String username = "devJegan";
	String password = ")I(Like4Pies^";

	// Produces a list of all reservations
	@Path("data.json")
	@GET
	@Produces("application/json")
	public Response getAllReservations() throws JSONException, SQLException {

		List<Reservation> reservationList = reservationDao.getAllReservations(username, password);

		JSONArray jArray = new JSONArray();
		try {

			for (Reservation reservation : reservationList) {
				JSONObject reservationJSON = new JSONObject();
				reservationJSON.put("reservationId", reservation.getReservationId());
				reservationJSON.put("userId", reservation.getUserId());
				reservationJSON.put("rentalId", reservation.getRentalId());
				reservationJSON.put("itemTypeId", reservation.getItemTypeId());
				reservationJSON.put("signature", reservation.getSignature());
				reservationJSON.put("startDate", reservation.getStartDate());
				reservationJSON.put("endDate", reservation.getEndDate());
				reservationJSON.put("itemId", reservation.getItemId());
				reservationJSON.put("barcode", reservation.getBarcode());
				reservationJSON.put("itemTypeName", reservation.getItemTypeName());
				reservationJSON.put("manufacturer", reservation.getManufacturer());
				reservationJSON.put("model", reservation.getModel());
				reservationJSON.put("serialNumber", reservation.getSerialNumber());
				reservationJSON.put("procurementOrder", reservation.getProcurementOrder());
				reservationJSON.put("typeId", reservation.getTypeId());
				reservationJSON.put("department", reservation.getDepartment());
				reservationJSON.put("aquireDate", reservation.getAquireDate());
				reservationJSON.put("yellowTag", reservation.getYellowTag());
				reservationJSON.put("cost", reservation.getCost());
				reservationJSON.put("assetTag", reservation.getAssetTag());
				reservationJSON.put("damageId", reservation.getDamageId());
				reservationJSON.put("damageName", reservation.getDamageName());
				reservationJSON.put("damageDescription", reservation.getDamageDescription());
				reservationJSON.put("warrentyId", reservation.getWarrentyId());
				reservationJSON.put("warrentyName", reservation.getWarrentyName());
				reservationJSON.put("warrentyCompany", reservation.getWarrentyCompany());
				reservationJSON.put("warrentyDescription", reservation.getWarrentyDescription());
				reservationJSON.put("warrentyEndDate", reservation.getEndDate());
				reservationJSON.put("severity", reservation.getSeverity());
				jArray.put(reservationJSON);
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

	// Produces a list of all rentals
	@Path("rentals/data.json")
	@GET
	@Produces("application/json")
	public Response getRentals() throws JSONException, SQLException {

		List<Reservation> rentalList = reservationDao.getRentals(username, password);

		JSONArray jArray = new JSONArray();
		try {

			for (Reservation rental : rentalList) {
				JSONObject reservationJSON = new JSONObject();
				reservationJSON.put("userId", rental.getUserId());
				reservationJSON.put("rentalId", rental.getRentalId());
				reservationJSON.put("signature", rental.getSignature());
				reservationJSON.put("startDate", rental.getStartDate());
				reservationJSON.put("endDate", rental.getEndDate());
				reservationJSON.put("itemTypeId", rental.getItemTypeId());
				reservationJSON.put("signature", rental.getSignature());
				reservationJSON.put("startDate", rental.getStartDate());
				reservationJSON.put("endDate", rental.getEndDate());
				reservationJSON.put("itemId", rental.getItemId());
				reservationJSON.put("barcode", rental.getBarcode());
				reservationJSON.put("itemTypeName", rental.getItemTypeName());
				reservationJSON.put("manufacturer", rental.getManufacturer());
				reservationJSON.put("model", rental.getModel());
				reservationJSON.put("serialNumber", rental.getSerialNumber());
				reservationJSON.put("procurementOrder", rental.getProcurementOrder());
				reservationJSON.put("typeId", rental.getTypeId());
				reservationJSON.put("department", rental.getDepartment());
				reservationJSON.put("aquireDate", rental.getAquireDate());
				reservationJSON.put("yellowTag", rental.getYellowTag());
				reservationJSON.put("cost", rental.getCost());
				reservationJSON.put("assetTag", rental.getAssetTag());

				jArray.put(reservationJSON);
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

	// Produces a list of all rentals
	@Path("checkouts/data.json")
	@GET
	@Produces("application/json")
	public Response getCheckouts() throws JSONException, SQLException {

		List<Reservation> checkoutList = reservationDao.getCheckouts(username, password);

		JSONArray jArray = new JSONArray();
		try {

			for (Reservation checkout : checkoutList) {
				JSONObject reservationJSON = new JSONObject();
				reservationJSON.put("userId", checkout.getUserId());
				reservationJSON.put("rentalId", checkout.getRentalId());
				reservationJSON.put("startDate", checkout.getStartDate());
				reservationJSON.put("endDate", checkout.getEndDate());
				reservationJSON.put("itemId", checkout.getItemId());

				jArray.put(reservationJSON);
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

	// Produces JSON of a specific reservation
	@Path("/{reservationId}/data.json")
	@GET
	@Produces("application/json")
	public Response getItemById(@PathParam("reservationId") long reservationId) throws JSONException, SQLException {

		Reservation reservation = reservationDao.getReservation(reservationId, username, password);

		JSONObject reservationJSON = new JSONObject();
		if (!reservation.equals(null)) {
			reservationJSON.put("reservationId", reservation.getReservationId());
			reservationJSON.put("userId", reservation.getUserId());
			reservationJSON.put("rentalId", reservation.getRentalId());
			reservationJSON.put("itemTypeId", reservation.getItemTypeId());
			reservationJSON.put("signature", reservation.getSignature());
			reservationJSON.put("startDate", reservation.getStartDate());
			reservationJSON.put("endDate", reservation.getEndDate());
			reservationJSON.put("itemId", reservation.getItemId());
			reservationJSON.put("barcode", reservation.getBarcode());
			reservationJSON.put("itemTypeName", reservation.getItemTypeName());
			reservationJSON.put("manufacturer", reservation.getManufacturer());
			reservationJSON.put("model", reservation.getModel());
			reservationJSON.put("serialNumber", reservation.getSerialNumber());
			reservationJSON.put("procurementOrder", reservation.getProcurementOrder());
			reservationJSON.put("typeId", reservation.getTypeId());
			reservationJSON.put("department", reservation.getDepartment());
			reservationJSON.put("aquireDate", reservation.getAquireDate());
			reservationJSON.put("yellowTag", reservation.getYellowTag());
			reservationJSON.put("cost", reservation.getCost());
			reservationJSON.put("assetTag", reservation.getAssetTag());
			reservationJSON.put("damageId", reservation.getDamageId());
			reservationJSON.put("damageName", reservation.getDamageName());
			reservationJSON.put("damageDescription", reservation.getDamageDescription());
			reservationJSON.put("warrentyId", reservation.getWarrentyId());
			reservationJSON.put("warrentyName", reservation.getWarrentyName());
			reservationJSON.put("warrentyCompany", reservation.getWarrentyCompany());
			reservationJSON.put("warrentyDescription", reservation.getWarrentyDescription());
			reservationJSON.put("warrentyEndDate", reservation.getEndDate());
			reservationJSON.put("severity", reservation.getSeverity());
		}

		String result = reservationJSON.toString();
		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();

	}

	// Produces JSON of a specific rental
	@Path("rentals/{rentalId}/data.json")
	@GET
	@Produces("application/json")
	public Response getRentalById(@PathParam("rentalId") long rentalId) throws JSONException, SQLException {

		Reservation reservation = reservationDao.getRental(rentalId, username, password);

		JSONObject reservationJSON = new JSONObject();
		if (!reservation.equals(null)) {
			reservationJSON.put("userId", reservation.getUserId());
			reservationJSON.put("rentalId", reservation.getRentalId());
			reservationJSON.put("itemTypeId", reservation.getItemTypeId());
			reservationJSON.put("signature", reservation.getSignature());
			reservationJSON.put("startDate", reservation.getStartDate());
			reservationJSON.put("endDate", reservation.getEndDate());
			reservationJSON.put("itemId", reservation.getItemId());
			reservationJSON.put("barcode", reservation.getBarcode());
			reservationJSON.put("itemTypeName", reservation.getItemTypeName());
			reservationJSON.put("manufacturer", reservation.getManufacturer());
			reservationJSON.put("model", reservation.getModel());
			reservationJSON.put("serialNumber", reservation.getSerialNumber());
			reservationJSON.put("procurementOrder", reservation.getProcurementOrder());
			reservationJSON.put("typeId", reservation.getTypeId());
			reservationJSON.put("department", reservation.getDepartment());
			reservationJSON.put("aquireDate", reservation.getAquireDate());
			reservationJSON.put("yellowTag", reservation.getYellowTag());
			reservationJSON.put("cost", reservation.getCost());
			reservationJSON.put("assetTag", reservation.getAssetTag());
			reservationJSON.put("damageId", reservation.getDamageId());
			reservationJSON.put("damageName", reservation.getDamageName());
			reservationJSON.put("damageDescription", reservation.getDamageDescription());
			reservationJSON.put("warrentyId", reservation.getWarrentyId());
			reservationJSON.put("warrentyName", reservation.getWarrentyName());
			reservationJSON.put("warrentyCompany", reservation.getWarrentyCompany());
			reservationJSON.put("warrentyDescription", reservation.getWarrentyDescription());
			reservationJSON.put("warrentyEndDate", reservation.getEndDate());
			reservationJSON.put("severity", reservation.getSeverity());
		}

		String result = reservationJSON.toString();
		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();

	}

	// Produces JSON of a list of reservations belong to a specific borrower
	@Path("/borrower/{userId}/data.json")
	@GET
	@Produces("application/json")
	public Response getReservationsByBorrower(@PathParam("userId") long userId) throws JSONException, SQLException {

		List<Reservation> reservationList = reservationDao.getReservations(userId, username, password);

		JSONArray jArray = new JSONArray();
		try {

			for (Reservation reservation : reservationList) {
				JSONObject reservationJSON = new JSONObject();
				reservationJSON.put("reservationId", reservation.getReservationId());
				reservationJSON.put("userId", reservation.getUserId());
				reservationJSON.put("rentalId", reservation.getRentalId());
				reservationJSON.put("itemTypeId", reservation.getItemTypeId());
				reservationJSON.put("signature", reservation.getSignature());
				reservationJSON.put("startDate", reservation.getStartDate());
				reservationJSON.put("endDate", reservation.getEndDate());
				reservationJSON.put("itemId", reservation.getItemId());
				reservationJSON.put("barcode", reservation.getBarcode());
				reservationJSON.put("itemTypeName", reservation.getItemTypeName());
				reservationJSON.put("manufacturer", reservation.getManufacturer());
				reservationJSON.put("model", reservation.getModel());
				reservationJSON.put("serialNumber", reservation.getSerialNumber());
				reservationJSON.put("procurementOrder", reservation.getProcurementOrder());
				reservationJSON.put("typeId", reservation.getTypeId());
				reservationJSON.put("department", reservation.getDepartment());
				reservationJSON.put("aquireDate", reservation.getAquireDate());
				reservationJSON.put("yellowTag", reservation.getYellowTag());
				reservationJSON.put("cost", reservation.getCost());
				reservationJSON.put("assetTag", reservation.getAssetTag());
				reservationJSON.put("damageId", reservation.getDamageId());
				reservationJSON.put("damageName", reservation.getDamageName());
				reservationJSON.put("damageDescription", reservation.getDamageDescription());
				reservationJSON.put("warrentyId", reservation.getWarrentyId());
				reservationJSON.put("warrentyName", reservation.getWarrentyName());
				reservationJSON.put("warrentyCompany", reservation.getWarrentyCompany());
				reservationJSON.put("warrentyDescription", reservation.getWarrentyDescription());
				reservationJSON.put("warrentyEndDate", reservation.getEndDate());
				reservationJSON.put("severity", reservation.getSeverity());
				jArray.put(reservationJSON);
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

	// Produces JSON of a list of rentals belonging to a specific borrower
	@Path("/rentalBorrower/{userId}/data.json")
	@GET
	@Produces("application/json")
	public Response getRentalsByBorrower(@PathParam("userId") long userId) throws JSONException, SQLException {

		List<Reservation> reservationList = reservationDao.getRentalsByBorrower(userId, username, password);

		JSONArray jArray = new JSONArray();
		try {

			for (Reservation reservation : reservationList) {
				JSONObject reservationJSON = new JSONObject();
				reservationJSON.put("userId", reservation.getUserId());
				reservationJSON.put("rentalId", reservation.getRentalId());
				reservationJSON.put("signature", reservation.getSignature());
				reservationJSON.put("startDate", reservation.getStartDate());
				reservationJSON.put("endDate", reservation.getEndDate());
				reservationJSON.put("itemId", reservation.getItemId());
				reservationJSON.put("barcode", reservation.getBarcode());
				reservationJSON.put("itemTypeName", reservation.getItemTypeName());
				reservationJSON.put("manufacturer", reservation.getManufacturer());
				reservationJSON.put("model", reservation.getModel());
				reservationJSON.put("serialNumber", reservation.getSerialNumber());
				reservationJSON.put("procurementOrder", reservation.getProcurementOrder());
				reservationJSON.put("typeId", reservation.getTypeId());
				reservationJSON.put("department", reservation.getDepartment());
				reservationJSON.put("aquireDate", reservation.getAquireDate());
				reservationJSON.put("yellowTag", reservation.getYellowTag());
				reservationJSON.put("cost", reservation.getCost());
				reservationJSON.put("assetTag", reservation.getAssetTag());
				reservationJSON.put("damageId", reservation.getDamageId());
				reservationJSON.put("damageName", reservation.getDamageName());
				reservationJSON.put("damageDescription", reservation.getDamageDescription());
				reservationJSON.put("warrentyId", reservation.getWarrentyId());
				reservationJSON.put("warrentyName", reservation.getWarrentyName());
				reservationJSON.put("warrentyCompany", reservation.getWarrentyCompany());
				reservationJSON.put("warrentyDescription", reservation.getWarrentyDescription());
				reservationJSON.put("warrentyEndDate", reservation.getEndDate());
				reservationJSON.put("severity", reservation.getSeverity());
				jArray.put(reservationJSON);
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

	// Adds a reservation to the database
	@POST
	@Path("/add")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addReservation(Reservation reservation) throws SQLException {
		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			long id = reservationDao.addReservation(reservation, username, password);
			if (id > 0) {
				jsonObject.put("status", id);
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

			String resultFormatted = result.replaceAll("\\\\", "");
			String resultFormatted2 = resultFormatted.replaceAll("\"\\[\"", "\\[");
			String resultFormatted3 = resultFormatted2.replaceAll("\"\\]\"", "\\]");
			String resultFormatted4 = resultFormatted3.replaceAll("\\}\",\"\\{", "\\},\\{");
			String resultFormatted5 = resultFormatted4.replaceAll("\"\\{", "\\{");
			String resultFormatted6 = resultFormatted5.replaceAll("\"\\]", "\\]");
			return Response.status(200).entity(resultFormatted6).header("Access-Control-Allow-Origin", "*").build();
		}

		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
	}

	// Adds a rental to the database
	@POST
	@Path("rental/add")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addRental(Reservation reservation) throws SQLException {
		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {

			jsonObject.put("rentalId", reservationDao.addRental(reservation, username, password));

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

	// Links item to rental in the database
	@POST
	@Path("item/attach")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response attachItem(Reservation reservation) throws SQLException {
		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			if (reservationDao.attachItem(reservation, username, password) == 1) {
				jsonObject.put("status", "rental now has item");
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

	// Checkout item(s) and borrower to the database
	@POST
	@Path("checkout")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response checkoutItem(Reservation reservation) throws SQLException {
		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			if (reservationDao.checkoutItem(reservation, username, password) == 1) {
				jsonObject.put("status", "item(s) are now checked out to borrower");
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

	// Checkout item(s) and borrower to the database
	@DELETE
	@Path("checkin")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response checkInItem(Reservation reservation) throws SQLException {
		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			if (reservationDao.checkInItem(reservation, username, password) == 1) {
				jsonObject.put("status", "item(s) are now checked in from borrower");
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

	// Unlinks item from rental in the database
	@DELETE
	@Path("item/detach")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response detachItem(Reservation reservation) throws SQLException {
		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			if (reservationDao.detachItem(reservation, username, password) == 1) {
				jsonObject.put("status", "rental no longer has item");
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

	// Updates reservation information in the database
	@PUT
	@Path("/update")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateReservation(Reservation reservation) throws SQLException {

		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			reservationDao.updateReservation(reservation, username, password);
			jsonObject.put("status", "reservation updated");
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

	// Updates rental information in the database
	@PUT
	@Path("rental/update")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateRental(Reservation reservation) throws SQLException {

		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			reservationDao.updateRental(reservation, username, password);
			jsonObject.put("status", "rental updated");
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
}

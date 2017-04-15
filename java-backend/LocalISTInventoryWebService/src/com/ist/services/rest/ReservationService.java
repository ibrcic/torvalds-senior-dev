package com.ist.services.rest;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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

	// Produces a list of all items
	@Path("data.json")
	@GET
	@Produces("application/json")
	public Response getAllReservations() throws JSONException, SQLException {

		List<Reservation> reservationList = reservationDao.getAllReservations(username, password);

		JSONObject jObject = new JSONObject();
		JSONArray jArray = new JSONArray();
		try {

			for (Reservation reservation : reservationList) {
				JSONObject reservationJSON = new JSONObject();
				reservationJSON.put("reservationId", reservation.getReservationId());
				reservationJSON.put("borrowerId", reservation.getBorrowerId());
				reservationJSON.put("rentalId", reservation.getRentalId());
				reservationJSON.put("itemTypeId", reservation.getItemTypeId());
				reservationJSON.put("signature", reservation.getSignature());
				reservationJSON.put("startDate", reservation.getStartDate());
				reservationJSON.put("endDate", reservation.getEndDate());
				reservationJSON.put("idItem", reservation.getIdItem());
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

	// Produces JSON of a specific reservation
	@Path("/{reservationId}/data.json")
	@GET
	@Produces("application/json")
	public Response getItemById(@PathParam("reservationId") long reservationId) throws JSONException, SQLException {

		Reservation reservation = reservationDao.getReservation(reservationId, username, password);

		JSONObject reservationJSON = new JSONObject();
		if (!reservation.equals(null)) {
			reservationJSON.put("reservationId", reservation.getReservationId());
			reservationJSON.put("borrowerId", reservation.getBorrowerId());
			reservationJSON.put("rentalId", reservation.getRentalId());
			reservationJSON.put("itemTypeId", reservation.getItemTypeId());
			reservationJSON.put("signature", reservation.getSignature());
			reservationJSON.put("startDate", reservation.getStartDate());
			reservationJSON.put("endDate", reservation.getEndDate());
			reservationJSON.put("idItem", reservation.getIdItem());
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
	@Path("/borrower/{borrowerId}/data.json")
	@GET
	@Produces("application/json")
	public Response getReservationsByBorrower(@PathParam("borrowerId") long borrowerId)
			throws JSONException, SQLException {

		List<Reservation> reservationList = reservationDao.getReservations(borrowerId, username, password);

		JSONObject jObject = new JSONObject();
		JSONArray jArray = new JSONArray();
		try {

			for (Reservation reservation : reservationList) {
				JSONObject reservationJSON = new JSONObject();
				reservationJSON.put("reservationId", reservation.getReservationId());
				reservationJSON.put("borrowerId", reservation.getBorrowerId());
				reservationJSON.put("rentalId", reservation.getRentalId());
				reservationJSON.put("itemTypeId", reservation.getItemTypeId());
				reservationJSON.put("signature", reservation.getSignature());
				reservationJSON.put("startDate", reservation.getStartDate());
				reservationJSON.put("endDate", reservation.getEndDate());
				reservationJSON.put("idItem", reservation.getIdItem());
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

}

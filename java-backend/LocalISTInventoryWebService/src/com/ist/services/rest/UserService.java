package com.ist.services.rest;

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

import com.ist.services.rest.pojo.User;

/**
 * @author Hassan Jegan Ndow
 *
 */

@Path("users")
public class UserService {

	UserDao userDao = new UserDao();
	String username = "devJegan";
	String password = ")I(Like4Pies^";

	@Path("data.json")
	@GET
	@Produces("application/json")
	public Response getAllUsers() throws JSONException, SQLException {

		List<User> userList = userDao.getAllUsers(username, password);

		JSONArray jArray = new JSONArray();
		try {

			for (User user : userList) {
				JSONObject userJSON = new JSONObject();
				userJSON.put("borrowerId", user.getBorrowerId());
				userJSON.put("userName", user.getUserName());
				userJSON.put("email", user.getEmail());
				userJSON.put("majorId", user.getMajorId());
				userJSON.put("majorTitle", user.getMajorTitle());
				userJSON.put("majorAbbr", user.getMajorAbbr());
				userJSON.put("flagged", user.getFlagged());
				userJSON.put("classId", user.getClassId());
				userJSON.put("classTitle", user.getClassTitle());
				userJSON.put("className", user.getClassName());
				userJSON.put("section", user.getSection());
				jArray.put(userJSON);
			}

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

		System.out.println(resultFormatted6);

		return Response.status(200).entity(resultFormatted6).header("Access-Control-Allow-Origin", "*").build();
	}

	@Path("/{userId}/data.json")
	@GET
	@Produces("application/json")
	public Response getUserById(@PathParam("userId") long userId) throws JSONException, SQLException {

		User user = userDao.getUser(userId, username, password);

		JSONObject userJSON = new JSONObject();
		if (!user.equals(null)) {
			userJSON.put("borrowerId", user.getBorrowerId());
			userJSON.put("userName", user.getUserName());
			userJSON.put("email", user.getEmail());
			userJSON.put("majorId", user.getMajorId());
			userJSON.put("majorTitle", user.getMajorTitle());
			userJSON.put("majorAbbr", user.getMajorAbbr());
			userJSON.put("flagged", user.getFlagged());
			userJSON.put("classId", user.getClassId());
			userJSON.put("classTitle", user.getClassTitle());
			userJSON.put("className", user.getClassName());
			userJSON.put("section", user.getSection());
		}

		String result = userJSON.toString();
		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();

	}

	// Adds a user to the database
	@POST
	@Path("/add")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addUser(User user) throws SQLException {
		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			userDao.addUser(user, username, password);
			jsonObject.put("status", "user added");
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

	// Updates user information in the database
	@PUT
	@Path("/update")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateUser(User user) throws SQLException {

		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			userDao.updateUser(user, username, password);
			jsonObject.put("status", "user updated");
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

	// Produces a list of all classes
	@Path("classes/data.json")
	@GET
	@Produces("application/json")
	public Response getClasses() throws JSONException, SQLException {

		List<User> classList = userDao.getClasses(username, password);

		JSONObject jObject = new JSONObject();
		JSONArray jArray = new JSONArray();
		try {

			for (User userClass : classList) {
				JSONObject userJSON = new JSONObject();
				userJSON.put("classId", userClass.getClassId());
				userJSON.put("classTitle", userClass.getClassTitle());
				userJSON.put("className", userClass.getClassName());
				userJSON.put("section", userClass.getSection());
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

	// Produces a list of all majors
	@Path("majors/data.json")
	@GET
	@Produces("application/json")
	public Response getMajors() throws JSONException, SQLException {

		List<User> majorList = userDao.getMajors(username, password);

		JSONObject jObject = new JSONObject();
		JSONArray jArray = new JSONArray();
		try {

			for (User major : majorList) {
				JSONObject userJSON = new JSONObject();
				userJSON.put("majorId", major.getMajorId());
				userJSON.put("majorTitle", major.getMajorTitle());
				userJSON.put("majorAbbr", major.getMajorAbbr());
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

}

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

		return Response.status(200).entity(resultFormatted6).build();
	}

	@Path("/{userId}/data.json")
	@GET
	@Produces("application/json")
	public Response getItemById(@PathParam("userId") long userId) throws JSONException, SQLException {

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
		return Response.status(200).entity(result).build();

	}

}

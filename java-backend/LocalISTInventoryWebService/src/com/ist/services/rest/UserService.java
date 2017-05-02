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

	@Path("classes/{classId}/data.json")
	@GET
	@Produces("application/json")
	public Response getClassById(@PathParam("classId") long classId) throws JSONException, SQLException {

		User user = userDao.getClass(classId, username, password);

		JSONObject userJSON = new JSONObject();
		if (!user.equals(null)) {
			userJSON.put("classId", user.getClassId());
			userJSON.put("classTitle", user.getClassTitle());
			userJSON.put("className", user.getClassName());
			userJSON.put("section", user.getSection());
		}

		String result = userJSON.toString();
		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();

	}

	@Path("majors/{majorId}/data.json")
	@GET
	@Produces("application/json")
	public Response getMajorById(@PathParam("majorId") long majorId) throws JSONException, SQLException {

		User user = userDao.getMajor(majorId, username, password);

		JSONObject userJSON = new JSONObject();
		if (!user.equals(null)) {
			userJSON.put("majorId", user.getMajorId());
			userJSON.put("majorTitle", user.getMajorTitle());
			userJSON.put("majorAbbr", user.getMajorAbbr());
		}

		String result = userJSON.toString();
		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();

	}

	// gets privilege by its id
	@Path("privilege/{privilegeId}/data.json")
	@GET
	@Produces("application/json")
	public Response getPrivilegeById(@PathParam("privilegeId") int privilegeId) throws JSONException, SQLException {

		User user = userDao.getPrivilege(privilegeId, username, password);

		JSONObject userJSON = new JSONObject();
		if (!user.equals(null)) {
			userJSON.put("privilegeId", user.getPrivilegeId());
			userJSON.put("privilegeName", user.getPrivilegeName());
		}

		String result = userJSON.toString();
		return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();

	}

	// gets offense by its id
	@Path("offense/{offenseId}/data.json")
	@GET
	@Produces("application/json")
	public Response getOffenseById(@PathParam("offenseId") int offenseId) throws JSONException, SQLException {

		User user = userDao.getOffense(offenseId, username, password);

		JSONObject userJSON = new JSONObject();
		if (!user.equals(null)) {
			userJSON.put("offenseId", user.getPrivilegeId());
			userJSON.put("offenseName", user.getPrivilegeName());
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
			if (userDao.addUser(user, username, password) == 1) {
				jsonObject.put("status", "user added");
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

	// web doc
	// Adds a class to the database
	@POST
	@Path("class/add")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addClass(User user) throws SQLException {
		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			if (userDao.addClass(user, username, password) == 1) {
				jsonObject.put("status", "class added");
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

	// web doc
	// Adds a major to the database
	@POST
	@Path("major/add")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addMajor(User user) throws SQLException {
		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			if (userDao.addMajor(user, username, password) == 1) {
				jsonObject.put("status", "major added");
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

	// web doc
	// Adds a privilege to the database
	@POST
	@Path("privilege/add")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addPrivilege(User user) throws SQLException {
		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			userDao.addPrivilege(user, username, password);
			jsonObject.put("status", "privilege added");
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

	// web doc
	// Adds a section to the database
	@POST
	@Path("section/add")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addSection(User user) throws SQLException {
		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			if (userDao.addSection(user, username, password) == 1) {
				jsonObject.put("status", "section added");
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

	// web doc
	// Adds a offense to the database
	@POST
	@Path("offense/add")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addOffense(User user) throws SQLException {
		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			if (userDao.addOffense(user, username, password) == 1) {
				jsonObject.put("status", "offense added");
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

	// Links class to user in the database
	@POST
	@Path("class/attach")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response attachClass(User user) throws SQLException {
		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			if (userDao.attachClass(user, username, password) == 1) {
				jsonObject.put("status", "user now has a class");
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

	// Unlinks class from user in the database
	@DELETE
	@Path("class/detach")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response detachClass(User user) throws SQLException {
		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			if (userDao.detachClass(user, username, password) == 1) {
				jsonObject.put("status", "user no longer has class");
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

	// Unlinks major from user in the database
	@DELETE
	@Path("major/detach")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response detachMajor(User user) throws SQLException {
		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			if (userDao.detachMajor(user, username, password) == 1) {
				jsonObject.put("status", "user no longer has major");
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

	// Links major to user in the database
	@POST
	@Path("major/attach")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response attachMajor(User user) throws SQLException {
		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			if (userDao.attachMajor(user, username, password) == 1) {
				jsonObject.put("status", "user now has a major");
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

	// Updates class information in the database
	@PUT
	@Path("class/update")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateClass(User user) throws SQLException {

		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			userDao.updateClass(user, username, password);
			jsonObject.put("status", "class updated");
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

	// Updates class information in the database
	@PUT
	@Path("section/update")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateSection(User user) throws SQLException {

		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			userDao.updateSection(user, username, password);
			jsonObject.put("status", "section updated");
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

	// Updates major information in the database
	@PUT
	@Path("major/update")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateMajor(User user) throws SQLException {

		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			userDao.updateMajor(user, username, password);
			jsonObject.put("status", "major updated");
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

	// Updates privilege information in the database
	@PUT
	@Path("privilege/update")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updatePrivilege(User user) throws SQLException {

		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			userDao.updatePrivilege(user, username, password);
			jsonObject.put("status", "privilege updated");
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

	// Updates offense information in the database
	@PUT
	@Path("offense/update")
	@Produces("application/json")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response updateOffense(User user) throws SQLException {

		String result = "";
		JSONObject jsonObject = new JSONObject();

		try {
			userDao.updateOffense(user, username, password);
			jsonObject.put("status", "offense updated");
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

	// Produces a list of all privileges
	@Path("privileges/data.json")
	@GET
	@Produces("application/json")
	public Response getPrivileges() throws JSONException, SQLException {

		List<User> privilegeList = userDao.getPrivileges(username, password);

		JSONArray jArray = new JSONArray();
		try {

			for (User privilege : privilegeList) {
				JSONObject userJSON = new JSONObject();
				userJSON.put("privilegeId", privilege.getPrivilegeId());
				userJSON.put("privilegeName", privilege.getPrivilegeName());
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

	// Produces a list of all sections
	@Path("sections/data.json")
	@GET
	@Produces("application/json")
	public Response getSections() throws JSONException, SQLException {

		List<User> privilegeList = userDao.getSections(username, password);

		JSONArray jArray = new JSONArray();
		try {

			for (User privilege : privilegeList) {
				JSONObject userJSON = new JSONObject();
				userJSON.put("classId", privilege.getClassId());
				userJSON.put("section", privilege.getSection());
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

	// Produces a list of class enrolled users
	@Path("classes/enrolledUsers/data.json")
	@GET
	@Produces("application/json")
	public Response getClassEnrolledUsers() throws JSONException, SQLException {

		List<User> enrolledList = userDao.getUsersClasses(username, password);

		JSONArray jArray = new JSONArray();
		try {

			for (User user : enrolledList) {
				JSONObject userJSON = new JSONObject();
				userJSON.put("classId", user.getClassId());
				userJSON.put("userId", user.getBorrowerId());
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

	// Produces a list of major enrolled users
	@Path("majors/enrolledUsers/data.json")
	@GET
	@Produces("application/json")
	public Response getMajorEnrolledUsers() throws JSONException, SQLException {

		List<User> enrolledList = userDao.getUsersMajors(username, password);

		JSONArray jArray = new JSONArray();
		try {

			for (User user : enrolledList) {
				JSONObject userJSON = new JSONObject();
				userJSON.put("majorId", user.getMajorId());
				userJSON.put("userId", user.getBorrowerId());
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

	// Produces JSON of a list of sections belonging to a specific class
	@Path("/sectionClass/{classId}/data.json")
	@GET
	@Produces("application/json")
	public Response getSectionsByClass(@PathParam("classId") long classId) throws JSONException, SQLException {

		List<User> privilegeList = userDao.getSectionsByClass(classId, username, password);

		JSONArray jArray = new JSONArray();
		try {

			for (User privilege : privilegeList) {
				JSONObject userJSON = new JSONObject();
				userJSON.put("classId", privilege.getClassId());
				userJSON.put("section", privilege.getSection());
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

	// Produces JSON of a list of classes belonging to a specific user
	@Path("classes/enrolledUsers/{userId}/data.json")
	@GET
	@Produces("application/json")
	public Response getClassesByUser(@PathParam("userId") long userId) throws JSONException, SQLException {

		List<User> classesList = userDao.getClassesByUser(userId, username, password);

		JSONArray jArray = new JSONArray();
		try {

			for (User userClass : classesList) {
				JSONObject userJSON = new JSONObject();
				userJSON.put("classId", userClass.getClassId());
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

	// Produces JSON of a list of majors belonging to a specific user
	@Path("majors/enrolledUsers/{userId}/data.json")
	@GET
	@Produces("application/json")
	public Response getMajorsByUser(@PathParam("userId") long userId) throws JSONException, SQLException {

		List<User> majorsList = userDao.getMajorsByUser(userId, username, password);

		JSONArray jArray = new JSONArray();
		try {

			for (User userMajor : majorsList) {
				JSONObject userJSON = new JSONObject();
				userJSON.put("majorId", userMajor.getMajorId());
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

	// Produces a list of all offenses
	@Path("offenses/data.json")
	@GET
	@Produces("application/json")
	public Response getOffenses() throws JSONException, SQLException {

		List<User> offenseList = userDao.getOffenses(username, password);

		JSONArray jArray = new JSONArray();
		try {

			for (User offense : offenseList) {
				JSONObject userJSON = new JSONObject();
				userJSON.put("offenseId", offense.getOffenseId());
				userJSON.put("offenseName", offense.getOffenseName());
				userJSON.put("offenseDescription", offense.getOffenseDescription());
				userJSON.put("offenseDate", offense.getOffenseDate());
				userJSON.put("rentalId", offense.getRentalId());
				userJSON.put("itemId", offense.getItemId());
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

		return Response.status(200).entity(resultFormatted6).header("Access-Control-Allow-Origin", "*").build();
	}

}

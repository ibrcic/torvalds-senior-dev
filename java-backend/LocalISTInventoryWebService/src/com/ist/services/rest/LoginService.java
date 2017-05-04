package com.ist.services.rest;

import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.json.JSONObject;

import security.UserLogin;


public class LoginService {

	// produces a key to allow the user to decrypt communications from the client.
	@Path("login/data.json")
    @GET
    @Produces("application/json")
    public Response getOffenses()
            throws JSONException, SQLException {
        String publicKey;
        JSONObject userJSON = new JSONObject();
        try {
            publicKey = UserLogin.getNewKeySet();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            publicKey = "Error occurred processing this request";
            userJSON.put("publicKey", publicKey);
            String result = userJSON.toString();
            return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();

        }
        
        userJSON.put("publicKey", publicKey);
        String result = userJSON.toString();
        return Response.status(200).entity(result).header("Access-Control-Allow-Origin", "*").build();
	}

}

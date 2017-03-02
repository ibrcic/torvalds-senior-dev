/**
 * 
 */
package api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
@Path("/LoanItem")
/**
 * @author Noah
 *
 */

public interface LabAssistantQueries {
	@GET
	@Path("/returnItem")
	public String returnItem();
	
	@GET
	@Produces("text/json")
	public String loanItem();
	
	@GET
	@Produces("application/json")
	public String getAvailableInventory();
	
	
	
}

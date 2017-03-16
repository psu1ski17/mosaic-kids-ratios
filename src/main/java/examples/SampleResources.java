package examples;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/sample")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SampleResources {

	@GET
	@Path("/Types")
	public List<Object> getList() {
		try {
			// do something
		} catch (Exception e) {
			// create smart webapp exception
		}
		return null;
	}

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("sample")
	public Response getPlainText(@PathParam("id") String id,
			@PathParam("id2") String id2, @PathParam("id3") String id3) {
		try {

		} catch (Exception e) {

		}
		return null;
	}
}

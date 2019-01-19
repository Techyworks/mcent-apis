package com.mcent.app.resource;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.mcent.app.model.UserModel.User;
import com.mcent.app.service.UserService;

@Path("/user")
public class UserResource {

	Connection con;
	//private static Logger logger = Logger.getLogger(ClassName.class.getName());
  
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsersInfo(){
		
		List<User> user = new ArrayList<User>();
		UserService service = new UserService();
		user = service.getAllUsers();
		
		return Response.status(Status.OK).entity(user).build();
		
	}

	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response addUser(User user){
		UserService service = new UserService();
		com.mcent.app.model.Response response = new com.mcent.app.model.Response();		
		response = service.addUser(user);
		return Response.status(Status.OK).entity(response).build();
		
	}

}

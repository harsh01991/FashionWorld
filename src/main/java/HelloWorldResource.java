import Modal.Furniture;
import com.codahale.metrics.annotation.Timed;
import com.google.common.base.Optional;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.util.concurrent.atomic.AtomicLong;


@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public HelloWorldResource(String template, String defaultName) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Path("/name")
    @Timed
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        final String value = String.format(template, defaultName);
        return new Saying(counter.incrementAndGet(), value);
    }

    @POST
    @Path("/authenticate")
    @Consumes(MediaType.APPLICATION_JSON)
    public User authenticate(LoginRequest loginRequest) throws Exception {
        boolean flag =  new HelloWorldService().authenticateUser(loginRequest);
        User user = new User();
        user.setFlag(flag);
        return user;
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("isAuthenticated",String.valueOf(new HelloWorldService().authenticateUser(loginRequest)));
//        return jsonObject;

         //return new JSONObject("isAuthenticated",String.valueOf(new HelloWorldService().authenticateUser(loginRequest)));
    }

    @POST
    @Path("/insert")
    @Consumes(MediaType.APPLICATION_JSON)
    public User insert(SignUpRequest request) throws Exception{
        boolean flag =  new HelloWorldService().signUpUser(request);
        User user = new User();
        user.setFlag(flag);
        return user;
    }
    @GET
    @Path("/details")
    public Furniture furDetail()
    {
        Furniture furniture = new HelloWorldService().furnitureDetails();
        return furniture;
    }
    @GET
    @Path("/photo")
    @Produces("image/png")
    public File picture()
    {
        File f = new File("C:\\Users\\harsh\\Documents\\furnitureImages\\frontPage2.jpg");
        return f;
    }
}

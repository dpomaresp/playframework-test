package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.inject.Inject;
import play.api.db.Database;
import play.mvc.*;

public class ApplicationController extends Controller {

    public Result index() throws SQLException {
        return ok("Hello world");
    }
    
}
            

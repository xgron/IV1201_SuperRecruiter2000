package model;

import integration.DBPortal;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Model {
    public DBPortal portal = new DBPortal();
    public User user = new User(portal);
    public Application application = new Application(portal);

}

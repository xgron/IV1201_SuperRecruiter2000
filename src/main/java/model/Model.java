package model;

import integration.DBPortal;

public class Model {
    public DBPortal portal = new DBPortal();
    public User user = new User(portal);
    public Application application = new Application(portal);
}

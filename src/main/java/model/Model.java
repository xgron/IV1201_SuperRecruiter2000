package model;

import integration.DBPortal;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Model {
    public DBPortal portal = new DBPortal();
    public User user = new User(portal);
    public Application application = new Application(portal);


    private final static Logger LOG = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private FileHandler fh = null;

    public Model() {
        //logging
       /* String logPath = "./logs/";
        SimpleDateFormat format = new SimpleDateFormat("M-d_HHmmss");
        try {
            fh = new FileHandler(logPath
                    + format.format(Calendar.getInstance().getTime()) + ".log");
        } catch (Exception e) {
            System.out.println("Log could not be written to file...");
        }

        fh.setFormatter(new SimpleFormatter());
        LOG.addHandler(fh);*/

    }
}

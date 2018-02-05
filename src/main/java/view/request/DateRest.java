package view.request;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by enfet on 2018-02-05.
 */
@XmlRootElement(name = "daterest")
public class DateRest {
    @XmlElement(name = "start")
    private String start;
    @XmlElement(name = "end" )
    private String end;

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "DateDTO{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    public DateRest(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public DateRest() {
    }
}


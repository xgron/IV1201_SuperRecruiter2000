package shared;

import com.sun.org.apache.xerces.internal.impl.dtd.XMLElementDecl;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

@XmlRootElement
public class DateDTO {
    @XmlElement(name = "start")
    private String start;
    @XmlElement(name = "end")
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
}

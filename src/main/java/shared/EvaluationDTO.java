package shared;

/**
 * Created by enfet on 2018-02-26.
 */
public class EvaluationDTO {
    private String recruiterID;
    private String eval;

    public EvaluationDTO() {}



    public String getRecruiterID() {
        return recruiterID;
    }

    public void setRecruiterID(String recruiterID) {
        this.recruiterID = recruiterID;
    }

    public String getEval() {
        return eval;
    }

    public void setEval(String eval) {
        this.eval = eval;
    }
}

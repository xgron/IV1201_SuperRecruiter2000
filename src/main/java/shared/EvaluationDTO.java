package shared;

/**
 * Created by enfet on 2018-02-26.
 */
public class EvaluationDTO {
    private String recruiterID;
    private boolean evaluation;

    public EvaluationDTO() {}

    public EvaluationDTO(String recruiterID, boolean evaluation) {
        this.recruiterID = recruiterID;
        this.evaluation = evaluation;
    }

    public String getRecruiterID() {
        return recruiterID;
    }

    public void setRecruiterID(String recruiterID) {
        this.recruiterID = recruiterID;
    }

    public boolean isEvaluation() {
        return evaluation;
    }

    public void setEvaluation(boolean evaluation) {
        this.evaluation = evaluation;
    }
}

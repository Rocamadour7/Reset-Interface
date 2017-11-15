package sample;

public class Vote {

    private int voteId;
    private int candidateId;

    public Vote() {}

    public Vote(int voteId, int candidateId) {
        setVoteId(voteId);
        setCandidateId(candidateId);
    }

    public int getVoteId() {
        return voteId;
    }

    public void setVoteId(int voteId) {
        this.voteId = voteId;
    }

    public int getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(int candidateId) {
        this.candidateId = candidateId;
    }
}

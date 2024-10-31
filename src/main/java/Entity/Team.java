package Entity;

import java.util.List;

public class Team <T>{
    private List<T> teams;

    public Team(List<T> teams) {
        this.teams = teams;
    }

    public Team() {
    }

    public void addMember(T member){

        teams.add(member);

    }

    public List<T> getTeams() {
        return teams;
    }

    public void setTeams(List<T> teams) {
        this.teams = teams;
    }
}

package Repository;

import Entity.Board.Board;
import Entity.Items.*;
import Entity.Monster.Monster;
import Entity.Team;

import java.util.List;

//event handler used for each character's event
public interface Event<T,U> {

  public boolean win(Team<U> opposingTeam);

  public void attack(U opposingTeam, T cur);

  public boolean dodge(U opposingTeam);

  public List<T> avalibleUnit(Team<T> team);

  public void castSpell(U opposingTeam, Item spell, T cur);

  public void usePotion(T target, Item potion, T cur);






}

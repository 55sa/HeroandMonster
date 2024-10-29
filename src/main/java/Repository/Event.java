package Repository;

import Entity.Board.Board;
import Entity.Items.Armor;
import Entity.Items.Potion;
import Entity.Items.Spell;
import Entity.Items.Weapon;
import Entity.Monster.Monster;
import Entity.Team;

import java.util.List;

//event handler
public interface Event<T,U> {

  public boolean win(Team<U> opposingTeam);

  public void attack(U opposingTeam, T cur);

  public boolean dodge(U opposingTeam);

  public List<T> avalibleUnit(Team<T> team);

  public void castSpell(U opposingTeam, Spell spell, T cur);

  public void usePotion(T target, Potion potion, T cur);






}

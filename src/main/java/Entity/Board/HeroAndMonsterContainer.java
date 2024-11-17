package Entity.Board;

import Entity.Human.Hero;
import Entity.Monster.Monster;

public class HeroAndMonsterContainer {
  private Monster monster;

  private Hero hero;

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public HeroAndMonsterContainer() {

    }

    public HeroAndMonsterContainer(Monster monster, Hero hero) {
        this.monster = monster;
        this.hero = hero;
    }
}

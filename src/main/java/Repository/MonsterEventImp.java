package Repository;

import Entity.Human.Hero;
import Entity.Items.*;
import Entity.Monster.Monster;
import Entity.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MonsterEventImp implements Event<Monster, Hero> {

    private final Random random = new Random();

    @Override
    public boolean win(Team<Hero> opposingTeam) {
        for (Hero hero : opposingTeam.getTeams()){
            if (hero.isAlive()) {
                return false; // Hero team still has living members, monster hasn't won
            }
        }
        return true; // All heroes are defeated, monster team wins
    }



    @Override
    public void attack(Hero target, Monster monster) {
        int effectiveDamage = (int) (monster.getDamage());
        if(target.getEquipment().getArmor()!=null){
       effectiveDamage = (int) (monster.getDamage() * (1 - target.getEquipment().getArmor().getDamageReduction() / 100.0));
        effectiveDamage = Math.max(0, effectiveDamage);
        }
        if (!dodge(target)) {
            target.reduceHealth(effectiveDamage);
            System.out.println(monster.getName() + " attacked " + target.getName() + " for " + effectiveDamage + " damage!");
        } else {
            System.out.println(target.getName() + " dodged the attack!");
        }
    }

    @Override
    public boolean dodge(Hero target) {
        return random.nextDouble() < target.getAgility() * 0.002; // Use hero's agility for dodge chance
    }

    @Override
    public List<Monster> avalibleUnit(Team<Monster> team) {
        List<Monster> availableMonsters = new ArrayList<>();
        for (Monster monster : team.getTeams()) {
            if (monster.isAlive()) {
                availableMonsters.add(monster);
            }
        }
        return availableMonsters;
    }

    @Override
    public void castSpell(Hero target, Item spell, Monster monster) {

    }



    @Override
    public void usePotion(Monster target, Item potion, Monster monster) {
        // Typically, monsters don't use potions, so this method might be unused or return a message.

    }
}

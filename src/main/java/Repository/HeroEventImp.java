package Repository;

import Entity.Human.Hero;
import Entity.Items.*;
import Entity.Monster.Monster;
import Entity.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Entity.Items.Spell.SpellType.*;

//implementation for hero's event
public class HeroEventImp implements Event<Hero, Monster> {

    private final Random random = new Random();

    // Check if the hero team has won by verifying if all monsters are defeated
    @Override
    public boolean win(Team<Monster> opposingTeam) {
        for (Monster monster : opposingTeam.getTeams()) {
            if (monster.isAlive()) {
                return false; // Hero team hasn't won if any monster is still alive
            }
        }
        return true; // Hero team wins if all monsters are defeated
    }

    // Hero attacks the target monster
    @Override
    public void attack(Monster target, Hero hero) {
        int damage;
        if (hero.getEquipment().getDoubleHand()) {
            // If a two-handed weapon is equipped, only calculate based on right-hand weapon
            Weapon weapon = hero.getEquipment().getRightHand();
            damage = (int) ((hero.getStrength() + (weapon != null ? weapon.getDamage() : 0)) * 0.05);
        } else {
            // If two one-handed weapons are equipped, calculate the sum of left and right hand weapon damages
            Weapon leftHandWeapon = hero.getEquipment().getLeftHand();
            Weapon rightHandWeapon = hero.getEquipment().getRightHand();
            int weaponDamage = (leftHandWeapon != null ? leftHandWeapon.getDamage() : 0)
                    + (rightHandWeapon != null ? rightHandWeapon.getDamage() : 0);
            damage = (int) ((hero.getStrength() + weaponDamage) * 0.05);
        }

        if (!dodge(target)) {
            target.reduceHealth((int) (Math.max(0,damage * (1 - target.getDefense() / 100.0))));
            System.out.println(hero.getName() + " attacked " + target.getName() + " for " + damage + " damage!");
        } else {
            System.out.println(target.getName() + " dodged the attack!");
        }
    }

    // Determine if the monster dodges the attack
    @Override
    public boolean dodge(Monster target) {
        // Hero dodge chance based on agility
        return random.nextDouble() < (target.getDodgeChance() * 0.01);
    }

    // Get a list of heroes who are still able to fight
    @Override
    public List<Hero> avalibleUnit(Team<Hero> team) {
        List<Hero> availableHeroes = new ArrayList<>();
        for (Hero hero : team.getTeams()) {
            if (hero.isAlive()) {
                availableHeroes.add(hero);
            }
        }
        return availableHeroes;
    }



    // Hero casts a spell on the monster
    @Override
    public void castSpell(Monster target, Item spell, Hero hero) {
        int manaCost = (int) spell.getInfo().get("manaCost");

        // Check if hero has enough mana to cast the spell
        if (hero.getMP() < manaCost) {
            System.out.println(hero.getName() + " does not have enough mana to cast " + spell.getName() + ".");
            return;  // Exit the method if mana is insufficient
        }

        int baseDamage = (int) spell.getInfo().get("damage");
        int adjustedDamage = (int) (baseDamage + (hero.getDexterity() / 10000.0) * baseDamage);
        int damageAfterDefense = (int) (adjustedDamage * (1 - target.getDefense() / 100.0));
        damageAfterDefense = Math.max(0, damageAfterDefense);
        if (!dodge(target)) {
            target.reduceHealth(damageAfterDefense);
            hero.reduceMana(manaCost);

            // Apply spell-specific effects
            Spell.SpellType spellType = (Spell.SpellType) spell.getInfo().get("spellType");
            switch (spellType) {
                case FIRE:
                    target.reduceDefense(target.getDefense() * 0.5);
                    System.out.println(hero.getName() + " cast Fire Spell on " + target.getName() + " reducing defense!");
                    break;
                case ICE:
                    target.reduceBaseDamage(target.getDamage() * 0.5);
                    System.out.println(hero.getName() + " cast Ice Spell on " + target.getName() + " reducing damage!");
                    break;
                case LIGHTNING:
                    target.reduceDodgeChance(target.getDodgeChance() * 0.5);
                    System.out.println(hero.getName() + " cast Lightning Spell on " + target.getName() + " reducing dodge!");
                    break;
            }
            System.out.println(hero.getName() + " cast " + spell.getName() + " on " + target.getName() + " for " + damageAfterDefense + " damage!");

            // Remove spell from hero's inventory after use
            hero.removeItem(spell);
        } else {
            System.out.println(target.getName() + " dodged the spell!");
        }
    }

    // Hero uses a potion on themselves or another hero
    @Override
    public void usePotion(Hero target, Item potion, Hero user) {
        Potion.StatType statType = (Potion.StatType) potion.getInfo().get("statType");
        int effectAmount = (int) potion.getInfo().get("effectAmount");

        switch (statType) {
            case HP:
                target.increaseHealth(effectAmount);
                break;
            case MP:
                target.increaseMana(effectAmount);
                break;
            case STRENGTH:
                target.setStrength(target.getStrength() + effectAmount);
                break;
            case DEXTERITY:
                target.setDexterity(target.getDexterity() + effectAmount);
                break;
            case AGILITY:
                target.setAgility(target.getAgility() + effectAmount);
                break;
            default:
                System.out.println("Invalid potion type.");
                return;
        }
        System.out.println(user.getName() + " used " + potion.getName() + " on " + target.getName() + " to increase " + statType + " by " + effectAmount + ".");

        // Remove potion from hero's inventory after use
        user.removeItem(potion);
    }
}

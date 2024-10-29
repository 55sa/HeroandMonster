package Repository;

import Entity.Human.Hero;
import Entity.Items.Armor;
import Entity.Items.Potion;
import Entity.Items.Spell;
import Entity.Items.Weapon;
import Entity.Monster.Monster;
import Entity.Team;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
            target.reduceHealth((int) (damage * (1 - target.getDefense() / 100.0)));
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
    public void castSpell(Monster target, Spell spell, Hero hero) {
        int damage = (int) (spell.getDamage() + (hero.getDexterity() / 10000.0) * spell.getDamage());
        damage = (int) (damage * (1 - target.getDefense() / 100.0));
        if (!dodge(target)) {
            target.reduceHealth(damage);
            hero.reduceMana(spell.getManaCost());

            // Apply spell-specific effects
            switch (spell.getSpellType()) {
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
            System.out.println(hero.getName() + " cast " + spell.getName() + " on " + target.getName() + " for " + damage + " damage!");

            // Remove spell from hero's inventory after use
            hero.removeItem(spell);
        } else {
            System.out.println(target.getName() + " dodged the spell!");
        }
    }

    // Hero uses a potion on themselves or another hero
    @Override
    public void usePotion(Hero target, Potion potion, Hero user) {
        switch (potion.getStatType()) {
            case HP:
                target.increaseHealth(potion.getEffectAmount());
                break;
            case MP:
                target.increaseMana(potion.getEffectAmount());
                break;
            case STRENGTH:
                target.setStrength(target.getStrength() + potion.getEffectAmount());
                break;
            case DEXTERITY:
                target.setDexterity(target.getDexterity() + potion.getEffectAmount());
                break;
            case AGILITY:
                target.setAgility(target.getAgility() + potion.getEffectAmount());
                break;
        }
        System.out.println(user.getName() + " used " + potion.getName() + " on " + target.getName() + " to increase " + potion.getStatType() + " by " + potion.getEffectAmount() + ".");

        // Remove potion from hero's inventory after use
        user.removeItem(potion);
    }
}

package Entity.Human;

import Entity.Items.Armor;
import Entity.Items.Weapon;

// Equipment class used to manage hero's weapon and armor
public class Equipment {
    private Weapon leftHand;
    private Weapon rightHand;
    private Boolean doubleHand;
    private Armor armor;

    public Equipment(Weapon leftHand, Weapon rightHand, Boolean doubleHand, Armor armor) {
        this.leftHand = leftHand;
        this.rightHand = rightHand;
        this.doubleHand = doubleHand;
        this.armor = armor;
    }

    public Weapon getLeftHand() {
        return leftHand;
    }

    public void setLeftHand(Weapon leftHand) {
        this.leftHand = leftHand;
    }

    public Weapon getRightHand() {
        return rightHand;
    }

    public void setRightHand(Weapon rightHand) {
        this.rightHand = rightHand;
    }

    public Boolean getDoubleHand() {
        return doubleHand;
    }

    public void setDoubleHand(Boolean doubleHand) {
        this.doubleHand = doubleHand;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }
}

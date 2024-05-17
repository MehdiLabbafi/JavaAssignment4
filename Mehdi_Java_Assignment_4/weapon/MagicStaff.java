package weapon;

public class MagicStaff extends Weapon {
    private int damageAmount = 200; // The amount of damage

    public MagicStaff() {
        super();
        super.setDamageAmount(damageAmount);
        super.setDexterityCost(150); // Movement cost
        super.setWeaponType("Magic Staff");
    }

    @Override
    public int strike(int weatherIntensity, int attackType, int strength, int dexterity) {
        int amount = 0;
        // Add attack logic for MagicStaff here
        return amount;
    }
}

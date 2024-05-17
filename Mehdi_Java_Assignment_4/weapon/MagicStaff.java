package weapon;

public class MagicStaff extends Weapon {
    private int damageAmount = 200; // مقدار آسیب

    public MagicStaff() {
        super();
        super.setDamageAmount(damageAmount);
        super.setDexterityCost(150); // هزینه حرکتی
        super.setWeaponType("Magic Staff");
    }

    @Override
    public int strike(int weatherIntensity, int attackType, int strength, int dexterity) {
        int amount = 0;
        // منطق حمله برای MagicStaff را اینجا اضافه کنید
        return amount;
    }
}

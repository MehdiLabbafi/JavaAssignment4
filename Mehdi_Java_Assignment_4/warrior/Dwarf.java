package warrior;

public class Dwarf extends Warrior {
  private int baseHealth = 100;
  private int bonusHealth = 150;
  private int baseStrength = 100;
  private int bonusStrength = 150;
  private int baseDexterity = 50;
  private int bonusDexterity = 75;

  public Dwarf(String warriorType) {
    super();
    super.setHealth(super.randNum.nextInt(bonusHealth) + baseHealth);
    super.setStrength(super.randNum.nextInt(bonusStrength) + baseStrength);
    super.setDexterity(super.randNum.nextInt(bonusDexterity) + baseDexterity);
    super.setWarriorType(warriorType);
  }
}

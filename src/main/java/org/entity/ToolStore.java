package org.entity;

public class ToolStore extends NormalLoc {
    public ToolStore(Player player) {
        super(player, "Mağaza");
    }
    boolean showMenu = true;
    @Override
    public boolean onLocation() {
        while (showMenu) {
            System.out.println("------Mağazaya hoşgeldiniz-------");
            System.out.println("1- Silahlar");
            System.out.println("2- Zırhlar");
            System.out.println("3- Çıkış yap");

            System.out.print("Seçiminiz: ");
            int selection = input.nextInt();
            while (selection < 1 || selection > 3) {
                System.out.print("Geçersiz değer, tekrar giriniz: ");
                selection = input.nextInt();
            }
            switch (selection) {
                case 1:
                    printWeapon();
                    buyWeapon();
                    break;
                case 2:
                    printArmor();
                    buyArmor();
                    break;
                case 3:
                    System.out.println("Tekrar bekleriz.");
                    showMenu = false;
                    break;
            }
        }
        return true;
    }
    public void printWeapon() {
        System.out.println("--------Silahlar--------");
        for (Weapon w : Weapon.weapons()) {
            System.out.println(w.getId() + "-" + w.getName() + "\tHasar:" + w.getDamage() + "\tPara:" + w.getPrice());
        }
        System.out.println("0-Çıkış yap");
    }
    private void printArmor() {
        System.out.println("--------Zırhlar-------");
        for (Armor a : Armor.armors()) {
            System.out.println(a.getId() + "-" + a.getName() + "\tBlock: " + a.getBlock() + "\tPara: " + a.getPrice());
        }
        System.out.println("0-Çıkış yap");
    }
    public void buyWeapon() {
        System.out.print("Bir silah seçiniz:");
        int selectWeaponID = input.nextInt();
        while (selectWeaponID < 0 || selectWeaponID > Weapon.weapons().length) {
            System.out.print("Geçersiz değer girdiniz, tekrar deneyiniz");
            selectWeaponID = input.nextInt();
        }
        if (selectWeaponID != 0) {
            Weapon selectedWeapon = Weapon.getWeaponObjByID(selectWeaponID);
            if (selectedWeapon != null) {
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney())
                    System.out.println("Yeterli paranız bulunmamaktadır!");
                else {
                    System.out.println(selectedWeapon.getName() + " silahını satın aldınız.");
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan paranız:" + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                    System.out.println("Yeni silahınız: "+ this.getPlayer().getInventory().getWeapon().getName());
                }
            }
        }
    }
    public void buyArmor(){
        System.out.print("Bir zırh seçiniz:");
        int selectArmorID = input.nextInt();
        while (selectArmorID < 0 || selectArmorID > Weapon.weapons().length) {
            System.out.print("Geçersiz değer girdiniz, tekrar deneyiniz");
            selectArmorID = input.nextInt();
        }
        if (selectArmorID != 0) {
            Armor selectedArmor = Armor.getArmorObjByID(selectArmorID);
            if (selectedArmor != null) {
                if (selectedArmor.getPrice() > this.getPlayer().getMoney())
                    System.out.println("Yeterli paranız bulunmamaktadır!");
                else {
                    System.out.println(selectedArmor.getName() + " zırh satın aldınız");
                    int balance = this.getPlayer().getMoney() - selectedArmor.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Kalan paranız:" + this.getPlayer().getMoney());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                }
            }
        }
    }
}

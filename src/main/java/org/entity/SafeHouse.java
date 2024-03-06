package org.entity;

public class SafeHouse extends NormalLoc{
    public SafeHouse(Player player) {
        super(player, "Güvenli Ev");
    }
    @Override
    public boolean onLocation() {
        System.out.println("Güvenli evdesiniz.");
        if (isWin())
            return false;
        this.getPlayer().setHealth(this.getPlayer().getFirstHealth());
        System.out.println("Canınız yenilendi");
        System.out.println("Canınız: "+ this.getPlayer().getHealth());
        return true;
    }
    public boolean isWin() {
        if (this.getPlayer().getInventory().awardList.contains("food") &&
                this.getPlayer().getInventory().awardList.contains("firewood") &&
                this.getPlayer().getInventory().awardList.contains("water")) {
            System.out.println("TEBRİKLER! OYUNU BİTİRDİNİZ..");
            return true;
        }
        return false;
    }
}
package org.entity;
import java.util.Random;

public abstract class BattleLoc extends Location {
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;
    public BattleLoc(Player player, String locationName, Obstacle obstacle, String award, int maxObstacle) {
        super(player, locationName);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }
    @Override
    public boolean onLocation() {
        if(this.getPlayer().getInventory().awardList.contains(this.award)) {
            System.out.println("Bu bölgenin ödülünü kazandınız! Lütfen başka bir bölge seçiniz");
            return true;
        }
        int obsNumber = randomObstacleNumber();
        System.out.println("Şuan buradasınız : "+ this.getLocationName());
        System.out.println("Dikkatli ol! Burada "+ obsNumber+ " tane " +this.getObstacle().getName() +   " yaşıyor");
        System.out.print("<S>avaş veya <K>aç : ");
        String selectCase = input.nextLine().toUpperCase();
        if (selectCase.equals("S") && combat(obsNumber)) {
            System.out.println(this.getLocationName() + " tüm canavarları yendiniz!");
            this.getPlayer().getInventory().addAward(this.getAward());
            System.out.println("Ödülünüz : "+ this.getAward());
            System.out.println("Kazanılan ödüller "+ getPlayer().getInventory().awardList);
            return true;
        }

        else if (this.getPlayer().getHealth() <= 0) {
            System.out.println("Öldünüz!");
            return false;
        }
        return true;
    }
    public boolean combat(int obsNumber) {
        for (int i = 1; i <= obsNumber; i++) {
            this.getObstacle().setHealth(this.getObstacle().getFirstHealth());
            playerStats();
            obstacleStats(i);
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                System.out.print("<V>ur veya <K>aç : ");
                String selectCombat = input.nextLine().toUpperCase();
                if (selectCombat.equals("V")) {
                    int randomNumber = randomHit();
                    System.out.println(randomNumber);
                    if (randomNumber == 0) {
                        System.out.println("Siz vurdunuz!");
                        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getDamage());
                        afterHit();
                        if (this.getObstacle().getHealth() > 0) {
                            System.out.println("Canavar size vurdu!");
                            int obsDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (obsDamage < 0)
                                obsDamage = 0;
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obsDamage);
                            afterHit();
                        }
                    } else if (randomNumber == 1) {
                        System.out.println("Canavar size vurdu!");
                        int obsDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if (obsDamage < 0)
                            obsDamage = 0;
                        this.getPlayer().setHealth(this.getPlayer().getHealth() - obsDamage);
                        afterHit();
                        if (this.getPlayer().getHealth() > 0) {
                            System.out.println("Siz vurdunuz!");
                            this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getDamage());
                            afterHit();
                        }
                    }
                } else
                    return false;
            }
            if (this.getPlayer().getHealth() > this.getObstacle().getHealth()) {
                System.out.println("Düşmanı yendiniz!");
                System.out.println(this.getObstacle().getAward() + " para kazandınız!");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                System.out.println("Güncel paranız: "+ this.getPlayer().getMoney());
                System.out.println();
            }
            else {
                return false;
            }
        }
        return true;
    }
    public void afterHit() {
        System.out.println("Canınız: " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " canı: "+ this.getObstacle().getHealth());
        System.out.println();
    }
    public void playerStats() {
        System.out.println("Oyuncu Değerleri");
        System.out.println("----------------");
        System.out.println("Silah:" + getPlayer().getInventory().getWeapon().getName());
        System.out.println("Hasar:"+ getPlayer().getTotalDamage());
        System.out.println("Zırh:" + getPlayer().getInventory().getArmor().getName());
        System.out.println("Block:" + getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Sağlık:"+ getPlayer().getHealth());
        System.out.println("Para:" + getPlayer().getMoney());
        System.out.println();

    }
    public void obstacleStats(int i) {
        System.out.println(i + "." +this.getObstacle().getName() + " Değerleri");
        System.out.println("-------------------");
        System.out.println("Sağlık:"+ this.getObstacle().getHealth());
        System.out.println("Hasar:"+ this.getObstacle().getDamage());
        System.out.println("Ödül:"+ this.getObstacle().getAward());

    }
    public int randomObstacleNumber() {
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle() + 1);
    }
    public int randomHit() {
        Random r = new Random();
        return r.nextInt(2);
    }
    public Obstacle getObstacle() {
        return obstacle;
    }
    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }
    public int getMaxObstacle() {
        return maxObstacle;
    }
    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}

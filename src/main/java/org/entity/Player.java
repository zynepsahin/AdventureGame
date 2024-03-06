package org.entity;

import java.util.Scanner;

public class Player {
    private String charName;
    private String name;
    private int damage;
    private int health;
    private int firstHealth;
    private int money;
    private Inventory inventory;
    Scanner input = new Scanner(System.in);

    public Player(String name) {
        this.name = name;
        this.inventory = new Inventory();
    }

    void selectChar() {
        GameChar[] charList = {new Samurai(), new Archer(), new Knight()};

        System.out.println("-----------------------------------------------------");
        System.out.println("Karakterler:");
        for (GameChar character : charList) {
            System.out.println("ID:" + character.getId() +
                    "\tKarakter:" + character.getName() +
                    "\t Hasar:" + character.getDamage() +
                    "\t Sağlık:" + character.getHealth() +
                    "\tPara:" + character.getMoney());
        }
        System.out.println("------------------------------------------------------");
        System.out.print("Karakter seçiniz: ");
        int selectChar = input.nextInt();
        switch (selectChar) {
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
                initPlayer(new Knight());
                break;
            default:
                initPlayer(new Samurai());
        }
        System.out.println("Karakter: " + this.getCharName() +
                "\tHasar:"+ this.getDamage() +
                "\tSağlık:"+ this.getHealth() +
                "\tPara:" + this.getMoney());
    }
    public void initPlayer(GameChar gameChar) {

        this.setHealth(gameChar.getHealth());
        this.setDamage(gameChar.getDamage());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());
        this.setFirstHealth(gameChar.getHealth());

    }
    public void printInfo() {
        System.out.println("Silah:" + this.getInventory().getWeapon().getName() +
                "\tZırh:" + this.getInventory().getArmor().getName() +
                "\tHasar:"+ this.getDamage() +
                "\tBlock:" + this.getInventory().getArmor().getBlock() +
                "\tSağlık:"+ this.getHealth() +
                "\tPara:" + this.getMoney());
    }

    public String getCharName() {
        return charName;
    }
    public void setCharName(String charName) {
        this.charName = charName;
    }
    public int getDamage() {
        return damage;
    }
    public int getTotalDamage() {
        return damage + this.getInventory().getWeapon().getDamage();
    }
    public void setDamage(int damage) {
        this.damage = damage;
    }
    public int getHealth() {
        return health;
    }
    public void setHealth(int health) {
        if (health < 0)
            health = 0;
        this.health = health;
    }
    public int getMoney() {
        return money;
    }
    public void setMoney(int money) {
        this.money = money;
    }
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

    public Inventory getInventory() {
        return inventory;
    }
    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getFirstHealth() {
        return firstHealth;
    }

    public void setFirstHealth(int firstHealth) {
        this.firstHealth = firstHealth;
    }
}


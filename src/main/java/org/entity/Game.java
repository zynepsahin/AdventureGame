package org.entity;

import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);
    public void start() {
        System.out.println("Macera oyununa hoşgeldiniz!");
        System.out.print("Adınızı giriniz: ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println("Merhaba " +player.getName() + " adaya hoşgeldin!");
        System.out.println("Oyuna başlamak için bir karakter seçiniz");
        player.selectChar();

        Location location = null;
        while(true) {
            player.printInfo();
            System.out.println();
            System.out.println("----------------Bölgeler------------------");
            System.out.println("1-Güvenli Ev --> Burada güvendesiniz.");
            System.out.println("2-Eşya Dükkanı --> Silah veya Zırh satın alabilirsiniz.");
            System.out.println("3-Mağara --> Ödül-Yemek. Dikkatli ol zombi çıkabilir.");
            System.out.println("4-Orman --> Ödül-Odun. Dikkatli ol vampir çıkabilir.");
            System.out.println("5-Nehir --> Ödül-Water. Dikkatli ol ayı çıkabilir.");
            System.out.println("0-Çıkış --> Oyunu sonlandır.");
            System.out.println("-------------------------------------------");
            System.out.print("Gitmek istediğiniz bölgeyi seçiniz: ");

            int selectLoc = input.nextInt();
            input.nextLine();
            switch (selectLoc) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    location = new Cave(player);
                    break;
                case 4:
                    location = new Forest(player);
                    break;
                case 5:
                    location = new River(player);
                    break;
                default:
                    System.out.println("Lütfen geçerli bir bölge giriniz!");
                    location = new SafeHouse(player);
            }
            if (location == null) {
                System.out.println("Game Over!");
                break;
            }
            if (!location.onLocation()) {
                System.out.println("Game Over!");
                break;
            }
        }
    }
}

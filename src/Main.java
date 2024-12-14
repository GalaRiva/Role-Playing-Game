import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {
    private static BufferedReader br;
    private static Character player = null;
    private static BattleScene battleScene = null;


    public static void main(String[] args) {
        br = new BufferedReader(new InputStreamReader(System.in));
        battleScene = new BattleScene();
        System.out.println("Придумайте имя Вашего персонажа:");
        try {
            command(br.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void command(String string) throws IOException {

        if (player == null) {
            player = new Hero(
                    string, 100, 20, 20, 20, 0
            );
            System.out.println(String.format("Наш мир спасет %s! Йооооху!",
                    player.getName()));


            Navigation();
            try {
                command(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        switch (string) {
            case "1": {
                commitSell();

            }
            break;

            case "2": {
                commitFight();
            }
            break;

            case "3":
                System.exit(1);
                break;
            case "да":
                command("2");
                break;
            case "нет": {
                Navigation();
                command(br.readLine());
            }

            command(br.readLine());
        }
    }

    private static void commitSell() throws IOException {

        if (player != null) {
            Trader trader = new Trader("Торговец", 50, 10);

            int goldAmount = 10;

            if (player.getGold() >= goldAmount) {
                player.decreaseGold(goldAmount);
                trader.increaseGold(goldAmount);

                System.out.println("Сделка состоялась! Герой заплатил " + goldAmount + " золотых монет. " +
                        "Продолжаем? да/нет");

                String userResponse = br.readLine();
                if (userResponse.equalsIgnoreCase("да")) {
                    command("1");
                } else if (userResponse.equalsIgnoreCase("нет")) {

                } else {
                    System.out.println("Пожалуйста, введите 'да' или 'нет'.");
                }

                Navigation();
            }
            if (player.getGold() < goldAmount) {
                player.decreaseGold(goldAmount);
                //trader.increaseGold(goldAmount);

                //} else {
                System.out.println("У героя недостаточно золота для покупки этого товара." +
                        "Продолжаем? да/нет");

                String userResponse = br.readLine();
                if (userResponse.equalsIgnoreCase("да")) {
                    command("1");
                } else if (userResponse.equalsIgnoreCase("нет")) {
                    Navigation();
                } else {
                    System.out.println("Не понял Вас. Пожалуйста, введите 'да' или 'нет'.");
                }
            }
            try {
                command(br.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void Navigation() {
        System.out.println("Куда отправимся?");
        System.out.println("1. К Торговцу");
        System.out.println("2. В темный лес");
        System.out.println("3. Выход");
    }


    private static void commitFight() {

        battleScene.fight(player, createMonster(), new FightCallback() {

            @Override
            public void fightWin() {
                System.out.println(String.format("%s победил! У Вас есть %d опыта и %d золота, а также осталось %d единиц здоровья.",
                        player.getName(), player.getExperience(), player.getGold(), player.getHealth()));
                System.out.println("Отправимся в темный лес или вернуться в город? (да/нет)");
                try {
                    command(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fightLost() {
            }
        });
    }


    interface FightCallback {
        void fightWin();

        void fightLost();
    }

    private static Character createMonster() {
        int random = (int) (Math.random() * 10);
        if (random % 2 == 0) return new Goblin(
                "Гоблин", 50, 10, 10, 100, 20);
        else return new Sceleton(
                "Скелет", 25, 20, 20, 100, 10);
    }

}
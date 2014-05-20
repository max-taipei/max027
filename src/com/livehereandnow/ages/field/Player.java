/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.livehereandnow.ages.field;

import com.livehereandnow.ages.card.AgesCard;
import java.util.ArrayList;
import java.util.List;

public class Player {
//    private List<AgesCard> 行政牌暫存區;

    public void moveOneCard(List<AgesCard> from, int index, List<AgesCard> to) {

        to.add(from.remove(index));
    }

    public String getName() {
        return name;
    }

    public void pay內政點數(int cost) {
        內政點數.addPoints((-1) * cost);
    }

    public Points get內政點數() {
        return 內政點數;
    }

    public Points get軍事點數() {
        return 軍事點數;
    }

    public Points get建築上限() {
        return 建築上限;
    }

    public Points get內政手牌上限() {
        return 內政手牌上限;
    }

    public void init建造中的奇蹟區() {
        wonderStages = new ArrayList<>();
        AgesCard card = 建造中的奇蹟區.get(0);
//            System.out.println(" WE KNOW CURRENT 建造中的奇蹟區 IS " + card.getName() + " " + card.getIconPoints());

        String cost1 = card.getIconPoints();
        String[] cost2 = cost1.split(":");
        String cost3 = cost2[1];
//            System.out.println(" COST IS " + cost3);
        String[] cost4 = cost3.split("-");

        for (String cost5 : cost4) {
//                System.out.println(" " + cost5);
            int cost6 = Integer.parseInt(cost5);
            wonderStages.add(cost6);
        }

    }

    public Points get軍事手牌上限() {
        return 軍事手牌上限;
    }

    public Points get殖民點數() {
        return 殖民點數;
    }

    public void produce() {
        produce文化();
        produce礦山();
        produce科技();
        produce農場();

    }

    private void produce文化() {
        get文化().addPoints(get文化生產_當回合().getVal());
    }

    private void produce農場() {
        for (AgesCard card : 農場區) {
            card.setTokenBlue(card.getTokenBlue() + card.getTokenYellow());
        }
    }

    private void produce礦山() {
        for (AgesCard card : 礦山區) {
            card.setTokenBlue(card.getTokenBlue() + card.getTokenYellow());
        }
    }

    private void produce科技() {
        get科技().addPoints(get科技生產_當回合().getVal());
    }

    public Points get文化() {
        return 文化;
    }

    public Points get文化生產_當回合() {
        return 文化生產_當回合;
    }

    public Points get科技() {
        return 科技;
    }

    public Points get科技生產_當回合() {
        return 科技生產_當回合;
    }

    public Points get軍力() {
        return 軍力;
    }

    public Points get資源庫_藍點() {
        return 資源庫_藍點;
    }

    public Points get人力庫_黃點() {
        return 人力庫_黃點;
    }

    public Points get笑臉_幸福指數() {
        return 笑臉_幸福指數;
    }

    public Points get工人區_黃點() {
        return 工人區_黃點;
    }

    public List<AgesCard> get領袖區() {
        return 領袖區;
    }

    public List<AgesCard> get政府區() {
        return 政府區;
    }

    public List<AgesCard> get實驗室() {
        return 實驗室;
    }

    public List<AgesCard> get神廟區() {
        return 神廟區;
    }

    public List<AgesCard> get農場區() {
        return 農場區;
    }

    public List<AgesCard> get礦山區() {
        return 礦山區;
    }

    public List<AgesCard> get步兵區() {
        return 步兵區;
    }

    public List<AgesCard> get建造中的奇蹟區() {
        return 建造中的奇蹟區;
    }

    public List<AgesCard> get已完成的奇蹟() {
        return 已完成的奇蹟;
    }

    public List<AgesCard> get殖民領土區() {
        return 殖民領土區;
    }

    public List<AgesCard> get特殊科技區() {
        return 特殊科技區;
    }

    public List<AgesCard> get手牌內政牌區() {
        return 手牌內政牌區;
    }

    public List<AgesCard> get行動牌暫存區() {
        return 行動牌區;
    }

    public List<AgesCard> get手牌軍事牌區() {
        return 手牌軍事牌區;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Player(String str) {
        name = str;
        init();
    }

    public void actBuild(int id) {
//2014-5-19-max 針對實驗室、神廟區、農場區、礦山區、步兵區
        List<AgesCard> buildList = new ArrayList<>();
        buildList.addAll(實驗室);
        buildList.addAll(神廟區);
        buildList.addAll(農場區);
        buildList.addAll(礦山區);
        buildList.addAll(步兵區);

        for (AgesCard card : buildList) {
            if (card.getId() == id) {
                System.out.println("before  內政點數:" + this.內政點數 + "  工人區:" + this.工人區_黃點 + "  這張牌的黃點:"
                        + +card.getTokenYellow() + "  (成本" + card.getIconPoints() + ")" + "  礦山區 A青銅1032 藍點:" + this.get礦山區().get(0).getTokenBlue() + "  資源庫【藍】:" + this.資源庫_藍點);
                card.setTokenYellow(card.getTokenYellow() + 1);//指定的卡上黃點+1
                this.工人區_黃點.addPoints(-1);//玩家的工人區-1
                this.內政點數.addPoints(-1);
                //支付石頭
                this.礦山區.get(0).setTokenBlue(礦山區.get(0).getTokenBlue() - card.getCostStone());
                //增加資源庫的藍點
                this.資源庫_藍點.addPoints(card.getCostStone());
                System.out.println("after  內政點數:" + this.內政點數 + "  工人區:" + this.工人區_黃點 + "  這張牌的黃點:"
                        + +card.getTokenYellow() + "  (成本" + card.getIconPoints() + ")" + "  礦山區 A青銅1032 藍點:" + this.get礦山區().get(0).getTokenBlue() + "  資源庫【藍】:" + this.資源庫_藍點);
                return;//一次只操作一張牌，找到後返回
            }
        }
//2014-5-19-max 針對奇蹟的部分
        for (AgesCard card : 建造中的奇蹟區) {
            if (card.getId() == id) {
                wonderStages.remove(0);
                this.內政點數.addPoints(-1);
                if (wonderStages.size() == 0) {
                    moveOneCard(建造中的奇蹟區, 0, 已完成的奇蹟);
                    int temp = 內政手牌上限.getVal();
                    update手牌上限();
//                    
                    if (內政手牌上限.getVal() != temp) {
                        System.out.println("************* 內政手牌上限 HAS BEEN CHANGED , SHOULD WE ADD 內政點數 TOKEN NOW?????");
                        System.out.println("max-2014-5-19，以金字塔為例建造完成後立即生效行動:獲得一點內政行動");
                        System.out.println("手牌上限值要+1");
                        System.out.println("內政點數也要+1，如同在遊戲盒裡拿出一個白色標記");
                    }
                }
                return;//一次只操作一張牌，找到後返回
            }
        }
//如果要建造的不在實驗室、神廟區、農場區、礦山區、步兵區
//如果不是奇蹟的建造
//提示該指令無效        
        System.out.println("your assigned ID " + id + " IS NOT FOUND???");
    }

    public void actAct(int id) {
//2014-5-19-max 針對實驗室、神廟區、農場區、礦山區、步兵區
        List<AgesCard> actbuildList = new ArrayList<>();
        actbuildList.addAll(實驗室);
        actbuildList.addAll(神廟區);
        actbuildList.addAll(農場區);
        actbuildList.addAll(礦山區);
        actbuildList.addAll(步兵區);
        System.out.println(get行動牌暫存區().get(0).getName() + "  效果:" + get行動牌暫存區().get(0).getAction());
        for (AgesCard card : actbuildList) {
            if (card.getId() == id) {
                System.out.println("目標卡名:" + card.getName());

                switch (get行動牌暫存區().get(0).getId()) {
                    case 1013:
                        System.out.println("處理1013富饒之土");
                        
                        break;
                    default:
                        System.out.println("待處理中");
                }
            }
            System.out.println("your assigned ID " + id + " IS NOT FOUND???");
        }
    }

    public void act摧毀(int id) {
//            System.out.println("這是在player裡面的");
//            this.農場區.get(0).setTokenYellow(this.農場區.get(0).getTokenYellow() + 1);
            /* OLD STYLE, ONE BY ONE
         for (AgesCard card : 農場區) {
         if (card.getId() == id) {
         card.setTokenYellow(card.getTokenYellow() - 1);
         this.工人區_黃點.addPoints(1);
         //                    System.out.println(" " + key + " " + getSameSizeName(card.getName()) + " " + card.getAction());
         }
         }
         for (AgesCard card : 礦山區) {
         if (card.getId() == id) {
         card.setTokenYellow(card.getTokenYellow() - 1);
         this.工人區_黃點.addPoints(1);
         //                    System.out.println(" " + key + " " + getSameSizeName(card.getName()) + " " + card.getAction());
         }
         }
         */

        List<AgesCard> buildList = new ArrayList<>();
        buildList.addAll(實驗室);
        buildList.addAll(神廟區);
        buildList.addAll(農場區);
        buildList.addAll(礦山區);
        buildList.addAll(步兵區);
        for (AgesCard card : buildList) {
            if (card.getId() == id) {
                card.setTokenYellow(card.getTokenYellow() - 1);
                this.工人區_黃點.addPoints(+1);
                return;
            }
        }

    }

    public void act擴充人口() {
        System.out.println("現在要擴充人口了");
//            this.農場區.get(0).setTokenYellow(this.農場區.get(0).getTokenYellow() + 1);
        this.人力庫_黃點.addPoints(-1);
        this.工人區_黃點.addPoints(1);

    }

    private String name;
    private Points 內政點數;
    private Points 軍事點數;
    private Points 建築上限;
    private Points 內政手牌上限;
    private Points 軍事手牌上限;
    private Points 殖民點數;
    private Points 文化;
    private Points 文化生產_當回合;
    private Points 科技;
    private Points 科技生產_當回合;
    private Points 軍力;
    private Points 資源庫_藍點;
    private Points 人力庫_黃點;
    private Points 笑臉_幸福指數;
    private Points 工人區_黃點;
    private List<AgesCard> 領袖區;
    private List<AgesCard> 政府區;
    private List<AgesCard> 實驗室;
    private List<AgesCard> 神廟區;
    private List<AgesCard> 農場區;
    private List<AgesCard> 礦山區;
    private List<AgesCard> 步兵區;
    private List<AgesCard> 未分類區;
// [09:54:37] maxchen20041: 請按照步兵區方式
// [09:55:19] maxchen20041: 建立騎兵區、炮兵區、飛機區、劇院區、圖書館區、競技場區
    private List<AgesCard> 騎兵區;
    private List<AgesCard> 炮兵區;
    private List<AgesCard> 飛機區;
    private List<AgesCard> 劇院區;
    private List<AgesCard> 圖書館區;
    private List<AgesCard> 競技場區;

    private List<AgesCard> 建造中的奇蹟區;
    private List<Integer> wonderStages;

    private List<AgesCard> 已完成的奇蹟;
    private List<AgesCard> 殖民領土區;
    private List<AgesCard> 特殊科技區;
    private List<AgesCard> 手牌內政牌區;
    private List<AgesCard> 行動牌區;
    private List<AgesCard> 手牌軍事牌區;

    public void init() {
        內政點數 = new Points("內政點數");
        軍事點數 = new Points("軍事點數");

        建築上限 = new Points("建築上限");
        內政手牌上限 = new Points("內政手牌上限");
        軍事手牌上限 = new Points("軍事手牌上限");
        殖民點數 = new Points("殖民點數");
        文化 = new Points("文化");
        文化生產_當回合 = new Points("文化十");//
        科技 = new Points("科技");
        科技生產_當回合 = new Points("科技十");
        軍力 = new Points("軍力");
        資源庫_藍點 = new Points("資源庫【藍】");
        人力庫_黃點 = new Points("人力庫【黃】");
        笑臉_幸福指數 = new Points("笑臉【】");
        工人區_黃點 = new Points("工人區【黃】");
        領袖區 = new ArrayList<>();
        政府區 = new ArrayList<>();
        實驗室 = new ArrayList<>();
        神廟區 = new ArrayList<>();
        農場區 = new ArrayList<>();
        礦山區 = new ArrayList<>();
        步兵區 = new ArrayList<>();
        未分類區 = new ArrayList<>();
// [09:54:37] maxchen20041: 請按照步兵區方式
// [09:55:19] maxchen20041: 建立 騎兵區、炮兵區、飛機區、劇院區、圖書館區、競技場區            
        騎兵區 = new ArrayList<>();
        炮兵區 = new ArrayList<>();
        飛機區 = new ArrayList<>();
        劇院區 = new ArrayList<>();
        圖書館區 = new ArrayList<>();
        競技場區 = new ArrayList<>();

        建造中的奇蹟區 = new ArrayList<>();
        wonderStages = new ArrayList<>();

        已完成的奇蹟 = new ArrayList<>();
        殖民領土區 = new ArrayList<>();
        特殊科技區 = new ArrayList<>();
        手牌內政牌區 = new ArrayList<>();
        行動牌區 = new ArrayList<>();
        手牌軍事牌區 = new ArrayList<>();

    }

    public void update手牌上限() {
//        int new回合內政點數 = 政府區.get(0).getEffectWhite();
//        int new回合軍事點數 = 政府區.get(0).getEffectRed();
////            System.out.println(""+政府區.get(0));
//        if (領袖區.size() == 1) {
//            AgesCard leader = 領袖區.get(0);
//            if (leader.getId() == 1023) {// A漢摩拉比1023-內政行動CA+1 軍事行動MA-1】
//                new回合內政點數++;
//                new回合軍事點數--;
//            }
//            if (leader.getId() == 1009) { //【A凱薩1009-軍事力量+1 軍事行動 MA+1】
////                    new回合內政點數++;
//                new回合軍事點數++;
//            }
//        }
//        內政手牌上限.setVal(new回合內政點數);
//        軍事手牌上限.setVal(new回合軍事點數);
        int white = 0;
        int red = 0;
        List<AgesCard> buildList = new ArrayList<>();
        buildList.addAll(政府區);
        buildList.addAll(領袖區);
        buildList.addAll(已完成的奇蹟);
        for (AgesCard card : buildList) {
            if (card.getEffectWhite() > 0) {
                white += card.getEffectWhite();
            }
            if (card.getEffectRed() > 0) {
                red += card.getEffectRed();
            }

        }

        內政手牌上限.setVal(white);
        軍事手牌上限.setVal(red);
        System.out.println("內政手牌上限，軍事手牌上限剛剛更新");
    }

    public void show建造中的奇蹟區Stages() {

        System.out.print("  建造中的奇蹟區Stages ");
        for (Integer stage : wonderStages) {
            System.out.print(" " + stage);
        }
    }

    public void show(List<AgesCard> list, String title) {

        switch (title) {

            case "政府區":
                System.out.println("  ");
                System.out.print("  " + title + " ");
                for (AgesCard card : list) {
                    System.out.print("" + card.toString(1));
                }
                break;
            case "領袖區":
                System.out.print("  " + title + " ");
                for (AgesCard card : list) {
                    System.out.print("" + card.toString(101));
                }
                break;
            case "建造中的奇蹟區":
                System.out.print("\n  " + title + " ");
                for (AgesCard card : list) {
                    System.out.print("" + card.toString(102));
                }
                break;
            case "已完成的奇蹟":
                System.out.print("\n  " + title + " ");
                for (AgesCard card : list) {
                    System.out.print("" + card.toString(103));
                }
                break;

            case "實驗室":
            case "神廟區":
            case "步兵區":

                System.out.println("  ");
                System.out.print("  " + title + " ");
                for (AgesCard card : list) {
                    System.out.print("" + card.toString(104));
                }
                break;

            case "農場區":
            case "礦山區":
                System.out.println("  ");
                System.out.print("  " + title + " ");
                for (AgesCard card : list) {
                    System.out.print("" + card.toString(8));
                }
                break;
            case "行動牌區":
                System.out.println("  ");
                System.out.print("" + title + " ");
                int j = 0;
                for (AgesCard card : list) {
                    System.out.print("" + (j++) + card.toString(105));
                }
                break;
            case "手牌內政牌區":
                System.out.println("  ");
                System.out.print("" + title + " ");
                int k = 0;
                for (AgesCard card : list) {
                    System.out.print("" + (k++) + card.toString(4));
                }
                break;
            default:
                System.out.println("");
                System.out.print("" + title + " ");
                for (AgesCard card : list) {
                    System.out.print("" + card.toString(4));

                }
        }

    }

    public void show() {
        System.out.println("\n  === " + name + " ===");
        內政點數.show();
        軍事點數.show();
        建築上限.show();
//            System.out.println("");
        內政手牌上限.show();
        軍事手牌上限.show();
        殖民點數.show();
        System.out.println("");
        文化.show();
        文化生產_當回合.show();
        科技.show();
        科技生產_當回合.show();
        軍力.show();
        System.out.println("");
        資源庫_藍點.show();
        人力庫_黃點.show();
        笑臉_幸福指數.show();
        工人區_黃點.show();
        System.out.println("");
        show(政府區, "政府區");
        show(領袖區, "領袖區");

        show(實驗室, "實驗室");
        show(神廟區, "神廟區");
        show(農場區, "農場區");
        show(礦山區, "礦山區");
        show(步兵區, "步兵區");
        show(未分類區, "未分類區");
        show(建造中的奇蹟區, "建造中的奇蹟區");
        show建造中的奇蹟區Stages();
        show(已完成的奇蹟, "已完成的奇蹟");
        show(手牌內政牌區, "手牌內政牌區");
        show(行動牌區, "行動牌區");
        show(手牌軍事牌區, "手牌軍事牌區");
        System.out.println("");

    }

    public void actPlayCard(int val) {
        if (val > 手牌內政牌區.size() - 1) {
            System.out.println("我無法作出這個動作，我這個位置沒有牌");
            return;
        }
        AgesCard card = this.手牌內政牌區.get(val);
//        System.out.println(""+card.getCostRevolution());
        System.out.println("打出這張牌需要花費(" + card.getCostIdea() + ")科技");
        switch (card.getTag()) {
            case "農場":
                System.out.println("現在打的是農場牌準本要放到農場區");
                moveOneCard(this.手牌內政牌區, val, this.農場區);
                this.科技.addPoints(-card.getCostIdea());
                break;
            case "領袖":
                System.out.println("***REPLACE CURRENT ONE");
                while (領袖區.size() > 0) {
                    領袖區.remove(0);
                }
                moveOneCard(this.手牌內政牌區, val, this.領袖區);
                break;
            case "行動":
                System.out.println("打行動牌");
                while (行動牌區.size() > 0) {
                    行動牌區.remove(0);
                }
                moveOneCard(this.手牌內政牌區, val, this.行動牌區);
                break;
            case "礦山":
                moveOneCard(this.手牌內政牌區, val, this.礦山區);
                this.科技.addPoints(-card.getCostIdea());
                break;
            case "實驗室":
                moveOneCard(this.手牌內政牌區, val, this.實驗室);
                this.科技.addPoints(-card.getCostIdea());
                break;
            case "神廟":
                moveOneCard(this.手牌內政牌區, val, this.神廟區);
                this.科技.addPoints(-card.getCostIdea());
                break;
            case "步兵":
                moveOneCard(this.手牌內政牌區, val, this.步兵區);
                this.科技.addPoints(-card.getCostIdea());
                break;
//                case "騎兵":
//                moveOneCard(this.手牌內政牌區, val, this.騎兵);
//                break;
//                    case "炮兵":
//                moveOneCard(this.手牌內政牌區, val, this.炮兵);
//                break;
//                        case "飛機":
//                moveOneCard(this.手牌內政牌區, val, this.飛機);
//                break;
//                            case "競技場":
//                moveOneCard(this.手牌內政牌區, val, this.競技場區);
//                break;
//                                case "圖書館":
//                moveOneCard(this.手牌內政牌區, val, this.圖書館);
//                break;

            case "政府":
                System.out.println("***REPLACE CURRENT ONE");

//                    System.out.print("" + card.toString(1));
                while (政府區.size() > 0) {
                    政府區.remove(0);
                }

                moveOneCard(this.手牌內政牌區, val, this.政府區);
                this.科技.addPoints(-card.getCostIdea());
                break;
            default:
//                    System.out.println("");
                System.out.print("" + card.toString(1));
                moveOneCard(this.手牌內政牌區, val, this.未分類區);
        }

        //
        // 06/16 13:30, by Mark
        //
        update手牌上限();
    }

    /**
     *
     * @param p1 from
     * @param p2 to
     */
    public void actUpgrade(int p1, int p2) {

        List<AgesCard> buildList = new ArrayList<>();
        buildList.addAll(實驗室);
        buildList.addAll(神廟區);
        buildList.addAll(農場區);
        buildList.addAll(礦山區);
        buildList.addAll(步兵區);
        System.out.println(" checking 實驗室|神廟區|農場區|礦山區|步兵區, how many cards now? " + buildList.size());

        for (AgesCard card : buildList) {
            if (card.getId() == p1) {
                card.setTokenYellow(card.getTokenYellow() - 1);
                break;
            }
        }

        for (AgesCard card : buildList) {
            if (card.getId() == p2) {
                card.setTokenYellow(card.getTokenYellow() + 1);
                break;
            }
        }

    }

    public void compute當回合文化and科技and軍力() {
        System.out.println("compute當回合文化and科技");
        // 1. Prepare source
        List<AgesCard> list = new ArrayList<>();

//        System.out.println("CURRENTLY ONLY CHECK 已完成的奇蹟");
        list.addAll(政府區);
        list.addAll(領袖區);
        list.addAll(實驗室);
        list.addAll(神廟區);
        list.addAll(農場區);
        list.addAll(步兵區);
        list.addAll(已完成的奇蹟);

        // 2. Process
        int 音樂 = 0;
        int 燈泡 = 0;
        int 武器 = 0;

        for (AgesCard card : list) {
            if (card.getTokenYellow() > 0) {

                if (card.getEffectMusic() != 0) {
                    System.out.println(".....getEffectMusic " + card.toString(103));

                    音樂 += card.getEffectMusic();
                }
                if (card.getEffectIdea() != 0) {

                    System.out.println("######getEffectIdea " + card.toString(103));
                    燈泡 += card.getEffectIdea();
                }
                if (card.getEffectWeapon() != 0) {

                    System.out.println("@@@@getEffectWeapon " + card.toString(103));
                    武器 += card.getEffectWeapon();
                }
            }

        }

        // 3. Update result
        文化生產_當回合.setVal(音樂);
        科技生產_當回合.setVal(燈泡);
        軍力.setVal(武器);

    }

    public void refill內政點數軍事點數() {
        內政點數.setVal(內政手牌上限.getVal());
        軍事點數.setVal(軍事手牌上限.getVal());

    }

}

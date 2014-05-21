/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.livehereandnow.ages.engine;

//import com.livehereandnow.ages.card.Card;
import com.livehereandnow.ages.card.AgesCard;
import com.livehereandnow.ages.exception.AgesException;
import com.livehereandnow.ages.field.Field;
import com.livehereandnow.ages.field.Player;
import com.livehereandnow.ages.server.AgesGameServerJDBC;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

/**
 *
 * @author mark
 */
public class AgesEngine {

    private AgesGameServerJDBC server;
//    private EngineCore core;
    private Field field;
    private Player player;
    private AgesCard card;

    public Field getField() {
        return field;
    }

    public AgesEngine() throws AgesException {
        server = new AgesGameServerJDBC();
        init();

    }

    private void init() {
        field = new Field();
        field.reset();
    }

    String returnStr = " return str...";

    public String getFeedback() {
//        return core.getCardRowInfo();
//        return core.getFeedback();
//        return returnStr;
        return "...";
    }

    public void setFeedback(String str) {
        returnStr = str;
    }

    public boolean parser(String cmd) throws IOException, AgesException {
        //
        // 1. init
        //
        int tokenCnt = 0;//命令行裡共有幾個字，給予初值為0
        String keyword = "";//指令是什麼，給予初值空字符串
        int p1 = -1;//指令的參數是什麼，給予初值為-1，-1通常是指不能用的值
        int p2 = -1;
        int p3 = -1;

        //
        // 2. parser to words 
        //
        //將命令行的句子拆解為字，以空格格開為依據，空格都不記
        String[] strTokens = cmd.split(" ");
        List<String> tokens = new ArrayList<>();
        for (String item : strTokens) {
            if (item.length() > 0) {
                tokens.add(item);
            }
        }
        tokenCnt = tokens.size();//賦予變量tokenCnt真正的值，真正的值是指到底打個幾個字

        //
        // 3. to execute command based on size
        //
        if (tokenCnt == 0) {//when simple enter
            return true; // silently ignore it
        }
        // 
        keyword = tokens.get(0);//指令的關鍵字是第0個字，例如take 3的take

        if (tokenCnt == 1) {//如果輸入的是一個字的話
            return doCmd(keyword);
        }
        if (tokenCnt == 2) {//如果輸入的是2個字的話
            try {
                p1 = Integer.parseInt(tokens.get(1));
            } catch (Exception ex) {
                System.out.println("Parameter must be integer!");
                return false;
            }
            return doCmd(keyword, p1);
        }

        if (tokenCnt == 3) {//如果輸入的是2個字的話
            try {
                p1 = Integer.parseInt(tokens.get(1));
                p2 = Integer.parseInt(tokens.get(2));
            } catch (Exception ex) {
                System.out.println("Parameter must be integer!");
                return false;
            }
            return doCmd(keyword, p1, p2);
        }

        // ver 0.62 for upgrad 3 0 1, Upgrad Farm from Age A to Age I
        if (tokenCnt == 4) {//如果輸入的是3個字的話
            try {
                p1 = Integer.parseInt(tokens.get(1));
                p2 = Integer.parseInt(tokens.get(2));
                p3 = Integer.parseInt(tokens.get(3));
            } catch (Exception ex) {
                System.out.println("Parameter must be integer!");
                return false;
            }
            return doCmd(keyword, p1, p2, p3);
        }

        //
//        System.out.println("Cureently command must be one or two words only!");
        setFeedback("   unknown command," + cmd + ", just ignore it!");
//        setFeedback();

        return false;

    }

//    public EngineCore getCore() {
//        return core;
//    }
//    public Player getCurrentPlayer() {
//        return core.get當前玩家();
//    }
//    public String doProtocol(String cmd) throws IOException, AgesException {
////        core.getRoundNum();
//        switch (cmd) {
//            case "history":
//                return core.getHistory();
//            default:
//                return core.getCardRowInfo();
//        }
//
////        return core.NOCARD.toString();
//    }
//    public String doUserCmd(String user, String cmd) throws IOException, AgesException {
//
//        if (user.equalsIgnoreCase("admin")) {
//            parser(cmd);
//            return core.getFeedback();
//        }
//
//        if (core.get當前玩家().getName().equalsIgnoreCase(user)) {
//            if (parser(cmd)) {
//                return core.getFeedback();
//
//            } else {
//                return "unknown command, " + cmd;
//            }
//
//        } else {
//            return "   " + user + ", not your turn!";
//        }
//    }
    public boolean doCmd(int id) throws IOException, AgesException {
//        return field.
        field.showCardInfo(id);

        return true;
    }

    public boolean doCmd(String keyword) throws IOException, AgesException {
        switch (keyword) {
            case "server":
                return doServer();
            case "new-game":
                return doNewGame();
            case "p"://工人區_黃點 
                return doPopulation();

//            case "brief":
//                return core.doBrief();
//            case "d"://v0.59
//            case "debug"://v0.59
//                return core.doDebug();
                case "act":
                return actActV1();
            case "list":
                return doList();
            case "start":
                return doStart();
            case "increase-population"://v0.52
            case "population"://v0.52
//                return core.doIncreasePopulation();
            case "revolution"://v0.39
//                return core.doRevolution();
            case "govt"://v0.39
            case "change-government"://v0.39
//                return core.doChangeGovernment();

            case "construct-wonder":
            case "wonder":
            case "w":
//                return core.doConstructWonder();

            case "farm": {
                System.out.println("請改用b 3 0");
//                return core.doFarm();
                return true;
            }
            case "help":
//                return core.doHelp();
            case "h":
//                return core.doHelpShort();
            case "s":
            case "status":
//                return core.doStatus();
                field.show(0);
                return true;
            case "version":
                return doVersion();

            case "change-turn":
            case "c":
            case "":
                return doChangeTurn();
            case "cc":

                return doChangeTurnV2();
            case "to":
            case "tt":

                return do拿牌打牌測試用();

            default:
                System.out.println("Unknown keyword, " + keyword);
                return false;
        }
    }

    public boolean doCmd(String keyword, int val) throws IOException, AgesException {
        switch (keyword) {
            case "status":
            case "s":
                return doStatus(val);
            case "list":
                return doList(val);

            case "b":
            case "build":
                return actBuild(val);
                
            case "act":
                return actAct(val);
            case "打":
            case "p":
            case "o":
            case "out":

            case "play":
            case "play-card":
            case "out-card":
                return actPlayCard(val);
            case "oo":
//                return core.doPlayCard革命(val);
            case "拿"://在我的環境NetBeans無法執行，但是在DOS可以
            case "拿牌":
            case "t":
            case "take":
            case "take-card":
                return actTakeCard(val);

            default:
                System.out.println("Unknown keyword, " + keyword);
                return false;
        }
    }

    public boolean doCmd(String keyword, int p1, int p2) throws IOException, AgesException {
        switch (keyword) {
            case "upgrade":
            case "u":
                return actUpgrade(p1, p2);

            default:
                System.out.println("Unknown keyword, " + keyword);
                return false;
        }
    }

    public boolean doCmd(String keyword, int p1, int p2, int p3) throws IOException, AgesException {
        switch (keyword) {
            case "upgrade":
            case "u":
//                return core.doUpgrade(p1, p2, p3);

            default:
                System.out.println("Unknown keyword, " + keyword);
                return false;
        }
    }

    public boolean doVersion() {
        //  System.out.println(" TODO   [A內政-亞歷山大圖書館 科技生產+1，文化生產+1，內政手牌上限+1，軍事手牌上限+1]  ");
        //getBuildingLimit()

        System.out.println("  === ver 0.5 ===  2014-5-12, 12:32, by Max　");
        System.out.println("    1.處理回合數內的生產");

        System.out.println("  === ver 0.4 ===  2014-5-12, 12:32, by Max　");
        System.out.println("    1.要處理回合數");

        System.out.println("  === ver 0.3 ===  2014-5-12, 11:45, by Max　");
        System.out.println("    1.準備作交換玩家");
        System.out.println("  === ver 0.2 ===  2014-5-12, 10:05, by Max　");
        System.out.println("    Done 需要增加setCurrentPlayer");
        System.out.println("    1.建立騎兵區、炮兵區、飛機區、劇院區、圖書館區、競技場區");
        System.out.println("  === ver 0.1 ===  2014-5-10, 16:47, by Max　");
        System.out.println("    1. 建立遊戲引擎的變量，能夠運作的地方");

        return true;
    }

    public String getCurrentPlayer() {
        return field.getCurrentPlayer().getName();
    }

    private boolean doNewGame() {
        field.reset();
        return true;
    }

    private boolean actPlayCard(int val) {
        field.getCurrentPlayer().actPlayCard(val);
        return true;
    }

    private boolean actTakeCard(int val) {
        int[] cardCost = {1, 1, 1, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3};
        int cost = cardCost[val];
        player = field.getCurrentPlayer();

//        目前拿牌不扣內政點
//        if (player.get內政點數().getVal() < cardCost[cost]) {
//            System.out.println("NOT ENOUGH 內政點數");
//            return false;
//        }
        AgesCard card = field.getCardRow().get(val);//宣告 card，並將卡牌列裡的指定編號的卡牌指定到這個card變量裡
        if (card.getId() == 1000) {
            System.out.println("這裡沒有牌");
            return true;
        }

        //奇蹟 is the only one not go to ON-HAND
        if (card.getTag().equals("奇蹟")) {
            List<AgesCard> list = field.getCurrentPlayer().get建造中的奇蹟區();
            while (list.size() > 0) { // easy for dev
                list.remove(0);
            }
            list.add(card);//當前玩家的手牌加入上一行的card
            field.getCardRow().remove(val);//從卡牌列拿掉剛剛那張card

            field.getCardRow().add(val, field.getNOCARD());//在卡牌列同樣的位子，補上一張空卡

            //
            // to start to track stages
            //
            field.getCurrentPlayer().init建造中的奇蹟區();

            player.pay內政點數(cost);
        } else {//除了奇蹟牌以外
            field.getCurrentPlayer().get手牌內政牌區().add(card);//當前玩家的手牌加入上一行的card
            field.getCardRow().remove(val);//從卡牌列拿掉剛剛那張card
            field.getCardRow().add(val, field.getNOCARD());//在卡牌列同樣的位子，補上一張空卡
            player.pay內政點數(cost);
        }
        return true;
    }

    private boolean doStart() {
        //
        field.reset();

        //卡牌列
        for (int k = 0; k < 13; k++) {
            field.moveOneCard(field.get時代A內政牌(), 0, field.getCardRow());
        }

        // AAA 內政點數=1, BBB 內政點數=2
        for (int turnOrder = 1; turnOrder <= field.getAllPlayers().size(); turnOrder++) {
            Player player = field.getCurrentPlayer();
            player.get內政點數().setVal(turnOrder);
//            player.update手牌上限();
            field.交換玩家();
        }
        //
        //
        //
        return true;
    }

    private boolean doChangeTurn() {

        //檢測暴動();
        field.getCurrentPlayer().compute當回合文化and科技and軍力();

        //執行生產
        field.getCurrentPlayer().produce();

        //腐敗();
        field.交換玩家();
        // before turn
        if (field.getRound().getVal() == 1) {
            return true;
        }

        field.getCurrentPlayer().update手牌上限();
        field.getCurrentPlayer().refill內政點數軍事點數();
        補牌();
        return true;
    }

    private boolean doChangeTurnV2() {
        for (int k = 0; k < 46; k++) {
            doChangeTurn();
        }
        return true;
    }

    private boolean doStatus(int val) {
        field.show(val);
        return true;
    }

    private void 補牌() {
//        System.out.println("在這裡作補牌");

//        移除前三張，實際作法是移除前三張增加三張三張空卡
        field.getCardRow().remove(0);
        field.getCardRow().remove(0);
        field.getCardRow().remove(0);
        AgesCard temp = new AgesCard();
        temp.setId(1000);
        temp.setName("");
        temp.setAge(4);
        temp.setTag("");
//        field.getCardRow().add(0, temp);//在卡牌列同樣的位子，補上一張空卡
//        field.getCardRow().add(0, temp);//在卡牌列同樣的位子，補上一張空卡

//      左推
        for (int k = 0; k < field.getCardRow().size(); k++) {
            if (field.getCardRow().get(k).getId() == 1000) {
                field.getCardRow().remove(k);
                k--;

            }
        }
        for (int k = field.getCardRow().size(); k < 13; k++) {

            if (field.get時代A內政牌().size() != 0) {
                field.moveOneCard(field.get時代A內政牌(), 0, field.getCardRow());
                for (int x = 0; x < field.get時代A內政牌().size(); x++) {
                    field.get時代A內政牌().remove(0);
                }
            } else if (field.get時代I內政牌().size() != 0) {
                field.moveOneCard(field.get時代I內政牌(), 0, field.getCardRow());
            } else if (field.get時代II內政牌().size() != 0) {
                field.moveOneCard(field.get時代II內政牌(), 0, field.getCardRow());
            } else if (field.get時代III內政牌().size() != 0) {
                //如果還有牌

                field.moveOneCard(field.get時代III內政牌(), 0, field.getCardRow());
            } else if (field.get時代III內政牌().size() == 0) {
                System.out.println("沒牌了");
                field.getCardRow().add(k, temp);//在卡牌列同樣的位子，補上一張空卡
                if (field.getCurrentPlayer() == field.getP1()) {
                    System.out.println("遊戲要結束了");
                } else {
                    System.out.println("遊戲下回合要結束了");
                }
            }
        }

    }

//  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    private boolean doList() {
//        Set<AgesCard> set=new HashSet(field.getQryCards());

        doList(0);
        return true;
    }

    private String getSameSizeName(String name) {
        int times = 8 - name.length();
        String cnSpace = "\u3000";
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < times; k++) {
            sb.append("\u3000");
        }
        return name + sb.toString();
    }

    private boolean doList(int style) {

        Map<Integer, AgesCard> map = new HashMap<>();

        for (AgesCard card : field.getQryCards()) {
            map.put(card.getId(), card);
//           System.out.println(" "+card.getId()+" "+card.getName());
        }

//        System.out.println(" map size is " + map.size());
        Set<Integer> idSet = map.keySet();
        List<Integer> idList = new ArrayList<>(idSet);
        Collections.sort(idList);
        for (Integer key : idList) {
            AgesCard card = map.get(key);
            switch (style) {
                case 0:
                    System.out.println(card.getTag() + " " + key + " " + getSameSizeName(card.getName()) + " " + card.getAction().trim() + " " + card.getIconPoints() + " " + card.getEffect());
                    break;
                case 1:
                    if (card.getAction().length() > 0) {
                        System.out.println(" " + key + " " + getSameSizeName(card.getName()) + " " + card.getAction());

//                        \u3000
                    }
                    break;
                case 2:
                    if (card.getIconPoints().length() > 0) {

                        System.out.println(" " + key + " " + card.getName() + " " + card.getIconPoints());
                    }
                    break;
                case 3:
                    if (card.getEffect().length() > 0) {
                        System.out.println(" " + key + " " + card.getName() + " " + card.getEffect());
                    }
                    break;
                case 4:
                    if (card.getEffectWhite() > 0) {
                        System.out.println(card.getTag() + " " + key + " " + card.getName() + " +白點: " + card.getEffectWhite());
                    }
                    break;
                case 5:
                    if (card.getEffectRed() > 0) {
                        System.out.println(" " + key + " " + card.getName() + " +紅點: " + card.getEffectRed());
                    }
                    break;
                case 6:
                    if (card.getEffectSmile() > 0) {
                        System.out.println(" " + key + " " + card.getName() + " +笑臉: " + card.getEffectSmile());
                    }
                    break;
                case 7:
                    if (card.getTag().equals("行動")) {
               System.out.println("時代:"+card.getAge()+" "+card.getTag() + " " + key + " " + getSameSizeName(card.getName()) + " " + card.getAction().trim() + " " + card.getIconPoints() + " " + card.getEffect());
                         }
                    break;
                        
                default:
                    System.out.println(" " + key + " " + getSameSizeName(card.getName()) + " " + card.getAction().trim() );

            }
        }
        System.out.println("");
        System.out.println(" list 4      SHOW +白點");
        System.out.println(" list 5      SHOW +紅點");
        System.out.println(" list 6      SHOW 笑臉");
         System.out.println(" list 7      SHOW TAG=行動");

        return true;

    }

    private boolean actBuild(int val) {
        System.out.println("按卡號build,適合所有的情況, including 奇蹟區");
        field.getCurrentPlayer().actBuild(val);

        return true;
    }
       private boolean actAct(int val) {
        System.out.println("執行行動牌");
//           System.out.println(field.getCurrentPlayer().get行動牌暫存區().get(0).getName()+"  效果:"+field.getCurrentPlayer().get行動牌暫存區().get(0).getAction());
                 field.getCurrentPlayer().actAct(val);

        return true;
    }

    private boolean do拿牌打牌測試用() {
//        for(int k=0;)
        actTakeCard(0);
        actPlayCard(0);

        actTakeCard(1);
        actPlayCard(0);

        actTakeCard(2);
        actPlayCard(0);

        actTakeCard(3);
        actPlayCard(0);

        actTakeCard(4);
        actPlayCard(0);
        doChangeTurn();
        field.show(1);
        return true;
    }

    private boolean doPopulation() {
        field.getCurrentPlayer().act擴充人口();
        return true;
    }

    private boolean actUpgrade(int p1, int p2) {
        field.getCurrentPlayer().actUpgrade(p1, p2);
        return true;
    }

    private boolean doServer() {
        String idx13 = field.getServerStatus();
        server.updateGameLiveCardRow(idx13);

        return true;
    }

    private boolean actActV1() {
              System.out.println("執行行動牌");
//           System.out.println(field.getCurrentPlayer().get行動牌暫存區().get(0).getName()+"  效果:"+field.getCurrentPlayer().get行動牌暫存區().get(0).getAction());
                 field.getCurrentPlayer().actActV1();

        return true;
    }
}

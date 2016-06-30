package com.example.wind.minstory.content;

import com.example.wind.minstory.R;

/**
 * Created by wind on 2016/6/29.
 */
public class GameModelData {
    private int[] img = {R.mipmap.games,R.mipmap.game1,R.mipmap.game2
            ,R.mipmap.game3,R.mipmap.game4,R.mipmap.game5,R.mipmap.game6
            ,R.mipmap.game7,R.mipmap.game8,R.mipmap.game9
    ,R.mipmap.game10,R.mipmap.game11,R.mipmap.game12,R.mipmap.game13,R.mipmap.game14};
    private String[] title = {"QooApp","偶像大師灰姑娘女孩 閃耀的舞台","白貓project/白貓計劃"
            ,"刀劍亂舞-ONLINE- Pocket","七雄戰記-最後的守護者","不思議の旅程"
            ,"OZ Chrono Chronicle","怪物彈珠 - 台灣版","櫻花與刀-極天下大戰之章"
            ,"火影忍者 忍Collection 疾風亂舞", "9イニングスマネジメント","魔法陣少女 信長的傳說"
    ,"高校之神","RunGun CannonBall","奇想之戰"};
    private String[] conntent = {"QooApp","アイドルマスター シンデレラガールズ スタ","白猫プロジェクト"
            ,"刀剣乱舞-ONLINE- Pocket","Seven Guardians","不思議の旅程"
            ,"OZ Chrono Chronicle","怪物彈珠","櫻花與刀-極天下大戰之章"
            ,"NARUTO -ナルト- 忍コレクション 疾風乱舞","9イニングスマネジメント"
    ,"魔法陣少女 ノブナガサーガ", "ゴッドオブハイスクール","ランガン キャノンボール"
            ,"ファンタジーウォータクティクス"};

    public int[] getImg() {
        return img;
    }

    public void setImg(int[] img) {
        this.img = img;
    }

    public String[] getTitle() {
        return title;
    }

    public void setTitle(String[] title) {
        this.title = title;
    }

    public String[] getConntent() {
        return conntent;
    }

    public void setConntent(String[] conntent) {
        this.conntent = conntent;
    }

    public String[] getOuther() {
        return outher;
    }

    public void setOuther(String[] outher) {
        this.outher = outher;
    }

    public String[] getTime() {
        return time;
    }

    public void setTime(String[] time) {
        this.time = time;
    }

    private String[] outher = {"by QooApp","by BANDAI NAMCO Entertainment Inc.","by COLOPL, Inc."
            ,"by DMM.com","by 4:33","by Gravity"
            ,"by DMM.com","by mixi, Inc.","by sakurasword"
            ,"by GREE Inc.","by Com2uS","by ACRODEA, INC.","by NC Japan K.K."
    ,"by SQUARE ENIX Co.,Ltd.","by NEXON Company"};
    private String[] time = {"V5.8.2","V2.0.0","V1.0.63"
            ,"V1.0.11","V1.1.35","V2.0.3"
            ,"V1.0.3","V5.4.0","V1.1901"
            ,"V2.10.0","V1.4.2","V1.0.3","預計配信：2016年夏季"
            ,"預計配信：2016年夏季","預計配信：2016年7月下旬"
    };

}

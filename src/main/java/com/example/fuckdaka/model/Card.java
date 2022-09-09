package com.example.fuckdaka.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Card {
    @Id
    private Long id;
    private Long qq;
    //region post数据
    /**
     * xhOrgh: 206102051402
     * tbrq: 2022-09-10 08:54:38
     * sffyhz: 0
     * sfgl: 0
     * jrstqk: 0
     * qtstqk:
     * twqk: 1
     * jjrStqk: 0
     * sfjc: 0
     * dqszd: 天心区
     * dqszsf: 湖南省
     * dqszs: 长沙市
     * szdf: 1
     * szqtdf:
     * qtqk:
     * lxdh: 18156551489
     * role:
     * jkmzp: uploadfile/undefined/1907029/IMG_7781.PNG
     * xcmzp: uploadfile/undefined/869934/IMG_7782.PNG
     */
    private String xhOrgh;
    /**
     * 填报日期，务必在使用时更新
     */
    private String tbrq;
    private String sffyhz = "0";
    private String sfgl = "0";
    private String jrstqk = "0";
    private String qtstqk = "";
    private String twqk = "1";
    private String jjrStqk = "0";
    private String sfjc = "0";
    private String dqszd = "天心区";
    private String dqszsf = "湖南省";
    private String dqszs = "长沙市";
    private String szdf = "1";
    private String szqtdf = "";
    private String qtqk = "";
    private String lxdh;
    private String role = "";
    private String jkmzp;
    private String xcmzp;
//endregion

    public String getTbrq() {
        if (tbrq == null) {
            throw new RuntimeException("填报日期，务必在使用时更新");
        }
        return tbrq;
    }
}

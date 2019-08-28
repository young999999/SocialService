package com.py.base.pojo;

import javax.persistence.*;
import java.io.Serializable;

//使用jpa配置映射关系
@Entity//告诉jpa这是一个实体类（和数据表映射的类）
@Table(name = "tb_label")//指定和哪个数据表对应，省略默认就是类名小写，即label
public class Label implements Serializable {

    //@GeneratedValue(strategy = GenerationType.IDENTITY)//自增主键
    @Id//这是一个主键
    private String id;//

    //@Column(name = "last_name",length = 50) //这是和数据表对应的一个列,省略默认列名就是属性名
    private String labelname;//标签名称
    private String state;//状态
    private Long count;//使用数量
    private Long fans;//关注数
    private String recommend;//是否推荐

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelname) {
        this.labelname = labelname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getFans() {
        return fans;
    }

    public void setFans(Long fans) {
        this.fans = fans;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }
}

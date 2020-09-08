package com.cxysl.entity;

//商品分类表			一对多
public class GoodsType {
    private int typeId;     //商品类别ID（主键）
    private String typeName;        //商品类别名称

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}

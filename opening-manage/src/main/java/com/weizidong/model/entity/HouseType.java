package com.weizidong.model.entity;

import java.io.Serializable;
import javax.persistence.*;

@Table(name = "house_type")
public class HouseType implements Serializable {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 户型名称
     */
    private String name;

    /**
     * 面积
     */
    private Double acreage;

    /**
     * 户型概述
     */
    private String description;

    /**
     * 户型规格
     */
    private String spec;

    /**
     * 户型图
     */
    private String pics;

    private static final long serialVersionUID = 1L;

    /**
     * 获取ID
     *
     * @return id - ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置ID
     *
     * @param id ID
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取户型名称
     *
     * @return name - 户型名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置户型名称
     *
     * @param name 户型名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取面积
     *
     * @return acreage - 面积
     */
    public Double getAcreage() {
        return acreage;
    }

    /**
     * 设置面积
     *
     * @param acreage 面积
     */
    public void setAcreage(Double acreage) {
        this.acreage = acreage;
    }

    /**
     * 获取户型概述
     *
     * @return description - 户型概述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置户型概述
     *
     * @param description 户型概述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取户型规格
     *
     * @return spec - 户型规格
     */
    public String getSpec() {
        return spec;
    }

    /**
     * 设置户型规格
     *
     * @param spec 户型规格
     */
    public void setSpec(String spec) {
        this.spec = spec;
    }

    /**
     * 获取户型图
     *
     * @return pics - 户型图
     */
    public String getPics() {
        return pics;
    }

    /**
     * 设置户型图
     *
     * @param pics 户型图
     */
    public void setPics(String pics) {
        this.pics = pics;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", acreage=").append(acreage);
        sb.append(", description=").append(description);
        sb.append(", spec=").append(spec);
        sb.append(", pics=").append(pics);
        sb.append("]");
        return sb.toString();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        HouseType other = (HouseType) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getAcreage() == null ? other.getAcreage() == null : this.getAcreage().equals(other.getAcreage()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getSpec() == null ? other.getSpec() == null : this.getSpec().equals(other.getSpec()))
            && (this.getPics() == null ? other.getPics() == null : this.getPics().equals(other.getPics()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getAcreage() == null) ? 0 : getAcreage().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getSpec() == null) ? 0 : getSpec().hashCode());
        result = prime * result + ((getPics() == null) ? 0 : getPics().hashCode());
        return result;
    }
}
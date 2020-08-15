package com.pinyougou.pojo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.print.DocFlavor;
import javax.print.attribute.standard.MediaSize;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "TB_ADDRESS")
public class TbAddress implements Serializable{
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "USER_ID")
    private String userId;

    @Column(name = "PROVINCE_ID")
    private String provinceId;

    @Column(name = "CITY_ID")
    private String cityId;

    @Column(name = "TOWN_ID")
    private String townId;

    @Column(name = "MOBILE")
    private String mobile;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "CONTACT")
    private String contact;

    @Column(name = "IS_DEFAULT")
    private String isDefault;

    @Column(name = "NOTES")
    private String notes;

    @Column(name = "CREATE_DATE")
    private String createDate;

    @Column(name = "ALIAS")
    private String alias;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId == null ? null : provinceId.trim();
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

    public String getTownId() {
        return townId;
    }

    public void setTownId(String townId) {
        this.townId = townId == null ? null : townId.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault == null ? null : isDefault.trim();
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes == null ? null : notes.trim();
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias == null ? null : alias.trim();
    }
}
package com.shop.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="item_img")
@Data
public class ItemImg {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="item_img_id")
	private Long id;
	
    @Column(name ="img_name")
	private String imgName;

    @Column(name ="ori_img_name")
	private String oriImgName;
	
    @Column(name ="img_url")
	private String imgUrl;
	
    @Column(name ="rep_img_yn")
	private String repImgYn;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "item_id")
	Item item;

	public void updateItemImg(String imgName, String oriImgName, String imgUrl) {
		this.imgName = imgName;
		this.oriImgName = oriImgName;
		this.imgUrl = imgUrl;
	}
	
	
	
	
	
	
}

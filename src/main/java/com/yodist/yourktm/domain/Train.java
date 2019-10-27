package com.yodist.yourktm.domain;

import javax.persistence.Id;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.yodist.yourktm.base.BaseEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=true)
@Document(collection = "train")
public class Train extends BaseEntity {

	public static final String TRAIN = "train";
	
	public static final String NAME = "name";
	public static final String TRAIN_CODE = "train_code";
	
	@Id
	@Field(_ID)
	private ObjectId id;
	
	@Field(NAME)
	private String name;
	
	@Field(TRAIN_CODE)
	private String trainCode;
	
	public Train() {
		super();
	}

	public String getId() {
		return id != null ? id.toHexString() : null;
	}

}

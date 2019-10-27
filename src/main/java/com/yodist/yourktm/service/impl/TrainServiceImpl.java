package com.yodist.yourktm.service.impl;

import static com.yodist.yourktm.util.MessageUtil.required;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.util.BsonUtils;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.UpdateResult;
import com.yodist.yourktm.domain.Train;
import com.yodist.yourktm.repository.TrainRepository;
import com.yodist.yourktm.service.TrainService;
import com.yodist.yourktm.util.CommonUtil;

@Service
public class TrainServiceImpl implements TrainService {

	@Autowired
	private TrainRepository repository;
	
	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * Only pass train entity with name and code, any basic information such as id
	 * and audit data is provided by populateTrain method (which is called by this
	 * method).
	 * 
	 * @param train with generated objectId
	 * @return populated Train data
	 */
	@Override
	public Train save(Train train) {
		Query query = new Query();
		Update update = new Update();
		populateTrain(train, update);
		query.addCriteria(Criteria.where(Train.TRAIN_CODE).is(train.getTrainCode()));
		UpdateResult result = mongoTemplate.upsert(query, update, Train.class);
		if (train.getIdAsString() == null) {
			ObjectId objectId = (ObjectId) BsonUtils.toJavaType(result.getUpsertedId());
			train.setId(objectId);
		}
		return train;
	}

	/**
	 * This method can save new data or update existing data.
	 * 
	 * @param trainList of not null train
	 * @return populated Train data in list
	 */
	@Override
	public List<Train> saveMultiple(List<Train> trainList) {
		for (Train train : trainList) {
			Query query = new Query();
			Update update = new Update();
			populateTrain(train, update);
			query.addCriteria(Criteria.where(Train.TRAIN_CODE).is(train.getTrainCode()));
			mongoTemplate.upsert(query, update, Train.class);
		}
		return trainList;
	}

	private void populateTrain(Train train, Update update) {
		Objects.requireNonNull(train, required(Train.TRAIN));
		Objects.requireNonNull(train.getTrainCode(), required(Train.TRAIN_CODE));
		Objects.requireNonNull(train.getName(), required(Train.NAME));
		String currentUser = CommonUtil.getUsername();
		Date curDate = CommonUtil.getDateForSave();
		update.set(Train.NAME, train.getName());
		if (train.getIdAsString() == null) {
			update.set(Train.CREATED_BY, currentUser);
			update.set(Train.CREATED_DATE, curDate);
		}
		update.set(Train.UPDATED_BY, currentUser);
		update.set(Train.UPDATED_DATE, curDate);
	}

	@Override
	public List<Train> findAll() {
		return repository.findAll();
	}

	@Override
	public Train findById(ObjectId id) {
		return repository.findById(id);
	}

	@Override
	public Train findByCode(String code) {
		return repository.findByTrainCode(code);
	}

	@Override
	public void delete(Train train) {
		repository.delete(train);
	}

}

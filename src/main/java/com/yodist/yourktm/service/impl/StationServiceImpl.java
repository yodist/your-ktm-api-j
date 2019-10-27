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
import com.yodist.yourktm.domain.Route;
import com.yodist.yourktm.domain.Station;
import com.yodist.yourktm.repository.StationRepository;
import com.yodist.yourktm.service.StationService;
import com.yodist.yourktm.util.CommonUtil;

@Service
public class StationServiceImpl implements StationService {

	@Autowired
	private StationRepository repository;

	@Autowired
	private MongoTemplate mongoTemplate;
	
	/**
	 * Only pass station entity with necessary data, any basic information such as
	 * id and audit data is provided by populateStation method (which is called by
	 * this method).
	 * 
	 * @param Station station
	 * @return populated Station data
	 */
	@Override
	public Station save(Station station) {
		Query query = new Query();
		Update update = new Update();
		populateStation(station, update);
		query.addCriteria(Criteria.where(Station.STATION_CODE).is(station.getStationCode()));
		UpdateResult result = mongoTemplate.upsert(query, update, Station.class);
		if (station.getIdAsString() == null) {
			ObjectId objectId = (ObjectId) BsonUtils.toJavaType(result.getUpsertedId());
			station.setId(objectId);
		}
		return station;
	}

	@Override
	public List<Station> saveMultiple(List<Station> stationList) {
		for (Station station : stationList) {
			Query query = new Query();
			Update update = new Update();
			populateStation(station, update);
			query.addCriteria(Criteria.where(Station.STATION_CODE).is(station.getStationCode()));
			mongoTemplate.upsert(query, update, Station.class);
		}
		return stationList;
	}

	private void populateStation(Station station, Update update) {
		Objects.requireNonNull(station, required(Station.STATION));
		Objects.requireNonNull(station.getName(), required(Station.NAME));
		Objects.requireNonNull(station.getStationCode(), required(Route.ROUTE));
		String currentUser = CommonUtil.getUsername();
		Date curDate = CommonUtil.getDateForSave();
		update.set(Station.NAME, station.getName());
		if (station.getIdAsString() == null) {
			update.set(Station.CREATED_BY, currentUser);
			update.set(Station.CREATED_DATE, curDate);
		}
		update.set(Station.UPDATED_BY, currentUser);
		update.set(Station.UPDATED_DATE, curDate);
	}

	@Override
	public List<Station> findAll() {
		return repository.findAll();
	}

	@Override
	public Station findById(ObjectId id) {
		return repository.findById(id);
	}

	@Override
	public Station findByCode(String code) {
		return repository.findByStationCode(code);
	}

	@Override
	public void delete(Station station) {
		repository.delete(station);
	}

}

package com.yodist.yourktm.service.impl;

import static com.yodist.yourktm.util.MessageUtil.required;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.util.BsonUtils;
import org.springframework.stereotype.Service;

import com.mongodb.client.result.UpdateResult;
import com.yodist.yourktm.domain.Route;
import com.yodist.yourktm.domain.Schedule;
import com.yodist.yourktm.domain.Station;
import com.yodist.yourktm.domain.Train;
import com.yodist.yourktm.repository.ScheduleRepository;
import com.yodist.yourktm.service.ScheduleService;
import com.yodist.yourktm.util.CommonUtil;
import com.yodist.yourktm.util.CustomDateUtil;

@Service
public class ScheduleServiceImpl implements ScheduleService {

	@Autowired
	private ScheduleRepository repository;

	@Autowired
	private MongoTemplate mongoTemplate;

	/**
	 * Only pass schedule entity with necessary data, any basic information such as
	 * id and audit data is provided by populateSchedule method (which is called by
	 * this method).
	 * 
	 * @param Schedule schedule
	 * @return populated Schedule data
	 */
	@Override
	public Schedule save(Schedule schedule) {
		Query query = new Query();
		Update update = new Update();
		populateSchedule(schedule, update);
		Criteria criteria = Criteria.where(Train.TRAIN).is(schedule.getTrain()).and(Station.STATION)
				.is(schedule.getTrain()).and(Route.ROUTE).is(schedule.getRoute());
		query.addCriteria(criteria);
		UpdateResult result = mongoTemplate.upsert(query, update, Schedule.class);
		if (schedule.getIdAsString() == null) {
			ObjectId objectId = (ObjectId) BsonUtils.toJavaType(result.getUpsertedId());
			schedule.setId(objectId);
		}
		return schedule;
	}

	@Override
	public List<Schedule> saveMultiple(List<Schedule> scheduleList) {
		for (Schedule schedule : scheduleList) {
			Query query = new Query();
			Update update = new Update();
			populateSchedule(schedule, update);
			Criteria criteria = Criteria.where(Train.TRAIN).is(schedule.getTrain()).and(Station.STATION)
					.is(schedule.getStation()).and(Route.ROUTE).is(schedule.getRoute());
			query.addCriteria(criteria);
			mongoTemplate.upsert(query, update, Schedule.class);
		}
		return scheduleList;
	}

	private void populateSchedule(Schedule schedule, Update update) {
		Objects.requireNonNull(schedule, required(Schedule.SCHEDULE));
		Objects.requireNonNull(schedule.getTrain(), required(Train.TRAIN));
		Objects.requireNonNull(schedule.getRoute(), required(Route.ROUTE));
		Objects.requireNonNull(schedule.getStation(), required(Station.STATION));
		Objects.requireNonNull(schedule.getTime(), required(Schedule.TIME));
		Objects.requireNonNull(schedule.getTimeString(), required(Schedule.TIME_STRING));
		Objects.requireNonNull(schedule.getSequence(), required(Schedule.SEQ));
		String currentUser = CommonUtil.getUsername();
		Date curDate = CommonUtil.getDateForSave();
		update.set(Train.TRAIN, schedule.getTrain());
		update.set(Route.ROUTE, schedule.getRoute());
		update.set(Station.STATION, schedule.getStation());
		update.set(Schedule.TIME, schedule.getTime());
		update.set(Schedule.TIME_STRING, schedule.getTimeString());
		update.set(Schedule.SEQ, schedule.getSequence());
		if (schedule.getIdAsString() == null) {
			update.set(Schedule.CREATED_BY, currentUser);
			update.set(Schedule.CREATED_DATE, curDate);
		}
		update.set(Schedule.UPDATED_BY, currentUser);
		update.set(Schedule.UPDATED_DATE, curDate);
	}

	@Override
	public List<Schedule> findAll() {
		return repository.findAll();
	}

	@Override
	public Schedule findById(ObjectId id) {
		return repository.findById(id);
	}

	@Override
	public void delete(Schedule schedule) {
		repository.delete(schedule);
	}

	@Override
	public List<Schedule> findScheduleByCriteria(Long currentTime, String routeCode, String stationCode) {
		long todayMinutes = CustomDateUtil.convertMsToTodayMinutes(currentTime);
		Query query = new Query();
		Criteria criteria = Criteria.where(Station.STATION + "." + Station.STATION_CODE).is(stationCode)
				.and(Schedule.TIME).gt(todayMinutes);
		if (StringUtils.isNotBlank(routeCode))
			criteria = criteria.and(Route.ROUTE + "." + Route.ROUTE_CODE).is(routeCode);
		query.addCriteria(criteria);
		query.with(new Sort(Sort.Direction.ASC, Schedule.TIME));
		query.isSorted();
		query.limit(5);
		return mongoTemplate.find(query, Schedule.class);
	}

}

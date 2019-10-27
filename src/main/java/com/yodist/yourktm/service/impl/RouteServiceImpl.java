package com.yodist.yourktm.service.impl;

import static com.yodist.yourktm.util.MessageUtil.required;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yodist.yourktm.domain.Route;
import com.yodist.yourktm.repository.RouteRepository;
import com.yodist.yourktm.service.RouteService;
import com.yodist.yourktm.util.CommonUtil;

@Service
public class RouteServiceImpl implements RouteService {

	@Autowired
	private RouteRepository repository;

	/**
	 * Only pass route entity with necessary data, any basic information such as
	 * id and audit data is provided by populateRoute method (which is called by
	 * this method).
	 * 
	 * @param Route route
	 * @return populated Route data
	 */
	@Override
	public Route save(Route station) {
		return repository.save(populateRoute(station));
	}

	@Override
	public List<Route> saveMultiple(List<Route> stationList) {
		for (Route station : stationList)
			station = populateRoute(station);
		return repository.saveAll(stationList);
	}

	private Route populateRoute(Route station) {

		// checking null
		Objects.requireNonNull(station, required(Route.ROUTE));
		Objects.requireNonNull(station.getName(), required(Route.NAME));
		Objects.requireNonNull(station.getRouteCode(), required(Route.ROUTE_CODE));

		String currentUser = CommonUtil.getUsername();
		Date curDate = CommonUtil.getDateForSave();

		station.setId(ObjectId.get());
		station.setCreatedBy(currentUser);
		station.setUpdatedBy(currentUser);
		station.setCreatedDate(curDate);
		station.setUpdatedDate(curDate);

		return station;
	}

	@Override
	public List<Route> findAll() {
		return repository.findAll();
	}

	@Override
	public Route findById(ObjectId id) {
		return repository.findById(id);
	}
	
	@Override
	public List<Route> findByName(String name) {
		return repository.findByName(name);
	}
	
	@Override
	public Route findByCode(String code) {
		return repository.findByRouteCode(code);
	}

	@Override
	public void delete(Route station) {
		repository.delete(station);
	}
	
}

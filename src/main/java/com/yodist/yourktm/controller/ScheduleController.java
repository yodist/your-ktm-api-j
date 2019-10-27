package com.yodist.yourktm.controller;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yodist.yourktm.base.BaseController;
import com.yodist.yourktm.domain.Route;
import com.yodist.yourktm.domain.Schedule;
import com.yodist.yourktm.domain.Station;
import com.yodist.yourktm.domain.Train;
import com.yodist.yourktm.handler.ResponseHandler;
import com.yodist.yourktm.service.RouteService;
import com.yodist.yourktm.service.ScheduleService;
import com.yodist.yourktm.service.StationService;
import com.yodist.yourktm.service.TrainService;
import com.yodist.yourktm.util.CsvUtil;
import com.yodist.yourktm.util.CustomDateUtil;

@RestController
@RequestMapping("/schedule")
public class ScheduleController extends BaseController {

	@Autowired
	ScheduleService scheduleService;
	
	@Autowired
	TrainService trainService;
	
	@Autowired
	StationService stationService;
	
	@Autowired
	RouteService routeService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<ResponseHandler> getAllSchedules() {
		return ok(scheduleService.findAll());
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<ResponseHandler> getScheduleById(@PathVariable("id") ObjectId id) {
		return ok(scheduleService.findById(id));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<ResponseHandler> modifyScheduleById(@PathVariable("id") ObjectId id,
			@Valid @RequestBody Schedule schedule) {
		schedule.setId(id);
		return ok(scheduleService.save(schedule));
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<ResponseHandler> createSchedule(@Valid @RequestBody Schedule schedule) {
		schedule.setId(ObjectId.get());
		return ok(scheduleService.save(schedule));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseHandler> deleteSchedule(@PathVariable ObjectId id) {
		trainService.delete(trainService.findById(id));
		return ok("Delete data success");
	}

	@RequestMapping(value = "/populateFromCsv", method = RequestMethod.POST)
	public ResponseEntity<ResponseHandler> uploadFileCsv(@RequestParam("file") MultipartFile file,
			@RequestParam(name = "routeCode", required = true) String routeCode) {
		
		try {
			Reader reader = new InputStreamReader(file.getInputStream());
			Objects.requireNonNull(reader, "reader cannot be null");
			
			List<String[]> rowList = new ArrayList<>();
			List<Train> trainList = new ArrayList<>();
			List<Station> stationList = new ArrayList<>();
			List<Schedule> scheduleList = new ArrayList<>();
			
			rowList = CsvUtil.parsePerLine(reader);
			Objects.requireNonNull(rowList, "csv file cannot be empty");
			
			// Route route = routeService.findByCode(rowList.get(2)[0]);
			Route route = routeService.findByCode(routeCode);
			Objects.requireNonNull(route, "route cannot be null");
			
			String[] trainRow = rowList.get(0);
			for (int i = 1; i < trainRow.length; i++) {
				String trainCode = trainRow[i];
				// if data exists, continue
				Train existing = trainService.findByCode(trainCode);
				if (existing != null) {
					trainList.add(existing);
					continue;
				}
				Train train = new Train();
				train.setName(trainCode);
				train.setTrainCode(trainCode);
				trainList.add(train);
			}
			trainList = trainService.saveMultiple(trainList);

			for (int i = 2; i < rowList.size(); i++) {
				String stationName = rowList.get(i)[0];
				// if data exists, continue
				Station existing = stationService.findByCode(stationName);
				if (existing != null) { 
					stationList.add(existing);
					continue; 
				}
				Station station = new Station();
				station.setName(stationName);
				station.setStationCode(stationName);
				stationList.add(station);
			}
			stationList = stationService.saveMultiple(stationList);

			for (int i = 2; i < rowList.size(); i++) {
				String[] row = rowList.get(i);
				int stationIndex = i - 2;
				for (int j = 1; j < row.length; j++) {
					if (StringUtils.isBlank(row[j])) continue; 
					int trainIndex = j - 1;
					Schedule schedule = new Schedule();
					schedule.setTime(CustomDateUtil.parseTimeToInt(row[j]));
					schedule.setTimeString(row[j]);
					schedule.setTrain(trainList.get(trainIndex));
					schedule.setStation(stationList.get(stationIndex));
					schedule.setRoute(route);
					schedule.setSequence(j);
					scheduleList.add(schedule);
				}
			}
			scheduleList = scheduleService.saveMultiple(scheduleList);
		} catch (Exception ex) {
			return somethingGoesWrong("Failed to upload CSV", ex.getMessage());
		}

		return ok(null, "Success populate data from CSV");
	}

}

package com.yodist.yourktm.controller;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.yodist.yourktm.base.BaseController;
import com.yodist.yourktm.constant.ResCode;
import com.yodist.yourktm.domain.Schedule;
import com.yodist.yourktm.handler.ResponseHandler;
import com.yodist.yourktm.repository.ScheduleRepository;
import com.yodist.yourktm.service.impl.FileStorageService;
import com.yodist.yourktm.util.CsvUtil;

@RestController
@RequestMapping("/schedule")
public class ScheduleController extends BaseController {
	
	@Autowired
	FileStorageService fileStorageService;
	
	@Autowired
	private ScheduleRepository repository;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Schedule> getAllSchedules() {
		return repository.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Schedule getScheduleById(@PathVariable("id") ObjectId id) {
		return repository.findById(id);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public void modifyScheduleById(@PathVariable("id") ObjectId id, @Valid @RequestBody Schedule schedule) {
		schedule.setId(id);
		repository.save(schedule);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public Schedule createSchedule(@Valid @RequestBody Schedule schedule) {
		schedule.setId(ObjectId.get());
		repository.save(schedule);
		return schedule;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void deleteSchedule(@PathVariable ObjectId id) {
		repository.delete(repository.findById(id));
	}
	
	// NOT READY YET
	@RequestMapping(value = "/populateFromPdf", method = RequestMethod.POST)
    public ResponseEntity<ResponseHandler> uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        String fileName = fileStorageService.storeFile(multipartFile);

        File file = new File("/pdf-file/" + fileName);

        try {
        	PDDocument doc = PDDocument.load(file);
        	logger.debug("FILE LOADED SUCCESSFULLY " + doc.getNumberOfPages());
        	doc.close();
        } catch(Exception ex) {
        	return somethingGoesWrong("Upload failed.", ex.getMessage());
        }
        
        return ok("Not ready yet.","Data updated successfully");
    }
	
	@RequestMapping(value = "/populateFromCsv", method = RequestMethod.POST)
    public ResponseEntity<ResponseHandler> uploadFileCsv(@RequestParam("file") MultipartFile multipartFile) {
        String fileName = fileStorageService.storeFile(multipartFile);
        File file = new File("/pdf-file/" + fileName);
        List<String[]> rowList = new ArrayList<>();
        try {
        	Reader reader = new FileReader(file);
        	rowList = CsvUtil.parsePerLine(reader);
        	int index = 0;
        	for (String[] row : rowList) {
//        		logger.debug(row.toString());
        		index++;
        	}
        } catch(Exception ex) {
        	return somethingGoesWrong("Failed to upload CSV", ex.getMessage());
        }
        
        return ok(null, "Success populate data from CSV");
    }
	
	
}

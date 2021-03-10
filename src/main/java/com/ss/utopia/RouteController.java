package com.ss.utopia;

import java.net.ConnectException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.ss.utopia.exceptions.AirportNotFoundException;
import com.ss.utopia.exceptions.RouteAlreadyExistsException;
import com.ss.utopia.exceptions.RouteNotFoundException;
import com.ss.utopia.models.HttpError;
import com.ss.utopia.models.Route;
import com.ss.utopia.services.RouteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/routes")
public class RouteController {
	
	@Autowired
	RouteService routeService;
	
	@GetMapping
	public ResponseEntity<Object> findAllRoutes(){
		List<Route> routeList = routeService.findAll();
		return !routeList.isEmpty()
			? new ResponseEntity<>(routeList, HttpStatus.OK)
			: new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Object> findById(@PathVariable Integer id){
		try {
			Route route =  routeService.findById(id);
			return new ResponseEntity<>(route, HttpStatus.OK);
		
		} catch(RouteNotFoundException err) {
			return new ResponseEntity<>(new HttpError(err.getMessage(), 404), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/search")
	public ResponseEntity<Object> findBySearchAndFilter(@RequestBody HashMap<String, String> filterMap) {
		List<Route> routeList = routeService.findBySearchAndFilter(filterMap);
		return !routeList.isEmpty()
			? new ResponseEntity<>(routeList, HttpStatus.OK)
			: new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

	}
	
	@PostMapping
	public ResponseEntity<Object> insert(@RequestBody HashMap<String, String> routeMap) {
		try {
			String routeOriginIataId = routeMap.get("routeOriginIataId");
			String routeDestinationIataId = routeMap.get("routeDestinationIataId");
			Route newRoute = routeService.insert(routeOriginIataId, routeDestinationIataId);
			return new ResponseEntity<>(newRoute, HttpStatus.CREATED);

		}	catch (AirportNotFoundException err) {
			return new ResponseEntity<>(new HttpError(err.getMessage(), 400), HttpStatus.BAD_REQUEST);

		}	catch(RouteAlreadyExistsException err) {
			return new ResponseEntity<>(new HttpError(err.getMessage(), 409), HttpStatus.CONFLICT);
		} 
	}

	@PutMapping
	public ResponseEntity<Object> update(@RequestBody HashMap<String, String> routeMap) {
		try {
			Integer routeId = Integer.parseInt(routeMap.get("routeId"));
			String routeOriginIataId = routeMap.get("routeOriginIataId");
			String routeDestinationIataId = routeMap.get("routeDestinationIataId");
			Route newRoute = routeService.update(routeId, routeOriginIataId, routeDestinationIataId);
			return new ResponseEntity<>(newRoute, HttpStatus.OK);
		
		}	catch (AirportNotFoundException err) {
			return new ResponseEntity<>(new HttpError(err.getMessage(), 400), HttpStatus.BAD_REQUEST);
		
		}	catch(RouteAlreadyExistsException err) {
			return new ResponseEntity<>(new HttpError(err.getMessage(), 409), HttpStatus.CONFLICT);
		
		}	catch (RouteNotFoundException err) {
			return new ResponseEntity<>(new HttpError(err.getMessage(), 404), HttpStatus.NOT_FOUND);
		} 
	}
	
	@DeleteMapping("{routeId}")
	public ResponseEntity<Object> delete(@PathVariable Integer routeId) {
		try {
			routeService.deleteById(routeId);
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

		} catch(RouteNotFoundException err) {
			return new ResponseEntity<>(new HttpError(err.getMessage(), 404), HttpStatus.NOT_FOUND);
		}
	}
	
	@ExceptionHandler(ConnectException.class)
	public ResponseEntity<Object> invalidConnection() {
		return new ResponseEntity<>(new HttpError("Service temporarily unavailabe.", 500), HttpStatus.SERVICE_UNAVAILABLE);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<Object> invalidMessage() {
		return new ResponseEntity<>(new HttpError("Invalid HTTP message content.", 400), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(SQLException.class)
	public ResponseEntity<Object> invalidSQL() {
		return new ResponseEntity<>(new HttpError("Service temporarily unavailabe.", 500), HttpStatus.SERVICE_UNAVAILABLE);
	}
}
package com.boot.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.boot.model.Shipwreck;
import com.boot.repository.ShipwreckRepository;

@RestController
@RequestMapping("api/v1")
public class ShipwrekController {
	
	@Autowired
	private ShipwreckRepository shipwreckRepo;
	
	@RequestMapping(value = "shipwrecks", method =RequestMethod.GET)
	public List<Shipwreck> list() {
		return shipwreckRepo.findAll();
	}
	
	@RequestMapping(value = "shipwrecks", method = RequestMethod.POST)
	public Shipwreck create(@RequestBody Shipwreck shipwreck) {
		return shipwreckRepo.saveAndFlush(shipwreck);
	}
	
	@RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.GET)
	public Shipwreck get(@PathVariable Long id) {
		return shipwreckRepo.findOne(id);
	}

	@RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.PUT)
	public Shipwreck update(@PathVariable Long id, @RequestBody Shipwreck shipwreck) {
		Shipwreck existingShipWreck = shipwreckRepo.findOne(id);
		BeanUtils.copyProperties( shipwreck, existingShipWreck);
		return shipwreckRepo.saveAndFlush(existingShipWreck);
	}
	
	@RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.DELETE)
	public Shipwreck delete(@PathVariable Long id) {
		Shipwreck existingShipWreck = shipwreckRepo.findOne(id);
		
		shipwreckRepo.delete(existingShipWreck);
		return existingShipWreck;
	}
	
}

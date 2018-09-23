package com.org.wms.app.workorder.controller;

import java.security.Principal;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.org.wms.app.workorder.collection.WorkOrder;
import com.org.wms.app.workorder.service.WorkOrderService;

/**
 * @author Arijit Bairagya
 * mail : ArijitBairagya@gmail.com
 *
 */
@RestController
public class WorkOrderController {
	
	private static Logger LOGGER = LogManager.getLogger(WorkOrderController.class);
	
	@Autowired
	private WorkOrderService workOrderService;

	@GetMapping("/woList")
	public List<WorkOrder> getAllWorkOrder(Principal principal) {
		
		OAuth2Authentication authentication = (OAuth2Authentication) principal;
		LOGGER.debug("Get Work orders for users - {}",authentication.getPrincipal().toString());
		return workOrderService.findAllByCreatedBy(authentication.getPrincipal().toString());
	}
	
	@PostMapping("/create")
	public String createWorkorder(@RequestBody WorkOrder wo, Principal principal) {
		
		OAuth2Authentication authentication = (OAuth2Authentication) principal;
		LOGGER.debug("Authorities:-", authentication.getName());
		
		workOrderService.createWorOrder(wo, authentication.getPrincipal().toString());
		
		return "Success";
	}
}

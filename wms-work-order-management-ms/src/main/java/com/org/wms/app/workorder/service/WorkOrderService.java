/**
 * 
 */
package com.org.wms.app.workorder.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.wms.app.workorder.collection.WorkOrder;
import com.org.wms.app.workorder.repo.WorkOrderRepository;

/**
 * @author Arijit Bairagya
 * mail : ArijitBairagya@gmail.com
 *
 */
@Service
public class WorkOrderService {

	@Autowired
	private WorkOrderRepository workOrderRepository;
	
	
	public List<WorkOrder> findAllByCreatedBy(String userName) {
		return workOrderRepository.findAllByCreatedByIgnoreCase(userName);
	}


	/**
	 * @param wo
	 * @param name
	 * @return
	 */
	public void createWorOrder(WorkOrder wo, String name) {
		
		wo.setCreatedBy(name);
		wo.setCreatedDate(new Date());
		wo.setModifiedDate(new Date());
		
		workOrderRepository.save(wo);
	}
	
}

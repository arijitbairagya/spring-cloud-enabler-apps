/**
 * 
 */
package com.org.wms.app.workorder.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.org.wms.app.workorder.collection.WorkOrder;

/**
 * @author Arijit Bairagya
 * mail : ArijitBairagya@gmail.com
 *
 */
public interface WorkOrderRepository extends MongoRepository<WorkOrder, String>{

	public List<WorkOrder> findAllByCreatedByIgnoreCase(String userName);
	
}

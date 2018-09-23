/**
 * 
 */
package com.org.wms.app.workorder.collection;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author Arijit Bairagya
 * mail : ArijitBairagya@gmail.com
 *
 */
@Document(collection="work-order")
public class WorkOrder {

	@Id
	private String id;
	
	private String workOrderNo;// should be a sequence number

	private Date createdDate;

	private Date modifiedDate;

	private String createdBy; // userName
	
	private String serviceId;

	private String teamId;
	
	private String status; // OPEN, ASSIGNED, WIP, COMPLETED, CLOSED, REOPEN

	private String assignedTo; // userName

	private String comments;
	
	private Date scheduleStartTime;
	
	private Date scheduleEndTime;
	
	private Date actualStartTime;
	
	private Date actualEndTime;
	
	// Need to store the working hours
	

	
	
	/**
	 * @return the workOrderNo
	 */
	public String getWorkOrderNo() {
		return workOrderNo;
	}

	/**
	 * @param workOrderNo the workOrderNo to set
	 */
	public void setWorkOrderNo(String workOrderNo) {
		this.workOrderNo = workOrderNo;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the modifiedDate
	 */
	public Date getModifiedDate() {
		return modifiedDate;
	}

	/**
	 * @param modifiedDate the modifiedDate to set
	 */
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the assignedTo
	 */
	public String getAssignedTo() {
		return assignedTo;
	}

	/**
	 * @param assignedTo the assignedTo to set
	 */
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * @return the serviceId
	 */
	public String getServiceId() {
		return serviceId;
	}

	/**
	 * @param serviceId the serviceId to set
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	/**
	 * @return the teamId
	 */
	public String getTeamId() {
		return teamId;
	}

	/**
	 * @param teamId the teamId to set
	 */
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the scheduleStartTime
	 */
	public Date getScheduleStartTime() {
		return scheduleStartTime;
	}

	/**
	 * @param scheduleStartTime the scheduleStartTime to set
	 */
	public void setScheduleStartTime(Date scheduleStartTime) {
		this.scheduleStartTime = scheduleStartTime;
	}

	/**
	 * @return the scheduleEndTime
	 */
	public Date getScheduleEndTime() {
		return scheduleEndTime;
	}

	/**
	 * @param scheduleEndTime the scheduleEndTime to set
	 */
	public void setScheduleEndTime(Date scheduleEndTime) {
		this.scheduleEndTime = scheduleEndTime;
	}

	/**
	 * @return the actualStartTime
	 */
	public Date getActualStartTime() {
		return actualStartTime;
	}

	/**
	 * @param actualStartTime the actualStartTime to set
	 */
	public void setActualStartTime(Date actualStartTime) {
		this.actualStartTime = actualStartTime;
	}

	/**
	 * @return the actualEndTime
	 */
	public Date getActualEndTime() {
		return actualEndTime;
	}

	/**
	 * @param actualEndTime the actualEndTime to set
	 */
	public void setActualEndTime(Date actualEndTime) {
		this.actualEndTime = actualEndTime;
	}
	
	

}

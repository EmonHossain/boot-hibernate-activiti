package com.csit.project.enetities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "loan")
public class LoanInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String clientAccNo;
	private int loanAmount;
	private String assigneeId;
	private String taskId;
	private String requestedTo;
	@Temporal(TemporalType.TIMESTAMP)
	private Date applyDate;
	private String approvedBy;
	private boolean isApproved;
	private String currentStatus;
	private String resolvedBy;

	public LoanInfo() {

	}

	public LoanInfo(long id) {
		this.id = id;
	}

	public LoanInfo(String clientAccNo, int loanAmount, String assigneeId, String taskId, String requestedTo,
			Date applyDate, String approvedBy, boolean isApproved, String currentStatus, String resolvedBy) {
		this.clientAccNo = clientAccNo;
		this.loanAmount = loanAmount;
		this.assigneeId = assigneeId;
		this.taskId = taskId;
		this.requestedTo = requestedTo;
		this.applyDate = applyDate;
		this.approvedBy = approvedBy;
		this.isApproved = isApproved;
		this.currentStatus = currentStatus;
		this.resolvedBy = resolvedBy;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClientAccNo() {
		return clientAccNo;
	}

	public void setClientAccNo(String clientAccNo) {
		this.clientAccNo = clientAccNo;
	}

	public int getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(int loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getAssigneeId() {
		return assigneeId;
	}

	public void setAssigneeId(String assigneeId) {
		this.assigneeId = assigneeId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}

	public String getRequestedTo() {
		return requestedTo;
	}

	public void setRequestedTo(String requestedTo) {
		this.requestedTo = requestedTo;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	public String getCurrentStatus() {
		return currentStatus;
	}

	public void setCurrentStatus(String currentStatus) {
		this.currentStatus = currentStatus;
	}

	public String getResolvedBy() {
		return resolvedBy;
	}

	public void setResolvedBy(String resolvedBy) {
		this.resolvedBy = resolvedBy;
	}

}

package com.std.pack.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * @author RXMARP <br>
 * ricky.martaputra@acegroup.com <br>
 * Feb 15, 2012 9:56:13 AM 
 */
@Entity
@Table(name = "S_HOLIDAY", schema = "CONCORDE")
public class SHoliday  extends BaseModel<SHoliday>{
	
	private static final long serialVersionUID = -4693600967044676315L;

	@Id
	@Column(name = "HOLIDAY_ID", columnDefinition = "NUMBER(10)")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HOLIDAYSEQ")
	@SequenceGenerator(name = "HOLIDAYSEQ", sequenceName = "HOLIDAY_SEQ", allocationSize = 1)
	private Long holidayId;
	
	@Column(name = "HOLIDAY_DATE", length = 150)
	@Temporal(TemporalType.DATE)
	private Date holidayDate;

	@Column(name = "HOLIDAY_DESC", length = 150)
	private String holidayDesc;
	
	@Column(name = "CREATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column(name = "UPDATED_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	
	public Long getHolidayId() {
		return holidayId;
	}

	public void setHolidayId(Long holidayId) {
		this.holidayId = holidayId;
	}

	public Date getHolidayDate() {
		return holidayDate;
	}

	public void setHolidayDate(Date holidayDate) {
		this.holidayDate = holidayDate;
	}

	public String getHolidayDesc() {
		return holidayDesc;
	}

	public void setHolidayDesc(String holidayDesc) {
		this.holidayDesc = holidayDesc;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((holidayId == null) ? 0 : holidayId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SHoliday other = (SHoliday) obj;
		if (holidayId == null) {
			if (other.holidayId != null)
				return false;
		} else if (!holidayId.equals(other.holidayId))
			return false;
		return true;
	}
	
}

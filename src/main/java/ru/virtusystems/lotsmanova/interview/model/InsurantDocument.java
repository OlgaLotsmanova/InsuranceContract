package ru.virtusystems.lotsmanova.interview.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "INSURANT_DOCUMENT")
public class InsurantDocument {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String documentNumber;

	@Column(nullable = false)
	private LocalDate startDate;

	@Column
	private LocalDate settlementDate = LocalDate.now();

	@Column
	private LocalDate conclusionDate = LocalDate.now();

	@Column(nullable = false)
	private LocalDate endDate;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CUTOMER_ID")
	private Customer customer;

	@Column
	private Float insurance;

	@Column
	private Float premium;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "REALTY_ID")
	private Realty realty;

	@Column
	private String comment;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(LocalDate settlementDate) {
		this.settlementDate = settlementDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Float getInsurance() {
		return insurance;
	}

	public void setInsurance(Float insurance) {
		this.insurance = insurance;
	}

	public float getPremium() {
		return premium;
	}

	public void setPremium(float premium) {
		this.premium = premium;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Realty getRealty() {
		return realty;
	}

	public void setRealty(Realty realty) {
		this.realty = realty;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDate getConclusionDate() {
		return conclusionDate;
	}

	public void setConclusionDate(LocalDate conclusionDate) {
		this.conclusionDate = conclusionDate;
	}

	public void setPremium(Float premium) {
		this.premium = premium;
	}

}

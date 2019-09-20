package ru.virtusystems.lotsmanova.interview.service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.virtusystems.lotsmanova.interview.dao.DocumentDao;
import ru.virtusystems.lotsmanova.interview.model.InsurantDocument;
import ru.virtusystems.lotsmanova.interview.model.Realty;

@Service
public class DocumentServiceImpl implements DocumentService {

	@Autowired
	private DocumentDao documentDao;

	@Override
	public List<InsurantDocument> getDocuments() {
		return documentDao.getDocuments();
	}

	@Override
	public InsurantDocument getById(int id) {
		return documentDao.getById(id);
	}

	@Override
	public InsurantDocument insert(InsurantDocument document) {

		// document.setPremium(calculateInsurancePremium(document));

		return documentDao.insert(document);

	}

	@Override
	public float calculateInsurancePremium(InsurantDocument document) {

		Realty realty = document.getRealty();

		ResourceBundle boundle = ResourceBundle.getBundle("calculations");

		float coefficientRealtyType = realty.getRealtyType().getCoefficient();

		float coefficientYearConstraction = 1;
		if (realty.getConstructionYear()
				.compareTo(boundle.getString("ConstructionYear.early")) > 0) {
			coefficientRealtyType = Float.parseFloat(
					boundle.getString("ConstructionYear.early.coefficient"));
		} else if (realty.getConstructionYear()
				.compareTo(boundle.getString("ConstructionYear.middle")) > 0) {
			coefficientRealtyType = Float.parseFloat(
					boundle.getString("ConstructionYear.middle.coefficient"));
		} else {
			coefficientRealtyType = Float.parseFloat(
					boundle.getString("ConstructionYear.late.coefficient"));
		}

		float coefficientArea = 1;
		if (realty.getArea() > Float
				.parseFloat(boundle.getString("Area.big"))) {
			coefficientArea = Float
					.parseFloat(boundle.getString("Area.big.coefficient"));
		} else if (realty.getArea() > Float
				.parseFloat(boundle.getString("Area.middle"))) {
			coefficientArea = Float
					.parseFloat(boundle.getString("Area.middle.coefficient"));
		} else {
			coefficientArea = Float
					.parseFloat(boundle.getString("Area.small.coefficient"));
		}

		return (document.getInsurance() / ChronoUnit.DAYS
				.between(document.getStartDate(), document.getEndDate()))
				* coefficientRealtyType * coefficientYearConstraction
				* coefficientArea;
	}

	@Override
	public InsurantDocument update(InsurantDocument document) {

		return documentDao.update(document);

	}

}

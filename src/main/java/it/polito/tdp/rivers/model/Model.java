package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.List;

import it.polito.tdp.rivers.db.RiversDAO;

public class Model {
	
	private RiversDAO dao;
	
	public Model() {
		this.dao = new RiversDAO();
	}
	
	public List<River> getAllRivers(){
		return this.dao.getAllRivers();
	}
	
	public List<Flow> getFlowsDatoRiver(River r){
		return this.dao.getFlowsDatoRiver(r);
	}
	
	//prendere data prima misurazione
	public LocalDate getDataMinima(River r) {
		//lista che contiene tutti i flow di quel fiume
		List<Flow> flows = this.dao.getFlowsDatoRiver(r);
		LocalDate dataMinima = flows.get(0).getDay();
		return dataMinima;
	}
	
	//prendere data ultima misurazione
	public LocalDate getDataMassima(River r) {
		//lista che contiene tutti i flow di quel fiume
		List<Flow> flows = this.dao.getFlowsDatoRiver(r);
		LocalDate dataMassima = flows.get(flows.size()-1).getDay();
		return dataMassima;
	}
	
	//numero totale misurazioni
	public int getTotMisutazioni(River r) {
		//lista che contiene tutti i flow di quel fiume
		List<Flow> flows = this.dao.getFlowsDatoRiver(r);
		int misurazioni = flows.size();
		return misurazioni;
	}
	
	//prendere flusso medio misurato
	public double getFlussoMedio(River r) {
		return this.dao.getFlussoMedio(r);
	}
	
	

}

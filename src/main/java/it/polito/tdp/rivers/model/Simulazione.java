package it.polito.tdp.rivers.model;

import java.util.List;
import java.util.PriorityQueue;

public class Simulazione {
	
	//stato del mondo
	private double C;
	
	//input 
	private List<Flow> flows;
	private double k;
	private double fmed;
	private double fOutMin;
	private double Q;
	private double fIrrigazione;
	
	//output
	private double Cmed;
	private int numeroGiorniNonServiti;
	
	//coda
	private PriorityQueue<Flow> queue;
	
	public Simulazione(List<Flow> flows, double k, double fmed) {
		this.flows = flows;
		this.k = k;
		this.fmed = fmed * 86400;
		this.Q = k*fmed*30*86400;
		this.C = Q/2;
		this.fOutMin = 0.8*this.fmed;
		this.fIrrigazione = 10*this.fmed;
		this.Cmed = 0;
		this.numeroGiorniNonServiti=0;
		this.queue = new PriorityQueue<Flow>();
		}
	
	public void inizialize() {
		this.queue.addAll(this.flows);
	}
	
	public String run() {
		double Ctot = 0.0;
		int cont = 0;
		while(!this.queue.isEmpty()) {
			Flow f = this.queue.poll();
			double flow = f.getFlow()*86400;
			//l'evento flow è l'acqua che entra nel bacino
			C += flow;
			//dopo che l'acqua è entrata deve uscire
			
			double n = Math.random();
			
			if (n<0.05) {//se c'è irrigazione
				C -= this.fIrrigazione;
			}
			else {//se non c'è irrigazione
				C -= this.fOutMin;
			}
			
			if (C<0.0) {//non abbiamo soddisfatto la richiesta
				this.numeroGiorniNonServiti ++;
				C = 0;
			}
			if (C>Q) {//il bacino non ha più posto per l'acqua
				C=Q;
			}
			Ctot += C;
			cont++;
		}
		Cmed = Ctot / cont;
		return "Si è registrata una capienza media di "+Cmed+"\nNon siamo riusciti a servire la richiesta per "+this.numeroGiorniNonServiti+" giorni";
	}
	
	
	
	
	
	
	
	
	

}

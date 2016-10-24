package ttma.mBean;

import ttma.Entities.Doctor;
import ttma.Entities.Operation;
import ttma.services.Interfaces.GestionDoctorLocal;
import ttma.services.Interfaces.GestionOperationLocal;
import util.Messages;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



@ManagedBean
@ViewScoped
public class OffersBean {
	private Operation operation = new Operation();
	
	


	private List<Operation> operations;
	

	private Boolean displayForm = false;
	

	@EJB
	private GestionOperationLocal gestionOperationLocal;


	
	@PostConstruct
	public void init() {
		setOperations(gestionOperationLocal.findAllOperations());
	}
	
	
	public String doCreatedOperation() {
		
	
		gestionOperationLocal.addOperation(operation);
		operation = new Operation();
		displayForm = false;
		return "";
	}

	public String delete() {
		gestionOperationLocal.deleteOperation(operation);
		return "index?faces-redirect=true";
	}
	
	public String cancel() {
		return "index?faces-redirect=true";
	}
	
	
	
	public void preRenderView() {
		if (operation == null) {
			operation = new Operation();
		}
	}

	public String saveOperation() {
		if (operation.getID_offre() != 0) {
			gestionOperationLocal.updateOperation(operation);
		} 
		else {
			gestionOperationLocal.addOperation(operation);
		}
		
		//addFlashMessage("Product " + product.getId() + " saved");
		
		return "index.xhtml?faces-redirect=true";
	}


	


	public Operation getOperation() {
		return operation;
	}


	public void setOperation(Operation operation) {
		this.operation = operation;
	}


	public List<Operation> getOperations() {
		return operations;
	}


	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
	

	


	

}

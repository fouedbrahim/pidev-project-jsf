package ttma.mBean;

import ttma.Entities.Doctor;
import ttma.services.Interfaces.GestionDoctorLocal;
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
public class DoctorBean {
	private Doctor doctor  = new Doctor();
	
	public Doctor getDoctor() {
		return doctor;
	}


	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}


	private List<Doctor> doctors;
	

	private Boolean displayForm = false;
	

	@EJB
	private GestionDoctorLocal gestionDoctorLocal;


	
	@PostConstruct
	public void init() {
		setDoctors(gestionDoctorLocal.findAllDoctors());
	}
	
	
	public String doCreatedDoctor() {
		
	
		gestionDoctorLocal.addDoctor(doctor);
		doctor = new Doctor();
		displayForm = false;
		return "";
	}

	public String delete() {
		gestionDoctorLocal.deleteDoctor(doctor);
		return "index?faces-redirect=true";
	}
	
	public String cancel() {
		return "index?faces-redirect=true";
	}
	
	
	
	public void preRenderView() {
		if (doctor == null) {
			doctor = new Doctor();
		}
	}

	public String saveDoctor() {
		if (doctor.getNumCinDoc() != 0) {
			gestionDoctorLocal.updateDoctor(doctor);
		} 
		else {
			gestionDoctorLocal.addDoctor(doctor);
		}
		
		//addFlashMessage("Product " + product.getId() + " saved");
		
		return "index.xhtml?faces-redirect=true";
	}


	public List<Doctor> getDoctors() {
		return doctors;
	}


	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}
	

	


	

}

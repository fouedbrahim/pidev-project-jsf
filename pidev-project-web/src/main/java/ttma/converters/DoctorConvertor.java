package ttma.converters;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import ttma.Entities.Doctor;
import ttma.services.Interfaces.GestionDoctorLocal;


@ManagedBean
public class DoctorConvertor implements Converter {

	@EJB
	private GestionDoctorLocal doctors;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
			return null;
		}
		int id = Integer.parseInt(value);
		
		return doctors.getDocotrByCIN(id);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}
		Integer id = ((Doctor) value).getNumCinDoc();
		
		return (id != null) ? id.toString() : null;
	}

}

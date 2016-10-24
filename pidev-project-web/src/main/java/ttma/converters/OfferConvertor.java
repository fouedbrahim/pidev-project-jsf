package ttma.converters;



import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;


import ttma.services.Interfaces.GestionOperationLocal;


@ManagedBean
public class OfferConvertor implements Converter {

	@EJB
	private GestionOperationLocal doctors;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null) {
			return null;
		}
		int id = Integer.parseInt(value);
		
		return doctors.getOperationByCIN(id);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value == null) {
			return null;
		}
		Integer id = ((ttma.Entities.Operation) value).getID_offre();
		
		return (id != null) ? id.toString() : null;
	}

}

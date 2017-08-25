package br.com.fiap.utils;
 
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

 
//@ManagedBean
public class PrimeFaceMessage {
      
    public void addMessage(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,  null);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
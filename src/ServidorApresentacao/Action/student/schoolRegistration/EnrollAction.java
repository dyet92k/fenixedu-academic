/*
 * Created on Jul 20, 2004
 *
 */
package ServidorApresentacao.Action.student.schoolRegistration;

import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.DynaActionForm;

import DataBeans.InfoPerson;
import ServidorAplicacao.IUserView;
import ServidorApresentacao.Action.base.FenixAction;
import ServidorApresentacao.Action.sop.utils.ServiceUtils;
import ServidorApresentacao.Action.sop.utils.SessionUtils;
import Util.EstadoCivil;

/**
 * @author Nuno Correia
 * @author Ricardo Rodrigues
 */
public class EnrollAction extends FenixAction {

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        IUserView userView = SessionUtils.getUserView(request);

        DynaActionForm totalForm = (DynaActionForm) form;
        HashMap answersMap = (HashMap) totalForm.get("answersMap");

        Integer idInternal = new Integer((String) totalForm.get("idInternal"));
        Integer dayOfEmissionDateOfDocumentId = (Integer) totalForm.get("dayOfEmissionDateOfDocumentId");
        Integer monthOfEmissionDateOfDocumentId = (Integer) totalForm.get("monthOfEmissionDateOfDocumentId");
        Integer yearOfEmissionDateOfDocumentId = (Integer) totalForm.get("yearOfEmissionDateOfDocumentId");
        Integer dayOfExpirationDateOfDocumentId = (Integer) totalForm.get("dayOfExpirationDateOfDocumentId");
        Integer monthOfExpirationDateOfDocumentId = (Integer) totalForm.get("monthOfExpirationDateOfDocumentId");
        Integer yearOfExpirationDateOfDocumentId = (Integer) totalForm.get("yearOfExpirationDateOfDocumentId");      
        String nameOfFather = (String) totalForm.get("nameOfFather");
        String nameOfMother = (String) totalForm.get("nameOfMother");
        String nacionality = (String) totalForm.get("nacionality");
        String parishOfBirth = (String) totalForm.get("parishOfBirth");
        String districtSubvisionOfBirth = (String) totalForm.get("districtSubvisionOfBirth");
        String districtOfBirth = (String) totalForm.get("districtOfBirth");
        String address = (String) totalForm.get("address");
        String area = (String) totalForm.get("area");
        Integer primaryAreaCode = (Integer) totalForm.get("primaryAreaCode");
        Integer secondaryAreaCode = (Integer) totalForm.get("secondaryAreaCode");
        String areaOfAreaCode = (String) totalForm.get("areaOfAreaCode");
        String parishOfResidence = (String) totalForm.get("parishOfResidence");
        String districtSubdivisionOfResidence = (String) totalForm.get("districtSubdivisionOfResidence");
        String districtOfResidence = (String) totalForm.get("districtOfResidence");
        Integer phone = (Integer) totalForm.get("phone");
        Integer mobile = (Integer) totalForm.get("mobile");
        String email = (String) totalForm.get("email");
        Boolean availableEmail = (Boolean) totalForm.get("availableEmail"); 
        String webAddress = (String) totalForm.get("webAddress");
        Boolean availableWebAdress = (Boolean) totalForm.get("availableWebAdress"); 
        String contributorNumber = (String) totalForm.get("contributorNumber");
        String occupation = (String) totalForm.get("occupation");
        String password = (String) totalForm.get("password");
        String maritalStatus = (String) totalForm.get("maritalStatus");
      
        System.out.println("A PRIMEIRA CHECKBOX TEM: " + availableEmail);
        System.out.println("A SEGUNDA CHECKBOX TEM: " + availableWebAdress);
        if(availableEmail == null)
            availableEmail = new Boolean(false);
        if(availableWebAdress == null)
            availableWebAdress = new Boolean(false);
        
        InfoPerson infoPerson = new InfoPerson();
        Calendar EmissionDateOfDocumentId = Calendar.getInstance();
        Calendar ExpirationDateOfDocumentId = Calendar.getInstance();
        EmissionDateOfDocumentId.set(yearOfEmissionDateOfDocumentId.intValue(), monthOfEmissionDateOfDocumentId.intValue(),
                dayOfEmissionDateOfDocumentId.intValue());
        ExpirationDateOfDocumentId.set(yearOfExpirationDateOfDocumentId.intValue(), monthOfExpirationDateOfDocumentId.intValue(),
                dayOfExpirationDateOfDocumentId.intValue());
                
        infoPerson.setDataEmissaoDocumentoIdentificacao(EmissionDateOfDocumentId.getTime());
        infoPerson.setDataValidadeDocumentoIdentificacao(ExpirationDateOfDocumentId.getTime());
        infoPerson.setNomePai(nameOfFather);
        infoPerson.setNomeMae(nameOfMother);
        infoPerson.setNacionalidade(nacionality);
        infoPerson.setFreguesiaNaturalidade(parishOfBirth);
        infoPerson.setConcelhoNaturalidade(districtSubdivisionOfResidence);
        infoPerson.setDistritoNaturalidade(districtOfBirth);
        infoPerson.setMorada(address);
        infoPerson.setLocalidade(area);
        infoPerson.setCodigoPostal(primaryAreaCode.toString() + "-" + secondaryAreaCode.toString());
        infoPerson.setLocalidadeCodigoPostal(areaOfAreaCode);
        infoPerson.setFreguesiaMorada(parishOfResidence);
        infoPerson.setConcelhoMorada(districtSubdivisionOfResidence);
        infoPerson.setDistritoMorada(districtOfResidence);
        infoPerson.setTelefone(phone.toString());
        infoPerson.setTelemovel(mobile.toString());
        infoPerson.setEmail(email);
        infoPerson.setAvailableEmail(availableEmail);
        infoPerson.setEnderecoWeb(webAddress);
        infoPerson.setAvailableWebSite(availableWebAdress);
        infoPerson.setNumContribuinte(contributorNumber);
        infoPerson.setProfissao(occupation);
        infoPerson.setPassword(password);    
        infoPerson.setEstadoCivil(new EstadoCivil(maritalStatus));
        infoPerson.setIdInternal(idInternal);

        Object args[] = { userView, answersMap, infoPerson };
        ServiceUtils.executeService(userView, "SchoolRegistration", args);

        System.out.println("O id da pessoa na Accao �: " + infoPerson.getIdInternal());
        
        return mapping.findForward("");
    }

}
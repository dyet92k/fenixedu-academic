/*
 * Created on 12/Nov/2003
 *
 */
package Dominio.reimbursementGuide;

import java.util.Calendar;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;

import Dominio.DomainObject;
import Dominio.IGuide;
import Util.State;

/**
 * 
 * 
 * This class contains all the information regarding a Reimbursement Guide.
 * <br>
 *@author <a href="mailto:joao.mota@ist.utl.pt">Jo�o Mota</a>
 *
 */
public class ReimbursementGuide extends DomainObject implements IReimbursementGuide
{

    protected Integer number;
    protected IGuide guide;
    protected Double value;
    protected String justification;
    protected Calendar creationDate;
    protected List reimbursementGuideSituations;
    private Integer keyGuide;

    /**
     * 
     */
    public ReimbursementGuide()
    {

    }

    /**
     * @param reimbursementGuideId
     */
    public ReimbursementGuide(Integer reimbursementGuideId)
    {
        setIdInternal(reimbursementGuideId);
    }

    /**
     * @return
     */
    public Calendar getCreationDate()
    {
        return creationDate;
    }

    /**
     * @param creationDate
     */
    public void setCreationDate(Calendar creationDate)
    {
        this.creationDate = creationDate;
    }

    /**
     * @return
     */
    public String getJustification()
    {
        return justification;
    }

    /**
     * @param justification
     */
    public void setJustification(String justification)
    {
        this.justification = justification;
    }

    /**
     * @return
     */
    public IGuide getGuide()
    {
        return guide;
    }

    /**
     * @param paymentGuide
     */
    public void setGuide(IGuide paymentGuide)
    {
        this.guide = paymentGuide;
    }

    /**
     * @return
     */
    public Double getValue()
    {
        return value;
    }

    /**
     * @param value
     */
    public void setValue(Double value)
    {
        this.value = value;
    }

    /**
     * @return
     */
    public Integer getKeyGuide()
    {
        return keyGuide;
    }

    /**
     * @param keyPaymentGuide
     */
    public void setKeyGuide(Integer keyPaymentGuide)
    {
        this.keyGuide = keyPaymentGuide;
    }

    /**
     * @return
     */
    public Integer getNumber()
    {
        return number;
    }

    /**
     * @param number
     */
    public void setNumber(Integer number)
    {
        this.number = number;
    }

    /**
     * @return
     */
    public List getReimbursementGuideSituations()
    {
        return reimbursementGuideSituations;
    }

    /**
     * @param reimbursementGuideSituations
     */
    public void setReimbursementGuideSituations(List reimbursementGuideSituations)
    {
        this.reimbursementGuideSituations = reimbursementGuideSituations;
    }

    public IReimbursementGuideSituation getActiveReimbursementGuideSituation()
    {

        return (
            IReimbursementGuideSituation) CollectionUtils
                .find(getReimbursementGuideSituations(), new Predicate()
        {
            public boolean evaluate(Object obj)
            {
                IReimbursementGuideSituation situation = (IReimbursementGuideSituation) obj;
                return situation.getState().getState().intValue() == State.ACTIVE;
            }
        });

    }

}

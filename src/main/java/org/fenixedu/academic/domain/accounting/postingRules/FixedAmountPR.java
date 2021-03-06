/**
 * Copyright © 2002 Instituto Superior Técnico
 *
 * This file is part of FenixEdu Academic.
 *
 * FenixEdu Academic is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FenixEdu Academic is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FenixEdu Academic.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.fenixedu.academic.domain.accounting.postingRules;

import org.fenixedu.academic.domain.accounting.EntryType;
import org.fenixedu.academic.domain.accounting.Event;
import org.fenixedu.academic.domain.accounting.EventType;
import org.fenixedu.academic.domain.accounting.ServiceAgreementTemplate;
import org.fenixedu.academic.domain.exceptions.DomainException;
import org.fenixedu.academic.util.Money;
import org.joda.time.DateTime;

public class FixedAmountPR extends FixedAmountPR_Base {

    protected FixedAmountPR() {
        super();
    }

    public FixedAmountPR(EntryType entryType, EventType eventType, DateTime startDate, DateTime endDate,
            ServiceAgreementTemplate serviceAgreementTemplate, Money fixedAmount) {
        this();
        init(entryType, eventType, startDate, endDate, serviceAgreementTemplate, fixedAmount);
    }

    private void checkParameters(Money fixedAmount) {
        if (fixedAmount == null) {
            throw new DomainException("error.accounting.postingRules.FixedAmountPR.fixedAmount.cannot.be.null");
        }

    }

    protected void init(EntryType entryType, EventType eventType, DateTime startDate, DateTime endDate,
            ServiceAgreementTemplate serviceAgreementTemplate, Money fixedAmount) {
        super.init(entryType, eventType, startDate, endDate, serviceAgreementTemplate);
        checkParameters(fixedAmount);
        super.setFixedAmount(fixedAmount);
    }

    @Override
    public void setFixedAmount(Money fixedAmount) {
        throw new DomainException("error.accounting.postingRules.FixedAmountPR.cannot.modify.fixedAmount");
    }

    @Override
    protected Money doCalculationForAmountToPay(Event event) {
        return getFixedAmount();
    }

    public FixedAmountPR edit(final Money fixedAmount) {

        deactivate();
        return new FixedAmountPR(getEntryType(), getEventType(), new DateTime().minus(1000), null, getServiceAgreementTemplate(),
                fixedAmount);
    }

}

package net.sourceforge.fenixedu.domain.util;

public class UsernameCounter extends UsernameCounter_Base {

    public UsernameCounter() {
        super();
    }

    @Deprecated
    public boolean hasBennu() {
        return getRootDomainObject() != null;
    }

    @Deprecated
    public boolean hasInvitationCounter() {
        return getInvitationCounter() != null;
    }

}

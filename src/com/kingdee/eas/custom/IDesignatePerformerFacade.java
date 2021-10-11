package com.kingdee.eas.custom;

import com.kingdee.bos.framework.*;
import java.util.*;
import com.kingdee.bos.*;

public interface IDesignatePerformerFacade extends IBizCtrl
{
    void setNextPerson(final ArrayList p0, final String[] p1, final ArrayList p2) throws BOSException;
    
    void autoTask() throws BOSException;
}

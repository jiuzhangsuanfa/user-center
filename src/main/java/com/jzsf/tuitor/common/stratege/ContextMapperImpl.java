/**
 * Alipay.com Inc. Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.jzsf.tuitor.common.stratege;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author senyang
 * @version $Id: ContextMapperImpl.java, v 0.1 2020年04月24日 9:00 PM senyang Exp $
 */
@Service
public class ContextMapperImpl implements ContextMapper {

    @Autowired
    private RegestProcessingStrategeImpl userStratege;

    @Override
    public UserStratege loadProcessor(OperatorStrategeEnum context) {
        if (context == OperatorStrategeEnum.SUCCESS || context == OperatorStrategeEnum.EMAILFAIL) {
            return this.userStratege;
        }
        return null;
    }

    /**
     * Getter method for property <tt>userStratege</tt>.
     *
     * @return property value of userStratege
     */
    public RegestProcessingStrategeImpl getUserStratege() {
        return userStratege;
    }

    /**
     * Setter method for property <tt>userStratege</tt>.
     *
     * @param userStratege value to be assigned to property userStratege
     */
    public void setUserStratege(RegestProcessingStrategeImpl userStratege) {
        this.userStratege = userStratege;
    }
}
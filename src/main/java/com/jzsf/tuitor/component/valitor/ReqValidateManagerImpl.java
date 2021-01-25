/**
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.jzsf.tuitor.component.valitor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author senyang
 * @version $Id: ReqValiadateManagerImpl.java, v 0.1 2020年04月24日 4:13 PM senyang
 *          Exp $
 */
@Service
public class ReqValidateManagerImpl implements ReqValidateManager {

    @Autowired
    private List<FormValidator> validators;

    @Override
    public void doExeute(Object aInPkg) {

        for (FormValidator validator : validators) {
            if (validator.canAccept(aInPkg)) {
                validator.validate(aInPkg);
            }
        }
    }
}

/**
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.jiuzhang.usercenter.component.valitor;

/**
 * @author
 * @version $Id: FromValitor.java, v 0.1 2020年04月19日 2:44 AM senyang Exp $
 */
public interface FormValidator<T> {
    /**
     * @param inPkg
     * @return
     */
    boolean canAccept(T inPkg);

    /**
     * @param aInPkg
     */
    void validate(T aInPkg);

}

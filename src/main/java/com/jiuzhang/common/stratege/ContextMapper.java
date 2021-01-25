/**
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.jiuzhang.common.stratege;

/**
 * @author senyang
 * @version $Id: ContextMapper.java, v 0.1 2020年04月24日 8:47 PM senyang Exp $
 */
public interface ContextMapper {
    UserStratege loadProcessor(OperatorStrategeEnum context);
}

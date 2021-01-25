/**
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.jiuzhang.common.stratege;

import com.jiuzhang.rpcdomain.common.RespResult;
import com.jiuzhang.rpcdomain.req.RegisterReq;

/**
 * @author senyang
 * @version $Id: UserStratege.java, v 0.1 2020年04月24日 8:52 PM senyang Exp $
 */
public interface UserStratege {
    RespResult doProcessor(RegisterReq req, OperatorStrategeEnum context);
}

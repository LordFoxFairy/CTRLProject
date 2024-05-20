package com.example.exception.common.exception;

import com.example.exception.common.enums.ServiceErrorCodeRange;
import lombok.*;

/**
 * 业务逻辑异常 Exception
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public final class ServiceException extends RuntimeException {
    
    /**
     * 业务错误码
     *
     * @see ServiceErrorCodeRange
     */
    private Integer code;
    /**
     * 错误提示
     */
    private String message;
    
}
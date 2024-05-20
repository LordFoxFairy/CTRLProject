package com.example.desensitize.handler;

import com.example.desensitize.annotation.RegexDesensitize;

/**
 * {@link RegexDesensitize} 的正则脱敏处理器
 */
public class DefaultRegexDesensitizationHandler extends AbstractRegexDesensitizationHandler<RegexDesensitize> {
    
    @Override
    String getRegex(RegexDesensitize annotation) {
        return annotation.regex();
    }
    
    @Override
    String getReplacer(RegexDesensitize annotation) {
        return annotation.replacer();
    }
}

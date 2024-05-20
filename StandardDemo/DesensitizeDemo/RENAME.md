脱敏工具类

Hutool 提供了 DesensitizedUtil (opens new window)脱敏工具类，支持用户 ID、 中文名、身份证、座机号、手机号、 地址、电子邮件、 密码、车牌、银行卡号的脱敏处理。

```java
DesensitizedUtil.desensitized("100", DesensitizedUtils.DesensitizedType.USER_ID)) =  "0"
DesensitizedUtil.desensitized("段正淳", DesensitizedUtils.DesensitizedType.CHINESE_NAME)) = "段**"
DesensitizedUtil.desensitized("51343620000320711X", DesensitizedUtils.DesensitizedType.ID_CARD)) = "5***************1X"
DesensitizedUtil.desensitized("09157518479", DesensitizedUtils.DesensitizedType.FIXED_PHONE)) = "0915*****79"
DesensitizedUtil.desensitized("18049531999", DesensitizedUtils.DesensitizedType.MOBILE_PHONE)) = "180****1999"
DesensitizedUtil.desensitized("北京市海淀区马连洼街道289号", DesensitizedUtils.DesensitizedType.ADDRESS)) = "北京市海淀区马********"
DesensitizedUtil.desensitized("duandazhi-jack@gmail.com.cn", DesensitizedUtils.DesensitizedType.EMAIL)) = "d*************@gmail.com.cn"
DesensitizedUtil.desensitized("1234567890", DesensitizedUtils.DesensitizedType.PASSWORD)) = "**********"
DesensitizedUtil.desensitized("苏D40000", DesensitizedUtils.DesensitizedType.CAR_LICENSE)) = "苏D4***0"
DesensitizedUtil.desensitized("11011111222233333256", DesensitizedUtils.DesensitizedType.BANK_CARD)) = "1101 **** **** **** 3256"

```


1. 如果自定义的是基于正则脱敏的注解，可选择继承 AbstractRegexDesensitizationHandler (opens new window)处理器。

2. 如果自定义的是基于滑块脱敏的注解，可选择继承 AbstractSliderDesensitizationHandler (opens new window)处理器。
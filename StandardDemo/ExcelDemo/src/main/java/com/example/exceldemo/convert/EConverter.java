package com.example.exceldemo.convert;

import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.example.exceldemo.annontaions.ExcelAnnotation;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import com.example.exceldemo.enums.ConverterEnum;
/**
 * Excel 数据字典转换器
 */
@Slf4j
public class EConverter implements Converter<Object> {
    
    @Override
    public Class<?> supportJavaTypeKey() {
        //指定状态码类型
        return Integer.class;
    }
    
    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        //指定需要获取的参数类型
        return CellDataTypeEnum.STRING;
    }
    
    @Override
    public Object convertToJavaData(ReadCellData readCellData, ExcelContentProperty contentProperty,
                                     GlobalConfiguration globalConfiguration) throws Exception{
        //value:状态码  contentProperty：字段属性  globalConfiguration：全局配置
        //获取字段属性中的注解
        Field field = contentProperty.getField();
        ExcelAnnotation excelAnnotation = field.getAnnotation(ExcelAnnotation.class);
        //获取注解中的枚举信息
        Class<? extends Enum> type = excelAnnotation.type();
        //获取枚举类的方法名 “codeOf”就是自己编写的函数，Integer.class 指定入参类型
        Method codeOf = type.getMethod(ConverterEnum.ENUM_BY_NAME, String.class);
        //反射执行方法，此方法得到的是一个枚举实例（具体得到什么，结合自身项目）
        Object invoke = codeOf.invoke(type, readCellData.getStringValue());
        //枚举实例调用getname方法，得到name的值
        Method getCode = invoke.getClass().getMethod(ConverterEnum.CODE);
        return getCode.invoke(invoke);
    }
    
    @Override
    public WriteCellData<?> convertToExcelData(Object object, ExcelContentProperty contentProperty, GlobalConfiguration globalConfiguration) throws Exception {
        //value:状态码  contentProperty：字段属性  globalConfiguration：全局配置
        //获取字段属性中的注解
        Field field = contentProperty.getField();
        ExcelAnnotation excelAnnotation = field.getAnnotation(ExcelAnnotation.class);
        //获取注解中的枚举信息
        Class<? extends Enum> type = excelAnnotation.type();
        //获取枚举类的方法名 “codeOf”就是自己编写的函数，Integer.class 指定入参类型
        Method codeOf = type.getMethod(ConverterEnum.ENUM_BY_CODE, Integer.class);
        //反射执行方法，此方法得到的是一个枚举实例（具体得到什么，结合自身项目）
        Object invoke = codeOf.invoke(type, object);
        //枚举实例调用getname方法，得到name的值
        Method getName = invoke.getClass().getMethod(ConverterEnum.NAME);
        String name = String.valueOf(getName.invoke(invoke));
        //将转换的值进行返回
        return  new WriteCellData<>(name);
    }
}

package com.example.droolsdemo;

import java.util.UUID;

public class DynamicDroolsGenerator {
    
    public static String generateDRL() {
        StringBuilder drlBuilder = new StringBuilder();
        
        drlBuilder.append("package com.example.rules;\n\n")
                .append("import com.example.droolsdemo.Demo1.entity.RuleEngineFact;\n")
                .append("import com.example.droolsdemo.Demo1.entity.Property;\n\n")
                .append("function boolean matchCondition(Number value, Property property) {\n")
                .append("    if (property.isRange()) {\n")
                .append("        return value.doubleValue() >= property.getMin() && value.doubleValue() <= property.getMax();\n")
                .append("    } else {\n")
                .append("        switch (property.getOperator()) {\n")
                .append("            case \">=\": return value.doubleValue() >= property.getThreshold();\n")
                .append("            case \"<=\": return value.doubleValue() <= property.getThreshold();\n")
                .append("            case \">\": return value.doubleValue() > property.getThreshold();\n")
                .append("            case \"<\": return value.doubleValue() < property.getThreshold();\n")
                .append("            case \"=\": return value.doubleValue() == property.getThreshold();\n")
                .append("            default: return false;\n")
                .append("        }\n")
                .append("    }\n")
                .append("}\n\n");
        
        drlBuilder.append("rule \"DynamicRule\"\n")
                .append("when\n")
                .append("    $fact: RuleEngineFact($name: name, $value: value)\n")
                .append("    $property: Property($name == $fact.name) from variables.get($name)\n")
                .append("    eval(matchCondition($value, $property))\n")
                .append("then\n")
                .append("    System.out.println(\"规则触发，条件满足，属性名：\" + $name + \", 值：\" + $value);\n")
                .append("end\n\n");
        
        return drlBuilder.toString();
    }
    
    public static void main(String[] args) {
        System.out.println(generateDRL());;
    }
}

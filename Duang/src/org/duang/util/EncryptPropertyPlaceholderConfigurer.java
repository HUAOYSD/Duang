package org.duang.util;

import org.duang.common.logger.LoggerUtils;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer  
{  
    private String[] encryptPropNames = {"connection.username", "connection.password"};  
      
    @Override  
    protected String convertProperty(String propertyName, String propertyValue)  
    {  
          
        //如果在加密属性名单中发现该属性  
        if (isEncryptProp(propertyName))  
        {  
            String decryptValue = JDBCDES.getDecryptString(propertyValue);  
            LoggerUtils.info("------------------------重新启动访问数据库"+decryptValue, this.getClass());  
            return decryptValue;  
        }else {  
            return propertyValue;  
        }  
          
    }  
      
    private boolean isEncryptProp(String propertyName)  
    {  
        for (String encryptName : encryptPropNames)  
        {  
            if (encryptName.equals(propertyName))  
            {  
                return true;  
            }  
        }  
        return false;  
    }  
}  

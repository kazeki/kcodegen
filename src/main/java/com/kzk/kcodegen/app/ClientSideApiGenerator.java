package com.kzk.kcodegen.app;

import com.google.common.collect.Maps;
import com.kzk.kcodegen.model.ApiDocs;
import com.kzk.kcodegen.tools.StringUtils;
import com.kzk.kcodegen.core.KCodeGenerator;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * @author kazeki
 */
@Slf4j
public class ClientSideApiGenerator{

    public static final String TEMPLATE_ID_FEIGN_SERVICE = "feign/feignClient";
    public static final String TEMPLATE_ID_FEIGN_MODEL = "feign/model";

    public static final String SUB_PACKAGE_API_SERVICE = ".service";
    public static final String SUB_PACKAGE_API_MODEL = ".model";

    private KCodeGenerator kCodeGenerator;

    public ClientSideApiGenerator(KCodeGenerator kCodeGenerator){
        this.kCodeGenerator = kCodeGenerator;
    }

    public void render(String serviceName, String rootPackage, ApiDocs apiDocs, String outputDir) {

        String apiServicePackage = rootPackage + SUB_PACKAGE_API_SERVICE;
        String apiModelPackage = rootPackage + SUB_PACKAGE_API_MODEL;
        Map<String, Object> datas = Maps.newHashMap();
        datas.put("serviceName", serviceName);
        datas.put("apiServicePackage", apiServicePackage);
        datas.put("apiModelPackage", apiModelPackage);
        datas.put("apiDocs", apiDocs);
        try {
            kCodeGenerator.render(TEMPLATE_ID_FEIGN_SERVICE, datas, outputDir + "/src/main/java/" + StringUtils.packageToPath(apiServicePackage) + "/" + StringUtils.upperCamel(serviceName) + ".java", true);

            for (Map.Entry<String, ApiDocs.Definition> entry : apiDocs.getDefinitions().entrySet()) {
                String modelName = StringUtils.getModelNameFromRefStr(entry.getKey());
                ApiDocs.Definition modelDefinition = entry.getValue();
                log.info("modelName: " + modelName);

                Map<String, Object> datasOfModel = Maps.newHashMap();
                datasOfModel.put("apiModelPackage", apiModelPackage);
                datasOfModel.put("modelName", modelName);
                datasOfModel.put("model", modelDefinition);
                kCodeGenerator.render(TEMPLATE_ID_FEIGN_MODEL, datasOfModel, outputDir + "/src/main/java/" + StringUtils.packageToPath(apiModelPackage) + "/" + modelName + ".java", true);
            }
        } catch (Exception e) {
            log.error("异常!", e);
        }
    }

}

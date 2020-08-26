package com.tron.modulepaser.utils;


import com.tron.modulepaser.entity.ModuleInfo;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.List;

public class PomDom4j {

    /*获取当前模块的模块信息*/
    public static ModuleInfo getSelectedModuleInfo(String pomXmlPath) {
        SAXReader reader = new SAXReader();
        ModuleInfo moduleInfo = new ModuleInfo();
        try {
            Document document = reader.read(pomXmlPath);
            Element root = document.getRootElement();
            moduleInfo.setGroupid(root.elementText("groupId"));
            moduleInfo.setArtifactid(root.elementText("artifactId"));
            moduleInfo.setVersion(root.elementText("version"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return moduleInfo;
    }

    /*获取当前模块依赖的模块的模块信息*/
    public static List<ModuleInfo> getDependencies(String pomXmlPath) {
        SAXReader reader = new SAXReader();
        List<ModuleInfo> moduleInfos = new ArrayList<>();
        try {
            Document document = reader.read(pomXmlPath);
            Element root = document.getRootElement();
            Element dependenciesNode = root.element("dependencies");
            List<Element> dependencies = dependenciesNode.elements("dependency");
            for (Element dependency : dependencies) {
                ModuleInfo moduleInfo = new ModuleInfo();
                moduleInfo.setGroupid(dependency.elementText("groupId"));
                moduleInfo.setArtifactid(dependency.elementText("artifactId"));
                moduleInfo.setVersion(dependency.elementText("version"));
                moduleInfos.add(moduleInfo);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return moduleInfos;
    }
}

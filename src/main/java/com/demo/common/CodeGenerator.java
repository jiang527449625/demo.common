//package com.demo.common;
//
//import freemarker.template.TemplateExceptionHandler;
//import org.mybatis.generator.api.MyBatisGenerator;
//import org.mybatis.generator.config.*;
//import org.mybatis.generator.internal.DefaultShellCallback;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
///**
// * 代码生成器，根据数据表名称生成对应的Model、Mapper、Service、Controller简化开发。
// * @author  jky
// */
//public class CodeGenerator {
//    private String jdbc_diver_class_name;
//    private String jdbc_password;
//    private String jdbc_url;
//    private String jdbc_username;
//    private String base_package;
//    private String model_entity;//entity
//    private String model_vo;//vo所在包
//    private String mapper_package;//Mapper所在包
//    private String service_package;//Service所在包
//    private String service_impl_package;//ServiceImpl所在包
//    private String controller_package;//Controller所在包
//    private String mapper_interface_reference;//Mapper插件基础接口的完全限定名
//    private String project_path;
//    private String package_path_service;//生成的Service存放路径
//    private String package_path_service_impl;//生成的Service实现存放路径
//    private String package_path_controller;//生成的Controller存放路径
//    private String package_path_model = "";//生成的vo存放路径
//    private String package_path_vo;//生成的vo存放路径
//    private String model;
//    private static final String JAVA_PATH = "/src/main/java"; //java文件路径
//    private static final String RESOURCES_PATH = "/src/main/resources";//资源文件路径
//    private static final String AUTHOR = "jky";//@author
//    private static final String DATE = new SimpleDateFormat("yyyy/MM/dd").format(new Date());//@date
//    private static final String model_base_package = "com.demo.object.domain";
//
//    public CodeGenerator(String base_package, String project_path, String jdbc_diver_class, String jdbc_url, String jdbc_username, String jdbc_password, String model) {
//        model_entity = base_package + ".model.entity";//entity
//        model_vo = base_package + ".model.vo";//vo所在包
//        mapper_package = base_package + ".dao";//Mapper所在包
//        service_package = base_package + ".service";//Service所在包
//        service_impl_package = service_package + ".impl";//ServiceImpl所在包
//        controller_package = base_package + ".controller";//Controller所在包
////        mapper_interface_reference = base_package + ".mybatis.Mapper";//Mapper插件基础接口的完全限定名
////        TEMPLATE_FILE_PATH = PROJECT_PATH + "/src/main/resources/generator/template
////        String path = this.getClass().getResource("template").getPath();
////        File file = new File(path);
////        file = new File(file.getParent());
//        package_path_service = packageConvertPath(service_package);//生成的Service存放路径
//        package_path_service_impl = packageConvertPath(service_impl_package);//生成的Service实现存放路径
//        package_path_controller = packageConvertPath(controller_package);//生成的Controller存放路径
//        package_path_model = packageConvertPath(model_entity);//生成的vo存放路径
//        package_path_vo = packageConvertPath(model_vo);
//        this.jdbc_password = jdbc_password;
//        this.jdbc_diver_class_name = jdbc_diver_class;
//        this.jdbc_url = jdbc_url;
//        this.jdbc_username = jdbc_username;
//        this.project_path = project_path;
//        this.base_package = base_package;
//        this.model = model;
//    }
//
//    public static void main(String[] args) {
////        com.mysql.jdbc.Driver
//        String jdbc_diver_class = "oracle.jdbc.driver.OracleDriver";
//        String jdbc_url = "jdbc:oracle:thin:@192.168.1.30:1521:slyyhis";
//        String jdbc_username = "shyt";
//        String jdbc_password = "shyt010203";
//        String base_package = "com.demo.object.sys";//项目基础包名称，根据自己公司的项目修改
//        String model = "sys";//模块
//        String project_path = System.getProperty("user.dir");//项目在硬盘上的基础路径
//        project_path="d:/代码生成点/ytkj.medical.domain";
//        CodeGenerator generator = new CodeGenerator(base_package, project_path, jdbc_diver_class, jdbc_url, jdbc_username, jdbc_password, model);
//        String [] tables={"sys_customer_form"};
//        Arrays.stream(tables).forEach(table->{
////          这里生成表对应的文件名所以第一个字母大写
//            String modelName = tableNameConvertLowerCamel(table,true);
//            generator.generatorCode(table, modelName);
////            generator.generatorCode(table, modelName.substring(1,modelName.length()));
//        });
//    }
//
//    public void genCode(String... tableNames) {
//        for (String tableName : tableNames) {
//            //根据需求生成，不需要的注掉，模板有问题的话可以自己修改。
//            String[] nameparams = tableName.split(",");
//            String modelName = null;
//            if (tableName.indexOf(",") > 0) {
//                // 表示生成的实体有别名
//                tableName = nameparams[0];
//                modelName = nameparams[1];
//            }
//            generatorCode(tableName, modelName);
//        }
//    }
//
//    public void generatorCode(String tableName) {
//        generatorCode(tableName, tableName);
//
//    }
//
//    public void generatorCode(String tableName, String modelName) {
//        genModelAndMapper(tableName, modelName);
//        genService(modelName, modelName);
//        genController(modelName, modelName);
//        genVo(tableName, modelName);
//    }
//
//    /**
//     *
//     * @param tableName
//     * @param modelName
//     * @param params
//     *  * model 只生成 *entity.java，*mapper.java,mapper.xml
//     *  * service 只生成sercice
//     *  * controller 只生成controller
//     *  * vo 只生成Vo
//     */
//    public void generatorCode(String tableName, String modelName, String... params) {
//        for (String param : params) {
//            if (param.equals("model")) genModelAndMapper(tableName, modelName);
//            if (param.equals("service")) genService(modelName, modelName);
//            if (param.equals("controller")) genController(modelName, modelName);
//            if (param.equals("vo")) genVo(tableName, modelName);
//        }
//    }
//
//    public void genModelAndMapper(String tableName, String modelName) {
//        Context context = new Context(ModelType.FLAT);
//        context.setId("Potato");
//        context.setTargetRuntime("MyBatis3Simple");
//        context.addProperty(PropertyRegistry.CONTEXT_BEGINNING_DELIMITER, "`");
//        context.addProperty(PropertyRegistry.CONTEXT_ENDING_DELIMITER, "`");
//
//        JDBCConnectionConfiguration jdbcConnectionConfiguration = new JDBCConnectionConfiguration();
//        jdbcConnectionConfiguration.setConnectionURL(jdbc_url);
//        jdbcConnectionConfiguration.setUserId(jdbc_username);
//        jdbcConnectionConfiguration.setPassword(jdbc_password);
//        jdbcConnectionConfiguration.setDriverClass(jdbc_diver_class_name);
//        context.setJdbcConnectionConfiguration(jdbcConnectionConfiguration);
//
//        PluginConfiguration pluginConfiguration = new PluginConfiguration();
//        pluginConfiguration.setConfigurationType("tk.mybatis.mapper.generator.MapperPlugin");
//        pluginConfiguration.addProperty("mappers", "com.demo.common.mybatis.Mapper");
//        context.addPluginConfiguration(pluginConfiguration);
//
//        JavaModelGeneratorConfiguration javaModelGeneratorConfiguration = new JavaModelGeneratorConfiguration();
//        javaModelGeneratorConfiguration.setTargetProject(project_path + JAVA_PATH);
//        javaModelGeneratorConfiguration.setTargetPackage(model_entity);
//        context.setJavaModelGeneratorConfiguration(javaModelGeneratorConfiguration);
//
////      创建目录，这里主要针对mapper，这快无法创建改目录则手动构建一次
//        File dir = new File(project_path+RESOURCES_PATH);
//        if(!dir.exists()){
//            dir.mkdirs();
//        }
//
//        SqlMapGeneratorConfiguration sqlMapGeneratorConfiguration = new SqlMapGeneratorConfiguration();
//        sqlMapGeneratorConfiguration.setTargetProject(project_path + RESOURCES_PATH);
//        sqlMapGeneratorConfiguration.setTargetPackage("mapper");
//        context.setSqlMapGeneratorConfiguration(sqlMapGeneratorConfiguration);
//
//        JavaClientGeneratorConfiguration javaClientGeneratorConfiguration = new JavaClientGeneratorConfiguration();
//        javaClientGeneratorConfiguration.setTargetProject(project_path + JAVA_PATH);
//        javaClientGeneratorConfiguration.setTargetPackage(mapper_package);
//        javaClientGeneratorConfiguration.setConfigurationType("XMLMAPPER");
//        context.setJavaClientGeneratorConfiguration(javaClientGeneratorConfiguration);
//
//        TableConfiguration tableConfiguration = new TableConfiguration(context);
//        tableConfiguration.setTableName(tableName);
//		Boolean flag = (modelName == null || "".equals(modelName) || "null".equals(modelName) || "undefined".equals(modelName)) ? true : false;
//        if (!flag) tableConfiguration.setDomainObjectName(modelName);
//        tableConfiguration.setGeneratedKey(new GeneratedKey("id", "Postgres", true, null));
//        context.addTableConfiguration(tableConfiguration);
//
//        List<String> warnings;
//        MyBatisGenerator generator;
//        try {
//            Configuration config = new Configuration();
//            config.addContext(context);
//            config.validate();
//
//            boolean overwrite = true;
//            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
//            warnings = new ArrayList<String>();
//            generator = new MyBatisGenerator(config, callback, warnings);
//            generator.generate(null);
//        } catch (Exception e) {
//            throw new RuntimeException("生成Model和Mapper失败", e);
//        }
//
//        if (generator.getGeneratedJavaFiles().isEmpty() || generator.getGeneratedXmlFiles().isEmpty()) {
//            throw new RuntimeException("生成Model和Mapper失败：" + warnings);
//        }
//
////        String modelName = tableNameConvertUpperCamel(tableName);
//        System.out.println(modelName + ".java 生成成功"+project_path + JAVA_PATH);
//        System.out.println(modelName + "Mapper.java 生成成功");
//        System.out.println(modelName + "Mapper.xml 生成成功");
//    }
//
//    public void genService(String tableName, String modelName) {
//        try {
//            freemarker.template.Configuration cfg = getConfiguration();
//
//            Map<String, Object> data = new HashMap<String, Object>();
//            data.put("date", DATE);
//            data.put("author", AUTHOR);
////            String modelNameUpperCamel = modelName;
//            data.put("modelNameUpperCamel", modelName);
//            data.put("modelNameLowerCamel", modelNameConvertUpperCamel(modelName));
//            data.put("basePackage", base_package);
////            data.put("modelBasePackage", base_package);
//
//
//            File file = new File(project_path + JAVA_PATH + package_path_service + "I" + modelName + "Service.java");
//            if (!file.getParentFile().exists()) {
//                file.getParentFile().mkdirs();
//            }
//            cfg.getTemplate("service.ftl").process(data,
//                    new FileWriter(file));
//            System.out.println(modelName + "Service.java 生成成功");
//
//            File file1 = new File(project_path + JAVA_PATH + package_path_service_impl + modelName + "ServiceImpl.java");
//            if (!file1.getParentFile().exists()) {
//                file1.getParentFile().mkdirs();
//            }
//            cfg.getTemplate("service-impl.ftl").process(data,
//                    new FileWriter(file1));
//            System.out.println(modelName + "ServiceImpl.java 生成成功");
//        } catch (Exception e) {
//            throw new RuntimeException("生成Service失败", e);
//        }
//    }
//
//    public void genController(String tableName, String modelName) {
//        try {
//            freemarker.template.Configuration cfg = getConfiguration();
//
//            Map<String, Object> data = new HashMap<String, Object>();
//            data.put("date", DATE);
//            data.put("author", AUTHOR);
//            data.put("baseRequestMapping", tableNameConvertMappingPath(tableName));
//            String modelNameUpperCamel = tableNameConvertUpperCamel(tableName);
////            data.put("baseRequestMapping", modelNameUpperCamel+"Controller");
//            data.put("modelNameUpperCamel", modelName);
//            data.put("modelNameLowerCamel", modelNameConvertUpperCamel(modelName));
//            data.put("basePackage", base_package);
//            data.put("modelBasePackage", model_base_package);
//            File file = new File(project_path + JAVA_PATH + package_path_controller + modelName + "Controller.java");
//            if (!file.getParentFile().exists()) {
//                file.getParentFile().mkdirs();
//            }
//            //cfg.getTemplate("controller-restful.ftl").process(data, new FileWriter(file));
//            cfg.getTemplate("controller.ftl").process(data, new FileWriter(file));
//
//            System.out.println(modelName + "Controller.java 生成成功");
//        } catch (Exception e) {
//            throw new RuntimeException("生成Controller失败", e);
//        }
//
//    }
//
//    public void genVo(String tableName, String modelName) {
//        try {
//            freemarker.template.Configuration cfg = getConfiguration();
//
//            Map<String, Object> data = new HashMap<String, Object>();
//            data.put("date", DATE);
//            data.put("author", AUTHOR);
//			Boolean flag = (modelName == null || "".equals(modelName) || "null".equals(modelName) || "undefined".equals(modelName)) ? true : false;
//            String modelNameUpperCamel = flag ? tableNameConvertUpperCamel(tableName) : modelName;
//            data.put("modelNameUpperCamel", modelNameUpperCamel);
//            data.put("modelNameLowerCamel", tableNameConvertLowerCamel(tableName,false));
//            data.put("basePackage", base_package);
//            data.put("commonsPackage", model_vo);
//            File file = new File(project_path + JAVA_PATH + package_path_vo + modelNameUpperCamel + "VO.java");
//            if (!file.getParentFile().exists()) {
//                file.getParentFile().mkdirs();
//            }
//            cfg.getTemplate("vo.ftl").process(data, new FileWriter(file));
//            System.out.println(modelNameUpperCamel + "VO.java 生成成功");
//
//        } catch (Exception e) {
//            throw new RuntimeException("生成VO失败", e);
//        }
//    }
//
//    private freemarker.template.Configuration getConfiguration() throws IOException {
//        freemarker.template.Configuration cfg = new freemarker.template.Configuration(freemarker.template.Configuration.VERSION_2_3_23);
//
////        cfg.setDirectoryForTemplateLoading(new File(template_file_path));
//        cfg.setDefaultEncoding("UTF-8");
//        cfg.setClassForTemplateLoading(this.getClass(), "/");
//        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);
//        return cfg;
//    }
//
//    /**
//     *
//     * @param tableName
//     * @param type 传递true 第一个字符大写 传递 false 第一个字符小写
//     * @return
//     */
//    public static String tableNameConvertLowerCamel(String tableName,Boolean type) {
//        StringBuilder result = new StringBuilder();
//        if (tableName != null && tableName.length() > 0) {
//            tableName = tableName.toLowerCase();//兼容使用大写的表名
//            boolean flag = type;
//            for (int i = 0; i < tableName.length(); i++) {
//                char ch = tableName.charAt(i);
//                if ("_".charAt(0) == ch) {
//                    flag = true;
//                } else {
//                    if (flag) {
//                        result.append(Character.toUpperCase(ch));
//                        flag = false;
//                    } else {
//                        result.append(ch);
//                    }
//                }
//            }
//        }
//        return result.toString();
//    }
//
//    public String tableNameConvertUpperCamel(String tableName) {
//        String camel = tableNameConvertLowerCamel(tableName,true);
//        return camel.substring(0, 1).toUpperCase() + camel.substring(1);
//
//    }
//
//    public String modelNameConvertUpperCamel(String modelName) {
//        return modelName.substring(0, 1).toLowerCase() + modelName.substring(1);
//
//    }
//
//    public String tableNameConvertMappingPath(String tableName) {
//        tableName = tableName.toLowerCase();//兼容使用大写的表名
//        return model + "/auth/" + (tableName.contains("_") ? tableName.replaceAll("_", "/") : tableName);
//    }
//
//    public String packageConvertPath(String packageName) {
//        return String.format("/%s/", packageName.contains(".") ? packageName.replaceAll("\\.", "/") : packageName);
//    }
//}

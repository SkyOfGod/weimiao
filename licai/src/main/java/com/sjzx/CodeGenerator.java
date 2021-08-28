package com.sjzx;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.io.IOException;

public class CodeGenerator {

    private static final String GLOBAL_PARENT = "com.sjzx";
    private static final String DB_NAEM = "licai";
    private static final boolean ALL_TABLE = false;
    private static final String GLOBAL_OUTPUT_DIR = "/src/main/java/";

    public static void main(String[] args) throws IOException {
        AutoGenerator gen = new AutoGenerator();
        //数据源配置
        gen.setDataSource(getDataSourceConfig());
        //全局配置
        gen.setGlobalConfig(getGlobalConfig());
        //策略配置 tables为null时生成库里面所有的表
        if (ALL_TABLE) {
            gen.setStrategy(getStrategyConfig());
        } else {
            gen.setStrategy(getStrategyConfig("fund", "fund_company"));
        }
        //包配置
        gen.setPackageInfo(getPackageConfig());
        //设置Freemarker模板引擎
        gen.setTemplateEngine(new FreemarkerTemplateEngine());
        //执行
        gen.execute();
    }

    private static PackageConfig getPackageConfig() {
        return new PackageConfig()
                .setParent(GLOBAL_PARENT)
                .setService("service")
                .setServiceImpl("service.impl")
                .setMapper("mapper")
                .setXml("mapper");
    }

    private static StrategyConfig getStrategyConfig(String... tables) {
        StrategyConfig strategyConfig = new StrategyConfig()
                //.setCapitalMode(true) // 全局大写命名
                //.setTablePrefix(new String[]{""}) // 此处可以修改为您的表前缀
                .setNaming(NamingStrategy.underline_to_camel)
                //.setExclude(new String[]{"test"}) // 排除生成的表
                //使用lombok
                .setEntityLombokModel(true)
                //rest风格
                //.setRestControllerStyle(true)
                ;
        if (tables != null) {
            strategyConfig.setInclude(tables);
        }
        return strategyConfig;

    }

    private static GlobalConfig getGlobalConfig() throws IOException {
        //获取项目路径
        String canonicalPath = new File("").getCanonicalPath();
        return new GlobalConfig()
                .setOutputDir(canonicalPath + GLOBAL_OUTPUT_DIR)//输出目录
                .setFileOverride(false)//是否覆盖文件
                .setActiveRecord(true)//activeRecord 实体类继承Model类
                .setEnableCache(false)// XML 二级缓存
                .setBaseResultMap(false)//XML ResultMap
                .setBaseColumnList(false)//XML columList
                //.setSwagger2(true)
                .setOpen(false)//生成后打开文件夹
                .setAuthor("")
                // 自定义文件命名，注意 %s 会自动填充表实体属性！
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                //.setControllerName("%sIController")
                ;
    }

    private static DataSourceConfig getDataSourceConfig() {
        return new DataSourceConfig()
                .setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUrl("jdbc:mysql://159.138.46.107:3307/" + DB_NAEM + "?serverTimezone=GMT%2B8")
                .setUsername("root")
                .setPassword("123456")
                //将datetime装换为localdatetime的类型换成date类型
                .setTypeConvert((globalConfig, fieldType) -> {
                    System.out.println("转换类型：" + fieldType);
                    if (fieldType.toLowerCase().contains("datetime")) {
                        return DbColumnType.DATE;
                    }
                    if (fieldType.toLowerCase().contains("date")) {//LocalDate
                        return DbColumnType.DATE;
                    }
                    if (fieldType.toLowerCase().contains("decimal")) {
                        return DbColumnType.BIG_DECIMAL;
                    }
                    if (fieldType.toLowerCase().contains("float")) {
                        return DbColumnType.BIG_DECIMAL;
                    }
                    if (fieldType.toLowerCase().contains("bit")) {
                        return DbColumnType.INTEGER;
                    }
                    return new MySqlTypeConvert().processTypeConvert(globalConfig, fieldType);
                });
    }

}

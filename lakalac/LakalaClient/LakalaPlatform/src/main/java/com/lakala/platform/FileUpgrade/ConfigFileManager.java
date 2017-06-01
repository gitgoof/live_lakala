package com.lakala.platform.FileUpgrade;

import android.text.TextUtils;

import com.lakala.platform.common.CrashlyticsUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by andy_lv on 14-3-18.
 * <p/>
 * 配置文件管理工具类
 */
public class ConfigFileManager {

    private static ConfigFileManager configFileManager;

    /**
     * 构造方法
     */
    private ConfigFileManager() {
    }

    /**
     * 得到ConfigFileManager 类实例
     *
     * @return
     */
    public static ConfigFileManager getInstance() {
        if (null == configFileManager) {
            configFileManager = new ConfigFileManager();
        }
        return configFileManager;
    }


    /**
     * 读取"business_launcher.config"文件
     * 此操作需要消耗时间，需放在Thread中工作
     *
     * @return 配置文件内容的JSONObject 对象
     */
    public JSONObject readBusinessLauncherConfig() {
        return readConfigFile("business_launcher");
    }

    /**
     * 读取"jiaoyi_jilu.config"文件
     * 此操作需要消耗时间，需放在Thread中工作
     *
     * @return 配置文件内容的JSONObject 对象
     */
    public JSONObject readJiaoyiJiluConfig() {
        return readConfigFile("jiaoyi_jilu");
    }

    /**
     * 读取"lakala_service.config"文件
     * 此操作需要消耗时间，需放在Thread中工作
     *
     * @return 配置文件内容的JSONObject 对象
     */
    public JSONObject readLakalaServiceConfig() {
        return readConfigFile("lakala_service");
    }

    /**
     * 读取"mywallet.config"文件
     * 此操作需要消耗时间，需放在Thread中工作
     *
     * @return 配置文件内容的JSONObject 对象
     */
    public JSONObject readMyWallentConfig() {
        return readConfigFile("mywallet");
    }

    /**
     * 读取"popular_business.config"文件
     * 此操作需要消耗时间，需放在Thread中工作
     *
     * @return 配置文件内容的JSONObject 对象
     */
    public JSONObject readPopularBusinessConfig() {
        return readConfigFile("popular_business");
    }

    /**
     * 读取"webapp.config"文件
     * 此操作需要消耗时间，需放在Thread中工作
     *
     * @return 配置文件内容的JSONObject 对象
     */
    public JSONObject readWebAppConfig() {
        return readConfigFile("webapp");
    }

    /**
     * 读取Statistic.config文件
     *
     * @return
     */
    public JSONObject readStatisticConfig() {
        return readConfigFile("statistic");
    }

    /**
     * 读取pluginfilter.config文件
     *
     * @return
     */
    public JSONObject readPluginFilterConfig() {
        return readConfigFile("pluginfilter");
    }


    /**
     * 读取cacherule.config 文件
     *
     * @return
     */
    public JSONObject readCacheRuleConfig(){
        return readConfigFile("cacherule");
    }

    /**
     * 读取cacherule.config 文件
     *
     * @return
     */
    public JSONObject readExpressConfig(){
        return readConfigFile("express");
    }
    /**
     * 读取配置文件内容
     *
     * @param configFileName 配置文件名称，不包括“文件扩展名"
     */
    public synchronized JSONObject readConfigFile(final String configFileName) {
        if (TextUtils.isEmpty(configFileName)) {
            return null;
        }

        String content = FileUpgradeManager.getInstance().read(configFileName);

        JSONObject resultJson = new JSONObject();
        try {
            resultJson = new JSONObject(content);
        } catch (JSONException e) {
            CrashlyticsUtil.logException(e);
        }

        return resultJson;
    }
}
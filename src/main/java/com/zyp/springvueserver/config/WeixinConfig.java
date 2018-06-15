package com.zyp.springvueserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by August.Zhou on 2017/8/28 15:56.
 */
@Configuration
public class WeixinConfig {

    private String pcConnectUrl = "https://open.work.weixin.qq.com/wwopen/sso/qrConnect";

    private String mobileConnectUrl = "https://open.weixin.qq.com/connect/oauth2/authorize";


    @Value("${test.qiyeWeixin.corpID}")
    private String corpID;
    @Value("${test.qiyeWeixin.redirectUri}")
    private String redirectUri;
    @Value("${test.qiyeWeixin.agentId}")
    private String agentId;
    @Value("${test.qiyeWeixin.state}")
    private String state;
    @Value("${test.qiyeWeixin.secret}")
    private String secret;

    public String getPcConnectUrl() {
        return pcConnectUrl;
    }

    public void setPcConnectUrl(String pcConnectUrl) {
        this.pcConnectUrl = pcConnectUrl;
    }

    public String getCorpID() {
        return corpID;
    }

    public void setCorpID(String corpID) {
        this.corpID = corpID;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }

    public String getAgentId() {
        return agentId;
    }

    public void setAgentId(String agentId) {
        this.agentId = agentId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    private String pcRequestUrl;

    /**
     * 组装请求URL :
     * 如：https://open.work.weixin.qq.com/wwopen/sso/qrConnect?appid=ww100000a5f2191&agentid=1000000&redirect_uri=http%3A%2F%2Fwww.oa.com&state=web_login@gyoss9
     *
     * @return
     */
    public String getPcRequestUrl() {
        if (pcRequestUrl == null) {
            StringBuilder stringBuilder = new StringBuilder(pcConnectUrl);
            stringBuilder
                    .append("?")
                    .append("appid=").append(corpID).append("&")
                    .append("agentid=").append(agentId).append("&")
                    .append("redirect_uri=").append(encodeRedirectUri(redirectUri)).append("&")
                    .append("state=").append(state);
            pcRequestUrl = stringBuilder.toString();
        }
        return pcRequestUrl;
    }


    private String mobileRequestUrl;

    /**
     * https://open.weixin.qq.com/connect/oauth2/authorize?appid=CORPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&agentid=AGENTID&state=STATE#wechat_redirect
     *
     * @return
     */
    public String getMobileRequestUrl() {
        if (mobileRequestUrl == null) {
            StringBuilder stringBuilder = new StringBuilder(mobileConnectUrl);
            stringBuilder
                    .append("?")
                    .append("appid=").append(corpID).append("&")
                    .append("redirect_uri=").append(encodeRedirectUri(redirectUri)).append("&")
                    .append("response_type=code&")
                    .append("scope=snsapi_userinfo&")
                    .append("agentid=").append(agentId).append("&")
                    .append("state=").append(state);
            mobileRequestUrl = stringBuilder.toString();
        }
        return mobileRequestUrl;
    }

    private String encodeRedirectUri(String redirect_uri) {
        String uri = "";
        try {
            uri = URLEncoder.encode(redirect_uri, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return uri;
    }
}

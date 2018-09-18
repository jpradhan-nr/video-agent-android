package com.newrelic.videoagent.backend;

import com.newrelic.agent.android.NewRelic;
import com.newrelic.videoagent.utils.NRLog;

import java.util.HashMap;
import java.util.Map;

public class BackendActions {

    private Map<String, Object> generalOptions = new HashMap<>();
    private Map<String, Map<String, Object>> actionOptions = new HashMap<>();

    // Getters and Setters

    public Map<String, Object> getGeneralOptions() {
        return generalOptions;
    }

    public void setGeneralOptions(Map<String, Object> generalOptions) {
        this.generalOptions = generalOptions;
    }


    public Map<String, Map<String, Object>> getActionOptions() {
        return actionOptions;
    }

    public void setActionOptions(Map<String, Map<String, Object>> actionOptions) {
        this.actionOptions = actionOptions;
    }

    // Senders

    public void sendAction(String name) {
        sendAction(name, null);
    }

    public void sendAction(String name, Map attr) {

        attr = (attr == null) ? new HashMap() : attr;
        attr.put("actionName", name);
        attr.putAll(generalOptions);
        attr.putAll(filteredActionOptions(name));

        if (NewRelic.currentSessionId() != null) {
            NewRelic.recordCustomEvent(EventDefs.VIDEO_EVENT, attr);
        }
        else {
            NRLog.e("⚠️ The NewRelicAgent is not initialized, you need to do it before using the NewRelicVideo. ⚠️");
        }

        NRLog.d("sendAction name = " + name + " attr = " + attr);
    }

    // Contents senders

    public void sendRequest() {
        sendAction(EventDefs.CONTENT_REQUEST);
    }

    public void sendStart() {
        sendAction(EventDefs.CONTENT_START);
    }

    public void sendEnd() {
        sendAction(EventDefs.CONTENT_END);
    }

    public void sendPause() {
        sendAction(EventDefs.CONTENT_PAUSE);
    }

    public void sendResume() {
        sendAction(EventDefs.CONTENT_RESUME);
    }

    public void sendSeekStart() {
        sendAction(EventDefs.CONTENT_SEEK_START);
    }

    public void sendSeekEnd() {
        sendAction(EventDefs.CONTENT_SEEK_END);
    }

    public void sendBufferStart() {
        sendAction(EventDefs.CONTENT_BUFFER_START);
    }

    public void sendBufferEnd() {
        sendAction(EventDefs.CONTENT_BUFFER_END);
    }

    public void sendHeartbeat() {
        sendAction(EventDefs.CONTENT_HEARTBEAT);
    }

    public void sendRenditionChange() {
        sendAction(EventDefs.CONTENT_RENDITION_CHANGE);
    }

    public void sendError() {
        sendAction(EventDefs.CONTENT_ERROR);
    }

    // Ads senders

    public void sendAdRequest() {
        sendAction(EventDefs.AD_REQUEST);
    }

    public void sendAdStart() {
        sendAction(EventDefs.AD_START);
    }

    public void sendAdEnd() {
        sendAction(EventDefs.AD_END);
    }

    public void sendAdPause() {
        sendAction(EventDefs.AD_PAUSE);
    }

    public void sendAdResume() {
        sendAction(EventDefs.AD_RESUME);
    }

    public void sendAdSeekStart() {
        sendAction(EventDefs.AD_SEEK_START);
    }

    public void sendAdSeekEnd() {
        sendAction(EventDefs.AD_SEEK_END);
    }

    public void sendAdBufferStart() {
        sendAction(EventDefs.AD_BUFFER_START);
    }

    public void sendAdBufferEnd() {
        sendAction(EventDefs.AD_BUFFER_END);
    }

    public void sendAdHeartbeat() {
        sendAction(EventDefs.AD_HEARTBEAT);
    }

    public void sendAdRenditionChange() {
        sendAction(EventDefs.AD_RENDITION_CHANGE);
    }

    public void sendAdError() {
        sendAction(EventDefs.AD_ERROR);
    }

    public void sendAdBreakStart() {
        sendAction(EventDefs.AD_BREAK_START);
    }

    public void sendAdBreakEnd() {
        sendAction(EventDefs.AD_BREAK_END);
    }

    public void sendAdQuartile() {
        sendAction(EventDefs.AD_QUARTILE);
    }

    public void sendAdClick() {
        sendAction(EventDefs.AD_CLICK);
    }

    // Misc senders

    public void sendPlayerReady() {
        sendAction(EventDefs.PLAYER_READY);
    }

    public void sendDownload() {
        sendAction(EventDefs.DOWNLOAD);
    }

    private Map<String, Object> filteredActionOptions(String filter) {

        Map<String, Object> dict = new HashMap<>();

        for (Map.Entry<String, Map<String, Object>> entry : actionOptions.entrySet()) {
            String key = entry.getKey();

            if (key.endsWith("_")) {
                // "key" is a Prefix
                if (filter.startsWith(key)) {
                    dict.putAll(actionOptions.get(key));
                }
            }
            else if (key.startsWith("_")) {
                // "key" is a Suffix
                if (filter.endsWith(key)) {
                    dict.putAll(actionOptions.get(key));
                }
            }
            else {
                // "key" is just a normal action name
                dict.putAll(actionOptions.get(key));
            }
        }

        return dict;
    }
}
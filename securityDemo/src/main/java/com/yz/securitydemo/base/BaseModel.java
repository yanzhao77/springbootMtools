package com.yz.securitydemo.base;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
public class BaseModel {

    private String pfKey = "";

    private String errCd = "";

    private String errMessage = "";

    private int pageNextFlg = 0;

    private String urlPath = "";

    private String dbUptFlg = "";

    /**
     * indicator.
     */
    private int[] indicatorArray = new int[300];

    private Set<String> recodes = new HashSet<String>();

    private int eventKey;
    private String eventValue;

    /**
     * Screen show check.
     *
     * @param recode Screen record
     * @return Show indicator
     */
    public int isShow(String recode) {
        if (getRecodes().contains(recode)) {
            return 1;
        }
        return 0;
    }
}

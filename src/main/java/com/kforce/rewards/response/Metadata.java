/**
 *
 */
package com.kforce.rewards.response;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Metadata implements Serializable {

    private String version;
    private String status;
    private String HTTP_status_code;
    private String result_count;
}

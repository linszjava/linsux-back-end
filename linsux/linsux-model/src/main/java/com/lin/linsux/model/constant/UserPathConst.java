package com.lin.linsux.model.constant;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>TODO</p>
 *
 * @author linsz
 * @version v1.0
 * @date 2023/11/10 18:33
 */
@Data
@Component
@ConfigurationProperties(prefix = "linsux.auth")
public class UserPathConst {

    private List<String> noAuthUrls;
}

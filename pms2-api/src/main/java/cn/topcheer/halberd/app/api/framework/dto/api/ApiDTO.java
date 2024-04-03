package cn.topcheer.halberd.app.api.framework.dto.api;

import cn.topcheer.halberd.app.api.framework.entity.api.AmApi;
import cn.topcheer.halberd.app.api.framework.entity.api.AmApiRequest;
import cn.topcheer.halberd.app.api.framework.entity.api.AmApiResponse;
import lombok.Data;

import java.util.List;

/**
 * 模型DTO
 *
 * @author Chill
 */
@Data
public class ApiDTO extends AmApi {

    private static final long serialVersionUID = 1L;

    private List<AmApiRequest> requests;

    private List<AmApiResponse> responses;

    private List<ApiDTO> children;

    private String ids;

    private String customQuerySql;

    private String orders;

}

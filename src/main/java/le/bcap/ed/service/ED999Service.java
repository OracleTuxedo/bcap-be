package le.bcap.ed.service;

import jakarta.servlet.http.HttpServletRequest;
import le.bcap.az.ServiceSupport;
import le.bcap.ed.dto.ED999InDto;
import le.bcap.ed.dto.ED999OutDto;
import le.bcap.module.ed.ed03.sed03f107r.SED03F107RInVo;
import le.bcap.module.ed.ed03.sed03f107r.SED03F107RModule;
import le.bcap.module.ed.ed03.sed03f107r.SED03F107ROutVo;
import mti.com.telegram.vo.TelegramOutputUserData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class ED999Service {

    private static final Logger logger = LogManager.getLogger(ED999Service.class);

    private final ServiceSupport support;
    private final SED03F107RModule sed03F107RModule;

    public ED999Service(ServiceSupport support, SED03F107RModule sed03F107RModule) {
        this.support = support;
        this.sed03F107RModule = sed03F107RModule;
    }

    public ED999OutDto getListOfEDC(HttpServletRequest request, ED999InDto inDto) throws Exception {

        SED03F107RInVo sed03F107RInVo = SED03F107RInVo.builder()
                .prd_tp_cd(inDto.getPrd_tp_cd())
                .sno(inDto.getSno())
                .srl_stat_cd(inDto.getSrl_stat_cd())
                .srl_st_cd(inDto.getSrl_st_cd())
                .prd_cd(inDto.getPrd_cd())
                .icc_id(inDto.getIcc_id())
                .build();

        TelegramOutputUserData sed03f107Result = sed03F107RModule.call(request,  sed03F107RInVo);
        SED03F107ROutVo sed03F107ROutVo = (SED03F107ROutVo) sed03f107Result.getOutput();

        return ED999OutDto.builder()
                .count(sed03F107ROutVo.count)
                .sno(sed03F107ROutVo.sno)
                .whous_cd(sed03F107ROutVo.whous_cd)
                .rack_no(sed03F107ROutVo.rack_no)
                .icc_id(sed03F107ROutVo.icc_id)
                .sim_no(sed03F107ROutVo.sim_no)
                .srl_stat_cd(sed03F107ROutVo.srl_stat_cd)
                .srl_st_cd(sed03F107ROutVo.srl_st_cd)
                .srl_loca_cd(sed03F107ROutVo.srl_loca_cd)
                .vend_no(sed03F107ROutVo.vend_no)
                .prd_cd(sed03F107ROutVo.prd_cd)
                .prd_nm(sed03F107ROutVo.prd_nm)
                .build();
    }
}

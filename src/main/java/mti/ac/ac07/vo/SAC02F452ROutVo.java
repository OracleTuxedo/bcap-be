package mti.ac.ac07.vo;

import java.util.List;

import mti.com.telegram.model.FieldType;
import mti.com.telegram.model.Kind;
import mti.com.telegram.model.NumberType;
import mti.com.telegram.model.TrimType;
import mti.com.telegram.model.annotation.DATATYPE;
import mti.com.telegram.model.annotation.FIELD;

public class SAC02F452ROutVo {


    @FIELD(kind=Kind.DATA, length = 10, type = FieldType.NUMBER, trim = TrimType.LTRIM)
    @DATATYPE(type=NumberType.INT, decimal=0)
    public long tot_cnt;



    @FIELD(kind=Kind.DATA, length = 0, type = FieldType.LIST, trim = TrimType.LTRIM)
    public List<SAC02F452ROutSubVo> subVo;


    public long getTot_cnt() {
        return tot_cnt;
    }

    public void setTot_cnt(long tot_cnt) {
        this.tot_cnt = tot_cnt;
    }

    public List<SAC02F452ROutSubVo> getSubVo() {
        return subVo;
    }

    public void setSubVo(List<SAC02F452ROutSubVo> subVo) {
        this.subVo = subVo;
    }

    @Override
    public String toString() {
        return "MC07V001ROutVo [tot_cnt=" + tot_cnt
                + ", subVo=" + subVo
                + "]";
    }
}

package com.kuark.tool.advance.advance20201105util.excel.advance1;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author rock
 * @detail 客户Excel文件上传监听器-带响应信息
 * @date 2020/11/4 17:10
 */
@Slf4j
public class ExcelUploadAdvanceListener<T,S> extends AnalysisEventListener<T> {
    /**
     * 每隔100条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    List<T> list = new ArrayList<T>();

    private EasyExcelBizAdvanceService<T,S> bizService;
    private ExcelRespContext<S> respContext;

    public ExcelUploadAdvanceListener(EasyExcelBizAdvanceService bizService, ExcelRespContext<S> respContext) {
        this.bizService = bizService;
        this.respContext=respContext;
    }

    /**
     * 这个每一条数据解析都会来调用
     * @param analysisContext
     */
    @Override
    public void invoke(T data, AnalysisContext analysisContext) {
        log.info("解析到一条数据:{}", data);
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            bizOperateData();
            // 存储完成清理 list
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     * @param analysisContext
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        bizOperateData();
        log.info("所有数据解析完成！");
    }

    /**
     * 数据具体操作
     */
    private void bizOperateData() {
        log.info("{}条数据，开始业务操作！", list.size());
        List<S> operateRespList = bizService.bizOperate(list);
        respContext.addData(operateRespList);
        log.info("业务操作完成！");
    }
}

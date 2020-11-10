<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="width: 100%;height: 40px">
    公司:&nbsp;&nbsp;<input class="easyui-textbox" id="listp_searchCompanyId">
    规格:&nbsp;&nbsp;<input class="easyui-textbox" id="listp_searchCompanyReportType">
    行业类型:&nbsp;&nbsp;<input class="easyui-textbox" id="listp_searchCompanyType">
    年份:&nbsp;&nbsp;<input class="easyui-textbox" id="listp_searchYear">
    <input type="hidden" id="listp_searchCompanyReportTypeKey"/>
    <button class="easyui-linkbutton" iconCls="icon-search" onclick="profitStatisticsListSearch()">搜索</button>
    <button id="export1" href="#" class="easyui-linkbutton" >导出excel</button>
</div>
<table id="profit-statistics-list" style="width:100%;height:600px"></table>

<script type="text/javascript">

    $("#listp_searchYear").combogrid({
        panelWidth: 170,
        idField: 'key',
        textField: 'value',
        url: '/company/yearCombogrid',
        mode: 'remote',
        delay: 500,
        columns: [[
            {field: 'value', title: '年份', width: 150, align: 'center'},
        ]],
    });

    $("#listp_searchCompanyType").combogrid({
        panelWidth: 170,
        idField: 'key',
        textField: 'value',
        url: '/company/typeCombogrid',
        mode: 'remote',
        delay: 500,
        columns: [[
            {field: 'key', title: '值', width: 30, align: 'center'},
            {field: 'value', title: '类型', width: 120, align: 'center'},
        ]],
    });

    $("#listp_searchCompanyReportType").combobox({
        valueField:'key',
        textField:'value',
        data: [{
            key: '',
            value: '全部'
        },{
            key: '1',
            value: '年报'
        },{
            key: '2',
            value: '第三季度报'
        },{
            key: '3',
            value: '半年报'
        },{
            key: '4',
            value: '第一季度报'
        }],
        onSelect: function (record) {
            $("#listp_searchCompanyReportTypeKey").val(record.key);
        }
    });

    $("#listp_searchCompanyId").combogrid({
        panelWidth: 260,
        idField: 'id',
        textField: 'name',
        url: '/company/combogrid',
        mode: 'remote',
        delay: 500,
        columns: [[
            {field: 'id', title: '主键', width: 40, align: 'center'},
            {field: 'code', title: '股票代码', width: 100, align: 'center'},
            {field: 'name', title: '公司名称', width: 100, align: 'center'},
        ]],
    });

    $('#profit-statistics-list').datagrid({
        url:'/profitStatistics/listPage',
        title: '合并利润表统计列表',
        pagePosition: 'top',
        singleSelect: true ,
        collapsible:true,
        pagination:true,
        rownumbers: true,
        pageSize: 20,
        pageList:[20,50,100],
        columns:[[
            {field:'id',checkbox:true},
            {field: 'code', title: '股票代码', width: 100, align: 'center'},
            {field: 'name', title: '公司名称', width: 100, align: 'center'},
            {field: 'year', title: '年份', width: 40, align: 'center'},
            {
                field: 'reportType', title: '规格', width: 60, align: 'center',
                formatter: function (value, row, index) {
                    if (value === 1) {
                        return '年报';
                    } else if (value === 2) {
                        return '第三季度报';
                    } else if (value === 3) {
                        return '半年报';
                    }  else if (value === 4) {
                        return '第一季度报';
                    }
                }
            },
            {field: 'businessIncomeGrowthRate', title: '营业收入增速', width: 100, align: 'center'},
            {field: 'grossProfitMargin', title: '毛利率', width: 80, align: 'center'},
            {field: 'costRate', title: '费用率', width: 80, align: 'center'},
            {field: 'costInProfit', title: '费用率/毛利率', width: 100, align: 'center'},
            {field: 'mainProfit', title: '主营利润', width: 100, align: 'center'},
            {field: 'businessToProfit', title: '经营活动产生的现金流量净额', width: 120, align: 'center'},
            {field: 'profitQuality', title: '利润质量 经营活动产生的现金流量净额/主营利润', width: 80, align: 'center'},
            {field: 'profitQualityOne', title: '利润质量 经营活动产生的现金流量净额/净利润', width: 80, align: 'center'},
            {field: 'mainProfitInProfitTotal', title: '主营利润/利润总额', width: 120, align: 'center'},
            {field: 'mainProfitInIncomeTotal', title: '主营利润率 主营利润/营业收入', width: 80, align: 'center'},
            {field: 'belongMotherNetProfit', title: '归母净利润', width: 100, align: 'center'},
            {field: 'belongMotherNetProfitGrowthRate', title: '归母净利润增速', width: 100, align: 'center'},
            {field: 'totalEquity', title: '总股本', width: 100, align: 'center'},
            {field: 'sharesProfit', title: '每股收益', width: 80, align: 'center'},
            {field:'createTime',title:'创建时间',width:150,align:'center'},
            {field:'updateTime',title:'修改时间',width:150,align:'center'}
        ]],
        onBeforeLoad: function (param) {
            param.pageNo = param.page;
            param.pageSize = param.rows;
            param.companyId = $("#listp_searchCompanyId").val();
            param.reportType = $("#listp_searchCompanyReportTypeKey").val();
            param.type = $("#listp_searchCompanyType").val();
            param.year = $("#listp_searchYear").val();
            return true;
        }
    });
    profitStatisticsListSearch = function () {
        $("#profit-statistics-list").datagrid('load');
    }

    $("#export1").click(function () {
        location ="/profitStatistics/export";
    });
</script>
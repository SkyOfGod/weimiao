<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="width: 100%;height: 40px">
    公司:&nbsp;&nbsp;<input class="easyui-textbox" id="listd_searchCompanyId">
    规格:&nbsp;&nbsp;<input class="easyui-textbox" id="listd_searchCompanyReportType">
    行业类型:&nbsp;&nbsp;<input class="easyui-textbox" id="listd_searchCompanyType">
    年份:&nbsp;&nbsp;<input class="easyui-textbox" id="listd_searchYear">
    <input type="hidden" id="listd_searchCompanyReportTypeKey"/>
    <button class="easyui-linkbutton" iconCls="icon-search" onclick="profitStatisticsListSearch()">搜索</button>
    <button id="listd_export" href="#" class="easyui-linkbutton" >导出excel</button>
</div>
<table id="profit-statistics-list" style="width:100%;height:600px"></table>

<script type="text/javascript">

    $("#listd_searchYear").combogrid({
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

    $("#listd_searchCompanyType").combogrid({
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

    $("#listd_searchCompanyReportType").combobox({
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
            $("#listd_searchCompanyReportTypeKey").val(record.key);
        }
    });

    $("#listd_searchCompanyId").combogrid({
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
            {field: 'reportType', title: '规格', width: 60, align: 'center'},
            {field: 'businessIncomeGrowthRate', title: '营业收入增速', width: 100, align: 'right'},
            {field: 'grossProfitMargin', title: '毛利率', width: 80, align: 'right'},
            {field: 'costRate', title: '费用率', width: 80, align: 'right'},
            {field: 'costInProfit', title: '费用率/毛利率', width: 100, align: 'right'},
            {field: 'mainProfit', title: '主营利润', width: 100, align: 'right'},
            {field: 'businessToProfit', title: '经营活动产生的现金流量净额', width: 120, align: 'right'},
            {field: 'profitQuality', title: '利润质量 经营活动产生的现金流量净额/主营利润', width: 80, align: 'right'},
            {field: 'profitQualityOne', title: '利润质量 经营活动产生的现金流量净额/净利润', width: 80, align: 'right'},
            {field: 'mainProfitInProfitTotal', title: '主营利润/利润总额', width: 120, align: 'right'},
            {field: 'mainProfitInIncomeTotal', title: '主营利润率 主营利润/营业收入', width: 80, align: 'right'},
            {field: 'belongMotherNetProfit', title: '归母净利润', width: 100, align: 'right'},
            {field: 'belongMotherNetProfitGrowthRate', title: '归母净利润增速', width: 100, align: 'right'},
            {field: 'totalEquity', title: '总股本', width: 100, align: 'right'},
            {field: 'sharesProfit', title: '每股收益', width: 80, align: 'right'},
            {field:'createTime',title:'创建时间',width:150,align:'center'},
            {field:'updateTime',title:'修改时间',width:150,align:'center'}
        ]],
        onBeforeLoad: function (param) {
            param.pageNo = param.page;
            param.pageSize = param.rows;
            param.companyId = $("#listd_searchCompanyId").val();
            param.reportType = $("#listd_searchCompanyReportTypeKey").val();
            param.type = $("#listd_searchCompanyType").val();
            param.year = $("#listd_searchYear").val();
            return true;
        }
    });
    profitStatisticsListSearch = function () {
        $("#profit-statistics-list").datagrid('load');
    }

    $("#listd_export").click(function () {
        var companyId = $("#listd_searchCompanyId").val();
        if(!companyId) {
            $.messager.alert('提示', '请选择公司!', 'warning');
            return;
        }
        $.messager.confirm('确认', '确定导出合并利润表指标数据吗？', function (r) {
            if (r) {
                location = "/profitStatistics/export?companyId=" + companyId
                    + "&reportType=" + $("#listd_searchCompanyReportTypeKey").val()
                    + "&type=" + $("#listd_searchCompanyType").val()
                    + "&year=" + $("#listd_searchYear").val();
            }
        });
    });
</script>
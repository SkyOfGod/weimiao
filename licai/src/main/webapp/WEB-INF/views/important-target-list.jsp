<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div style="width: 100%;height: 40px">
    公司:&nbsp;&nbsp;<input class="easyui-textbox" id="listh_searchCompanyId">
    规格:&nbsp;&nbsp;<input class="easyui-textbox" id="listh_searchCompanyReportType">
    行业类型:&nbsp;&nbsp;<input class="easyui-textbox" id="listh_searchCompanyType">
    年份:&nbsp;&nbsp;<input class="easyui-textbox" id="listh_searchYear">
    <input type="hidden" id="listh_searchCompanyReportTypeKey"/>
    <button class="easyui-linkbutton" iconCls="icon-search" onclick="importantTargetSearch()">搜索</button>
</div>
<table id="important-target-list" style="width:100%;height:600px"></table>

<script type="text/javascript">

    $("#listh_searchYear").combogrid({
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

    $("#listh_searchCompanyType").combogrid({
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

    $("#listh_searchCompanyReportType").combobox({
        valueField: 'key',
        textField: 'value',
        data: [{
            key: '',
            value: '全部'
        }, {
            key: '1',
            value: '年报'
        }, {
            key: '2',
            value: '第三季度报'
        }, {
            key: '3',
            value: '半年报'
        }, {
            key: '4',
            value: '第一季度报'
        }],
        onSelect: function (record) {
            $("#listh_searchCompanyReportTypeKey").val(record.key);
        }
    });

    $("#listh_searchCompanyId").combogrid({
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


    $('#important-target-list').datagrid({
        url: '/importantTarget/listPage',
        title: '重要指标表统计列表',
        pagePosition: 'top',
        singleSelect: false,
        collapsible: true,
        pagination: true,
        rownumbers: true,
        pageSize: 20,
        pageList: [20, 50, 100],
        columns: [[
            {field: 'id', checkbox: true},
            {field: 'code', title: '股票代码', width: 100, align: 'center'},
            {field: 'name', title: '公司名称', width: 100, align: 'center'},
            {field: 'year', title: '年份', width: 40, align: 'center'},
            {field: 'reportType', title: '规格', width: 60, align: 'center'},
            {field: 'tp1', title: 'ROE', width: 60, align: 'right'},
            {field: 'tp2', title: '净利润现金比例', width: 100, align: 'right'},
            {field: 'tp3', title: '资产负债率', width: 60, align: 'right'},
            {field: 'tp4', title: '毛利率', width: 60, align: 'right'},
            {field: 'tp5', title: '营业利润率', width: 100, align: 'right'},
            {field: 'tp6', title: '营业收入增长率', width: 100, align: 'right'},
            {field: 'tp7', title: '固定资产比例', width: 100, align: 'right'},
            {field: 'tp8', title: '分红率', width: 60, align: 'right'},
            {field: 'ta1', title: '人均年薪', width: 80, align: 'right'},
            {field: 'ta2', title: 'PE中位数', width: 80, align: 'center'},
            {field: 'remark', title: '备注', width: 300, align: 'left'},
            {field: 'createTime', title: '创建时间', width: 150, align: 'center'},
            {field: 'updateTime', title: '跟新时间', width: 150, align: 'center'}
        ]],
        onBeforeLoad: function (param) {
            param.pageNo = param.page;
            param.pageSize = param.rows;
            param.companyId = $("#listh_searchCompanyId").val();
            param.reportType = $("#listh_searchCompanyReportTypeKey").val();
            param.type = $("#listh_searchCompanyType").val();
            param.year = $("#listh_searchYear").val();
            return true;
        }
    });
    importantTargetSearch = function () {
        $('#important-target-list').datagrid('load');
    }
</script>
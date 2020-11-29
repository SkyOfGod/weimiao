<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div style="width: 100%;height: 40px">
    公司:&nbsp;&nbsp;<input class="easyui-textbox" id="listf_searchCompanyId">
    规格:&nbsp;&nbsp;<input class="easyui-textbox" id="listf_searchCompanyReportType">
    行业类型:&nbsp;&nbsp;<input class="easyui-textbox" id="listf_searchCompanyType">
    年份:&nbsp;&nbsp;<input class="easyui-textbox" id="listf_searchYear">
    <input type="hidden" id="listf_searchCompanyReportTypeKey"/>
    <button class="easyui-linkbutton" iconCls="icon-search" onclick="cashFlowListSearch()">搜索</button>
    <button id="listf_export" href="#" class="easyui-linkbutton" >导出excel</button>
</div>
<table id="cash-flow-statistics-list" style="width:100%;height:600px"></table>

<script type="text/javascript">

    $("#listf_searchYear").combogrid({
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

    $("#listf_searchCompanyType").combogrid({
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

    $("#listf_searchCompanyReportType").combobox({
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
            $("#listf_searchCompanyReportTypeKey").val(record.key);
        }
    });

    $("#listf_searchCompanyId").combogrid({
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


    $('#cash-flow-statistics-list').datagrid({
        url: '/cashFlowStatistics/listPage',
        title: '合并现金流量表统计列表',
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
            {field: 'businessToProfit', title: '经营活动产生的现金流量净额', width: 120, align: 'center'},
            {field: 'bonusCash', title: '分红金额', width: 120, align: 'center'},
            {field: 'profitSubstractBonus', title: '现金净增额 经营活动产生的现金流量净额-分红', width: 150, align: 'center'},
            {field: 'cashInIncoming', title: '销售商品提供劳务收到的现金/营业收入', width: 150, align: 'center'},
            {field: 'expandInProfitRate', title: '购建资产/经营活动产生的现金流量净额', width: 150, align: 'center'},
            {field: 'sellInExpandRate', title: '处置资产/购建资产', width: 110, align: 'center'},
            {field: 'remark', title: '备注', width: 300, align: 'center'},
            {field: 'createTime', title: '创建时间', width: 150, align: 'center'},
            {field: 'updateTime', title: '跟新时间', width: 150, align: 'center'}
        ]],
        onBeforeLoad: function (param) {
            param.pageNo = param.page;
            param.pageSize = param.rows;
            param.companyId = $("#listf_searchCompanyId").val();
            param.reportType = $("#listf_searchCompanyReportTypeKey").val();
            param.type = $("#listf_searchCompanyType").val();
            param.year = $("#listf_searchYear").val();
            return true;
        }
    });
    cashFlowListSearch = function () {
        $('#cash-flow-statistics-list').datagrid('load');
    }
    $("#listf_export").click(function () {
        var companyId = $("#listf_searchCompanyId").val();
        if(!companyId) {
            $.messager.alert('提示', '请选择公司!', 'warning');
            return;
        }
        $.messager.confirm('确认', '确定导出合并现金流量表指标数据吗？', function (r) {
            if (r) {
                location = "/cashFlowStatistics/export?companyId=" + companyId
                    + "&reportType=" + $("#listf_searchCompanyReportTypeKey").val()
                    + "&type=" + $("#listf_searchCompanyType").val()
                    + "&year=" + $("#listf_searchYear").val();
            }
        });
    });
</script>
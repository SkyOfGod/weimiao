<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div style="width: 100%;height: 40px;">
    公司:&nbsp;&nbsp;<input class="easyui-textbox" id="listl_searchCompanyId">
    规格:&nbsp;&nbsp;<input class="easyui-textbox" id="listl_searchCompanyReportType">
    行业类型:&nbsp;&nbsp;<input class="easyui-textbox" id="listl_searchCompanyType">
    年份:&nbsp;&nbsp;<input class="easyui-textbox" id="listl_searchYear">
    <input type="hidden" id="listl_searchCompanyReportTypeKey"/>
    <input type="hidden" id="listl_searchCompanyTypeKey"/>
    <input type="hidden" id="listl_searchCompanyYearKey"/>
    <button style="margin-left: 10px" class="easyui-linkbutton" iconCls="icon-search" onclick="liabilitiesListSearch()">
        搜索
    </button>
</div>
<table id="consolidated-assets-liabilities-list" style="width:100%;height:600px"></table>

<div id="liabilitiesAdd" class="easyui-dialog" data-options="closed:true">
    <form id="liabilitiesAddForm" method="post">
        <table cellpadding="5">
            <input type="hidden" name="id">
            <tr>
                <td>公司:</td>
                <td>
                    <input class="easyui-textbox" name="companyId" style="width: 150px;" data-options="required:true"/>
                </td>
                <td>年份:</td>
                <td>
                    <select class="easyui-combobox" name="year" style="width: 150px;" data-options="editable:true">
                        <option value="2020">2020年</option>
                        <option value="2019">2019年</option>
                        <option value="2018">2018年</option>
                        <option value="2017">2017年</option>
                        <option value="2016">2016年</option>
                        <option value="2015">2015年</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>规格:</td>
                <td>
                    <select class="easyui-combobox" name="reportType" style="width: 150px;"
                            data-options="editable:true">
                        <option value="1">年报</option>
                        <option value="2">第三季度报</option>
                        <option value="3">半年报</option>
                        <option value="4">第一季度报</option>
                    </select>
                </td>
                <td>总资产:</td>
                <td>
                    <input class="easyui-textbox" name="totalAssets" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>总负债:</td>
                <td>
                    <input class="easyui-textbox" name="totalLiabilities" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>股东权益合计:</td>
                <td>
                    <input class="easyui-textbox" name="shareHolderEquity" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>短期负债:</td>
                <td>
                    <input class="easyui-textbox" name="shortTermLiabilities" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>一年内到期的非流动负债:</td>
                <td>
                    <input class="easyui-textbox" name="yearArriveNoCurrentLiabilities" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>长期借款:</td>
                <td>
                    <input class="easyui-textbox" name="longTermLiabilities" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>应付债券:</td>
                <td>
                    <input class="easyui-textbox" name="payableBonds" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>长期应付款:</td>
                <td>
                    <input class="easyui-textbox" name="longPayableMoney" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>应付利息:</td>
                <td>
                    <input class="easyui-textbox" name="payableInterest" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>货币资金:</td>
                <td>
                    <input class="easyui-textbox" name="monetaryCapital" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>应付票据:</td>
                <td>
                    <input class="easyui-textbox" name="payableBill" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>应付账款:</td>
                <td>
                    <input class="easyui-textbox" name="payableMoney" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>预收款项:</td>
                <td>
                    <input class="easyui-textbox" name="advanceReceivableMoney" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>应收票据:</td>
                <td>
                    <input class="easyui-textbox" name="receivableBill" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>应收账款:</td>
                <td>
                    <input class="easyui-textbox" name="receivableMoney" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>预付款项:</td>
                <td>
                    <input class="easyui-textbox" name="advancePayableMoney" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>固定资产:</td>
                <td>
                    <input class="easyui-textbox" name="fixedAssets" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>在建工程:</td>
                <td>
                    <input class="easyui-textbox" name="reconstructionProject" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>工程物资:</td>
                <td>
                    <input class="easyui-textbox" name="engineeringMaterials" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>以公允价值计量且变动计入当期损益的金融资产:</td>
                <td>
                    <input class="easyui-textbox" name="fairValueProject" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>可供出售金融资产:</td>
                <td>
                    <input class="easyui-textbox" name="prepareSaleFinanceProject" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>持有至到期投资:</td>
                <td>
                    <input class="easyui-textbox" name="heldToMaturityInvestment" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>投资房地产:</td>
                <td>
                    <input class="easyui-textbox" name="investinInRealEstate" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>长期股权投资（与主业无关）:</td>
                <td>
                    <input class="easyui-textbox" name="longTermEquityInvestment" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>归属于母公司所有者权益合计:</td>
                <td>
                    <input class="easyui-textbox" name="belongMotherEquity" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>应付职工薪酬:</td>
                <td>
                    <input class="easyui-textbox" name="payableSalary" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>备注:</td>
                <td><input class="easyui-textbox" name="remark" data-options="multiline:true,validType:'length[0,150]'"
                           style="height:60px;width: 200px;"/></td>
            </tr>
        </table>
    </form>
</div>

<div id="liabilitiesEdit" class="easyui-dialog" data-options="closed:true">
    <form id="liabilitiesEditForm" method="post">
        <table cellpadding="5">
            <input type="hidden" name="id">
            <tr>
                <td>公司:</td>
                <td>
                    <input class="easyui-textbox" name="name" style="width: 150px;" data-options="editable:false"/>
                </td>
                <td>年份:</td>
                <td>
                    <select class="easyui-combobox" name="year" style="width: 150px;" data-options="editable:false">
                        <option value="2020">2020年</option>
                        <option value="2019">2019年</option>
                        <option value="2018">2018年</option>
                        <option value="2017">2017年</option>
                        <option value="2016">2016年</option>
                        <option value="2015">2015年</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>规格:</td>
                <td>
                    <select class="easyui-combobox" name="reportType" style="width: 150px;"
                            data-options="editable:false">
                        <option value="1">年报</option>
                        <option value="2">第三季度报</option>
                        <option value="3">半年报</option>
                        <option value="4">第一季度报</option>
                    </select>
                </td>
                <td>总资产:</td>
                <td>
                    <input class="easyui-numberbox" name="totalAssets" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>总负债:</td>
                <td>
                    <input class="easyui-numberbox" name="totalLiabilities" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>股东权益合计:</td>
                <td>
                    <input class="easyui-numberbox" name="shareHolderEquity" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>短期负债:</td>
                <td>
                    <input class="easyui-numberbox" name="shortTermLiabilities" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>一年内到期的非流动负债:</td>
                <td>
                    <input class="easyui-numberbox" name="yearArriveNoCurrentLiabilities" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>长期借款:</td>
                <td>
                    <input class="easyui-numberbox" name="longTermLiabilities" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>应付债券:</td>
                <td>
                    <input class="easyui-numberbox" name="payableBonds" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>长期应付款:</td>
                <td>
                    <input class="easyui-numberbox" name="longPayableMoney" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>应付利息:</td>
                <td>
                    <input class="easyui-numberbox" name="payableInterest" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>货币资金:</td>
                <td>
                    <input class="easyui-numberbox" name="monetaryCapital" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>应付票据:</td>
                <td>
                    <input class="easyui-numberbox" name="payableBill" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>应付账款:</td>
                <td>
                    <input class="easyui-numberbox" name="payableMoney" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>预收款项:</td>
                <td>
                    <input class="easyui-numberbox" name="advanceReceivableMoney" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>应收票据:</td>
                <td>
                    <input class="easyui-numberbox" name="receivableBill" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>应收账款:</td>
                <td>
                    <input class="easyui-numberbox" name="receivableMoney" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>预付款项:</td>
                <td>
                    <input class="easyui-numberbox" name="advancePayableMoney" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>固定资产:</td>
                <td>
                    <input class="easyui-numberbox" name="fixedAssets" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>在建工程:</td>
                <td>
                    <input class="easyui-numberbox" name="reconstructionProject" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>工程物资:</td>
                <td>
                    <input class="easyui-numberbox" name="engineeringMaterials" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>以公允价值计量且变动计入当期损益的金融资产:</td>
                <td>
                    <input class="easyui-numberbox" name="fairValueProject" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>可供出售金融资产:</td>
                <td>
                    <input class="easyui-numberbox" name="prepareSaleFinanceProject" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>持有至到期投资:</td>
                <td>
                    <input class="easyui-numberbox" name="heldToMaturityInvestment" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>投资房地产:</td>
                <td>
                    <input class="easyui-numberbox" name="investinInRealEstate" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>长期股权投资（与主业无关）:</td>
                <td>
                    <input class="easyui-numberbox" name="longTermEquityInvestment" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>归属于母公司所有者权益合计:</td>
                <td>
                    <input class="easyui-numberbox" name="belongMotherEquity" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>应付职工薪酬:</td>
                <td>
                    <input class="easyui-numberbox" name="payableSalary" style="width: 110px;"
                           data-options="required:true"/>
                </td>
                <td>备注:</td>
                <td><input class="easyui-textbox" name="remark" data-options="multiline:true,validType:'length[0,150]'"
                           style="height:60px;width: 200px;"/></td>
            </tr>
        </table>
    </form>
</div>

<script type="text/javascript" charset="utf-8">

    $("#listl_searchYear").combobox({
        valueField: 'key',
        textField: 'value',
        data: [{
            key: '',
            value: '全部'
        }, {
            key: '2020',
            value: '2020年'
        }, {
            key: '2019',
            value: '2019年'
        }, {
            key: '2018',
            value: '2018年'
        }, {
            key: '2017',
            value: '2017年'
        }, {
            key: '2016',
            value: '2016年'
        }, {
            key: '2015',
            value: '2015年'
        }],
        onSelect: function (record) {
            $("#listl_searchCompanyYearKey").val(record.key);
        }
    });

    $("#listl_searchCompanyType").combobox({
        valueField: 'key',
        textField: 'value',
        data: [{
            key: '',
            value: '全部'
        }, {
            key: '1',
            value: '汽车制造'
        }, {
            key: '2',
            value: '白色家电'
        }],
        onSelect: function (record) {
            $("#listl_searchCompanyTypeKey").val(record.key);
        }
    });

    $("#listl_searchCompanyReportType").combobox({
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
            $("#listl_searchCompanyReportTypeKey").val(record.key);
        }
    });

    $("#listl_searchCompanyId").combogrid({
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

    $("#liabilitiesAddForm [name='companyId']").combogrid({
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

    $('#consolidated-assets-liabilities-list').datagrid({
        url: '/liabilities/listPage',
        title: '合并资产负债表列表',
        pagePosition: 'top',
        rownumbers: true,
        singleSelect: true,
        collapsible: true,
        pagination: true,
        pageSize: 20,
        pageList: [20, 50, 100],
        toolbar: [{
            text: '新增',
            iconCls: 'icon-add',
            handler: function () {
                addLiabilitiesData();
            }
        }, '-', {
            text: '编辑',
            iconCls: 'icon-edit',
            handler: function () {
                editLiabilitiesData();
            }
        }, '-',
            {
                text: '删除',
                iconCls: 'icon-cancel',
                handler: function () {
                    deleteLiabilitiesData();
                }
            },
        ],
        columns: [[
            {field: 'id', checkbox: true},
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
                    } else if (value === 4) {
                        return '第一季度报';
                    }
                }
            },
            {field: 'totalAssets', title: '总资产', width: 110, align: 'center'},
            {field: 'totalLiabilities', title: '总负债', width: 110, align: 'center'},
            {field: 'shareHolderEquity', title: '股东权益合计', width: 100, align: 'center'},
            {field: 'shortTermLiabilities', title: '短期负债', width: 100, align: 'center'},
            {field: 'yearArriveNoCurrentLiabilities', title: '一年内到期的非流动负债', width: 100, align: 'center'},
            {field: 'longTermLiabilities', title: '长期借款', width: 100, align: 'center'},
            {field: 'payableBonds', title: '应付债券', width: 100, align: 'center'},
            {field: 'longPayableMoney', title: '长期应付款', width: 100, align: 'center'},
            {field: 'payableInterest', title: '应付利息', width: 100, align: 'center'},
            {field: 'monetaryCapital', title: '货币资金', width: 100, align: 'center'},
            {field: 'payableBill', title: '应付票据', width: 100, align: 'center'},
            {field: 'payableMoney', title: '应付账款', width: 100, align: 'center'},
            {field: 'advanceReceivableMoney', title: '预收款项', width: 100, align: 'center'},
            {field: 'receivableBill', title: '应收票据', width: 100, align: 'center'},
            {field: 'receivableMoney', title: '应收账款', width: 100, align: 'center'},
            {field: 'advancePayableMoney', title: '预付款项', width: 100, align: 'center'},
            {field: 'fixedAssets', title: '固定资产', width: 100, align: 'center'},
            {field: 'reconstructionProject', title: '在建工程', width: 100, align: 'center'},
            {field: 'engineeringMaterials', title: '工程物资', width: 100, align: 'center'},
            {field: 'fairValueProject', title: '以公允价值计量且变动计入当期损益的金融资产', width: 100, align: 'center'},
            {field: 'prepareSaleFinanceProject', title: '可供出售金融资产', width: 100, align: 'center'},
            {field: 'heldToMaturityInvestment', title: '持有至到期投资', width: 100, align: 'center'},
            {field: 'investinInRealEstate', title: '投资房地产', width: 100, align: 'center'},
            {field: 'longTermEquityInvestment', title: '长期股权投资（与主业无关）', width: 200, align: 'center'},
            {field: 'belongMotherEquity', title: '归属于母公司所有者权益合计', width: 200, align: 'center'},
            {field: 'payableSalary', title: '应付职工薪酬', width: 200, align: 'center'},
            {field: 'remark', title: '备注', width: 300, align: 'center'},
            {field: 'createTime', title: '创建时间', width: 150, align: 'center'},
            {field: 'updateTime', title: '修改时间', width: 150, align: 'center'},
        ]],
        onBeforeLoad: function (param) {
            param.pageNo = param.page;
            param.pageSize = param.rows;
            param.companyId = $("#listl_searchCompanyId").val();
            param.reportType = $("#listl_searchCompanyReportTypeKey").val();
            param.type = $("#listl_searchCompanyTypeKey").val();
            param.year = $("#listl_searchCompanyYearKey").val();
            return true;
        }
    });

    liabilitiesListSearch = function () {
        $('#consolidated-assets-liabilities-list').datagrid('load');
    }

    addLiabilitiesData = function () {
        $("#liabilitiesAdd").dialog({
            title: '新增合并资产负债数据',
            width: 700,
            height: 650,
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    var params = $("#liabilitiesAddForm").serialize();
                    $.post("/liabilities/add", params, function (data) {
                        if (data.code == 200) {
                            $.messager.alert('提示', '新增成功!', 'info',
                                function () {
                                    $("#liabilitiesAdd").dialog('close');
                                    $("#consolidated-assets-liabilities-list").datagrid("reload");
                                });
                        } else {
                            $.messager.alert('提示', data.msg, 'warning');
                        }
                    });
                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#liabilitiesAdd").dialog("close");
                }
            }],
            onBeforeClose: function () {
                $("#liabilitiesAddForm").form("clear");
            }
        });
    }

    editLiabilitiesData = function () {
        var ids = getLiabilitiesListSelectionsIds();
        if (ids.length === 0) {
            $.messager.alert('提示', '必须选择一条数据才能编辑!');
            return;
        }
        if (ids.indexOf(',') > 0) {
            $.messager.alert('提示', '只能选择一条数据!');
            return;
        }
        $("#liabilitiesEdit").dialog({
            title: '编辑资产负债表数据',
            width: 700,
            height: 650,
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    var params = $("#liabilitiesEditForm").serialize();
                    $.post("/liabilities/update", params, function (data) {
                        if (data.code == 200) {
                            $.messager.alert('提示', '修改成功!', 'info',
                                function () {
                                    $("#liabilitiesEdit").dialog('close');
                                    $("#consolidated-assets-liabilities-list").datagrid("reload");
                                });
                        } else {
                            $.messager.alert('提示', data.msg, 'warning');
                        }
                    });
                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#liabilitiesEdit").dialog("close");
                }
            }],
            onBeforeClose: function () {
                $("#liabilitiesEditForm").form("clear");
            }
        });
        var data = $("#consolidated-assets-liabilities-list").datagrid("getSelected");
        $("#liabilitiesEditForm").form("load", data);
    }

    deleteLiabilitiesData = function () {
        var ids = getLiabilitiesListSelectionsIds();
        if (ids.length === 0) {
            $.messager.alert('提示', '未选中数据!');
            return;
        }
        $.messager.confirm('确认', '确定删除ID为 ' + ids + ' 的资产负债数据吗？', function (r) {
            if (r) {
                var params = {"id": ids};
                $.post("/liabilities/delete", params, function (data) {
                    if (data.code == 200) {
                        $.messager.alert('提示', '删除成功!', 'info',
                            function () {
                                $("#consolidated-assets-liabilities-list").datagrid("reload");
                            });
                    }
                });
            }
        });
    }

    getLiabilitiesListSelectionsIds = function () {
        var itemList = $("#consolidated-assets-liabilities-list");
        var sels = itemList.datagrid("getSelections");
        var ids = [];
        for (var i in sels) {
            ids.push(sels[i].id);
        }
        ids = ids.join(",");
        return ids;
    }

</script>

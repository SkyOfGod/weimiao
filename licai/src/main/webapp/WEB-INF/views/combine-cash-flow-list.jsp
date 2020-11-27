<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div style="width: 100%;height: 40px">
    公司:&nbsp;&nbsp;<input class="easyui-textbox" id="liste_searchCompanyId">
    规格:&nbsp;&nbsp;<input class="easyui-textbox" id="liste_searchCompanyReportType">
    行业类型:&nbsp;&nbsp;<input class="easyui-textbox" id="liste_searchCompanyType">
    年份:&nbsp;&nbsp;<input class="easyui-textbox" id="liste_searchYear">
    <input type="hidden" id="liste_searchCompanyReportTypeKey"/>
    <button class="easyui-linkbutton" iconCls="icon-search" onclick="cashFlowListSearch()">搜索</button>
</div>
<table id="cash-flow-list" style="width:100%;height:600px"></table>

<div id="addCashFlow" class="easyui-dialog" data-options="closed:true">
    <form id="addCashFlowForm" method="post">
        <table cellpadding="5">
            <input type="hidden" name="id">
            <tr>
                <td>公司:</td>
                <td>
                    <input class="easyui-textbox" name="companyId" style="width: 150px;" data-options="required:true"/>
                </td>
                <td>年份:</td>
                <td>
                    <input class="easyui-textbox" name="year" style="width: 150px;" data-options="required:true"/>
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
                <td>经营活动产生的现金流量净额:</td>
                <td>
                    <input class="easyui-textbox" name="businessToProfit" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>投资活动产生的现金流量净额:</td>
                <td>
                    <input class="easyui-textbox" name="investmentToProfit" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>筹资活动产生的现金流量净额:</td>
                <td>
                    <input class="easyui-textbox" name="financingToProfite" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>销售商品提供劳务收到的现金:</td>
                <td>
                    <input class="easyui-textbox" name="saleToCash" style="width: 150px;" data-options="required:true"/>
                </td>
                <td>支付给职工以及为职工支付的现金:</td>
                <td>
                    <input class="easyui-textbox" name="salary" style="width: 150px;" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>购建固定资产、无形资产和其他长期资产支付的现金:</td>
                <td>
                    <input class="easyui-textbox" name="investmentInsideOut" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>处置固定资产、无形资产和其他长期资产收回的现金净额:</td>
                <td>
                    <input class="easyui-textbox" name="investmentInsideIn" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>收购或投资子公司，对外扩大经营:</td>
                <td>
                    <input class="easyui-textbox" name="investmentOutsideSubOut" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>投资支付的现金:</td>
                <td>
                    <input class="easyui-textbox" name="investmentOutsideMoneyOut" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>现金及现金等价物净增加额:</td>
                <td>
                    <input class="easyui-textbox" name="cashAndCashEquivalentsAdd" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>期初现金及现金等价物余额:</td>
                <td>
                    <input class="easyui-textbox" name="cashAndCashEquivalentsBegin" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>期末现金及现金等价物余额:</td>
                <td>
                    <input class="easyui-textbox" name="cashAndCashEquivalentsEnd" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>分红:</td>
                <td>
                    <input class="easyui-textbox" name="bonusCash" style="width: 150px;" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>备注:</td>
                <td><input class="easyui-textbox" name="remark" data-options="multiline:true,validType:'length[0,150]'"
                           style="height:60px;width: 200px;"/></td>
            </tr>staffTotal
            <tr>
                <td>员工总数:</td>
                <td>
                    <input class="easyui-textbox" name="staffTotal" style="width: 150px;" data-options="required:true"/>
                </td>
            </tr>
        </table>
    </form>
</div>

<div id="editCashFlow" class="easyui-dialog" data-options="closed:true">
    <form id="editCashFlowForm" method="post">
        <table cellpadding="5">
            <input type="hidden" name="id">
            <tr>
                <td>公司:</td>
                <td>
                    <input class="easyui-textbox" name="name" style="width: 150px;" data-options="editable:false"/>
                </td>
                <td>年份:</td>
                <td>
                    <input class="easyui-textbox" name="year" style="width: 150px;" data-options="editable:false"/>
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
                <td>经营活动产生的现金流量净额:</td>
                <td>
                    <input class="easyui-numberbox" name="businessToProfit" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>投资活动产生的现金流量净额:</td>
                <td>
                    <input class="easyui-numberbox" name="investmentToProfit" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>筹资活动产生的现金流量净额:</td>
                <td>
                    <input class="easyui-numberbox" name="financingToProfite" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>销售商品提供劳务收到的现金:</td>
                <td>
                    <input class="easyui-numberbox" name="saleToCash" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>支付给职工以及为职工支付的现金:</td>
                <td>
                    <input class="easyui-numberbox" name="salary" style="width: 150px;" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>购建固定资产、无形资产和其他长期资产支付的现金:</td>
                <td>
                    <input class="easyui-numberbox" name="investmentInsideOut" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>处置固定资产、无形资产和其他长期资产收回的现金净额:</td>
                <td>
                    <input class="easyui-numberbox" name="investmentInsideIn" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>收购或投资子公司，对外扩大经营:</td>
                <td>
                    <input class="easyui-numberbox" name="investmentOutsideSubOut" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>投资支付的现金:</td>
                <td>
                    <input class="easyui-numberbox" name="investmentOutsideMoneyOut" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>现金及现金等价物净增加额:</td>
                <td>
                    <input class="easyui-numberbox" name="cashAndCashEquivalentsAdd" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>期初现金及现金等价物余额:</td>
                <td>
                    <input class="easyui-numberbox" name="cashAndCashEquivalentsBegin" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>期末现金及现金等价物余额:</td>
                <td>
                    <input class="easyui-numberbox" name="cashAndCashEquivalentsEnd" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>分红:</td>
                <td>
                    <input class="easyui-numberbox" name="bonusCash" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>备注:</td>
                <td><input class="easyui-textbox" name="remark" data-options="multiline:true,validType:'length[0,150]'"
                           style="height:60px;width: 200px;"/></td>
            </tr>
            <tr>
                <td>员工总数:</td>
                <td>
                    <input class="easyui-numberbox" name="staffTotal" style="width: 150px;" data-options="required:true"/>
                </td>
            </tr>
        </table>
    </form>
</div>

<div id="importExcel" class="easyui-dialog" title="导入文件" style="width:450px; padding:20px;" data-options="closed:true">
    <form id="uploadExcel"  method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>公司:</td>
                <td><input id="upload_searchCompanyId" name="id" class="easyui-textbox" style="width: 250px;"/></td>
            </tr>
            <tr>
                <td>文件:</td>
                <td><input id="file" name="file" class="easyui-filebox" style="width: 250px;"
                           data-options="prompt:'请选择文件...'"/></td>
            </tr>
        </table>
    </form>
</div>
<script type="text/javascript">

    $("#addCashFlowForm [name='year']").combogrid({
        panelWidth: 150,
        idField: 'key',
        textField: 'value',
        url: '/company/yearCombogrid',
        mode: 'remote',
        delay: 500,
        columns: [[
            {field: 'value', title: '年份', width: 130, align: 'center'},
        ]],
    });

    $("#upload_searchCompanyId").combogrid({
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



    $("#editCashFlowForm [name='year']").combogrid({
        panelWidth: 150,
        idField: 'key',
        textField: 'value',
        url: '/company/yearCombogrid',
        mode: 'remote',
        delay: 500,
        columns: [[
            {field: 'value', title: '年份', width: 130, align: 'center'},
        ]],
    });

    $("#liste_searchYear").combogrid({
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

    $("#liste_searchCompanyType").combogrid({
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

    $("#liste_searchCompanyReportType").combobox({
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
            $("#liste_searchCompanyReportTypeKey").val(record.key);
        }
    });

    $("#liste_searchCompanyId").combogrid({
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

    $("#addCashFlowForm [name='companyId']").combogrid({
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


    $('#cash-flow-list').datagrid({
        url: '/combineCashFlow/listPage',
        title: '合并现金流量表列表',
        pagePosition: 'top',
        singleSelect: true,
        collapsible: true,
        pagination: true,
        rownumbers: true,
        pageSize: 20,
        pageList: [20, 50, 100],
        toolbar: [{
            text: '新增',
            iconCls: 'icon-add',
            handler: function () {
                addCashFlowData();
            }
        }, '-', {
            text: '编辑',
            iconCls: 'icon-edit',
            handler: function () {
                editCashFlowData();
            }
        }, '-',
            {
                text: '删除',
                iconCls: 'icon-cancel',
                handler: function () {
                    deleteCashFlowData();
                }
            },'-',{
                text: '导入',
                iconCls: 'icon-save',
                handler: function () {
                    importCashFlowData();
                }
            }
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
            {field: 'businessToProfit', title: '经营活动产生的现金流量净额', width: 150, align: 'center'},
            {field: 'investmentToProfit', title: '投资活动产生的现金流量净额', width: 150, align: 'center'},
            {field: 'financingToProfite', title: '筹资活动产生的现金流量净额', width: 150, align: 'center'},
            {field: 'saleToCash', title: '销售商品提供劳务收到的现金', width: 150, align: 'center'},
            {field: 'salary', title: '支付给职工以及为职工支付的现金', width: 150, align: 'center'},
            {field: 'investmentInsideOut', title: '购建固定资产、无形资产和其他长期资产支付的现金', width: 150, align: 'center'},
            {field: 'investmentInsideIn', title: '处置固定资产、无形资产和其他长期资产收回的现金净额', width: 150, align: 'center'},
            {field: 'investmentOutsideSubOut', title: '收购或投资子公司，对外扩大经营', width: 150, align: 'center'},
            {field: 'investmentOutsideMoneyOut', title: '投资支付的现金', width: 120, align: 'center'},
            {field: 'cashAndCashEquivalentsAdd', title: '现金及现金等价物净增加额', width: 150, align: 'center'},
            {field: 'cashAndCashEquivalentsBegin', title: '期初现金及现金等价物余额', width: 150, align: 'center'},
            {field: 'cashAndCashEquivalentsEnd', title: '期末现金及现金等价物余额', width: 150, align: 'center'},
            {field: 'bonusCash', title: '分红', width: 110, align: 'center'},
            {field: 'staffTotal', title: '员工总数', width: 110, align: 'center'},
            {field: 'remark', title: '备注', width: 300, align: 'center'},
            {field: 'createTime', title: '创建时间', width: 150, align: 'center'},
            {field: 'updateTime', title: '更新时间', width: 150, align: 'center'},
        ]],
        onBeforeLoad: function (param) {
            param.pageNo = param.page;
            param.pageSize = param.rows;
            param.companyId = $("#liste_searchCompanyId").val();
            param.reportType = $("#liste_searchCompanyReportTypeKey").val();
            param.type = $("#liste_searchCompanyType").val();
            param.year = $("#liste_searchYear").val();
            return true;
        }
    });

    addCashFlowData = function () {
        $("#addCashFlow").dialog({
            title: '新增合并现金流量表数据',
            width: 700,
            height: 650,
            top: 10,
            left: 150,
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    var params = $("#addCashFlowForm").serialize();
                    $.post("/combineCashFlow/add", params, function (data) {
                        if (data.code == 200) {
                            $.messager.alert('提示', '新增成功!', 'info',
                                function () {
                                    $("#addCashFlow").dialog('close');
                                    $("#cash-flow-list").datagrid("reload");
                                });
                        } else {
                            $.messager.alert('提示', data.msg, 'warning');
                        }
                    });
                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#addCashFlow").dialog("close");
                }
            }],
            onBeforeClose: function () {
                $("#addCashFlowForm").form("clear");
            }
        });
    }

    editCashFlowData = function () {
        var ids = getCashFlowListSelectionsIds();
        if (ids.length === 0) {
            $.messager.alert('提示', '必须选择一条数据才能编辑!');
            return;
        }
        if (ids.indexOf(',') > 0) {
            $.messager.alert('提示', '只能选择一条数据!');
            return;
        }
        $("#editCashFlow").dialog({
            title: '编辑合并现金流量表',
            width: 700,
            height: 650,
            top: 10,
            left: 150,
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    var params = $("#editCashFlowForm").serialize();
                    $.post("/combineCashFlow/update", params, function (data) {
                        if (data.code == 200) {
                            $.messager.alert('提示', '修改成功!', 'info',
                                function () {
                                    $("#editCashFlow").dialog('close');
                                    $("#cash-flow-list").datagrid("reload");
                                });
                        } else {
                            $.messager.alert('提示', data.msg, 'warning');
                        }
                    });
                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#editCashFlow").dialog("close");
                }
            }],
            onBeforeClose: function () {
                $("#editCashFlowForm").form("clear");
            }
        });
        var data = $("#cash-flow-list").datagrid("getSelected");
        $("#editCashFlowForm").form("load", data);
    }

    deleteCashFlowData = function () {
        var ids = getCashFlowListSelectionsIds();
        if (ids.length === 0) {
            $.messager.alert('提示', '未选中数据!');
            return;
        }
        $.messager.confirm('确认', '确定删除ID为 ' + ids + ' 的合并现金流量表数据吗？', function (r) {
            if (r) {
                var params = {"id": ids};
                $.post("/combineCashFlow/delete", params, function (data) {
                    if (data.code == 200) {
                        $.messager.alert('提示', '删除成功!', 'info',
                            function () {
                                $("#cash-flow-list").datagrid("reload");
                            });
                    }
                });
            }
        });
    }

    importCashFlowData = function(){
        $("#importExcel").dialog({
            title: 'Excel上传',
            width: 400,
            height: 200,
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
                text: '上传',
                handler: function () {
                    var compName= $('#upload_searchCompanyId').filebox('getValue');
                    if (compName === "") {
                        $.messager.alert('提示', '请选择公司!');
                        return;
                    }
                    //进行基本校验
                    var fileName = $('#file').filebox('getValue');
                    if(fileName == ""){
                        $.messager.alert('提示','请选择上传文件！','info');
                        return;
                    }
                    //对文件格式进行校验
                    var d1 = /\.[^\.]+$/.exec(fileName);
                    if(d1 == ".xlsx" || d1 == ".xls"){
                        var formdata = new FormData($("#uploadExcel")[0]);
                        $.ajax({
                            url: "/combineCashFlow/upload",
                            type: "POST",
                            data:formdata,
                            contentType : "application/json;charset=UTF-8",
                            dataType: "json",
                            processData: false,  // 告诉jQuery不要去处理发送的数据
                            contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
                            success: function (data) {
                                if (data.code == 200) {
                                    $.messager.alert('提示', '导入成功!', 'info',
                                        function () {
                                            $("#importExcel").dialog('close');
                                            $("#consolidated-assets-liabilities-list").datagrid("reload");
                                        });
                                } else {
                                    $.messager.alert('提示', data.msg, 'warning');
                                }
                            }
                        })
                    }else{
                        $.messager.alert('提示','请选择xlsx或xls格式文件！','info');
                        $('#fileUpload').filebox('setValue','');
                    }

                }
            },{
                text: '关闭',
                handler: function () {
                    $("#importExcel").dialog("close");
                }
            }],
            onBeforeClose: function () {
                $("#liabilitiesAddForm").form("clear");
            }
        });
    }

    cashFlowListSearch = function () {
        $('#cash-flow-list').datagrid('load');
    }

    getCashFlowListSelectionsIds = function () {
        var itemList = $("#cash-flow-list");
        var sels = itemList.datagrid("getSelections");
        var ids = [];
        for (var i in sels) {
            ids.push(sels[i].id);
        }
        ids = ids.join(",");
        return ids;
    }

</script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div style="width: 100%;height: 40px">
    公司:&nbsp;&nbsp;<input class="easyui-textbox" id="listc_searchCompanyId">
    规格:&nbsp;&nbsp;<input class="easyui-textbox" id="listc_searchCompanyReportType">
    行业类型:&nbsp;&nbsp;<input class="easyui-textbox" id="listc_searchCompanyType">
    年份:&nbsp;&nbsp;<input class="easyui-textbox" id="listc_searchYear">
    <input type="hidden" id="listc_searchCompanyReportTypeKey"/>
    <button style="margin-left: 10px" class="easyui-linkbutton" iconCls="icon-search" onclick="profitListSearch()">
        搜索
    </button>
</div>
<table id="combine-profit-list" style="width:100%;height:600px"></table>

<div id="addCombineProfit" class="easyui-dialog" data-options="closed:true">
    <form id="addCombineProfitForm" method="post">
        <table cellpadding="5">
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
                            data-options="required:true">
                        <option value="1">年报</option>
                        <option value="2">第三季度报</option>
                        <option value="3">半年报</option>
                        <option value="4">第一季度报</option>
                    </select>
                </td>
                <td>营业收入:</td>
                <td>
                    <input class="easyui-textbox" name="businessIncome" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>营业成本:</td>
                <td>
                    <input class="easyui-textbox" name="businessCosts" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>税金及附加:</td>
                <td>
                    <input class="easyui-textbox" name="taxRevenueTotal" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>销售费用:</td>
                <td>
                    <input class="easyui-textbox" name="sellingExpenses" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>管理费用:</td>
                <td>
                    <input class="easyui-textbox" name="manageExpenses" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>财务费用:</td>
                <td>
                    <input class="easyui-textbox" name="financialExpenses" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>资产减值损失:</td>
                <td>
                    <input class="easyui-textbox" name="assetsImpairmentLoss" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>公允价值变动收益:</td>
                <td>
                    <input class="easyui-textbox" name="incomeFromChangesInFairValue" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>投资收益:</td>
                <td>
                    <input class="easyui-textbox" name="incomeFromInvestment" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>营业利润:</td>
                <td>
                    <input class="easyui-textbox" name="businessProfit" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>营业外收入:</td>
                <td>
                    <input class="easyui-textbox" name="nonBusinessIncome" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>营业外支出:</td>
                <td>
                    <input class="easyui-textbox" name="nonBusinessCosts" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>利润总额:</td>
                <td>
                    <input class="easyui-textbox" name="totalProfit" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>所得税费用:</td>
                <td>
                    <input class="easyui-textbox" name="incomeTaxExpense" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>净利润:</td>
                <td>
                    <input class="easyui-textbox" name="netProfit" style="width: 150px;" data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>归母净利润:</td>
                <td>
                    <input class="easyui-textbox" name="belongMotherNetProfit" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>备注:</td>
                <td><input class="easyui-textbox" name="remark" data-options="multiline:true,validType:'length[0,150]'"
                           style="height:60px;width: 200px;"/></td>
            </tr>
        </table>
    </form>
</div>

<div id="editCombineProfit" class="easyui-dialog" data-options="closed:true">
    <form id="editCombineProfitForm" method="post">
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
                <td>营业收入:</td>
                <td>
                    <input class="easyui-numberbox" name="businessIncome" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>营业成本:</td>
                <td>
                    <input class="easyui-numberbox" name="businessCosts" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>税金及附加:</td>
                <td>
                    <input class="easyui-numberbox" name="taxRevenueTotal" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>销售费用:</td>
                <td>
                    <input class="easyui-numberbox" name="sellingExpenses" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>管理费用:</td>
                <td>
                    <input class="easyui-numberbox" name="manageExpenses" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>财务费用:</td>
                <td>
                    <input class="easyui-numberbox" name="financialExpenses" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>资产减值损失:</td>
                <td>
                    <input class="easyui-numberbox" name="assetsImpairmentLoss" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>公允价值变动收益:</td>
                <td>
                    <input class="easyui-numberbox" name="incomeFromChangesInFairValue" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>投资收益:</td>
                <td>
                    <input class="easyui-numberbox" name="incomeFromInvestment" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>营业利润:</td>
                <td>
                    <input class="easyui-numberbox" name="businessProfit" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>营业外收入:</td>
                <td>
                    <input class="easyui-numberbox" name="nonBusinessIncome" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>营业外支出:</td>
                <td>
                    <input class="easyui-numberbox" name="nonBusinessCosts" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>利润总额:</td>
                <td>
                    <input class="easyui-numberbox" name="totalProfit" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>所得税费用:</td>
                <td>
                    <input class="easyui-numberbox" name="incomeTaxExpense" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>净利润:</td>
                <td>
                    <input class="easyui-numberbox" name="netProfit" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>归母净利润:</td>
                <td>
                    <input class="easyui-numberbox" name="belongMotherNetProfit" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>备注:</td>
                <td><input class="easyui-textbox" name="remark" data-options="multiline:true,validType:'length[0,150]'"
                           style="height:60px;width: 200px;"/></td>
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
<script type="text/javascript" charset="utf-8">

    $("#addCombineProfitForm [name='year']").combogrid({
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

    $("#editCombineProfitForm [name='year']").combogrid({
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

    $("#listc_searchYear").combogrid({
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

    $("#listc_searchCompanyType").combogrid({
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



    $("#listc_searchCompanyReportType").combobox({
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
            $("#listc_searchCompanyReportTypeKey").val(record.key);
        }
    });

    $("#listc_searchCompanyId").combogrid({
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

    $("#addCombineProfitForm [name='companyId']").combogrid({
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

    $('#combine-profit-list').datagrid({
        url: '/combineProfit/listPage',
        title: '合并利润表列表',
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
                addCombineProfitData();
            }
        }, '-', {
            text: '编辑',
            iconCls: 'icon-edit',
            handler: function () {
                editCombineProfitData();
            }
        }, '-',
            {
                text: '删除',
                iconCls: 'icon-cancel',
                handler: function () {
                    deleteCombineProfitData();
                }
            },'-',{
                text: '导入',
                iconCls: 'icon-save',
                handler: function () {
                    importCombineProfitData();
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
            {field: 'businessIncome', title: '营业收入', width: 110, align: 'center'},
            {field: 'businessCosts', title: '营业成本', width: 110, align: 'center'},
            {field: 'taxRevenueTotal', title: '税金及附加', width: 100, align: 'center'},
            {field: 'sellingExpenses', title: '销售费用', width: 100, align: 'center'},
            {field: 'manageExpenses', title: '管理费用', width: 110, align: 'center'},
            {field: 'financialExpenses', title: '财务费用', width: 100, align: 'center'},
            {field: 'assetsImpairmentLoss', title: '资产减值损失', width: 100, align: 'center'},
            {field: 'incomeFromChangesInFairValue', title: '公允价值变动收益', width: 100, align: 'center'},
            {field: 'incomeFromInvestment', title: '投资收益', width: 100, align: 'center'},
            {field: 'businessProfit', title: '营业利润', width: 100, align: 'center'},
            {field: 'nonBusinessIncome', title: '营业外收入', width: 100, align: 'center'},
            {field: 'nonBusinessCosts', title: '营业外支出', width: 100, align: 'center'},
            {field: 'totalProfit', title: '利润总额', width: 100, align: 'center'},
            {field: 'incomeTaxExpense', title: '所得税费用', width: 100, align: 'center'},
            {field: 'netProfit', title: '净利润', width: 100, align: 'center'},
            {field: 'belongMotherNetProfit', title: '归母净利润', width: 100, align: 'center'},
            {field: 'remark', title: '备注', width: 300, align: 'center'},
            {field: 'createTime', title: '创建时间', width: 150, align: 'center'},
            {field: 'updateTime', title: '修改时间', width: 150, align: 'center'},
        ]],
        onBeforeLoad: function (param) {
            param.pageNo = param.page;
            param.pageSize = param.rows;
            param.companyId = $("#listc_searchCompanyId").val();
            param.reportType = $("#listc_searchCompanyReportTypeKey").val();
            param.type = $("#listc_searchCompanyType").val();
            param.year = $("#listc_searchYear").val();
            return true;
        }
    });

    addCombineProfitData = function () {
        $("#addCombineProfit").dialog({
            title: '新增合并利润表数据',
            width: 600,
            height: 550,
            top: 10,
            left: 150,
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    var params = $("#addCombineProfitForm").serialize();
                    $.post("/combineProfit/add", params, function (data) {
                        if (data.code == 200) {
                            $.messager.alert('提示', '新增成功!', 'info',
                                function () {
                                    $("#addCombineProfit").dialog('close');
                                    $("#combine-profit-list").datagrid("reload");
                                });
                        } else {
                            $.messager.alert('提示', data.msg, 'warning');
                        }
                    });
                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#addCombineProfit").dialog("close");
                }
            }],
            onBeforeClose: function () {
                $("#addCombineProfitForm").form("clear");
            }
        });
    }

    editCombineProfitData = function () {
        var ids = getCombineProfitListSelectionsIds();
        if (ids.length === 0) {
            $.messager.alert('提示', '必须选择一条数据才能编辑!');
            return;
        }
        if (ids.indexOf(',') > 0) {
            $.messager.alert('提示', '只能选择一条数据!');
            return;
        }
        $("#editCombineProfit").dialog({
            title: '编辑合并利润表',
            width: 600,
            height: 550,
            top: 10,
            left: 150,
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    var params = $("#editCombineProfitForm").serialize();
                    $.post("/combineProfit/update", params, function (data) {
                        if (data.code == 200) {
                            $.messager.alert('提示', '修改成功!', 'info',
                                function () {
                                    $("#editCombineProfit").dialog('close');
                                    $("#combine-profit-list").datagrid("reload");
                                });
                        } else {
                            $.messager.alert('提示', data.msg, 'warning');
                        }
                    });
                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#editCombineProfit").dialog("close");
                }
            }],
            onBeforeClose: function () {
                $("#editCombineProfitForm").form("clear");
            }
        });
        var data = $("#combine-profit-list").datagrid("getSelected");
        $("#editCombineProfitForm").form("load", data);
    }

    deleteCombineProfitData = function () {
        var ids = getCombineProfitListSelectionsIds();
        if (ids.length === 0) {
            $.messager.alert('提示', '未选中数据!');
            return;
        }
        $.messager.confirm('确认', '确定删除ID为 ' + ids + ' 的合并利润表数据吗？', function (r) {
            if (r) {
                var params = {"id": ids};
                $.post("/combineProfit/delete", params, function (data) {
                    if (data.code == 200) {
                        $.messager.alert('提示', '删除成功!', 'info',
                            function () {
                                $("#combine-profit-list").datagrid("reload");
                            });
                    }
                });
            }
        });
    }

    importCombineProfitData = function(){
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
                            url: "/combineProfit/upload",
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
    profitListSearch = function () {
        $('#combine-profit-list').datagrid('load');
    }

    getCombineProfitListSelectionsIds = function () {
        var itemList = $("#combine-profit-list");
        var sels = itemList.datagrid("getSelections");
        var ids = [];
        for (var i in sels) {
            ids.push(sels[i].id);
        }
        ids = ids.join(",");
        return ids;
    }

</script>

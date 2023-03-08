<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div style="width: 100%;height: 40px;">
    公司:&nbsp;&nbsp;<input class="easyui-textbox" id="listp_searchCompanyId">
    年份:&nbsp;&nbsp;<input class="easyui-textbox" id="listp_searchYear">
    <button style="margin-left: 10px" class="easyui-linkbutton" iconCls="icon-search" onclick="profitStatementlistSearch()">
        搜索
    </button>
</div>
<table id="profitStatement-list" style="width:100%;height:600px"></table>

<div id="profitStatementEdit" class="easyui-dialog" data-options="closed:true">
    <form id="profitStatementEditForm" method="post">
        <table cellpadding="8">
            <input type="hidden" name="id">
            <tr>
                <td>公司:</td>
                <td>
                    <input class="easyui-textbox" name="name" style="width: 150px;" data-options="required:true"/>
                </td>
                <td>年份:</td>
                <td>
                    <input class="easyui-textbox" name="year" style="width: 150px;" data-options="editable:false"/>
                </td>
            </tr>
            <tr>
                <td>营业收入:</td>
                <td>
                    <input class="easyui-numberbox" name="operatingIncome" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>营业成本:</td>
                <td>
                    <input class="easyui-numberbox" name="operatingCost" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>销售费用:</td>
                <td>
                    <input class="easyui-numberbox" name="expenseSales" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>管理费用:</td>
                <td>
                    <input class="easyui-numberbox" name="administrativeExpenses" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>研发费用:</td>
                <td>
                    <input class="easyui-numberbox" name="researchDevelopmentExpenses" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>财务费用:</td>
                <td>
                    <input class="easyui-numberbox" name="financialExpenses" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>营业税金及附加:</td>
                <td>
                    <input class="easyui-numberbox" name="businessTaxesSurcharges" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>营业利润:</td>
                <td>
                    <input class="easyui-numberbox" name="operatingProfit" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>净利润:</td>
                <td>
                    <input class="easyui-numberbox" name="netProfit" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>归属于母公司所有者的净利润:</td>
                <td>
                    <input class="easyui-numberbox" name="ttm" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>备注:</td>
                <td><input class="easyui-textbox" name="remark" data-options="multiline:true,validType:'length[0,150]'"
                           style="height:60px;width: 200px;"/></td>
                </td>
            </tr>
        </table>
    </form>
</div>

<div id="importExcel-p" class="easyui-dialog" title="导入文件" style="width:450px; padding:20px;" data-options="closed:true">
    <form id="uploadExcel-p"  method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>公司:</td>
                <td><input id="upload_searchCompanyId_p" name="id" class="easyui-textbox" style="width: 250px;"/></td>
            </tr>
            <tr>
                <td>文件:</td>
                <td><input id="file-p" name="file" class="easyui-filebox" style="width: 250px;"
                           data-options="prompt:'请选择文件...'"/></td>
            </tr>
        </table>
    </form>
</div>

<script type="text/javascript" charset="utf-8">

    $("#profitStatementEditForm [name='year']").combogrid({
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

    $("#upload_searchCompanyId_p").combogrid({
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

    $('#profitStatement-list').datagrid({
        url: '/profitStatement/listpage',
        title: '利润表',
        pagePosition: 'top',
        rownumbers: true,
        singleSelect: true,
        collapsible: true,
        pagination: true,
        pageSize: 20,
        pageList: [20, 50, 100],
        toolbar: [
            {
                text: '编辑',
                iconCls: 'icon-edit',
                handler: function () {
                    editProfitStatementData();
                }
            }, '-',{
                text: '删除',
                iconCls: 'icon-cancel',
                handler: function () {
                    deleteProfitStatementData();
                }
            },'-',{
                text: '导入',
                iconCls: 'icon-save',
                handler: function () {
                    importProfitStatementData();
                }
            }
        ],
        columns: [[
            {field: 'id', checkbox: true},
            {field: 'code', title: '股票代码', width: 100, align: 'center'},
            {field: 'name', title: '公司名称', width: 100, align: 'center'},
            {field: 'year', title: '年份', width: 40, align: 'center'},
            {field: 'operatingIncome', title: '营业收入', width: 120, align: 'right'},
            {field: 'operatingCost', title: '营业成本', width: 120, align: 'right'},
            {field: 'expenseSales', title: '销售费用', width: 120, align: 'right'},
            {field: 'administrativeExpenses', title: '管理费用', width: 120, align: 'right'},
            {field: 'researchDevelopmentExpenses', title: '研发费用', width: 120, align: 'right'},
            {field: 'financialExpenses', title: '财务费用', width: 120, align: 'right'},
            {field: 'businessTaxesSurcharges', title: '营业税金及附加', width: 120, align: 'right'},
            {field: 'operatingProfit', title: '营业利润', width: 120, align: 'right'},
            {field: 'netProfit', title: '净利润', width: 120, align: 'right'},
            {field: 'ttm', title: '归属于母公司所有者的净利润', width: 120, align: 'right'},
            {field: 'remark', title: '备注', width: 200, align: 'left'},
            {field: 'createTime', title: '创建时间', width: 150, align: 'center'},
            {field: 'updateTime', title: '修改时间', width: 150, align: 'center'},
        ]],
        onBeforeLoad: function (param) {
            param.pageNo = param.page;
            param.pageSize = param.rows;
            param.companyId = $("#listp_searchCompanyId").val();
            param.year = $("#listp_searchYear").val();
            return true;
        }
    });

    profitStatementlistSearch = function () {
        $('#profitStatement-list').datagrid('load');
    }


    importProfitStatementData = function(){
        $("#importExcel-p").dialog({
            title: 'Excel上传',
            width: 400,
            height: 200,
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
                text: '上传',
                handler: function () {
                    var compName= $('#upload_searchCompanyId_p').filebox('getValue');
                    if (compName === "") {
                        $.messager.alert('提示', '请选择公司!');
                        return;
                    }
                    //进行基本校验
                    var fileName = $('#file-p').filebox('getValue');
                    if(fileName == ""){
                        $.messager.alert('提示','请选择上传文件！','info');
                        return;
                    }
                    //对文件格式进行校验
                    var d1 = /\.[^\.]+$/.exec(fileName);
                    if(d1 == ".xlsx" || d1 == ".xls"){
                        var formdata = new FormData($("#uploadExcel-p")[0]);
                        $.ajax({
                            url: "/profitStatement/upload",
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
                                            $("#importExcel-p").dialog('close');
                                            $("#profitStatement-list").datagrid("reload");
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
                    $("#importExcel-p").dialog("close");
                }
            }],
            onBeforeClose: function () {
                $("#profitStatementEditForm").form("clear");
            }
        });
    }

    editProfitStatementData = function () {
        var ids = getprofitStatementListSelectionsIds();
        if (ids.length === 0) {
            $.messager.alert('提示', '必须选择一条数据才能编辑!');
            return;
        }
        if (ids.indexOf(',') > 0) {
            $.messager.alert('提示', '只能选择一条数据!');
            return;
        }
        $("#profitStatementEdit").dialog({
            title: '编辑利润表数据',
            width: 800,
            height: 600,
            top: 10,
            left: 150,
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    var params = $("#profitStatementEditForm").serialize();
                    $.post("/profitStatement/update", params, function (data) {
                        if (data.code == 200) {
                            $.messager.alert('提示', '修改成功!', 'info',
                                function () {
                                    $("#profitStatementEdit").dialog('close');
                                    $("#profitStatement-list").datagrid("reload");
                                });
                        } else {
                            $.messager.alert('提示', data.msg, 'warning');
                        }
                    });
                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#profitStatementEdit").dialog("close");
                }
            }],
            onBeforeClose: function () {
                $("#profitStatementEditForm").form("clear");
            }
        });
        var data = $("#profitStatement-list").datagrid("getSelected");
        $("#profitStatementEditForm").form("load", data);
    }

    deleteProfitStatementData = function () {
        var ids = getprofitStatementListSelectionsIds();
        if (ids.length === 0) {
            $.messager.alert('提示', '未选中数据!');
            return;
        }
        $.messager.confirm('确认', '确定删除ID为 ' + ids + ' 的利润表的数据吗？', function (r) {
            if (r) {
                var params = {"id": ids};
                $.post("/profitStatement/delete", params, function (data) {
                    if (data.code == 200) {
                        $.messager.alert('提示', '删除成功!', 'info',
                            function () {
                                $("#profitStatement-list").datagrid("reload");
                            });
                    }
                });
            }
        });
    }

    getprofitStatementListSelectionsIds = function () {
        var itemList = $("#profitStatement-list");
        var sels = itemList.datagrid("getSelections");
        var ids = [];
        for (var i in sels) {
            ids.push(sels[i].id);
        }
        ids = ids.join(",");
        return ids;
    }

</script>

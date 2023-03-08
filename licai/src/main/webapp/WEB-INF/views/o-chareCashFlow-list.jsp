<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div style="width: 100%;height: 40px;">
    公司:&nbsp;&nbsp;<input class="easyui-textbox" id="listo_searchCompanyId">
    年份:&nbsp;&nbsp;<input class="easyui-textbox" id="listo_searchYear">
    <button style="margin-left: 10px" class="easyui-linkbutton" iconCls="icon-search" onclick="oChareCashFlowlistSearch()">
        搜索
    </button>
</div>
<table id="o-chareCashFlow-list" style="width:100%;height:600px"></table>

<div id="oChareCashFlowEdit" class="easyui-dialog" data-options="closed:true">
    <form id="oChareCashFlowEditForm" method="post">
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
                <td>经营活动产生的现金流量净额:</td>
                <td>
                    <input class="easyui-numberbox" name="operatingCashFlow" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>购建固定资产、无形资产和其他长期资产支付的现金:</td>
                <td>
                    <input class="easyui-numberbox" name="purchaseConstructionFixedAssets" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>分配股利、利润或偿付利息支付的现金:</td>
                <td>
                    <input class="easyui-numberbox" name="shareBonus" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>投资活动产生的现金流量净额:</td>
                <td>
                    <input class="easyui-numberbox" name="cashFlowsInvestingActivities" style="width: 150px;"
                           data-options="required:true"/>
                </td>
            </tr>
            <tr>
                <td>筹资活动产生的现金流量净额:</td>
                <td>
                    <input class="easyui-numberbox" name="netCashFlowsFinancingActivities" style="width: 150px;"
                           data-options="required:true"/>
                </td>
                <td>备注:</td>
                <td><input class="easyui-textbox" name="remark" data-options="multiline:true,validType:'length[0,150]'"
                           style="height:60px;width: 200px;"/></td>
                </td>
            </tr>
        </table>
    </form>
</div>

<div id="importExcel-o" class="easyui-dialog" title="导入文件" style="width:450px; padding:20px;" data-options="closed:true">
    <form id="uploadExcel"  method="post" enctype="multipart/form-data">
        <table>
            <tr>
                <td>公司:</td>
                <td><input id="upload_searchCompanyId-o" name="id" class="easyui-textbox" style="width: 250px;"/></td>
            </tr>
            <tr>
                <td>文件:</td>
                <td><input id="file-o" name="file" class="easyui-filebox" style="width: 250px;"
                           data-options="prompt:'请选择文件...'"/></td>
            </tr>
        </table>
    </form>
</div>

<script type="text/javascript" charset="utf-8">

    $("#oChareCashFlowEditForm [name='year']").combogrid({
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

    $("#listo_searchYear").combogrid({
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


    $("#listo_searchCompanyId").combogrid({
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

    $("#upload_searchCompanyId-o").combogrid({
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

    $('#o-chareCashFlow-list').datagrid({
        url: '/chartCashFlow/listpage',
        title: '现金流量表',
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
                    editChareCashFlowData();
                }
            }, '-',{
                text: '删除',
                iconCls: 'icon-cancel',
                handler: function () {
                    deleteChareCashFlowData();
                }
            },'-',{
                text: '导入',
                iconCls: 'icon-save',
                handler: function () {
                    importChartCashFlowData();
                }
            }
        ],
        columns: [[
            {field: 'id', checkbox: true},
            {field: 'code', title: '股票代码', width: 100, align: 'center'},
            {field: 'name', title: '公司名称', width: 100, align: 'center'},
            {field: 'year', title: '年份', width: 40, align: 'center'},
            {field: 'operatingCashFlow', title: '经营活动产生的现金流量净额', width: 120, align: 'right'},
            {field: 'purchaseConstructionFixedAssets', title: '购建固定资产、无形资产和其他长期资产支付的现金', width: 120, align: 'right'},
            {field: 'shareBonus', title: '分配股利、利润或偿付利息支付的现金', width: 120, align: 'right'},
            {field: 'cashFlowsInvestingActivities', title: '投资活动产生的现金流量净额', width: 120, align: 'right'},
            {field: 'netCashFlowsFinancingActivities', title: '筹资活动产生的现金流量净额', width: 120, align: 'right'},
            {field: 'remark', title: '备注', width: 200, align: 'left'},
            {field: 'createTime', title: '创建时间', width: 150, align: 'center'},
            {field: 'updateTime', title: '修改时间', width: 150, align: 'center'},
        ]],
        onBeforeLoad: function (param) {
            param.pageNo = param.page;
            param.pageSize = param.rows;
            param.companyId = $("#listo_searchCompanyId").val();
            param.year = $("#listo_searchYear").val();
            return true;
        }
    });

    oChareCashFlowlistSearch = function () {
        $('#o-chareCashFlow-list').datagrid('load');
    }


    importChartCashFlowData = function(){
        $("#importExcel-o").dialog({
            title: 'Excel上传',
            width: 400,
            height: 200,
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
                text: '上传',
                handler: function () {
                    var compName= $('#upload_searchCompanyId-o').filebox('getValue');
                    if (compName === "") {
                        $.messager.alert('提示', '请选择公司!');
                        return;
                    }
                    //进行基本校验
                    var fileName = $('#file-o').filebox('getValue');
                    if(fileName == ""){
                        $.messager.alert('提示','请选择上传文件！','info');
                        return;
                    }
                    //对文件格式进行校验
                    var d1 = /\.[^\.]+$/.exec(fileName);
                    if(d1 == ".xlsx" || d1 == ".xls"){
                        var formdata = new FormData($("#uploadExcel")[0]);
                        $.ajax({
                            url: "/chartCashFlow/upload",
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
                                            $("#importExcel-o").dialog('close');
                                            $("#o-chareCashFlow-list").datagrid("reload");
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
                    $("#importExcel-o").dialog("close");
                }
            }],
            onBeforeClose: function () {
                $("#oChareCashFlowEditForm").form("clear");
            }
        });
    }

    editChareCashFlowData = function () {
        var ids = getChareCashFlowListSelectionsIds();
        if (ids.length === 0) {
            $.messager.alert('提示', '必须选择一条数据才能编辑!');
            return;
        }
        if (ids.indexOf(',') > 0) {
            $.messager.alert('提示', '只能选择一条数据!');
            return;
        }
        $("#oChareCashFlowEdit").dialog({
            title: '编辑现金流量表数据',
            width: 800,
            height: 400,
            top: 10,
            left: 150,
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    var params = $("#oChareCashFlowEditForm").serialize();
                    $.post("/chartCashFlow/update", params, function (data) {
                        if (data.code == 200) {
                            $.messager.alert('提示', '修改成功!', 'info',
                                function () {
                                    $("#oChareCashFlowEdit").dialog('close');
                                    $("#o-chareCashFlow-list").datagrid("reload");
                                });
                        } else {
                            $.messager.alert('提示', data.msg, 'warning');
                        }
                    });
                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#oChareCashFlowEdit").dialog("close");
                }
            }],
            onBeforeClose: function () {
                $("#oChareCashFlowEditForm").form("clear");
            }
        });
        var data = $("#o-chareCashFlow-list").datagrid("getSelected");
        $("#oChareCashFlowEditForm").form("load", data);
    }

    deleteChareCashFlowData = function () {
        var ids = getChareCashFlowListSelectionsIds();
        if (ids.length === 0) {
            $.messager.alert('提示', '未选中数据!');
            return;
        }
        $.messager.confirm('确认', '确定删除ID为 ' + ids + ' 的现金流量表的数据吗？', function (r) {
            if (r) {
                var params = {"id": ids};
                $.post("/chartCashFlow/delete", params, function (data) {
                    if (data.code == 200) {
                        $.messager.alert('提示', '删除成功!', 'info',
                            function () {
                                $("#o-chareCashFlow-list").datagrid("reload");
                            });
                    }
                });
            }
        });
    }

    getChareCashFlowListSelectionsIds = function () {
        var itemList = $("#o-chareCashFlow-list");
        var sels = itemList.datagrid("getSelections");
        var ids = [];
        for (var i in sels) {
            ids.push(sels[i].id);
        }
        ids = ids.join(",");
        return ids;
    }

</script>

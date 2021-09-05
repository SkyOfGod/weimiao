<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div style="width: 100%;height: 40px">
    基金:&nbsp;&nbsp;<input class="easyui-textbox" id="listl_searchFundId">
    公司:&nbsp;&nbsp;<input class="easyui-textbox" id="listl_searchCompanyId">
    公司(统计):&nbsp;&nbsp;<input class="easyui-textbox" id="listl_searchCompanyCountId">
    <button class="easyui-linkbutton" iconCls="icon-search" onclick="fundCompanySearch()">搜索</button>
</div>
<table id="fund-company-list" style="width:100%;height:600px"></table>

<div id="editFundCompany" class="easyui-dialog" data-options="closed:true">
    <form id="editFundCompanyForm" method="post">
        <table cellpadding="5">
            <input type="hidden" name="id">
            <tr>
                <td>基金:</td>
                <td>
                    <input class="easyui-textbox" name="fundId" style="width: 300px;"
                           data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>公司:</td>
                <td>
                    <input class="easyui-textbox" name="companyId" style="width: 300px;"
                           data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>建仓时间:</td>
                <td>
                    <input class="easyui-textbox" name="joinTime" style="width: 300px;"
                           data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>撤离时间:</td>
                <td>
                    <input class="easyui-textbox" name="leaveTime" style="width: 300px;"
                           data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>占基金比例(格式 年月日/比例，中间用逗号隔开):</td>
                <td>
                    <input class="easyui-textbox" name="percent" style="width: 300px;"
                           data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>股价:</td>
                <td>
                    <input class="easyui-numberbox" name="price" style="width: 300px;"
                           data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>类型:</td>
                <td>
                    <select class="easyui-combobox" name="validIs" style="width: 100px;" data-options="editable:true,required:true">
                        <option value="1">有效</option>
                        <option value="0">无效</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>排序:</td>
                <td>
                    <input class="easyui-textbox" name="sort" value="0" style="width: 300px;"
                           data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>备注:</td>
                <td><input class="easyui-textbox" name="remark" data-options="multiline:true,validType:'length[0,150]'"
                           style="height:60px;width: 300px;"/></td>
            </tr>
        </table>
    </form>
</div>

<script type="text/javascript">

    $("#listl_searchFundId").combogrid({
        panelWidth: 350,
        idField: 'id',
        textField: 'name',
        url: '/fund/combogrid',
        mode: 'remote',
        delay: 500,
        columns: [[
            {field: 'id', title: '主键', width: 40, align: 'center'},
            {field: 'code', title: '基金代码', width: 100, align: 'center'},
            {field: 'name', title: '基金名称', width: 200, align: 'center'},
        ]],
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

    $("#listl_searchCompanyCountId").combogrid({
        panelWidth: 320,
        idField: 'id',
        textField: 'name',
        url: '/fundCompany/combogrid',
        mode: 'remote',
        delay: 500,
        columns: [[
            {field: 'id', title: '主键', width: 40, align: 'center'},
            {field: 'code', title: '股票代码', width: 100, align: 'center'},
            {field: 'name', title: '公司名称', width: 100, align: 'center'},
            {field: 'count', title: '总计', width: 40, align: 'center'},
        ]],
    });

    $("#editFundCompanyForm [name='fundId']").combogrid({
        panelWidth: 350,
        idField: 'id',
        textField: 'name',
        url: '/fund/combogrid',
        mode: 'remote',
        delay: 500,
        columns: [[
            {field: 'id', title: '主键', width: 40, align: 'center'},
            {field: 'code', title: '基金代码', width: 100, align: 'center'},
            {field: 'name', title: '基金名称', width: 200, align: 'center'},
        ]],
    });

    $("#editFundCompanyForm [name='companyId']").combogrid({
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


    $('#fund-company-list').datagrid({
        url: '/fundCompany/listPage',
        title: '基金持股列表',
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
                addFundCompanyData();
            }
        }, '-', {
            text: '编辑',
            iconCls: 'icon-edit',
            handler: function () {
                editFundCompanyData();
            }
        }, '-',
            {
                text: '删除',
                iconCls: 'icon-cancel',
                handler: function () {
                    deleteFundCompanyData();
                }
            }
        ],
        columns: [[
            {field: 'id', checkbox: true},
            {field: 'fundCode', title: '基金代码', width: 100, align: 'center'},
            {field: 'fundName', title: '基金名称', width: 200, align: 'center'},
            {field: 'companyCode', title: '公司代码', width: 100, align: 'center'},
            {field: 'companyName', title: '公司名称', width: 100, align: 'center'},
            {field: 'joinTime', title: '建仓时间', width: 90, align: 'center'},
            {field: 'leaveTime', title: '撤离时间', width: 90, align: 'center'},
            {field: 'percent', title: '占基金比例', width: 300, align: 'left'},
            {field: 'price', title: '股价', width: 40, align: 'center'},
            {field: 'year', title: '财报年限', width: 40, align: 'center'},
            {field: 'ttm', title: 'TTM动态市盈率', width: 100, align: 'center'},
            {field: 'ttmTime', title: '计算TTM时间', width: 150, align: 'center'},
            {
                field: 'validIs', title: '有/无效', width: 40, align: 'center',
                formatter: function (value, row, index) {
                    if (value === 1) {
                        return '有效';
                    }
                    return '无效';
                }
            },
            {field: 'sort', title: '排序', width: 40, align: 'center'},
            {field: 'remark', title: '备注', width: 300, align: 'left'},
            {field: 'createTime', title: '创建时间', width: 150, align: 'center'},
            {field: 'updateTime', title: '更新时间', width: 150, align: 'center'},
        ]],
        onBeforeLoad: function (param) {
            param.pageNo = param.page;
            param.pageSize = param.rows;
            param.companyId = $("#listl_searchCompanyId").val();
            param.fundId = $("#listl_searchFundId").val();
            param.companyCountId = $("#listl_searchCompanyCountId").val();
            return true;
        }
    });

    addFundCompanyData = function () {
        $("#editFundCompany").dialog({
            title: '新增基金持股数据',
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
                    var params = $("#editFundCompanyForm").serialize();
                    $.post("/fundCompany/add", params, function (data) {
                        if (data.code == 200) {
                            $.messager.alert('提示', '新增成功!', 'info',
                                function () {
                                    $("#editFundCompany").dialog('close');
                                    $("#fund-company-list").datagrid("reload");
                                });
                        } else {
                            $.messager.alert('提示', data.msg, 'warning');
                        }
                    });
                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#editFundCompany").dialog("close");
                }
            }],
            onBeforeClose: function () {
                $("#editFundCompanyForm").form("clear");
            }
        });
    }

    editFundCompanyData = function () {
        var ids = getFundCompanyListSelectionsIds();
        if (ids.length === 0) {
            $.messager.alert('提示', '必须选择一条数据才能编辑!');
            return;
        }
        if (ids.indexOf(',') > 0) {
            $.messager.alert('提示', '只能选择一条数据!');
            return;
        }
        $("#editFundCompany").dialog({
            title: '编辑基金持股',
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
                    var params = $("#editFundCompanyForm").serialize();
                    $.post("/fundCompany/update", params, function (data) {
                        if (data.code == 200) {
                            $.messager.alert('提示', '修改成功!', 'info',
                                function () {
                                    $("#editFundCompany").dialog('close');
                                    $("#fund-company-list").datagrid("reload");
                                });
                        } else {
                            $.messager.alert('提示', data.msg, 'warning');
                        }
                    });
                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#editFundCompany").dialog("close");
                }
            }],
            onBeforeClose: function () {
                $("#editFundCompanyForm").form("clear");
            }
        });
        var data = $("#fund-company-list").datagrid("getSelected");
        $("#editFundCompanyForm").form("load", data);
    }

    deleteFundCompanyData = function () {
        var ids = getFundCompanyListSelectionsIds();
        if (ids.length === 0) {
            $.messager.alert('提示', '未选中数据!');
            return;
        }
        $.messager.confirm('确认', '确定删除ID为 ' + ids + ' 的表数据吗？', function (r) {
            if (r) {
                var params = {"id": ids};
                $.post("/fundCompany/delete", params, function (data) {
                    if (data.code == 200) {
                        $.messager.alert('提示', '删除成功!', 'info',
                            function () {
                                $("#fund-company-list").datagrid("reload");
                            });
                    }
                });
            }
        });
    }

    fundCompanySearch = function () {
        $('#fund-company-list').datagrid('load');
    }

    getFundCompanyListSelectionsIds = function () {
        var itemList = $("#fund-company-list");
        var sels = itemList.datagrid("getSelections");
        var ids = [];
        for (var i in sels) {
            ids.push(sels[i].id);
        }
        ids = ids.join(",");
        return ids;
    }

</script>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div style="width: 100%;height: 40px">
    热点名称:&nbsp;&nbsp;<input class="easyui-textbox" id="listj_searchHotTypeId">
    公司:  <input class="easyui-textbox" id="listj_searchCompany">
    <button class="easyui-linkbutton" iconCls="icon-search" onclick="hotCompanyListSearch()">搜索</button>
</div>
<table id="hot-company-list" style="width:100%;height:1120px"></table>

<div id="editHotCompany" class="easyui-dialog" data-options="closed:true">
    <form id="editHotCompanyForm" method="post">
        <table cellpadding="5">
            <input type="hidden" name="id">
            <tr>
                <td>股票代码:</td>
                <td>
                    <input class="easyui-textbox" name="code" style="width: 300px;" data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>公司名称:</td>
                <td><input class="easyui-textbox" name="name" style="width: 300px;" data-options="editable:true,required:true"/></td>
            </tr>
            <tr>
                <td>概念1:</td>
                <td>
                    <input class="easyui-textbox" name="hotType1" style="width: 180px;" data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>概念2:</td>
                <td>
                    <input class="easyui-textbox" name="hotType2" style="width: 180px;" data-options="editable:true"/>
                </td>
            </tr>
            <tr>
                <td>概念3:</td>
                <td>
                    <input class="easyui-textbox" name="hotType3" style="width: 180px;" data-options="editable:true"/>
                </td>
            </tr>
            <tr>
                <td>概念4:</td>
                <td>
                    <input class="easyui-textbox" name="hotType4" style="width: 180px;" data-options="editable:true"/>
                </td>
            </tr>
            <tr>
                <td>概念5:</td>
                <td>
                    <input class="easyui-textbox" name="hotType5" style="width: 180px;" data-options="editable:true"/>
                </td>
            </tr>
            <tr>
                <td>流通股占比(%):</td>
                <td>
                    <input class="easyui-textbox" name="percent" value="0" style="width: 300px;"
                           data-options="editable:true,required:false"/>
                </td>
            </tr>
            <tr>
                <td>最近连扳次数:</td>
                <td>
                    <input class="easyui-textbox" name="continuityTime" value="0" style="width: 300px;"
                           data-options="editable:true,required:true"/>
                </td>
            </tr>
            <tr>
                <td>首板日期:</td>
                <td>
                    <input class="easyui-datebox" name="firstDate" value="0" style="width: 300px;"
                           data-options="editable:true,required:false"/>
                </td>
            </tr>
            <tr>
                <td>备注:</td>
                <td><input class="easyui-textbox" name="remark" data-options="multiline:true,validType:'length[0,500]'"
                           style="height:150px;width: 300px;"/></td>
            </tr>
        </table>
    </form>
</div>

<script type="text/javascript" charset="utf-8">

    $("#listj_searchHotTypeId").combogrid({
        panelWidth: 200,
        idField: 'id',
        textField: 'name',
        url: '/hotType/combogrid',
        mode: 'remote',
        delay: 500,
        columns: [[
            {field: 'id', title: '主键', width: 40, align: 'center'},
            {field: 'name', title: '名称', width: 100, align: 'center'},
            {field: 'sort', title: '排序', width: 40, align: 'center'},
        ]],
    });

    $("#editHotCompanyForm [name='hotType1']").combogrid({
        panelWidth: 200,
        idField: 'id',
        textField: 'name',
        url: '/hotType/combogrid',
        mode: 'remote',
        delay: 500,
        columns: [[
            {field: 'id', title: '主键', width: 40, align: 'center'},
            {field: 'name', title: '名称', width: 100, align: 'center'},
            {field: 'sort', title: '排序', width: 40, align: 'center'},
        ]],
    });

    $("#editHotCompanyForm [name='hotType2']").combogrid({
        panelWidth: 200,
        idField: 'id',
        textField: 'name',
        url: '/hotType/combogrid',
        mode: 'remote',
        delay: 500,
        columns: [[
            {field: 'id', title: '主键', width: 40, align: 'center'},
            {field: 'name', title: '名称', width: 100, align: 'center'},
            {field: 'sort', title: '排序', width: 40, align: 'center'},
        ]],
    });

    $("#editHotCompanyForm [name='hotType3']").combogrid({
        panelWidth: 200,
        idField: 'id',
        textField: 'name',
        url: '/hotType/combogrid',
        mode: 'remote',
        delay: 500,
        columns: [[
            {field: 'id', title: '主键', width: 40, align: 'center'},
            {field: 'name', title: '名称', width: 100, align: 'center'},
            {field: 'sort', title: '排序', width: 40, align: 'center'},
        ]],
    });

    $("#editHotCompanyForm [name='hotType4']").combogrid({
        panelWidth: 200,
        idField: 'id',
        textField: 'name',
        url: '/hotType/combogrid',
        mode: 'remote',
        delay: 500,
        columns: [[
            {field: 'id', title: '主键', width: 40, align: 'center'},
            {field: 'name', title: '名称', width: 100, align: 'center'},
            {field: 'sort', title: '排序', width: 40, align: 'center'},
        ]],
    });

    $("#editHotCompanyForm [name='hotType5']").combogrid({
        panelWidth: 200,
        idField: 'id',
        textField: 'name',
        url: '/hotType/combogrid',
        mode: 'remote',
        delay: 500,
        columns: [[
            {field: 'id', title: '主键', width: 40, align: 'center'},
            {field: 'name', title: '名称', width: 100, align: 'center'},
            {field: 'sort', title: '排序', width: 40, align: 'center'},
        ]],
    });

    $('#hot-company-list').datagrid({
        url: '/hotCompany/listPage',
        title: '热点类型列表',
        pagePosition: 'top',
        singleSelect: true,
        rownumbers: true,
        collapsible: true,
        pagination: true,
        pageSize: 100,
        pageList: [20, 50, 100],
        toolbar: [{
            text: '新增',
            iconCls: 'icon-add',
            handler: function () {
                addHotCompany();
            }
        }, '-', {
            text: '编辑',
            iconCls: 'icon-edit',
            handler: function () {
                editHotCompany();
            }
        }, '-', {
            text: '删除',
            iconCls: 'icon-cancel',
            handler: function () {
                deleteHotCompany();
            }
        }
        ],
        columns: [[
            {field: 'id', checkbox: true},
            {field: 'code', title: '股票代码', width: 64, align: 'center'},
            {field: 'name', title: '公司名称', width: 64, align: 'center'},
            {field: 'circulationMarketValue', title: '流通市值(亿)', width: 80, align: 'center',
                formatter: function (value, row, index) {
                    if (value < 100) {
                        return '<span style="color:red;">' + value + '</span>';
                    }
                    return value;
                }
            },
            {field: 'percent', title: '流通股占比(%)', width: 100, align: 'center'},
            {field: 'hotType1', title: '概念1', width: 80, align: 'center'},
            {field: 'hotType2', title: '概念2', width: 80, align: 'center'},
            {field: 'hotType3', title: '概念3', width: 80, align: 'center'},
            {field: 'hotType4', title: '概念4', width: 80, align: 'center'},
            {field: 'hotType5', title: '概念5', width: 80, align: 'center'},
            {field: 'hotType6', title: '概念6', width: 80, align: 'center'},
            {field: 'hotType7', title: '概念7', width: 80, align: 'center'},
            {field: 'hotType8', title: '概念8', width: 80, align: 'center'},
            {field: 'hotType9', title: '概念9', width: 80, align: 'center'},
            {field: 'hotType10', title: '概念10', width: 80, align: 'center'},
            {field: 'firstTime', title: '首扳数', width: 48, align: 'center'},
            {field: 'secondTime', title: '二扳数', width: 48, align: 'center'},
            {field: 'thirdTime', title: '三扳数', width: 48, align: 'center'},
            {field: 'forthTime', title: '四扳数', width: 48, align: 'center'},
            {field: 'fifthTime', title: '五扳数', width: 48, align: 'center'},
            {field: 'sixthTime', title: '六扳数', width: 48, align: 'center'},
            {field: 'seventhTime', title: '七扳数', width: 48, align: 'center'},
            {field: 'remark', title: '备注', width: 300, align: 'left',
                formatter: function (value, row, index) {
                    if (value == null) {
                        return value;
                    }
                    return '<span title="' + value + '">' + value + '</span>';
                }
            },
            {field: 'continuityTime', title: '最近连扳次数', width: 100, align: 'center'},
            {field: 'firstDate', title: '最近首板日期', width: 100, align: 'center'},
            {field: 'createTime', title: '创建时间', width: 150, align: 'center'},
            {field: 'updateTime', title: '修改时间', width: 150, align: 'center'}
        ]],
        onBeforeLoad: function (param) {
            param.pageNo = param.page;
            param.pageSize = param.rows;
            param.hotTypeId = $('#listj_searchHotTypeId').val();
            param.hotCompany = $('#listj_searchCompany').val();
            return true;
        }
    });

    addHotCompany = function () {
        $("#editHotCompany").dialog({
            title: '新增热点公司',
            width: 500,
            height: 700,
            top: 70,
            left: 150,
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    if (!$("#editHotCompanyForm").form('validate')) {
                        return false;
                    }
                    var params = $("#editHotCompanyForm").serialize();
                    $.post("/hotCompany/add", params, function (data) {
                        if (data.code == 200) {
                            $.messager.alert('提示', '新增成功!', 'info',
                                function () {
                                    $("#editHotCompany").dialog('close');
                                    $("#hot-company-list").datagrid("reload");
                                });
                        } else {
                            $.messager.alert('提示', data.msg, 'warning');
                        }
                    });
                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#editHotCompany").dialog("close");
                }
            }],
            onBeforeClose: function () {
                $("#editHotCompanyForm").form("clear");
            }
        });
    }

    editHotCompany = function () {
        var ids = getHotCompanyListSelectionsIds();
        if (ids.length == 0) {
            $.messager.alert('提示', '必须选择一条数据才能编辑!');
            return;
        }
        if (ids.indexOf(',') > 0) {
            $.messager.alert('提示', '只能选择一条数据!');
            return;
        }
        $("#editHotCompany").dialog({
            title: '编辑热点公司',
            width: 500,
            height: 700,
            top: 70,
            left: 150,
            closed: false,
            cache: false,
            modal: true,
            buttons: [{
                text: '保存',
                handler: function () {
                    if (!$("#editHotCompanyForm").form('validate')) {
                        return false;
                    }
                    var params = $("#editHotCompanyForm").serialize();

                    $.post("/hotCompany/update", params, function (data) {
                        if (data.code == 200) {
                            $.messager.alert('提示', '修改成功!', 'info',
                                function () {
                                    $("#editHotCompany").dialog('close');
                                    $("#hot-company-list").datagrid("reload");
                                });
                        } else {
                            $.messager.alert('提示', data.msg, 'warning');
                        }
                    });
                }
            }, {
                text: '关闭',
                handler: function () {
                    $("#editHotCompany").dialog("close");
                }
            }],
            onBeforeClose: function () {
                $("#editHotCompanyForm").form("clear");
            }
        });
        var data = $("#hot-company-list").datagrid("getSelected");
        $("#editHotCompanyForm").form("load", data);
    }

    deleteHotCompany = function () {
        var ids = getHotCompanyListSelectionsIds();
        if (ids.length == 0) {
            $.messager.alert('提示', '未选中数据!');
            return;
        }
        $.messager.confirm('确认', '确定删除ID为 ' + ids + ' 的热点公司吗？', function (r) {
            if (r) {
                var params = {"id": ids};
                $.post("/hotCompany/delete", params, function (data) {
                    if (data.code == 200) {
                        $.messager.alert('提示', '删除成功!', 'info',
                            function () {
                                $("#hot-company-list").datagrid("reload");
                            });
                    }
                });
            }
        });
    }

    hotCompanyListSearch = function () {
        $('#hot-company-list').datagrid('load');
    }

    getHotCompanyListSelectionsIds = function () {
        var itemList = $("#hot-company-list");
        var sels = itemList.datagrid("getSelections");
        var ids = [];
        for (var i in sels) {
            ids.push(sels[i].id);
        }
        ids = ids.join(",");
        return ids;
    }

</script>
